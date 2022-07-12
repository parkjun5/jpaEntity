package jpaTest.domain.collection;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address")
public class AddressEntity {

    @Id @GeneratedValue
    @Column(name = "address_entity_id")
    private Long id;

    @Embedded
    private Address address;

    public Long getId() {
        return id;
    }

    public AddressEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public AddressEntity setAddress(Address address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
