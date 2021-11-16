package com.soft.ressystem.domain;

import javax.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "games")
public class Game {

	@Id
	String id;

	private String gametype, customertype, time;
	public String court;
	private double price;

	public Game() {
	}

	public Game(String id, String gametype, String time, String customertype, double price, String court) {

		this.id = id;

		this.gametype = gametype;

		this.time = time;

		this.customertype = customertype;

		this.price = price;

		this.court = court;

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

	public double getPrice() {

		return price;
	}

	public void setPrice(double price) {

		this.price = price;
	}

	public String getCourt() {

		return court;
	}

	public void setCourt(String court) {

		this.court = court;
	}

	@Override
	public String toString() {
		return "Id=" + id + ", Gametype=" + gametype + "Court=" + court + ", Time=" + time + ", Customertype="
				+ customertype + ", Price=" + price;
	}
}
