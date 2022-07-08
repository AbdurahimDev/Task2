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
public class Student extends AbsEntity{

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Group group;

    public Student(Integer id, String name, boolean active, Group group) {
        super(id, name, active);
        this.group = group;
    }
}
