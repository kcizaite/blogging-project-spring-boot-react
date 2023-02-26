package lt.techin.karolina.blogging.blogs;

public class BlogMapper {
    public static BlogDto toBlogDto(Blog blog) {
        var blogDto = new BlogDto();
        blogDto.setId(blog.getId());
        blogDto.setHeadline(blog.getHeadline());
        blogDto.setText(blog.getText());
        blogDto.setCreatedDate(blog.getCreatedDate());
        blogDto.setComment(blog.getComment());
        return blogDto;
    }

    public static Blog toBlog(BlogDto blogDto) {
        var blog = new Blog();
        blog.setId(blogDto.getId());
        blog.setHeadline(blogDto.getHeadline());
        blog.setText(blogDto.getText());
        blog.setCreatedDate(blogDto.getCreatedDate());
        blog.setComment(blogDto.getComment());
        return blog;
    }
}

