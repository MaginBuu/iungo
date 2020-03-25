package com.model;

import com.model.enums.WeekDay;
import com.model.utilities.Hour;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "events")
@NamedQueries({
//     @NamedQuery(name = "Room.findById", query = "SELECT r,te.email FROM Room r  "
//             + "LEFT JOIN Tenant te ON te.room = r.id"
//             + "WHERE r.id = :id")

})
public class Event implements Serializable {

    private static final long serialVersionUID = 2681531852204068105L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "EVENT_ID")
    private String eventId;

    @Column(name = "TITLE")
    //@NotNull
    private String name;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "SUBJECT_ID")
    private Subject subjectEvent;

    public Event() {
    }

    public Event(String name) {
        this.name = name;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
