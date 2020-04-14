package com.model;

import com.model.enums.Department;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "subjects")
@NamedQueries({
//     @NamedQuery(name = "Room.findById", query = "SELECT r,te.email FROM Room r  "
//             + "LEFT JOIN Tenant te ON te.room = r.id"
//             + "WHERE r.id = :id")
        @NamedQuery(name = "Subject.findById", query = "SELECT r FROM Subject r WHERE r.subjectId = :id"),
        @NamedQuery(name = "Subject.findByIdWithAll", query ="SELECT s FROM Subject s LEFT JOIN FETCH s.timeline k LEFT JOIN FETCH s.teachers t JOIN FETCH s.subjectGroup d WHERE s.subjectId = :id"),
        @NamedQuery(name = "Subject.findByGroupId", query ="SELECT s FROM Subject s LEFT JOIN FETCH s.teachers t WHERE s.subjectGroup.groupId = :id"),


})
public class Subject implements Serializable {

    private static final long serialVersionUID = 2681531852204068105L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "SUBJECT_ID")
    private String subjectId;

    @Column(name = "NAME")
    //@NotNull
    private String name;

    @OneToMany(mappedBy="subjectTimeLine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TimeLine> timeline;

    @OneToMany(mappedBy="subjectEvent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> events;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "GROUP_ID")
    private ClassGroup subjectGroup;

    @Column(name = "DEPARTMENT")
    private Department department;

    @Transient
    private String groupId="";

    @ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(name = "teacher_subjects")
    private Set<RoleTeacher> teachers;

    @OneToMany(mappedBy="subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;

    public Subject() { }

    public Subject(String name) {
        this.name = name;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TimeLine> getTimeline() {
        return timeline;
    }

    public void setTimeline(List<TimeLine> timeline) {
        this.timeline = timeline;
    }

    public void deleteTimeline(TimeLine timeLine) { this.timeline.remove(timeLine); }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public ClassGroup getSubjectGroup() {
        return subjectGroup;
    }

    public void setSubjectGroup(ClassGroup subjectGroup) {
        this.subjectGroup = subjectGroup;
    }

    public void addTeacher(RoleTeacher roleTeacher){ this.teachers.add(roleTeacher); }

    public void deleteTeacher(RoleTeacher roleTeacher){ this.teachers.remove(roleTeacher); }

    public Department getDepartment() { return department; }

    public void setDepartment(Department department) { this.department = department; }

    public String getGroupId() {
        String group = groupId;
        if("".equals(group)) {
            group = getSubjectGroup().getGroupId();
        }
        return group;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(subjectId, subject.subjectId) &&
                Objects.equals(name, subject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, name);
    }

    public Set<RoleTeacher> getTeachers() { return teachers; }

    public void setTeachers(Set<RoleTeacher> teachers) { this.teachers = teachers; }
}
