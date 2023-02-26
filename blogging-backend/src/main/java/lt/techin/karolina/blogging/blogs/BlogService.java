package lt.techin.karolina.blogging.blogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    @Autowired

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    public Blog finById(Long id) {
        return blogRepository.findById(id).orElse(new Blog());
    }

    public Blog create(Blog blog) {
        if (blog.getHeadline() == null) return null;
        if (findByHeadline(blog.getHeadline())) {
            return null;
        } else {
            return blogRepository.save(blog);
        }
    }

    public boolean findByHeadline(String headline) {
        return getAll().stream()
                .anyMatch(classroom -> classroom.getHeadline().matches(headline));
    }
}
