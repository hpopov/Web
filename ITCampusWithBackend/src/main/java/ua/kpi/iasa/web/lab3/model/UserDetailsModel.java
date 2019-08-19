package ua.kpi.iasa.web.lab3.model;

import java.util.Set;

//@Entity
//@Table(name = "user_details")
public class UserDetailsModel{

//	@Id
//	@Column(name = "username")
	private String username;

//	@Column(name = "password")
	private String password;

//	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
//			fetch = FetchType.EAGER)
//	@JoinTable(name = "user_details2authorities",
//			joinColumns = @JoinColumn(name="user_details_id"),
//			inverseJoinColumns = @JoinColumn(name="authorities_PK")
//			)
	private Set<AuthorityModel> authorities;

	public Set<AuthorityModel> getAuthorities() {
		return authorities;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Set<AuthorityModel> authorities) {
		this.authorities = authorities;
	}

}
