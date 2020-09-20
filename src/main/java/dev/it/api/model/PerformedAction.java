package dev.it.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "performed_actions")

public class PerformedAction   {

     @Id
    public String uuid;
    public String action;
    public String blogpost_uuid;
    public String user_uuid;
    public LocalDate creation_date;
    public LocalDate working_date;

    public PerformedAction() {
    }
}
