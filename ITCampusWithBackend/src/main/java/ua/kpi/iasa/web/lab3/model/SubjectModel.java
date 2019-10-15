package ua.kpi.iasa.web.lab3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "subjects", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "name" }))
public class SubjectModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int subjectId;

//	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH },
//			fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id", nullable = false)
//	private UserModel user;
    @Column(name = "user_id", updatable = false, nullable = false)
    private int userId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "teacher", nullable = false)
    private String teacher;
    @Enumerated(EnumType.STRING)
    @Column(name = "test_type", nullable = false)
    private TestType testType;
    @Column(name = "academic_hours", nullable = false)
    private int academicHours;
    @Column(name = "mark", nullable = false)
    private int mark;
}
