package rest.model.mapper;

import rest.model.domain.ContactDomain;
import rest.model.ui.ContactUi;


public interface UiDomain {

	public ContactUi getUi(ContactDomain d);
	
	public ContactDomain getDomain(ContactUi ui);

}
