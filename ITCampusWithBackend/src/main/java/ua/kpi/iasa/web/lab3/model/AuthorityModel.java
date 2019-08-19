package ua.kpi.iasa.web.lab3.model;

//@Entity
//@Table(name = "authorities")
public class AuthorityModel {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "PK")
	private byte pk;
	
//	@Enumerated(EnumType.STRING)
//	@Column(name = "value", unique = true)
	private AuthorityType authorityRole;

	public byte getPk() {
		return pk;
	}

	public void setPk(byte pk) {
		this.pk = pk;
	}

	public AuthorityType getAuthorityRole() {
		return authorityRole;
	}

	public void setAuthorityRole(AuthorityType authorityRole) {
		this.authorityRole = authorityRole;
	}

}
