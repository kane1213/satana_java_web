package com.green.factory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_data")
public class ProductData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "detail")
	private String detail;
	
	@Column(name = "kind")
	private String kind;
	
	@Column(name = "price")
	private int price;
	
	public ProductData() {}
	
	public ProductData(int id, String name, String kind, String detail, int price) {
		super();
		this.id = id;
		this.name = name;
		this.kind = kind;
		this.detail = detail;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ProductDataService [id=" + id + ", name=" + name + ", detail=" + detail + ", kind=" + kind + ", price="
				+ price + "]";
	}
}
