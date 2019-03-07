package ru.kinoguide.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class DisplayableEntity extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private EntityMedia mediaEntity;

    public DisplayableEntity() {
    }

    public EntityMedia getMediaEntity() {
        return mediaEntity;
    }

    public void setMediaEntity(EntityMedia mediaEntity) {
        this.mediaEntity = mediaEntity;
    }

    public List<Media> getMediaList() {
        return mediaEntity.getMediaList();
    }

    public void setMediaList(List<Media> mediaList) {
        mediaEntity.setMediaList(mediaList);
    }

    public Media getMediaByType(String type) {
        if (mediaEntity == null) {
            return null;
        } else {
            return mediaEntity.getMediaByType(type);
        }
    }
}
