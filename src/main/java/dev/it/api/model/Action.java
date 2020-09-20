package dev.it.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "actions")

public class Action   {

    @Id
    public String uuid;
    public String name;
    public String icon;
    public long operation_to_execute;

    public Action() {

        this.operation_to_execute = 1l;
    }

}
