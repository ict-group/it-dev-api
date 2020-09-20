package dev.it.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "blogposts")
public class BlogPost   {

    @Id
    public String uuid;
    // VIDEO - BLOG - NEWS
    public String type;
    public String title;
    public String content;
    public String content_preview;
    public String tags;
    public String author;
    public String video_url;
    public String developer_uuid;

    public LocalDateTime insert_date;
    public LocalDateTime update_date;

    public BlogPost() {
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "uuid='" + uuid + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", content_preview='" + content_preview + '\'' +
                ", tags='" + tags + '\'' +
                ", author='" + author + '\'' +
                ", developer_uuid='" + developer_uuid + '\'' +
                ", video_url='" + video_url + '\'' +
                ", insert_date=" + insert_date +
                ", update_date=" + update_date +
                '}';
    }
}
