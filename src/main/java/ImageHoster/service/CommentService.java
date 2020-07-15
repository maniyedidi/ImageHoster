package ImageHoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    //The method calls the uploadImage() method in the Repository and passes the image to be persisted in the database
    public void uploadComment(Comment comment) {
        commentRepository.uploadComment(comment);
    }

    //This methos regturn the list comments by given imgae id
    public List<Comment> getAllCommentByImageId(Integer imageId) {
        return commentRepository.getAllComments(imageId);
    }

}