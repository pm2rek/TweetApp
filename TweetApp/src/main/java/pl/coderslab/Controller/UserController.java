package pl.coderslab.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.coderslab.model.User;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TweetRepository tweetRepository;
	// LIST
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String showUsers(Model model) {
		model.addAttribute("user", userRepository.findAll());
		return "userList";
	}

	//USER
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public String showUser(Model model, @PathVariable Long id) {
		model.addAttribute("tweet", tweetRepository.findByUserIdOrderByCreatedDesc(id));
		return "tweetListNoForm";
	}
	// ADD
	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("path", "/users");
		return "userForm";
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public String processAddUserForm(@Valid @ModelAttribute User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "userForm";
		}
		userRepository.save(user);
		return "redirect:/users/list";
	}
	
	// EDIT
	@RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
	public String editUser(Model model, @PathVariable Long id) {
		model.addAttribute("user", userRepository.findOne(id));
		model.addAttribute("path", "/users");
		return "userForm";
	}

	@RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)
	public String processEditUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "userForm";
		}
		userRepository.save(user);
		return "redirect:/users/list";
	}

	// DELETE
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
	public String removeUser(@PathVariable Long id, Model model) {
		model.addAttribute("user", userRepository.findOne(id));
		return "confirmRemove";
	}

	@RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
	public String processRemoveUser(@ModelAttribute User user) {
		userRepository.delete(user);
		return "redirect:/users/list";
	}
}
