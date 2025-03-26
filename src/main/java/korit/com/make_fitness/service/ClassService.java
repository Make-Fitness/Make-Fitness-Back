package korit.com.make_fitness.service;

import korit.com.make_fitness.entity.Class;
import korit.com.make_fitness.entity.User;
import korit.com.make_fitness.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

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
}
