package com.model;


import com.model.enums.TaskType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "task")
@NamedQueries({
        @NamedQuery(name = "Task.findTaskById", query = "SELECT r FROM Task r WHERE r.taskId = :id"),
})
public class Task implements Serializable, Comparable<Task> {

    private static final long serialVersionUID = 2681531852204068105L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "TASK_ID")
    private String taskId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DEADLINE")
    private Date deadline;

    @Column(name = "VALUE")
    private float value;

    @Column(name = "TASK_TYPE")
    private TaskType taskType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CHAPTER_ID")
    private Chapter chapter;

    @OneToMany(mappedBy = "task", targetEntity = UserTask.class)
    private List<UserTask> userTasks = new LinkedList<>();

    @Column(name = "DATE")
    private Date creationDate;

    @Column(name = "REPORTS")
    private int reports;

    public Task() { }

    public Task(String name) {
        this.title = name;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getName() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public Chapter getChapter() { return chapter; }

    public void setChapter(Chapter chapter) { this.chapter = chapter; }

    public List<UserTask> getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(List<UserTask> userTasks) {
        this.userTasks = userTasks;
    }

    public Date getCreationDate() { return creationDate; }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public int getReports() {
        return reports;
    }

    public void setReports(int reports) {
        this.reports = reports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskId, task.taskId) &&
                Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, title);
    }

    @Override
    public int compareTo(Task o) {
        return o.getCreationDate().compareTo(this.creationDate);
    }
}
