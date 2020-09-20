package dev.it.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    public String uuid;
    public String name;
    public long number_of;

    public Company() {
    }
}
