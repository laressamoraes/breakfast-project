package com.grupowl.breakfastproject.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.grupowl.breakfastproject.entities.Item;
import com.grupowl.breakfastproject.entities.User;
import com.grupowl.breakfastproject.exceptions.ItemException;
import com.grupowl.breakfastproject.exceptions.ResourceNotFoundException;
import com.grupowl.breakfastproject.repositories.ItemRepository;
import com.grupowl.breakfastproject.repositories.UserRepository;

@Service
@Transactional
@Controller
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	public List<User> listAll() {
		return this.userRepository.findAll();
	}

	public User save(User user) {
		return this.userRepository.save(user);
	}

	public User getUser(Long id) {
		return this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User user) {
		return userRepository.save(user);
	}
	
	public User update(Long id, User obj) {
		User entity = userRepository.getOne(id);
		updateData(entity, obj);
		return userRepository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setNome(obj.getNome());
		entity.setCpf(obj.getCpf());
		entity.setItem(obj.getItem());
	}

	public void delete(Long id) {
		this.userRepository.deleteById(id);
	}
	
	@RequestMapping(value = "/home")
	public String callList(Model model) {
		List <User> listUsers = listAll();
		model.addAttribute("listUsers", listUsers);
		return "home";
	}

	@RequestMapping(value = "/")
	public String callIndex(){
		return "index";
	}

	@RequestMapping(value = "/new")
	public ModelAndView callNew() {
		ModelAndView model = new ModelAndView("new");
		User user = new User();
		model.addObject("user", user);
		return model;
	}

	@RequestMapping(value = "/update/{id}")
	public ModelAndView callUpdate(@PathVariable(name = "id") Long id) {
		ModelAndView model  = new ModelAndView("update");
		User user = this.getUser(id);
		model.addObject("user", user);
		return model;
	}

	@RequestMapping(value = "/save")
	public String callSave(@ModelAttribute("user") User user, @ModelAttribute("item") Item item) {
		List<User> listAll = userRepository.findAll();
		for (User u: listAll) {
			if(u.getItem() == user.getItem()) {
				throw new ItemException();
			}	
		}
		itemRepository.save(item);
		userRepository.save(user);
		return "redirect:/home";
	}

	@RequestMapping(value = "/delete/{id}")
	public String callDelete(@PathVariable(name = "id") Long id) {
		this.delete(id);
		return "redirect:/home";
	}
}