package lt.techin.karolina.blogging.blogs;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static lt.techin.karolina.blogging.blogs.BlogMapper.toBlog;
import static lt.techin.karolina.blogging.blogs.BlogMapper.toBlogDto;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

    Logger logger = Logger.getLogger(BlogController.class.getName());

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<BlogDto> getBlogs() {
        return blogService.getAll().stream().map(BlogMapper::toBlogDto).toList();
    }

    @GetMapping(value = "/blog/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BlogDto> getBlog(@PathVariable Long id) {
        logger.log(Level.INFO, "Something went wrong: {0} ", id);
        return ResponseEntity.ok(toBlogDto(blogService.finById(id)));
    }

    @PostMapping(value = "/create-blog", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> createBlog(@RequestBody BlogDto blogDto) {
        var createBlog = blogService.create(toBlog(blogDto));
        if (createBlog == null) {
            logger.info("Blog with this headline already published");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Blog with this headline already published"));
        }
        logger.info("Blog was created, successfully");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", toBlogDto(createBlog).toString()));
    }
}
