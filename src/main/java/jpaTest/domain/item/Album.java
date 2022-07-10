package jpaTest.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("selab_album")
public class Album extends ItemSuper {
    private String artist;


}
