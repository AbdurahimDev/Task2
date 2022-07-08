package uz.formal.task2.entity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class University extends AbsEntity{

    @Column(nullable = false)
    private String address;

    @Column(name = "open_year",nullable = false)
    private Integer openYear;

    public University(Integer id, String name, boolean active, String address, Integer openYear) {
        super(id, name, active);
        this.address = address;
        this.openYear = openYear;
    }
}
