package rest.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rest.model.dao.ContactDao;
import rest.repository.interfaces.CrudHsql;
import rest.repository.interfaces.CrudRepository;

@Repository("crudRepository")
public class CrudRepositoryHsqldbImpl implements CrudRepository {

    @Autowired
    private CrudHsql crudHsql;	

    @Override
    public List<ContactDao> findAll() {
        return crudHsql.findAll();				
    }

    @Override
    public ContactDao save(ContactDao contact) {		
        ContactDao result = crudHsql.save(contact);		
        return result;
    }

    @Override
    public ContactDao getById(Long id) {		
        if(isExist(id)){		
            return crudHsql.findOne(id);
        }
        else{
            return new ContactDao();
        }
    }

    private boolean isExist(Long id) {
        ContactDao dao = crudHsql.findOne(id);
        return dao != null;
    }

    @Override
    public void delete(Long id) {		
        if(isExist(id)){
            crudHsql.delete(id);
        }		
    }

}
