package com.service;


import com.dao.TaskDao;
import com.model.Task;
import com.model.UserTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDao taskDao;

    @Override
    public void addUserTask(UserTask userTask) { taskDao.addUserTask(userTask); }

    @Override
    public void addTask(Task task) { taskDao.addTask(task); }

    public Task getTaskById(String taskId) { return taskDao.getTaskById(taskId); }

    @Override
    public List<UserTask> getUserTaskByUserAndSubject(String userId, String subjectId) { return taskDao.getUserTaskByUserAndSubject(userId, subjectId); }

    public List<UserTask> getUserTaskByTaskId(String taskId) { return  taskDao.getUserTaskByTaskId(taskId); }
    
    @Override
    public List<UserTask> getUserTaskByUser(String userId) { return taskDao.getUserTaskByUser(userId); }

    @Override
    public List<UserTask> getUserTaskByStudent(String studentId) { return taskDao.getUserTaskByStudent(studentId); }

    public UserTask getUserTaskByUserAndTask(String userId, String taskId){ return taskDao.getUserTaskByUserAndTask(userId, taskId); }

    @Override
    public void deleteTask(Task task) { taskDao.deleteTask(task); }

    @Override
    public void deleteUserTask(String taskId) {
        taskDao.deleteUserTask(taskId);
    }
}
