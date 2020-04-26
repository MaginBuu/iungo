package com.model.encapsulators;

import com.model.User;
import com.model.enums.FaultType;

public class Incidence {

    FaultType faultType;

    String description;

    User user;

    public FaultType getFaultType() { return faultType; }

    public void setFaultType(FaultType faultType) { this.faultType = faultType; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Incidence() { }


}
