package uz.formal.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.formal.task2.entity.Group;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
    List<Group> findAllByFacultyId(Integer id);
}
