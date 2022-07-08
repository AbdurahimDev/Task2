package uz.formal.task2.entity;
import lombok.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Faculty extends AbsEntity{

    @ManyToOne(cascade = CascadeType.PERSIST)
    private University university;

    public Faculty(Integer id, String name, boolean active, University university) {
        super(id, name, active);
        this.university = university;
    }
}
