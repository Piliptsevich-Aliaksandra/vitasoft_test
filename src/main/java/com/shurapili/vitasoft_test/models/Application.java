package com.shurapili.vitasoft_test.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
public class Application {

    public Application() {
    }

    public Application(String text) {
        this.text = text;
        this.status = Status.DRAFT;
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Lob
    @NotNull(message = "Text of application shouldn't be empty.")
    @NotEmpty(message = "Text of application shouldn't be empty.")
    @Size(max=10000, message = "Text of application shouldn't contain more than 10000 characters.")
    private String text;

    @CreationTimestamp
    private Date creationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public String getFormattedText() {
        String result = text.replace("", "-");
        return result.substring(1, result.length() - 1);
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getFormattedCreationTime() {
        return new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).format(creationTime);
    }

    public enum Status {
        DRAFT,
        SENT,
        ACCEPTED,
        REJECTED
    }
}
