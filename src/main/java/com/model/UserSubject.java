package com.model;

import com.model.Evaluation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_subject")
@NamedQueries({
        @NamedQuery(name = "UserSubject.findByUserAndSubject", query = "SELECT c FROM UserSubject c where c.subject.subjectId =:subjectId and c.student.userR.userId =:userId"),


})
public class UserSubject implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn
    private RoleStudent student;

    @Id
    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID")
    private Subject subject;

    @Column(name = "FINAL_GRADE")
    private float grade;

    @Column(name = "OBSERVATIONS")
    private String observations;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "EVALUATION_ID")
    private Evaluation evaluation;

    public UserSubject(){}

    public RoleStudent getStudent() { return student; }

    public void setStudent(RoleStudent student) { this.student = student; }

    public Subject getSubject() { return subject; }

    public void setSubject(Subject subject) { this.subject = subject; }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSubject that = (UserSubject) o;
        return Float.compare(that.grade, grade) == 0 &&
                Objects.equals(student, that.student) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(observations, that.observations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, subject, grade, observations);
    }
}