package lt.techin.karolina.blogging;

import lt.techin.karolina.blogging.blogs.Blog;
import lt.techin.karolina.blogging.blogs.BlogRepository;
import lt.techin.karolina.blogging.comments.Comment;
import lt.techin.karolina.blogging.comments.CommentRepository;
import lt.techin.karolina.blogging.comments.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private BlogRepository blogRepository;

    private CommentService commentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        commentService = new CommentService(commentRepository, blogRepository);
    }

    @Test
    public void testGetAll() {
        // Arrange
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(1L, "Comment 1", "Content 1"));
        comments.add(new Comment(2L, "Comment 2", "Content 2"));
        Mockito.when(commentRepository.findAll()).thenReturn(comments);

        // Act
        List<Comment> result = commentService.getAll();

        // Assert
        Mockito.verify(commentRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(comments, result);
    }

    @Test
    public void testFinById() {
        // Arrange
        Comment comment = new Comment(1L, "Comment 1", "Content 1");
        Mockito.when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        // Act
        Comment result = commentService.finById(1L);

        // Assert
        Mockito.verify(commentRepository, Mockito.times(1)).findById(1L);
        Assertions.assertEquals(comment, result);
    }

    @Test
    public void testCreateWithNonExistingBlog() {
        // Arrange
        Comment comment = new Comment(1L, "Comment 1", "Content 1");
        Mockito.when(blogRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Blog result = commentService.create(comment, 1L);

        // Assert
        Mockito.verify(blogRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(commentRepository, Mockito.never()).save(comment);
        Mockito.verify(blogRepository, Mockito.never()).save(Mockito.any(Blog.class));
        Assertions.assertNull(result);
    }
}
