package rest.service;

import java.util.List;

import rest.model.domain.ContactDomain;

public interface CrudRestService {

	public List<ContactDomain> findAll();	
	
	public ContactDomain getById(Long id);	

	public ContactDomain save (ContactDomain contact);	
	
	public void delete (Long id);
}
