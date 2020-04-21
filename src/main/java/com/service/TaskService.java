package com.service;


import com.model.Task;
import com.model.UserTask;

import java.util.List;

public interface TaskService {

    void addUserTask(UserTask userTask);

    void addTask(Task task);

    List<UserTask> getUserTaskByUserAndSubject(String userId, String subjectId);

    List<UserTask> getUserTaskByTaskId(String taskId);

}
