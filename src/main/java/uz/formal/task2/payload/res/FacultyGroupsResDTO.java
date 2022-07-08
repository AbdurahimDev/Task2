package uz.formal.task2.payload.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyGroupsResDTO {
    private String name;
    private String facultyName;
    private int year;
    private int numberOfStudents;
}
