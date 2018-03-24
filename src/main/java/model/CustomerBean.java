package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@Entity
@Table(name="CUSTOMER")
public class CustomerBean {
	@Id
	private String custid;
	private byte[] password;
	private String email;
	private java.util.Date birth;
	public static void main(String[] args) {
		StandardServiceRegistry serviceRegistry =
				new StandardServiceRegistryBuilder().configure().build();
		SessionFactory factory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		try {
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			CustomerBean insert = new CustomerBean();
			insert.setCustid("hahaha");
			insert.setPassword("hahaha".getBytes());
			insert.setEmail("hahaha@gmail.com");
			insert.setBirth(new java.util.Date(0));
			
			session.save(insert);

			transaction.commit();
			session.close();
		} finally {
			factory.close();
		}
	}
	
	@Override
	public String toString() {
		return "CustomerBean [custid="+ custid+ ", email="+ email+ ", birth="+ birth+ "]";
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public byte[] getPassword() {
		return password;
	}
	public void setPassword(byte[] password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public java.util.Date getBirth() {
		return birth;
	}
	public void setBirth(java.util.Date birth) {
		this.birth = birth;
	}
}
