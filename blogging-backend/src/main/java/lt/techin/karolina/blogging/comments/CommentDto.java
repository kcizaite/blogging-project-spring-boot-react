package lt.techin.karolina.blogging.comments;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Objects;

public class CommentDto {

    private Long id;

    private String authorName;

    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdDate;

    public CommentDto() {
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
        CommentDto that = (CommentDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(authorName, that.authorName)
                && Objects.equals(comment, that.comment)
                && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorName, comment, createdDate);
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", comment='" + comment + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
