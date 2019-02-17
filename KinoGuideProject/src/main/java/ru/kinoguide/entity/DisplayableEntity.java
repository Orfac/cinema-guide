package ru.kinoguide.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class DisplayableEntity extends BaseEntity {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entity", orphanRemoval = true)
    private List<Media> mediaList;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private EntityMedia mediaEntity;

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }
}
