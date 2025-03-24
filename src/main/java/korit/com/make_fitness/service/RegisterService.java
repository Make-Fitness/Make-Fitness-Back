package korit.com.make_fitness.service;

import korit.com.make_fitness.dto.request.ReqClassDto;
import korit.com.make_fitness.entity.Class;
import korit.com.make_fitness.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Transactional(rollbackFor = Exception.class)
    public Class insertClass(ReqClassDto reqClassDto) {
        Class classes = reqClassDto.toClass();

        registerRepository.save(classes);
        return classes;
    }
}
