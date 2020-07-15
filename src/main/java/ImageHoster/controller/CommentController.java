package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import ImageHoster.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments" , method = RequestMethod.POST)
    public String addComment(@PathVariable("imageId") String imageId, @PathVariable("imageTitle") String imageTitle, Model model, HttpSession session, @RequestParam("comment") String comment) {
        Image image = imageService.getImage(Integer.parseInt(imageId));
        User user = (User) session.getAttribute("loggeduser");
        Comment commentObj = new Comment(comment, new Date(), user, image);
        commentService.uploadComment(commentObj);
        return "redirect:/images/"+ image.getId()+"/"+imageTitle;
    }
}
