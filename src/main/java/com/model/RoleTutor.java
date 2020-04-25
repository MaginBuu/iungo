package com.model;

import javax.persistence.*;

@Entity
@Table(name = "role_tutor")
@NamedQueries({
        @NamedQuery(name = "RoleTutor.findById", query = "SELECT o FROM RoleTutor o WHERE o.userR.userId = :tutorId"),
        @NamedQuery(name = "RoleTutor.findByIdWithGroup", query = "SELECT o FROM RoleTutor o LEFT JOIN FETCH o.group i WHERE o.userR.userId = :tutorId"),
})
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
