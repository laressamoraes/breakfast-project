package com.grupowl.breakfastproject.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.grupowl.breakfastproject.entities.Item;
import com.grupowl.breakfastproject.entities.User;

public interface ItemRepository extends JpaRepository <Item, Long> {
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO TB_ITEM (NOME) VALUES (?)", nativeQuery = true)
	Item insert(Item item);
	
	@Query(value = "SELECT * FROM TB_ITEM", nativeQuery = true)
	List<Item> listAll();

	@Query(value = "SELECT * FROM TB_ITEM i WHERE i.ID = ?", nativeQuery = true)
	Item getItem(Long id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE TB_ITEM SET FULL_NAME = ?2 WHERE ID = ?1", nativeQuery = true)
	Item update(Long nome, Item obj);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM TB_ITEM WHERE ID = ? ", nativeQuery = true)
	void delete(Long id);
}