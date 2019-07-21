package ua.kpi.iasa.web.lab3;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin("https://localhost:4200")
@EnableAutoConfiguration
public class AdminController {

	@GetMapping("/admin")
	public String getAdminPage(Model model) {
//		ModelAndView modelAndView = new ModelAndView("adminPage");
//		model.addAttribute("message", "Hreha!!");
//		return modelAndView;
		return "adminPage";
	}
}
