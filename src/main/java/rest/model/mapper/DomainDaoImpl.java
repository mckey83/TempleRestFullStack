package rest.model.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import rest.model.dao.ContactDao;
import rest.model.dao.PhoneDao;
import rest.model.domain.ContactDomain;

@Component("domainDao")
public class DomainDaoImpl implements DomainDao {

	@Override
	public ContactDao getDao(ContactDomain domen) {
		String name = domen.getName();;
		String phoneAsLineAll = domen.getPhones();
		phoneAsLineAll = phoneAsLineAll.replaceAll("\\+", "");
		String[] phoneAsStringAll = phoneAsLineAll.split(",");
		List<PhoneDao> phoneDaoAll = new ArrayList<>();
		for(String str: phoneAsStringAll){
			phoneDaoAll.add(new PhoneDao(Long.parseLong(str)));
		}		
		return new ContactDao(domen.getId(), name, phoneDaoAll);
	}

	@Override
	public ContactDomain getDomain(ContactDao dao) {
		String phoneAll = "";
		for (PhoneDao currentPhone: dao.getPhones()){
			phoneAll+= "+"+currentPhone.getNumber()+",";
		}
		if(!phoneAll.isEmpty()){
			phoneAll = phoneAll.substring(0, phoneAll.length() - 1);
		}
		return new ContactDomain(dao.getId(), dao.getName(), phoneAll);
	}
}
