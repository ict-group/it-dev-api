package dev.it.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "attachments")

public class Attachment   {

    @Id
    public String uuid;

    public String name;
    public Long size;
    public String s3name;

    public Date creation_date;
    public String mime_type;
    public String s3_url;

    // the uuid of related entity (for example a BlogPost uuid)
    public String external_uuid;
    // the name of entity (es: blogpost, developer, project, user)
    public String external_type;

    public Attachment() {
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", s3name='" + s3name + '\'' +
                ", creation_date=" + creation_date +
                ", mime_type='" + mime_type + '\'' +
                ", external_type='" + external_type + '\'' +
                ", external_uuid='" + external_uuid + '\'' +
                ", s3_url='" + s3_url + '\'' +
                '}';
    }
}
