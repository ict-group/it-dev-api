package dev.it.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "performed_action_blog_post")

public class PerformedActionBlogPost {

    @Id
    public String uuid;
    public LocalDateTime last_update;
    @Lob
    public Object actions;

    public PerformedActionBlogPost() {
    }

}
