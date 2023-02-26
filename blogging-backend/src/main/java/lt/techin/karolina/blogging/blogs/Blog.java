package lt.techin.karolina.blogging.blogs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lt.techin.karolina.blogging.comments.Comment;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 100)
    private String headline;

    @Size(min = 0, max = 1000)
    private String text;

    @OneToMany
    @JoinColumn(name = "blogId")
    private List<Comment> comment;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdDate;

    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now();
    }

    public Blog() {
    }

    public Blog(Long id, String headline, String text) {
        this.id = id;
        this.headline = headline;
        this.text = text;
    }

    public Blog(Long id, String headline, String text, List<Comment> comment, LocalDateTime createdDate) {
        this.id = id;
        this.headline = headline;
        this.text = text;
        this.comment = comment;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return Objects.equals(id, blog.id)
                && Objects.equals(headline, blog.headline)
                && Objects.equals(text, blog.text)
                && Objects.equals(comment, blog.comment)
                && Objects.equals(createdDate, blog.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, headline, text, comment, createdDate);
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", headline='" + headline + '\'' +
                ", text='" + text + '\'' +
                ", comment=" + comment +
                ", createdDate=" + createdDate +
                '}';
    }
}

