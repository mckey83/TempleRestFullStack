package rest.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import rest.model.dao.ContactDao;


@Component("CrudHsql")
public interface CrudHsql extends JpaRepository<ContactDao, Long>, JpaSpecificationExecutor<ContactDao>{

}
