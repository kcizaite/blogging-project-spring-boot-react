package lt.techin.karolina.blogging.comments;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 100)
    private String authorName;

    @NotBlank
    @Size(min = 1, max = 1000)
    private String comment;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdDate;

    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now();
    }

    public Comment() {
    }

    public Comment(Long id, String authorName, String comment) {
        this.id = id;
        this.authorName = authorName;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return Objects.equals(id, comment1.id)
                && Objects.equals(authorName, comment1.authorName)
                && Objects.equals(comment, comment1.comment)
                && Objects.equals(createdDate, comment1.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorName, comment, createdDate);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", comment='" + comment + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
