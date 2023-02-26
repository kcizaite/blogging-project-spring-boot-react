package lt.techin.karolina.blogging.comments;

import lt.techin.karolina.blogging.blogs.BlogController;
import lt.techin.karolina.blogging.blogs.BlogDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static lt.techin.karolina.blogging.blogs.BlogMapper.toBlogDto;
import static lt.techin.karolina.blogging.comments.CommentMapper.toComment;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    Logger logger = Logger.getLogger(CommentController.class.getName());
    private final CommentService commentService;
    private final CommentRepository commentRepository;

    public CommentController(CommentService commentService,
                             CommentRepository commentRepository) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CommentDto> getComments() {
        logger.info("Comment was created");
        return commentService.getAll().stream().map(CommentMapper::toCommentDto).toList();
    }

    @PostMapping(value = "/create-comment/{blogId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BlogDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Long blogId) {
        var addCommenttoBlog = commentService.create(toComment(commentDto), blogId);
        if (addCommenttoBlog == null) {
            logger.log(Level.INFO, "Blog was not found with id: {0} ", blogId);
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
        }
        logger.log(Level.INFO, "Comment was created to blog id: {}", blogId);
        return ResponseEntity.status(HttpStatus.CREATED).body(toBlogDto(addCommenttoBlog));
    }

    @GetMapping(value = "/get-comments/{blogId}")
    public ResponseEntity<List<CommentDto>> getCommentsBlog(@PathVariable Long blogId) {
        var comments = commentRepository.findCommentByBlogId(blogId)
                .stream().map(CommentMapper::toCommentDto).toList();
        logger.log(Level.INFO, "getting comments from blog id {}", blogId);
        return ResponseEntity.ok(comments);
    }
}
