package model;

import java.util.List;

public interface ProductDAO {

	List<ProductBean> select();

	ProductBean select(int id);

	ProductBean update(String name, double price, java.util.Date make, int expire, int id);

	ProductBean insert(ProductBean bean);

	boolean delete(int id);

}