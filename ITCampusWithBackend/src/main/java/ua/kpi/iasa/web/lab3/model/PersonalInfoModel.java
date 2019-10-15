package ua.kpi.iasa.web.lab3.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "personal_infos")
public class PersonalInfoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.REMOVE }, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private UserModel user;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "city")
    private String city;
    @Column(name = "education")
    private String education;
    @Column(name = "faculty")
    private String faculty;
    @Column(name = "first_edu_year")
    private Integer startEducationYear;
    @Column(name = "last_edu_year")
    private Integer finishEducationYear;

    @ElementCollection
    @CollectionTable(name = "user_skills", joinColumns = @JoinColumn(name = "personal_info_id"), uniqueConstraints = @UniqueConstraint(columnNames = {
            "personal_info_id", "skill_name" }))
    private Set<UserSkillModel> skills;

    @ElementCollection
    @CollectionTable(name = "user_languages", joinColumns = @JoinColumn(name = "personal_info_id"), uniqueConstraints = @UniqueConstraint(columnNames = {
            "personal_info_id", "language" }))
    private Set<UserLanguageModel> languages;

//  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//  @JoinColumn(name="personal_info_id")
//  @MapKey(name = "name")
//  private Map<String, UserSkillModel> skills;
//
//  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//  @JoinColumn(name="personal_info_id")
//  @MapKey(name = "language")
//  private Map<String, UserLanguageModel> languages;
//
//  @ElementCollection
//  @CollectionTable(name = "user_skills",
//      joinColumns = @JoinColumn(name = "personal_info_id"),
//      uniqueConstraints = @UniqueConstraint(columnNames = {"personal_info_id", "skill_name" }))
//  @MapKeyColumn(name = "skill_name")
//  private Map<String, UserSkillModel> skills;
//
//  @ElementCollection
//  @CollectionTable(name = "user_languages",
//      joinColumns = @JoinColumn(name = "personal_info_id"),
//      uniqueConstraints = @UniqueConstraint(columnNames = {"personal_info_id", "language" }))
//  @MapKeyColumn(name = "language")
//  private Map<String, UserLanguageModel> languages;
}
