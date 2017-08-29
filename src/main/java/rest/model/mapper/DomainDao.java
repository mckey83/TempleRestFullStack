package rest.model.mapper;

import rest.model.dao.ContactDao;
import rest.model.domain.ContactDomain;


public interface DomainDao {

	public ContactDao getDao (ContactDomain domen);
	
	public ContactDomain getDomain (ContactDao dao);
}
