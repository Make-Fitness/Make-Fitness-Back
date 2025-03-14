package korit.com.make_fitness.repository;

import korit.com.make_fitness.entity.User;
import korit.com.make_fitness.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    public User save(User user) {
        return userMapper.insert(user);
    }
}
