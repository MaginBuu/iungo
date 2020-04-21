package com.model.encapsulators;

import com.model.UserTask;

import java.util.List;

public class UserTaskEncapsulator {

    private List<UserTask> tasks;

    public List<UserTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<UserTask> tasks) {
        this.tasks = tasks;
    }
}
