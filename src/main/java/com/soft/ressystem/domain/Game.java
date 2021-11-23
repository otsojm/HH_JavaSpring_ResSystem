package com.soft.ressystem.domain;

import javax.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "games")
public class Game {

	@Id
	public String id;

	private String gametype, customertype, time, username;

	private Double pricecategory;

	public Game() {
	}

	public Game(String gametype, String time, String customertype, Double pricecategory,
			String username) {

		this.gametype = gametype;

		this.time = time;

		this.customertype = customertype;

		this.pricecategory = pricecategory;

		this.username = username;

	}

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getGametype() {

		return gametype;
	}

	public void setGametype(String gametype) {

		this.gametype = gametype;
	}

	public String getTime() {

		return time;
	}

	public void setTime(String time) {

		this.time = time;
	}

	public String getCustomertype() {

		return customertype;
	}

	public void setCustomertype(String customertype) {

		this.customertype = customertype;
	}

	public Double getPricecategory() {

		return pricecategory;
	}

	public void setPricecategory(Double pricecategory) {

		this.pricecategory = pricecategory;
	}
	
	public String getUsername() {

		return username;
	}

	public void setUsername(String username) {

		this.username = username;
	}

	@Override
	public String toString() {
		return " Id=" + id + ", Gametype=" + gametype + ", Time=" + time + ", Customertype="
				+ customertype + ", Pricecategory=" + pricecategory + ", Username=" + username;
	}
}
