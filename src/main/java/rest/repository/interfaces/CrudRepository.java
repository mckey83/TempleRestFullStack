package rest.repository.interfaces;

import java.util.List;

import rest.model.dao.ContactDao;

public interface CrudRepository {	
	
	public List<ContactDao> findAll();
	
	public ContactDao getById(Long id);
	
	public ContactDao save(ContactDao contact);

	public void delete(Long id);

}
