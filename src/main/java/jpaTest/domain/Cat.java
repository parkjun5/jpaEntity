package jpaTest.domain;

import javax.persistence.*;

@Entity
public class Cat {

    @Id @GeneratedValue
    @Column(name = "cat_id")
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id")
    private Home home;

    public Long getId() {
        return id;
    }

    public Cat setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Cat setName(String name) {
        this.name = name;
        return this;
    }

    public Home getHome() {
        return home;
    }

    public Cat setHome(Home home) {
        this.home = home;
        return this;
    }
}
