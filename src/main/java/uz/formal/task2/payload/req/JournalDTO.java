package uz.formal.task2.payload.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.formal.task2.entity.Subject;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalDTO {
    private String name;
    private Set<Subject> subjects;
    private Integer groupId;
}
