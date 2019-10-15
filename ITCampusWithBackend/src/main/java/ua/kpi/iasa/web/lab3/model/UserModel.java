package ua.kpi.iasa.web.lab3.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = UserModel.WITH_USER_DETAILS, attributeNodes = @NamedAttributeNode(value = "userDetails")),
        @NamedEntityGraph(name = UserModel.WITH_PROJECTS, attributeNodes = @NamedAttributeNode(value = "projects")) })
public class UserModel {

    public static final String WITH_USER_DETAILS = "User[userDetails]";
    public static final String WITH_PROJECTS = "User[projects]";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(targetEntity = SubjectModel.class, mappedBy = "userId", cascade = { CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<SubjectModel> subjects;
    
    @OneToMany(targetEntity = ProjectModel.class, mappedBy = "userId", cascade = { CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<ProjectModel> projects;
    
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "username", nullable = false, unique = true, updatable = false, insertable = false)
    private String username;

    @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE,
            CascadeType.PERSIST }, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username")
    private UserDetailsModel userDetails;
}