package lt.techin.karolina.blogging.blogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

}
