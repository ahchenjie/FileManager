package com.springapp.model;

/**
 * Created by yuriy on 04.10.15.
 */
import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;

@Entity
@Table(name="files")
public class File {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Blob getContent() {
        return content;
    }

    public void setContent(Blob content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;


    @Column(name="content")
    @Lob
    private Blob content;

    @Column(name="content_type")
    private String contentType;

    @Column(name="created")
    private Date created;


}