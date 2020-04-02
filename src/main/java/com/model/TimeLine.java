package com.model;

import com.model.enums.WeekDay;
import com.model.utilities.Hour;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "timelines")
@NamedQueries({
//     @NamedQuery(name = "Room.findById", query = "SELECT r,te.email FROM Room r  "
//             + "LEFT JOIN Tenant te ON te.room = r.id"
//             + "WHERE r.id = :id")
        @NamedQuery(name = "TimeLine.findById", query = "SELECT r FROM TimeLine r JOIN FETCH r.spaceTimeLine s JOIN FETCH r.subjectTimeLine o WHERE r.timeLineId = :id"),

})
public class TimeLine implements Serializable {

    private static final long serialVersionUID = 2681531852204068105L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "TIMELINE_ID")
    private String timeLineId;

    @Column(name = "WEEKDAY")
    //@NotNull
    private WeekDay weekday;

    @Column(name = "STARTING_HOUR")
    private String startingHour;

    @Column(name = "FINISHING_HOUR")
    private String finishingHour;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "SUBJECT_ID")
    private Subject subjectTimeLine;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "SPACE_ID")
    private Space spaceTimeLine;

    @Transient
    private String subjectName = "";

    @Transient
    private String spaceName = "";
    
    @Transient
    private String timelineSpaceId = "";

    @Transient
    private String timelineSubjectId = "";

    public TimeLine() {
    }

    public TimeLine(WeekDay weekday, String startingHour, String finishingHour) {
        this.weekday = weekday;
        this.startingHour = startingHour;
        this.finishingHour = finishingHour;
    }

    public String getTimeLineId() {
        return timeLineId;
    }

    public WeekDay getWeekday() {
        return weekday;
    }

    public void setWeekday(WeekDay weekday) {
        this.weekday = weekday;
    }

    public String getStartingHour() {
        return startingHour;
    }

    public void setStartingHour(String startingHour) {
        this.startingHour = startingHour;
    }

    public String getFinishingHour() {
        return finishingHour;
    }

    public void setFinishingHour(String finishingHour) {
        this.finishingHour = finishingHour;
    }

    public Subject getSubjectTimeLine() {
        return subjectTimeLine;
    }

    public void setSubjectTimeLine(Subject subjectTimeLine) {
        this.subjectTimeLine = subjectTimeLine;
    }

    public Space getSpaceTimeLine() {
        return spaceTimeLine;
    }

    public void setSpaceTimeLine(Space spaceTimeLine) {
        this.spaceTimeLine = spaceTimeLine;
    }

    public String getSubjectName() {
        this.subjectName=subjectTimeLine.getName(); return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSpaceName() {
        this.spaceName=spaceTimeLine.getName(); return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getTimelineSpaceId() {
        String space = timelineSpaceId;
        if("".equals(space)) {
            if(getSpaceTimeLine()!=null) space = getSpaceTimeLine().getSpaceId();
        }
        return space;
    }

    public void setTimelineSpaceId(String timelineSpaceId) {
        this.timelineSpaceId = timelineSpaceId;
    }

    public String getTimelineSubjectId() {
        String space = timelineSubjectId;
        if("".equals(space)) {
            if(getSubjectTimeLine()!=null) space = getSubjectTimeLine().getSubjectId();
        }
        return space;
    }

    public void setTimelineSubjectId(String timelineSubjectId) {
        this.timelineSubjectId = timelineSubjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeLine timeLine = (TimeLine) o;
        return Objects.equals(timeLineId, timeLine.timeLineId) &&
                weekday == timeLine.weekday &&
                Objects.equals(startingHour, timeLine.startingHour) &&
                Objects.equals(finishingHour, timeLine.finishingHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeLineId, weekday, startingHour, finishingHour);
    }

}
