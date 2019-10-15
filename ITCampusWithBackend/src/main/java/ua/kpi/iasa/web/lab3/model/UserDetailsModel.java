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

import lombok.Data;

@Data
@Entity
@Table(name = "user_details")
public class UserDetailsModel {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    // according to the article, DELETE will also delete both sides of the relation
    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @JoinTable(name = "user_details2authorities", joinColumns = @JoinColumn(name = "user_details_username"), inverseJoinColumns = @JoinColumn(name = "authorities_PK"))
    private Set<AuthorityModel> authorities;
}
