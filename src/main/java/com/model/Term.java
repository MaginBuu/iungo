package com.model;

import com.model.enums.WeekDay;
import com.model.utilities.Hour;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "evaluations")
@NamedQueries({
//     @NamedQuery(name = "Room.findById", query = "SELECT r,te.email FROM Room r  "
//             + "LEFT JOIN Tenant te ON te.room = r.id"
//             + "WHERE r.id = :id")

})
public class Term implements Serializable {

    private static final long serialVersionUID = 2681531852204068105L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "EVALUATION_ID")
    private String evaluationId;

    @Column(name = "TERM")
    //@NotNull
    private String term;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "SUBJECT_ID")
    private Subject subjectEvaluation;


    public String getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(String evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String name) {
        this.term = name;
    }
}
