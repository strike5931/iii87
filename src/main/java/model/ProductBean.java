package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT")
public class ProductBean {
	@Id
	private int id;
	private String name;
	private double price;
	private java.util.Date make;
	private int expire;
	public static void main(String[] args) {
		ProductBean bean = new ProductBean();
		bean.setId(100);
		bean.setName("hahaha");
		bean.setPrice(123.456);
		bean.setMake(new java.util.Date());
		bean.setExpire(789);
		
		System.out.println("bean="+bean);
	}
	
	@Override
	public String toString() {

		return "ProductBean [id="+ id+ ", name="+ name+ ", price="+ price+ ", make="+ make+ ", expire="+ expire+ "]";
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public java.util.Date getMake() {
		return make;
	}
	public void setMake(java.util.Date make) {
		this.make = make;
	}
	public int getExpire() {
		return expire;
	}
	public void setExpire(int expire) {
		this.expire = expire;
	}
}
