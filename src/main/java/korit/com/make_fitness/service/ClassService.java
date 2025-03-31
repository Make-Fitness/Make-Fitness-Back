package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.response.RespClassListDto;
import korit.com.make_fitness.dto.response.RespClassReservationDto;
import korit.com.make_fitness.dto.response.RespClassReservationRow;
import korit.com.make_fitness.entity.Class;
import korit.com.make_fitness.entity.User;
import korit.com.make_fitness.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
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

    // ✅ 매니저가 등록한 수업 + 예약자 명단
    @Transactional(readOnly = true)
    public List<RespClassReservationDto> getClassWithReservations(int managerId) {
        List<RespClassReservationRow> rows = classRepository.findClassWithReservations(managerId);

        Map<Long, RespClassReservationDto> resultMap = new LinkedHashMap<>();

        for (RespClassReservationRow row : rows) {
            Long classId = row.getClassId();

            // 수업별로 처음 등장한 경우 초기화
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

            // 예약자 닉네임 추가
            if (row.getReservedMember() != null) {
                resultMap.get(classId).getReservedMembers().add(row.getReservedMember());
            }
        }

        return new ArrayList<>(resultMap.values());
    }



    // DTO 변환
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
