package model;

import java.sql.SQLException;
import java.util.List;

public interface DataAccessObject<T> {

	List<T> findAll() throws Exception;

	T findById(String id) throws Exception;

	boolean save(T entity) throws SQLException;

	boolean update(T entity) throws Exception;

	boolean delete(T entity) throws Exception;

}
