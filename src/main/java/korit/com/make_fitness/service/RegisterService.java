package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ClassDto;
import korit.com.make_fitness.dto.request.ReqClassDto;
import korit.com.make_fitness.entity.Class;
import korit.com.make_fitness.repository.RegisterRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

//    @Transactional(rollbackFor = Exception.class)
//    public List<Class> insertClasses(ReqClassDto reqClassDto) {

//        List<Class> classes = reqClassDto.getDateTimes().stream().map(dateTime ->
//                Class.builder()
//                        .managerId(reqClassDto.getManagerId())
//                        .classSubjectId(reqClassDto.getClassSubjectId())
//                        .classTime(reqClassDto.getClassTime())
//                        .classMaxCustomer(reqClassDto.getClassSubjectId() == 1)
//                        .build());

//        registerRepository.save(classes);
//        return classes;
//    }

    public List<Class> findAll() throws NotFoundException {
        return registerRepository.findAll().orElseThrow(() -> new NotFoundException("예약된 정보가 조회되지 않습니다."));
    }
}
