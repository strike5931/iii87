package model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.ProductBean;
import model.ProductDAO;
import model.hibernate.HibernateUtil;

public class ProductDAOHibernate implements ProductDAO {
	private SessionFactory sessionFactory;
	public ProductDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

			ProductDAO productDao = new ProductDAOHibernate(HibernateUtil.getSessionFactory());
			List<ProductBean> beans = productDao.select();
			System.out.println("beans="+beans);
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
	@Override
	public List<ProductBean> select() {
		return this.getSession().createQuery(
				"from ProductBean", ProductBean.class).list();
	}
	@Override
	public ProductBean select(int id) {
		return this.getSession().get(ProductBean.class, id);
	}
	@Override
	public ProductBean update(String name, double price, Date make, int expire, int id) {
		ProductBean bean = this.select(id);
		if(bean!=null) {
			bean.setName(name);
			bean.setPrice(price);
			bean.setMake(make);
			bean.setExpire(expire);
		}
		return bean;
	}

	@Override
	public ProductBean insert(ProductBean bean) {
		if(bean!=null) {
			ProductBean temp = this.select(bean.getId());
			if(temp==null) {
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}
	@Override
	public boolean delete(int id) {
		ProductBean bean = this.select(id);
		if(bean!=null) {
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}
}
