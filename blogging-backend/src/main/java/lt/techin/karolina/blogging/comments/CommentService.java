package lt.techin.karolina.blogging.comments;

import lt.techin.karolina.blogging.blogs.Blog;
import lt.techin.karolina.blogging.blogs.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Comment finById(Long id) {
        return commentRepository.findById(id).orElse(new Comment());
    }

    public Blog create(Comment comment, Long blogId) {
        var blog = blogRepository.findById(blogId).orElse(null);
        if (blog == null) {
            return null;
        } else {
            var saveComment = commentRepository.save(comment);
            List<Comment> list = blog.getComment();
            list.add(saveComment);
            blog.setComment(list);
            return blogRepository.save(blog);
        }
    }
}
