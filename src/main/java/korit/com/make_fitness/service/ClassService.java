package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.response.RespClassListDto;
import korit.com.make_fitness.entity.Class;
import korit.com.make_fitness.entity.User;
import korit.com.make_fitness.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

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
    public List<RespClassListDto> getAllClassWithUserAndSubject() {
        return classRepository.findAllUserAndSubject().stream()
                .map(this::convertToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RespClassListDto> getBySubjectName(String subjectName) {
        return classRepository.findBySubjectName(subjectName).stream()
                .map(this::convertToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RespClassListDto> getByManagerNickname(String nickname) {
        return classRepository.findByManagerNickname(nickname).stream()
                .map(this::convertToDto)
                .toList();
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
