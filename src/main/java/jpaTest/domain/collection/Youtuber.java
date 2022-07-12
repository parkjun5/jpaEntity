package jpaTest.domain.collection;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Youtuber {

    @Id @GeneratedValue
    @Column(name = "youtuber_id")
    private Long id;

    private String name;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "favorite_food",
                    joinColumns = @JoinColumn(name = "youtuber_id")
    )
    @Column(name = "food_name")
    private Set<String> favoriteFoods = new HashSet<>();

//    @ElementCollection
//    @CollectionTable(name = "address",
//                    joinColumns = @JoinColumn(name = "youtuber_id")
//    )
//    private List<Address> addressHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "youtuber_id")
    private List<AddressEntity> addressHistory = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public Youtuber setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Youtuber setName(String name) {
        this.name = name;
        return this;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public Youtuber setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public Youtuber setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
        return this;
    }

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public Youtuber setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
        return this;
    }
}
