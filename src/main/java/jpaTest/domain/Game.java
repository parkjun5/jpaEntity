package jpaTest.domain;

import javax.persistence.*;

@Entity
public class Game {

    @Id @GeneratedValue
    private Long id;

    private String title;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "steam_id")
    private Steam steam;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Steam getSteam() {
        return steam;
    }

    public void setSteam(Steam steam) {
        this.steam = steam;
    }
}
