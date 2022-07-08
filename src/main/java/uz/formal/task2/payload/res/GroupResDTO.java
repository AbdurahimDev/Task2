package uz.formal.task2.payload.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.formal.task2.entity.Mark;
import uz.formal.task2.entity.Student;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupResDTO {
    List<Student> students;
    private List<Mark> marks;
}
