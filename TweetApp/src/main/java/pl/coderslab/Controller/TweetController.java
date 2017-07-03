package pl.coderslab.Controller;

import java.util.List;

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
import pl.coderslab.model.Tweet;
import pl.coderslab.model.User;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

@Controller
@RequestMapping("/tweets")
public class TweetController {
	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private UserRepository userRepository;

	@ModelAttribute("users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// LIST
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String showTweets(Model model, HttpServletRequest request) {
		model.addAttribute("tweet", tweetRepository.findAllByOrderByCreatedDesc());
		model.addAttribute("newTweet", new Tweet());
		return "tweetList";
	}
	
	// ADD
	@RequestMapping(path = "/list", method = RequestMethod.POST)
	public String processAddTweetFromList(@Valid @ModelAttribute Tweet tweet, BindingResult result,
											Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			return "tweetList";
		}
		tweet.setUser(userRepository.findOne(((Long) request.getSession().getAttribute("userId"))));
		tweetRepository.save(tweet);
		return "redirect:/tweets/list";
	}
	
	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public String showAddTweetForm(Model model) {
		model.addAttribute("tweet", new Tweet());
		model.addAttribute("path", "/tweets");
		return "tweetForm";
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public String processAddTweetForm(@Valid @ModelAttribute Tweet tweet, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "tweetForm";
		}
		tweetRepository.save(tweet);
		return "redirect:/tweets/list";
	}

	// EDIT
	@RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
	public String editTweet( Model model, @PathVariable Long id) {
		model.addAttribute("tweet", tweetRepository.findOne(id));
		model.addAttribute("path", "/tweets");
		return "tweetForm";
	}

	@RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)
	public String processEditTweet(@Valid @ModelAttribute Tweet tweet, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "tweetForm";
		}
		tweetRepository.save(tweet);
		return "redirect:/tweets/list";
	}

	// DELETE
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
	public String removeTweet(@PathVariable Long id, Model model) {
		model.addAttribute("tweet", tweetRepository.findOne(id));
		return "confirmRemoveTweet";
	}

	@RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
	public String processRemoveTweet(@ModelAttribute Tweet tweet) {
		tweetRepository.delete(tweet);
		return "redirect:/tweets/list";
	}
	
	//FIND BY USER ID
	@RequestMapping(path = "/list/{id}", method = RequestMethod.GET)
	public String showTweetsByUserId(@PathVariable Long id, Model model) {
		model.addAttribute("tweet", tweetRepository.findByUserId(id));
		return "tweetList";
	}
}
