package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String email;
	
	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="description")
	private String description;
	
	@ManyToMany
	@JoinTable(name="user_has_trail", 
	joinColumns=@JoinColumn(name="user_id", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="trail_id", referencedColumnName="id"))
	private List<Trail> favorites;
	
	@OneToMany(mappedBy="user")
	private List<Report> reports;
	
	
	public User(){}
	
	public User(int id, String email, String password, String firstName, String lastName, String description,
			List<Trail> favorites, List<Report> reports) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.favorites = favorites;
		this.reports = reports;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Trail> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Trail> favorites) {
		this.favorites = favorites;
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public int getId() {
		return id;
	}

	
}
