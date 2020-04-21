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

    @Override
    public List<UserTask> getUserTaskByUserAndSubject(String userId, String subjectId) { return taskDao.getUserTaskByUserAndSubject(userId, subjectId); }

    @Override
    public List<UserTask> getUserTaskByUser(String userId) { return taskDao.getUserTaskByUser(userId); }

    @Override
    public List<UserTask> getUserTaskByStudent(String studentId) { return taskDao.getUserTaskByStudent(studentId); }
}
