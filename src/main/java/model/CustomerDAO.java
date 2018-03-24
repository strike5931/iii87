package model;

public interface CustomerDAO {

	boolean update(byte[] password, String email, java.util.Date birth, String custid);

	CustomerBean select(String custid);

}