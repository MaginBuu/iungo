package com.model.encapsulators;

import com.model.UserSubject;

import java.util.List;

public class UserSubjectEncapsulator {

    private List<UserSubject> userSubjects;

    public List<UserSubject> getUserSubjects() {
        return userSubjects;
    }

    public void setUserSubjects(List<UserSubject> userSubjects) {
        this.userSubjects = userSubjects;
    }
}
