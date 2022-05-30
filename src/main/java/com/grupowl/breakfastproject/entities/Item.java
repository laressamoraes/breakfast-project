package com.grupowl.breakfastproject.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_item")
public class Item {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private String nome;
		
		@JsonIgnore
		@OneToMany(mappedBy = "item")
		private List<User> users = new ArrayList<>();
		
		public Item() {
		
		}

		public Item(Long id, String nome) {
			super();
			this.id = id;
			this.nome = nome;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}
		
		public List<User> getUsers() {
			return users;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(id, nome);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Item other = (Item) obj;
			return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
		}
}