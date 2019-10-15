package ua.kpi.iasa.web.lab3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "authorities")
public class AuthorityModel implements EnumModel<AuthorityType> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK")
    private Integer pk;

    @Enumerated(EnumType.STRING)
    @Column(name = "value", unique = true, nullable = false)
    private AuthorityType value;
}
