package uz.formal.task2.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "subjects")
public class Subject extends AbsEntity{

    public Subject(Integer id, String name, boolean active) {
        super(id, name, active);
    }
}
