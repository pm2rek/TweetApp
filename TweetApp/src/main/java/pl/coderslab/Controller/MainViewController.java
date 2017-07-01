package pl.coderslab.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainViewController {
	// MAIN VIEW
		@RequestMapping(path="/", method= RequestMethod.GET)
		public String showMain() {
			return "mainAppView";
		}
}
