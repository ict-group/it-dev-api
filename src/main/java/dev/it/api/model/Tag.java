package dev.it.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tags")

public class Tag   {

    @Id
    public String uuid;
    public String name;
    public long numberOf;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
        this.numberOf = 1L;
    }
}
