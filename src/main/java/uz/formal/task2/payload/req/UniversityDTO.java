package uz.formal.task2.payload.req;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityDTO {

    private String name;
    private String address;
    private Integer openYear;
}
