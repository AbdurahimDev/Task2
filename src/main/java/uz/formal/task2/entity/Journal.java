package uz.formal.task2.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Journal  extends AbsEntity{

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private Set<Subject> subjects;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Group group;

    public Journal(Integer id, String name, boolean active, Set<Subject> subjects, Group group) {
        super(id, name, active);
        this.subjects = subjects;
        this.group = group;
    }
    public Journal(Integer id, String name, boolean active, Group group) {
        super(id, name, active);
        this.group = group;
    }
}
