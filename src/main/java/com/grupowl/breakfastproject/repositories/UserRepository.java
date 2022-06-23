package com.grupowl.breakfastproject.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.grupowl.breakfastproject.entities.User;

public interface UserRepository extends JpaRepository <User, Long> {
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO TB_USER (NOME, CPF, ITEM) VALUES (?)", nativeQuery = true)
	User insert(User user);

	@Query(value = "SELECT * FROM TB_USER ORDER BY ID", nativeQuery = true)
	List<User> findAll();

	@Query(value = "SELECT * FROM TB_USER u WHERE u.ID = ?", nativeQuery = true)
	User getUser(Long id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE TB_USER SET NOME = ?2, CPF = ?3 WHERE ID = ?1", nativeQuery = true)
	User update(Long id, User obj);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM TB_USER WHERE ID = ?", nativeQuery = true)
	void delete(Long id);
}