package lt.techin.karolina.blogging.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM COMMENT WHERE BLOG_ID = ?1 ", nativeQuery = true)
    List<Comment> findCommentByBlogId(Long blogId);
}
