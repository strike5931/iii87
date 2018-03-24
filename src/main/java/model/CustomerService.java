package model;

import java.util.Arrays;

import model.dao.CustomerDAOHibernate;
import model.hibernate.HibernateUtil;

public class CustomerService {
	private CustomerDAO customerDao;
	public CustomerService() {
		customerDao = new CustomerDAOHibernate(HibernateUtil.getSessionFactory());
	}
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

			CustomerService customerService = new CustomerService();
			CustomerBean login = customerService.login("Alex", "A");
			System.out.println("login="+login);

			boolean changePassword = 
					customerService.changePassword("Ellen", "AAA", "E");
			System.out.println("changePassword="+changePassword);
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
	public boolean changePassword(String username, String oldPassword, String newPassword) {
		CustomerBean bean = this.login(username, oldPassword);
		if(bean!=null) {
			byte[] pass = newPassword.getBytes();	//使用者輸入
			return customerDao.update(pass,
					bean.getEmail(), bean.getBirth(), username);
		}
		return false;
	}
	
	public CustomerBean login(String username, String password) {
		CustomerBean bean = customerDao.select(username);
		if(bean!=null) {
			if(password!=null && password.length()!=0) {
				byte[] pass = password.getBytes();	//使用者輸入
				byte[] temp = bean.getPassword();	//資料庫抓出
				if(Arrays.equals(pass, temp)) {
					return bean;
				}
			}
		}
		return null;
	}
}
