package uz.formal.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.formal.task2.entity.Subject;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer>{

    @Query(value = "SELECT subjects.id,subjects.name,subjects.active FROM subjects JOIN public.journal_subjects ON subjects.id=journal_subjects.subjects_id where journal_subjects.journal_id=:journalId", nativeQuery = true)
    List<Subject> findAllByJournalId(Integer journalId);

    Optional<Subject> findByName(String name);

}
