package com.model.encapsulators;

import com.model.Subject;
import com.model.UserSubject;
import com.model.UserTask;

import java.util.List;

public class GradesEncapsulator {

    List<UserTask> userTasks;

    UserSubject userSubject;

    Subject subject;

    public List<UserTask> getUserTasks() { return userTasks; }

    public void setUserTasks(List<UserTask> userTasks) { this.userTasks = userTasks; }

    public UserSubject getUserSubject() { return userSubject; }

    public void setUserSubject(UserSubject userSubject) { this.userSubject = userSubject; }

    public Subject getSubject() { return subject; }

    public void setSubject(Subject subject) { this.subject = subject; }

    public GradesEncapsulator() { }

    public GradesEncapsulator(List<UserTask> userTasks, UserSubject userSubject, Subject subject) {
        this.userTasks = userTasks;
        this.userSubject = userSubject;
        this.subject = subject;
    }
}
