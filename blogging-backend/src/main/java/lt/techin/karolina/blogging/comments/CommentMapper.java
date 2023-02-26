package lt.techin.karolina.blogging.comments;

public class CommentMapper {

    public static CommentDto toCommentDto(Comment comment) {
        var commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setAuthorName(comment.getAuthorName());
        commentDto.setComment(comment.getComment());
        commentDto.setCreatedDate(comment.getCreatedDate());
        return commentDto;
    }

    public static Comment toComment(CommentDto commentDto) {
        var comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setAuthorName(commentDto.getAuthorName());
        comment.setComment(commentDto.getComment());
        comment.setCreatedDate(commentDto.getCreatedDate());
        return comment;
    }
}
