package util;

import static java.util.Arrays.asList;

import java.util.List;

import rest.model.domain.ContactDomain;
import rest.model.ui.ContactUi;

public final class TestUtils {

    public static List<ContactDomain> getContactDomainAll(){
        return asList(getDomainFirstContact(), getDomainSecondContact()); 
    }

    public static ContactDomain getDomainFirstContact() {
        return new ContactDomain(1l, "Alex", "+380501234561,+380937654321");
    }

    public static ContactDomain getDomainSecondContact() {
        return new ContactDomain(2l, "Roman", "+380501234562,+380937654322");
    }

    public static List<ContactUi> getContactUiAll(){          
        return asList(getUiFirstContact(), getUiSecondContact()); 
    }

    public static ContactUi getUiFirstContact() {
        return new ContactUi(1l, "Alex", asList(new String("+380501234561"),new String("+380937654321")));
    }

    public static ContactUi getUiSecondContact() {
        return new ContactUi(2l, "Roman", asList(new String("+380501234562"),new String("+380937654322")));
    } 
       
}
