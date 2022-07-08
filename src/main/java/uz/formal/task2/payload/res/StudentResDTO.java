package uz.formal.task2.payload.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResDTO {
    private String name;
    private String groupName;
    private String facultyName;
}
