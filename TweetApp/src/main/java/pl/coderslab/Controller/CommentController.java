package pl.coderslab.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.coderslab.model.Comment;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

@Controller
public class CommentController {
	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	//COMMENTS
	@RequestMapping(path = "/tweets/{id}", method = RequestMethod.GET)
	public String showComments(@PathVariable Long id, Model model) {
		model.addAttribute("tweet", tweetRepository.findOne(id));
		model.addAttribute("comments", commentRepository.findAll());
		model.addAttribute("comment", new Comment());
		model.addAttribute("path", "/tweets");
		return "tweet";
	}
	//ADD COMMENT
	@RequestMapping(path = "/tweets/{id}", method = RequestMethod.POST)
	public String addComment(@Valid @ModelAttribute Comment comment, BindingResult result, Model model,
							@PathVariable Long id, HttpServletRequest request) {
		if (result.hasErrors()) {
			return "tweet";
		}
		comment.setTweet(tweetRepository.findOne(id));
		comment.setUser(userRepository.findOne(((Long) request.getSession().getAttribute("userId"))));
		commentRepository.save(comment);
		return "redirect:/tweets/"+id;
	}
}
