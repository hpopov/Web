package ua.kpi.iasa.web.lab3.model;

//@Entity
//@Table(name = "users")
public class UserModel  {
	
//	@Id
//	@Column(name = "id")
	private int id;
	
//	@Column(name = "name")
	private String name;
	
//	@Column(name = "surname")
	private String surname;
	
//	@OneToOne(cascade = 
//		{CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE},
//		fetch = FetchType.EAGER, optional = false)
//	@JoinColumn(name = "username")
	private UserDetailsModel userDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public UserDetailsModel getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetailsModel userDetails) {
		this.userDetails = userDetails;
	}


}