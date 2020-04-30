package com.model;

import com.model.enums.FaultType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "incidences")

@NamedQueries({
        @NamedQuery(name = "Incidence.findById", query = "SELECT i FROM Incidence i WHERE i.incidenceId =:id"),
        @NamedQuery(name = "Incidence.findByProcedureId", query = "SELECT i FROM Incidence i WHERE i.procedure.procedureId =:id"),
        @NamedQuery(name = "Incidence.findByStudent", query = "SELECT i FROM Incidence i WHERE i.student.roleId =:id ORDER BY i.faultType, i.creationDate"),

})
public class Incidence {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "ICIDENCE_ID")
    private String incidenceId;

    @Column(name = "FAULT_TYPE")
    private FaultType faultType;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToOne
    private RoleStudent student;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @OneToOne
    private Procedure procedure;

    @Column(name="JUSTIFIED")
    private Boolean justified;

    public FaultType getFaultType() { return faultType; }

    public void setFaultType(FaultType faultType) { this.faultType = faultType; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public RoleStudent getStudent() { return student; }

    public void setStudent(RoleStudent student) { this.student = student; }

    public String getIncidenceId() { return incidenceId; }

    public void setIncidenceId(String incidenceId) { this.incidenceId = incidenceId; }

    public Date getCreationDate() { return creationDate; }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public Procedure getProcedure() { return procedure; }

    public void setProcedure(Procedure procedure) { this.procedure = procedure; }

    public Boolean getJustified() { return justified; }

    public void setJustified(Boolean justified) { this.justified = justified; }

    public Incidence() { }


}
