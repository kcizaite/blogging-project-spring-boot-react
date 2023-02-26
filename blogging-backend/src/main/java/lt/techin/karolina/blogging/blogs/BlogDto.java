package lt.techin.karolina.blogging.blogs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lt.techin.karolina.blogging.comments.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class BlogDto {
    private Long id;
    private String headline;
    private String text;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdDate;

    private List<Comment> comment;

    public BlogDto() {
    }

    public BlogDto(Long id, String headline, String text, LocalDateTime createdDate, List<Comment> comment) {
        this.id = id;
        this.headline = headline;
        this.text = text;
        this.createdDate = createdDate;
        this.comment = comment;
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
        BlogDto blogDto = (BlogDto) o;
        return Objects.equals(id, blogDto.id) && Objects.equals(headline, blogDto.headline)
                && Objects.equals(text, blogDto.text)
                && Objects.equals(createdDate, blogDto.createdDate)
                && Objects.equals(comment, blogDto.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, headline, text, createdDate, comment);
    }

    @Override
    public String toString() {
        return "BlogDto{" +
                "id=" + id +
                ", headline='" + headline + '\'' +
                ", text='" + text + '\'' +
                ", createdDate=" + createdDate +
                ", comment=" + comment +
                '}';
    }
}
