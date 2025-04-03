package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.response.RespClassListDto;
import korit.com.make_fitness.dto.response.RespClassReservationDto;
import korit.com.make_fitness.dto.response.RespClassReservationRow;
import korit.com.make_fitness.dto.response.RespClassSubjectDto;
import korit.com.make_fitness.entity.Class;
import korit.com.make_fitness.entity.User;
import korit.com.make_fitness.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Transactional(rollbackFor = Exception.class)
    public Class createClass(Class classEntity, User user) throws AccessDeniedException {
        if (!user.getRoleName().equals("ROLE_MASTER") && !user.getRoleName().equals("ROLE_MANAGER")) {
            throw new AccessDeniedException("수업 등록 권한이 없습니다.");
        }

        // ✅ 트레이너가 등록 가능한 수업 subjectId 목록 확인
        List<Integer> allowedSubjectIds = classRepository.getAllowedSubjectIdsByTrainer(user.getUserId());
        if (!allowedSubjectIds.contains(classEntity.getClassSubjectId())) {
            throw new AccessDeniedException("해당 수업 주제를 등록할 권한이 없습니다.");
        }

        // ✅ 중복 수업 시간 체크
        boolean isDuplicate = classRepository.existsByTrainerAndTime(user.getUserId(), classEntity.getClassTime());
        if (isDuplicate) {
            throw new IllegalArgumentException("이미 해당 시간에 등록된 수업이 있습니다.");
        }

        classEntity.setUserId(user.getUserId());
        classEntity.setCreatedAt(LocalDateTime.now());
        classEntity.setUpdatedAt(LocalDateTime.now());

        classRepository.insertClass(classEntity);
        return classEntity;
    }

    @Transactional(readOnly = true)
    public List<RespClassListDto> getFilteredClassList(String subject, String manager) {
        boolean isSubjectEmpty = subject == null || subject.trim().isEmpty();
        boolean isManagerEmpty = manager == null || manager.trim().isEmpty();

        List<Class> classes = (isSubjectEmpty && isManagerEmpty)
                ? classRepository.findAllUserAndSubject()
                : classRepository.findFiltered(subject, manager);

        return classes.stream()
                .map(this::convertToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public RespClassListDto getClassById(int classId) {
        Class classEntity = classRepository.findById(classId);
        if (classEntity == null) {
            throw new IllegalArgumentException("해당 수업이 존재하지 않습니다.");
        }
        return convertToDto(classEntity);
    }

    public void increaseCustomerReserve(int classId) {
        classRepository.increaseCustomerReserve(classId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteClass(int classId, User user) throws AccessDeniedException {

        if (!user.getRoleName().equals("ROLE_MASTER") && !user.getRoleName().equals("ROLE_MANAGER")) {
            throw new AccessDeniedException("수업 삭제 권한이 없습니다.");
        }
        classRepository.deleteClassById(classId);
    }

    @Transactional(readOnly = true)
    public List<RespClassReservationDto> getClassWithReservations(int managerId) {
        List<RespClassReservationRow> rows = classRepository.findClassWithReservations(managerId);

        Map<Long, RespClassReservationDto> resultMap = new LinkedHashMap<>();

        for (RespClassReservationRow row : rows) {
            Long classId = row.getClassId();

            if (!resultMap.containsKey(classId)) {
                RespClassReservationDto dto = RespClassReservationDto.builder()
                        .classId(row.getClassId())
                        .classTime(row.getClassTime())
                        .subject(row.getSubject())
                        .maxCustomer(row.getMaxCustomer())
                        .currentCustomer(row.getCurrentCustomer())
                        .reservedMembers(new ArrayList<>())
                        .build();

                resultMap.put(classId, dto);
            }

            if (row.getReservedMember() != null) {
                resultMap.get(classId).getReservedMembers().add(row.getReservedMember());
            }
        }

        return new ArrayList<>(resultMap.values());
    }

    @Transactional(readOnly = true)
    public RespClassSubjectDto getTrainerClassSubject(User user) throws AccessDeniedException {
        if (!user.getRoleName().equals("ROLE_MANAGER") && !user.getRoleName().equals("ROLE_MASTER")) {
            throw new AccessDeniedException("접근 권한이 없습니다.");
        }

        List<Integer> subjectIds = classRepository.getAllowedSubjectIdsByTrainer(user.getUserId());
        if (subjectIds.isEmpty()) {
            throw new IllegalArgumentException("등록 가능한 수업 주제가 없습니다.");
        }

        int subjectId = subjectIds.get(0);
        String subjectName = switch (subjectId) {
            case 1 -> "PT";
            case 2 -> "필라테스";
            default -> "기타";
        };

        return RespClassSubjectDto.builder()
                .classSubjectId(subjectId)
                .classSubjectName(subjectName)
                .build();
    }

    // ✅ 특정 날짜에 등록된 시간(hour)만 반환
    @Transactional(readOnly = true)
    public List<Integer> getRegisteredTimesByDate(User user, String date) throws AccessDeniedException {
        if (!user.getRoleName().equals("ROLE_MANAGER") && !user.getRoleName().equals("ROLE_MASTER")) {
            throw new AccessDeniedException("접근 권한이 없습니다.");
        }

        return classRepository.findRegisteredTimesByTrainerAndDate(user.getUserId(), date);
    }

    private RespClassListDto convertToDto(Class c) {
        return RespClassListDto.builder()
                .classId(c.getClassId())
                .userId(c.getUserId())
                .classSubjectName(c.getClassSubject().getClassSubjectName())
                .classTime(c.getClassTime().toString())
                .classMaxCustomer(c.getClassMaxCustomer())
                .classCustomerReserve(c.getClassCustomerReserve())
                .remainingSeats(c.getClassMaxCustomer() - c.getClassCustomerReserve())
                .nickname(c.getUser().getNickname())
                .ph(c.getUser().getPh())
                .gender(c.getUser().getGender())
                .build();
    }
}
