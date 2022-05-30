package com.grupowl.breakfastproject.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grupowl.breakfastproject.entities.pk.OptionPk;

@Entity
@Table(name = "tb_option")
public class Option {

	@EmbeddedId
	private OptionPk id = new OptionPk();

	public Option() {

	}

	public Option(User user, Item item) {
		super();
		id.setUser(user);
		id.setItem(item);
	}

	@JsonIgnore
	public User getUser() {
		return id.getUser();
	}

	public void setUser(User user) {
		id.setUser(user);
	}

	public Item getItem() {
		return id.getItem();
	}

	public void setItem(Item item) {
		id.setItem(item);
	}
}