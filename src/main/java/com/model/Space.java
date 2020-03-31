package com.model;

import com.model.enums.Typology;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "spaces")
@NamedQueries({
//     @NamedQuery(name = "Room.findById", query = "SELECT r,te.email FROM Room r  "
//             + "LEFT JOIN Tenant te ON te.room = r.id"
//             + "WHERE r.id = :id")
        @NamedQuery(name = "Space.findById", query = "SELECT r FROM Space r WHERE r.spaceId = :id"),
        @NamedQuery(name = "Space.findAll", query = "SELECT r FROM Space r"),
        @NamedQuery(name = "Space.findByIdWithTimeline", query ="SELECT s FROM Space s JOIN FETCH s.timelines t WHERE s.spaceId = :id"),
        @NamedQuery(name = "Space.findByIdWithTimelineDay", query ="SELECT s FROM Space s JOIN FETCH s.timelines t WHERE s.spaceId = :id AND t.weekday = :wd"),

})
public class Space implements Serializable {

    private static final long serialVersionUID = 2681531852204068105L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "SPACE_ID")
    private String spaceId;

    @Column(name = "NAME")
    //@NotNull
    private String name;

    @Column(name = "BLACKBOARD")
    //@NotNull
    private boolean blackboard;

    @Column(name = "INTERIOR")
    //@NotNull
    private boolean interior;

    @Column(name = "PROJECTOR")
    //@NotNull
    private boolean projector;

    @Column(name = "PLATFORM")
    //@NotNull
    private boolean platform;

    @Column(name = "TABLES")
    //@NotNull
    private boolean tables;

    @Column(name = "TYPOLOGY")
    //@NotNull
    private Typology typology;

    @Column(name = "CAPACITY")
    //@NotNull
    private int capacity;

    @OneToMany(mappedBy="spaceTimeLine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TimeLine> timelines;

    @Transient
    private String attributesTemp;

    public Space() { }

    public Space(String name, boolean blackboard, boolean interior, boolean projector, boolean platform, boolean tables, boolean lab, Typology typology, int capacity) {
        this.name = name;
        this.blackboard = blackboard;
        this.interior = interior;
        this.projector = projector;
        this.platform = platform;
        this.tables = tables;
        this.typology = typology;
        this.capacity = capacity;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String id) { this.spaceId = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBlackboard() {
        return blackboard;
    }

    public void setBlackboard(boolean blackboard) {
        this.blackboard = blackboard;
    }

    public boolean isInterior() {
        return interior;
    }

    public void setInterior(boolean interior) {
        this.interior = interior;
    }

    public boolean isProjector() {
        return projector;
    }

    public void setProjector(boolean projector) {
        this.projector = projector;
    }

    public boolean isPlatform() {
        return platform;
    }

    public void setPlatform(boolean platform) {
        this.platform = platform;
    }

    public boolean isTables() {
        return tables;
    }

    public void setTables(boolean tables) {
        this.tables = tables;
    }

    public Typology getTypology() {
        return typology;
    }

    public void setTypology(Typology typology) {
        this.typology = typology;
    }

    public int isCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() { return capacity; }

    public String getAttributesTemp() { return attributesTemp; }

    public void setAttributesTemp(String attributesTemp) { this.attributesTemp = attributesTemp; }

    public List<TimeLine> getTimelines() {
        return timelines;
    }

    public void setTimelines(List<TimeLine> timeline) {
        this.timelines = timeline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Space space = (Space) o;
        return blackboard == space.blackboard &&
                interior == space.interior &&
                projector == space.projector &&
                platform == space.platform &&
                tables == space.tables &&
                capacity == space.capacity &&
                Objects.equals(spaceId, space.spaceId) &&
                Objects.equals(name, space.name) &&
                typology == space.typology;
    }

    @Override
    public int hashCode() {
        return Objects.hash(spaceId, name, blackboard, interior, projector, platform, tables, typology, capacity);
    }
}
