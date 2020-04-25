package com.service;

import com.model.ClassGroup;
import com.model.enums.Stage;

import java.util.List;

public interface GroupService {

    void addGroup(ClassGroup group);

    List<ClassGroup> getQueryResults(String query);

    List<ClassGroup> getAllClassGroup();

    ClassGroup getClassGroupById(String id);

    ClassGroup getGroupBySubjectId(String id);

    List<Integer> getLevelsByStage(Stage stage);

    List<String> getGroupsByStageAndLevel(Stage stage, int level);

    ClassGroup getGroupsByStageAndLevelAndGroupAndCourse(ClassGroup groupTmp);
}
