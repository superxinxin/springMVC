package controller;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class HelloController {
	@RequestMapping("/hello")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("hello");
		mav.addObject("message", "Hello Spring MVC ~~~");
		return mav;
	}
	@RequestMapping("/jump")
	public ModelAndView jump()
	{
		ModelAndView mav = new ModelAndView("redirect:/hello");
		return mav;
	}
	@RequestMapping("/check")
	public ModelAndView check(HttpSession session)
	{
		Integer i = (Integer)session.getAttribute("counter");
		if(i == null)
		{
			i = 0;
		}
		i++;
		session.setAttribute("counter", i);
		ModelAndView mav = new ModelAndView("check");
		return mav;
	}
}