package uz.formal.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.formal.task2.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University,Integer> {
    boolean existsByName(String name);
}
