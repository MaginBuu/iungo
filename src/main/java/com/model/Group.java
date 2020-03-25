package com.model;

import org.hibernate.annotations.GenericGenerator;
import com.model.enums.Stage;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "groups")
@NamedQueries({
//     @NamedQuery(name = "Room.findById", query = "SELECT r,te.email FROM Room r  "
//             + "LEFT JOIN Tenant te ON te.room = r.id"
//             + "WHERE r.id = :id")

})
public class Group implements Serializable {

    private static final long serialVersionUID = 2681531852204068105L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "GROUP_ID")
    private String groupId;

    @Column(name = "NAME")
    //@NotNull
    private String name;

    @Column(name = "STAGE")
    //@NotNull
    private Stage stage;

    @Column(name = "LEVEL")
    //@NotNull
    private int level;

    @Column(name = "GROUP_VALUE")
    //@NotNull
    private String group;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "COURSE_ID")
    private Course course;

    @OneToMany(mappedBy="subjectGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subject> timeline;

    public Group() { }

    public Group(String name, Stage stage, int level, String group) {
        this.name = name;
        this.stage = stage;
        this.level = level;
        this.group = group;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Course getCourse() { return course; }

    public void setCourse(Course course) { this.course = course; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group1 = (Group) o;
        return Objects.equals(groupId, group1.groupId) &&
                Objects.equals(name, group1.name) &&
                stage == group1.stage &&
                Objects.equals(level, group1.level) &&
                Objects.equals(group, group1.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, name, stage, level, group);
    }
}
