package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pojo.Product;

@Controller
public class ProductController
{
	@RequestMapping("/addProduct")
	public ModelAndView add(Product product)
	{
		ModelAndView mav = new ModelAndView("showProduct");
		return mav;
	}
}
