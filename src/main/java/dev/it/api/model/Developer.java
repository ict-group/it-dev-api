package dev.it.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "developers")

public class Developer {

    @Id
    public String uuid;

    public String username;
    public String name;
    public String surname;
    public String tags;
    public String biography;
    public String biography_preview;
    public String lastCompany;
    public String photo_url;
    public Date birthdate;
    public String companies;
    @Lob
    public Object properties;

    public LocalDateTime insert_date;
    public LocalDateTime update_date;

    public Developer() {
    }

    @Override
    public String toString() {
        return "Developer{" +
                "uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", tags='" + tags + '\'' +
                ", biography='" + biography + '\'' +
                ", biography_preview='" + biography_preview + '\'' +
                ", lastCompany='" + lastCompany + '\'' +
                ", photo_url='" + photo_url + '\'' +
                ", birthdate=" + birthdate +
                ", companies='" + companies + '\'' +
                ", properties=" + properties +
                ", insert_date=" + insert_date +
                ", update_date=" + update_date +
                '}';
    }
}
