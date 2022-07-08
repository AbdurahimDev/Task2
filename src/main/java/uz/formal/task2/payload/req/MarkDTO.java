package uz.formal.task2.payload.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkDTO {
    private int value;
    private Integer studentId;
    private Integer journalId;
    private Integer subjectId;
}
