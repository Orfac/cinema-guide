package ru.kinoguide.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class EntityMedia extends BaseEntity {
    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> mediaList;

    @OneToOne(mappedBy = "mediaEntity")
    private DisplayableEntity displayableEntity;


    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public DisplayableEntity getDisplayableEntity() {
        return displayableEntity;
    }

    public void setDisplayableEntity(DisplayableEntity displayableEntity) {
        this.displayableEntity = displayableEntity;
    }
}
