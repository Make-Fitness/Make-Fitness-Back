package korit.com.make_fitness.repository;

import korit.com.make_fitness.dto.request.ReqManagerDto;
import korit.com.make_fitness.mapper.MasterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MasterRepository {
    @Autowired
    private MasterMapper masterMapper;

    public void deleteManager(String roleName, int userId) {
        masterMapper.deleteManager(userId, roleName);
    }

    public void updateManager(int userId) {
        masterMapper.updateRoleName(userId);
    }

    public List<ReqManagerDto> getManager(LocalDate classTime) {
        return masterMapper.findManager(classTime);
    }
}
