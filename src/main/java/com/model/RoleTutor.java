package com.model;

import javax.persistence.*;

@Entity
@Table(name = "role_tutor")
public class RoleTutor extends RoleClass {


    @OneToOne(mappedBy = "tutor", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private ClassGroup group;

    public RoleTutor() {}

    public ClassGroup getGroup() {
        return group;
    }

    public void setGroup(ClassGroup group) {
        this.group = group;
    }
}
