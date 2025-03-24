package korit.com.make_fitness.repository;

import korit.com.make_fitness.entity.Class;
import korit.com.make_fitness.mapper.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RegisterRepository {

    @Autowired
    private RegisterMapper registerMapper;

    public Optional<Class> save(Class classes) {
        try {
            registerMapper.save(classes);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(classes);
    }
}
