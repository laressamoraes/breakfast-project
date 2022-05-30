package com.grupowl.breakfastproject.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.grupowl.breakfastproject.entities.Item;
import com.grupowl.breakfastproject.exceptions.DatabaseException;
import com.grupowl.breakfastproject.exceptions.ResourceNotFoundException;
import com.grupowl.breakfastproject.repositories.ItemRepository;

@Service
@Transactional
@Controller
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public List<Item> listAll(){
		return this.itemRepository.findAll();
	}

	public Item save(Item item) {
		return this.itemRepository.save(item);
	}

	public Item getItem(Long id) {
		return this.itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Item update(Long nome, Item obj) {
		Item entity = itemRepository.getOne(nome);
		updateData(entity, obj);
		return itemRepository.save(entity);
	}

	private void updateData(Item entity, Item obj) {
		entity.setNome(obj.getNome());
	}

	public void delete(Long id) {
		try {
			itemRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}