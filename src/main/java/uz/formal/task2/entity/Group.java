package uz.formal.task2.entity;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "groups")
public class Group extends AbsEntity{

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Faculty faculty;

    private int year;

    public Group(Integer id, String name, boolean active, Faculty faculty, int year) {
        super(id, name, active);
        this.faculty = faculty;
        this.year = year;
    }
}
