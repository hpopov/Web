package ua.kpi.iasa.web.lab3.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetailsModel{

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "username")
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	//according to the article, DELETE will also delete both sides of the relation
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
			fetch = FetchType.EAGER)
	@JoinTable(name = "user_details2authorities",
			joinColumns = @JoinColumn(name="user_details_username"),
			inverseJoinColumns = @JoinColumn(name="authorities_PK")
			)
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
