package lt.techin.karolina.blogging;

import lt.techin.karolina.blogging.blogs.Blog;
import lt.techin.karolina.blogging.blogs.BlogRepository;
import lt.techin.karolina.blogging.blogs.BlogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class BlogServiceTest {

    @Mock
    private BlogRepository blogRepository;

    private BlogService blogService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        blogService = new BlogService(blogRepository);
    }

    @Test
    public void testGetAll() {
        List<Blog> blogs = new ArrayList<>();
        blogs.add(new Blog(1L, "Blog 1", "Content 1"));
        blogs.add(new Blog(2L, "Blog 2", "Content 2"));
        when(blogRepository.findAll()).thenReturn(blogs);
        List<Blog> result = blogService.getAll();
        verify(blogRepository, times(1)).findAll();
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Blog 1", result.get(0).getHeadline());
        Assertions.assertEquals("Blog 2", result.get(1).getHeadline());
    }

    @Test
    public void testFindById() {
        Blog blog = new Blog(1L, "Blog 1", "Content 1");
        when(blogRepository.findById(1L)).thenReturn(Optional.of(blog));

        Blog result = blogService.finById(1L);

        verify(blogRepository, times(1)).findById(1L);
        Assertions.assertEquals(blog, result);
    }


    @Test
    public void testCreateWithDuplicateHeadline() {
        Blog blog1 = new Blog(1L, "Blog 1", "Content 1");
        Blog blog2 = new Blog(2L, "Blog 1", "Content 2");
        when(blogRepository.findAll()).thenReturn(List.of(blog1, blog2));

        Blog result = blogService.create(blog2);

        verify(blogRepository, times(0)).save(any(Blog.class));
        Assertions.assertNull(result);
    }

    @Test
    public void testCreateWithUniqueHeadline() {
        Blog blog1 = new Blog(1L, "Blog 1", "Content 1");
        when(blogRepository.findAll()).thenReturn(List.of(blog1));
        Blog blog2 = new Blog(2L, "Blog 2", "Content 2");
        when(blogRepository.save(any(Blog.class))).thenReturn(blog2);

        Blog result = blogService.create(blog2);

        verify(blogRepository, times(1)).save(blog2);
        Assertions.assertEquals(blog2, result);
    }
}




