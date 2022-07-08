package uz.formal.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.formal.task2.entity.Journal;
import uz.formal.task2.entity.Subject;

import java.util.List;
import java.util.Optional;

@Repository
public interface JournalRepository extends JpaRepository<Journal,Integer> {

    Optional<Journal> findByGroupId(Integer id);

}
