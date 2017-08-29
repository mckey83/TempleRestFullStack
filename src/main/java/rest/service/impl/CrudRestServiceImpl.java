package rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import rest.model.dao.ContactDao;
import rest.model.domain.ContactDomain;
import rest.model.mapper.DomainDao;
import rest.repository.interfaces.CrudRepository;
import rest.service.CrudRestService;

@Service("crudRestService")
public class CrudRestServiceImpl implements CrudRestService{

	@Autowired
	@Qualifier("crudRepository")
	private CrudRepository crudRepository; 
	
	@Autowired
	private DomainDao domainDao;
	
	@Override
	public List<ContactDomain> findAll() {		
		List<ContactDao> daoAll = crudRepository.findAll();
		List<ContactDomain> result = new ArrayList<>();
		for(ContactDao dao : daoAll){
			result.add(domainDao.getDomain(dao));
		}
		return result;
	}

	@Override
	public ContactDomain getById(Long id) {
		return domainDao.getDomain(crudRepository.getById(id));
	}

	@Override
	public void delete(Long id) {
		crudRepository.delete(id);		
	}

    @Override
    public ContactDomain save(ContactDomain contactDomain) {
        return domainDao.getDomain(crudRepository.save(domainDao.getDao(contactDomain))); 
    }	
}
