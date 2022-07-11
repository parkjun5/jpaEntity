package jpaTest.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    @Column(name = "parent_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "parent",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }

    public Long getId() {
        return id;
    }

    public Parent setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Parent setName(String name) {
        this.name = name;
        return this;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public Parent setChildList(List<Child> childList) {
        this.childList = childList;
        return this;
    }
}
