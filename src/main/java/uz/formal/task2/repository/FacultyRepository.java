package uz.formal.task2.repository;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.formal.task2.entity.Faculty;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
    @Query(value = "SELECT * from faculty where university_id=:id", nativeQuery = true)
    List<Faculty> facultiesByUniversityId(Integer id);
}
