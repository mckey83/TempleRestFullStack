package rest.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "CONTACT")
public class ContactDao implements Serializable {	

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTACT_ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<PhoneDao> phones ;

	public ContactDao(){
		this.id = 0l;
		this.name = "";
		this.phones = new ArrayList<>();
	}
	
	public ContactDao(Long id, String name, List<PhoneDao> phones) {		
		this.id = id;
		this.name = name;
		this.phones = phones;
	}
	
	@Override
	public String toString() {
		return "ContactDao [id=" + id + ", name=" + name + ", phones=" + phones + "]";
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
	public List<PhoneDao> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneDao> phones) {
		this.phones = phones;
	}
}
