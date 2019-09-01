package ua.kpi.iasa.web.lab3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class AuthorityModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK")
	private Integer pk;

	@Enumerated(EnumType.STRING)
	@Column(name = "value", unique = true, nullable = false)
	private AuthorityType authorityRole;

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public AuthorityType getAuthorityRole() {
		return authorityRole;
	}

	public void setAuthorityRole(AuthorityType authorityRole) {
		this.authorityRole = authorityRole;
	}

}
