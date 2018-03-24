package model.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.CustomerBean;
import model.CustomerDAO;
import model.hibernate.HibernateUtil;

public class CustomerDAOHibernate implements CustomerDAO {
	private SessionFactory sessionFactory;
	public CustomerDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

			CustomerDAO customerDao = new CustomerDAOHibernate(HibernateUtil.getSessionFactory());
			CustomerBean bean = customerDao.select("Alex");
			System.out.println("bean="+bean);
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
	@Override
	public boolean update(byte[] password, String email, Date birth, String custid) {
		CustomerBean bean = this.select(custid);
		if(bean!=null) {
			bean.setPassword(password);
			bean.setEmail(email);
			bean.setBirth(birth);
			return true;
		}
		return false;
	}

	@Override
	public CustomerBean select(String custid) {
		return this.getSession().get(CustomerBean.class, custid);
	}
}
