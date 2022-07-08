package uz.formal.task2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.formal.task2.entity.Mark;

@Repository
public interface MarkRepository extends JpaRepository<Mark,Integer> {

}
