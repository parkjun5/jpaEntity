package jpaTest.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Home {

    @Id @GeneratedValue
    @Column(name = "home_id")
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "home")
//    private List<Cat> cats = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Home setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Home setName(String name) {
        this.name = name;
        return this;
    }
}
