package rest.model.ui;

import java.util.ArrayList;
import java.util.List;

public class ContactUi {	

	private Long id;

	private String name;

	private List<String> phones ;
	
	public ContactUi() {	
		this.id = 0l;
		this.name = "";
		this.phones = new ArrayList<>();
	}

	public ContactUi(String name, List<String> phones) {
		this.name = name;
		this.phones = phones;
	}
	
	public ContactUi(Long id, String name, List<String> phones) {
	    this.id = id;
        this.name = name;
        this.phones = phones;
    }
	
	@Override
	public String toString() {
		return "ContactUi [id=" + id + ", name=" + name + ", phones=" + phones + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
}
