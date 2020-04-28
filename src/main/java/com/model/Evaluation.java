package com.model;

import com.model.enums.Term;
import com.model.enums.WeekDay;
import com.model.utilities.Hour;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "evaluations")
@NamedQueries({
        @NamedQuery(name = "Evaluation.findAll", query = "SELECT e FROM Evaluation e"),

})
public class Evaluation implements Serializable {

    private static final long serialVersionUID = 2681531852204068105L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "EVALUATION_ID")
    private String evaluationId;

    @Column(name = "TERM")
    //@NotNull
    private Term term;

    @Column(name = "VISIBILITY_DATE")
    //@NotNull
    private Date visibilityDate;

    @OneToMany(mappedBy="evaluation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserSubject> userSubjects;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "COURSE_ID")
    private Course course;

    public Evaluation() {
    }

    public Evaluation(String id) {
        this.evaluationId = id;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public List<UserSubject> getUserSubjects() {
        return userSubjects;
    }

    public void setUserSubjects(List<UserSubject> userSubjects) {
        this.userSubjects = userSubjects;
    }

    public String getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(String evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Date getVisibilityDate() {
        return visibilityDate;
    }

    public void setVisibilityDate(Date visibilityDate) {
        this.visibilityDate = visibilityDate;
    }

    public Term getTerm() {
        return term;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
