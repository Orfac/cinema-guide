package ru.kinoguide.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Media extends BaseEntity {
    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne
    @NotNull
    private DisplayableEntity entity;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
