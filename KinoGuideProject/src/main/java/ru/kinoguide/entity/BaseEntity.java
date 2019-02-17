package ru.kinoguide.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "pooled")
    @GenericGenerator(name = "pooled", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "value_column_name", value = "sequence_next_hi_value"),
            @org.hibernate.annotations.Parameter(name = "prefer_entity_table_as_segment_value", value = "true"),
            @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "100")})
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }
}
