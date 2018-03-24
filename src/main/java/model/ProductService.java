package model;

import java.util.ArrayList;
import java.util.List;

import model.dao.ProductDAOHibernate;
import model.hibernate.HibernateUtil;

public class ProductService {
	private ProductDAO productDao;
	public ProductService() {
		productDao = new ProductDAOHibernate(HibernateUtil.getSessionFactory());
	}
	
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

			ProductService service = new ProductService();
			List<ProductBean> beans = service.select(null);
			System.out.println("beans="+beans);
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
	public List<ProductBean> select(ProductBean bean) {
		List<ProductBean> result = null;
		if(bean!=null && bean.getId()!=0) {
			ProductBean temp = productDao.select(bean.getId());
			if(temp!=null) {
				result = new ArrayList<ProductBean>();
				result.add(temp);
			}
		} else {
			result = productDao.select(); 
		}
		return result;
	}
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		if(bean!=null) {
			result = productDao.insert(bean);
		}
		return result;
	}
	public ProductBean update(ProductBean bean) {
		ProductBean result = null;
		if(bean!=null) {
			result = productDao.update(bean.getName(), bean.getPrice(),
					bean.getMake(), bean.getExpire(), bean.getId());
		}
		return result;
	}
	public boolean delete(ProductBean bean) {
		boolean result = false;
		if(bean!=null) {
			result = productDao.delete(bean.getId());
		}
		return result;
	}
}
