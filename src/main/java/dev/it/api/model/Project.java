package dev.it.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "projects")

public class Project  {

    @Id
    public String uuid;
    public String name;
    public String description;
    public String tags;
    @Lob
    public Object properties;

    public Project() {
    }

    @Override
    public String toString() {
        return "Project{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tags='" + tags + '\'' +
                ", properties=" + properties +
                '}';
    }
}
