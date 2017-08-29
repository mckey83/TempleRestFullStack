package rest.model.domain;

public class ContactDomain {

	private Long id;
	private String name ;
	private String phones;

	public ContactDomain() {
		this.id = 0l;
		this.name = "";
		this.phones = "";
	}
	
	public ContactDomain(String name, String phones) {
        this.id = 0l;
        this.name = name;
        this.phones = phones;
    }
	
	public ContactDomain(Long id, String name, String phones) {
		this.id = id;
		this.name = name;
		this.phones = phones;
	}
		
	@Override
	public String toString() {
		return "ContactDomain [id=" + id + ", name=" + name + ", phones=" + phones + "]";
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
	public String getPhones() {
		return phones;
	}
	public void setPhones(String phones) {
		this.phones = phones;
	}

}
