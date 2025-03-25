package korit.com.make_fitness.repository;

import korit.com.make_fitness.entity.Class;
import korit.com.make_fitness.mapper.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RegisterRepository {

    @Autowired
    private RegisterMapper registerMapper;

    public Optional<List<Class>> save(List<Class> classes) {
        try {
            registerMapper.insert(classes);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(classes);
    }

    public Optional<List<Class>> findAll() {
        List<Class> foundList = registerMapper.selectAll();
        return foundList.isEmpty() ? Optional.empty() : Optional.of(foundList);
    }
}
