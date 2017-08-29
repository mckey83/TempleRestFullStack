package rest.model.mapper;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import rest.model.domain.ContactDomain;
import rest.model.ui.ContactUi;

@Component("uiDomain")
public class UiDomainImpl implements UiDomain{

	@Override
	public ContactUi getUi(ContactDomain d) {		
		ContactUi ui = new  ContactUi();
		if(d.getId() != null && !d.getName().isEmpty() && !d.getPhones().isEmpty()){
			ui.setId(d.getId());
			ui.setName(d.getName());
			String phonesAsString = d.getPhones();		    
			List<String> phonesAsStringCollection = Arrays.asList(phonesAsString.split(","));
			ui.setPhones(phonesAsStringCollection);
		}
		return ui;
	}

	@Override
	public ContactDomain getDomain(ContactUi ui) {
		System.out.println("getDomain()" + ui);
		String name = ui.getName();
		String phoneAll = "";
		for(String phone: ui.getPhones()){
			phoneAll += phone+",";
		}
		phoneAll = phoneAll.substring(0, phoneAll.length() - 1);
		ContactDomain result = new ContactDomain(ui.getId(), name, phoneAll);
		System.out.println("getDomain() result" + result);
		return result;
				
	}
}
