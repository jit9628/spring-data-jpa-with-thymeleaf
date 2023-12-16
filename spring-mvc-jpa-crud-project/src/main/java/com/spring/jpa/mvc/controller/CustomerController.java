package com.spring.jpa.mvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jpa.mvc.entity.Customer;
import com.spring.jpa.mvc.services.CustomerService;
@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@RequestMapping("/")
	public ModelAndView home(Model model) {
		model.addAttribute("customer", new Customer());
		List<Customer> listCustomer = customerService.listAll();
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("listCustomer", listCustomer);
		return mav;
	}
	@RequestMapping("/new")
	public String newCustomerForm(Map<String, Object> model) {
		System.out.println("Hii I AM Jiytendra . ");
		Customer customer = new Customer();
		System.out.println("{one }"+customer.getName());
		System.out.println("{two}"+customer.getEmail());
		System.out.println("{}three "+customer.getEmail());
		model.put("customer", customer);
		return "new_customer";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.save(customer);
		return "redirect:/";
	}
	@RequestMapping("/edit")
	public ModelAndView editCustomerForm(@RequestParam long id) {
		ModelAndView mav = new ModelAndView("edit_customer");
		Customer customer = customerService.get(id);
		mav.addObject("customer", customer);
		return mav;
	}

	@RequestMapping("/delete")
	public String deleteCustomerForm(@RequestParam long id) {
		customerService.delete(id);
		return "redirect:/";
	}

	@RequestMapping("/search")
	public ModelAndView search(@RequestParam String keyword) {
		List<Customer> result = customerService.search(keyword);
		ModelAndView mav = new ModelAndView("search");
		mav.addObject("result", result);
		return mav;
	}

}