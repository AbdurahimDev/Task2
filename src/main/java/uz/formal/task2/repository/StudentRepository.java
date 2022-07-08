package uz.formal.task2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.formal.task2.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Optional<Student> findByNameAndGroupId(String name,Integer id);

    @Query(value = "SELECT  count(student.id) from student where group_id=:id",nativeQuery = true)
    int countNumberOfStudentsByGroupId(Integer id);

    List<Student> findAllByGroup_Id(Integer id);

    List<Student> findStudentByNameContains(String name);


}
