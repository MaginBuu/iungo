package com.model;

import com.model.enums.Typology;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "spaces")
@NamedQueries({
//     @NamedQuery(name = "Room.findById", query = "SELECT r,te.email FROM Room r  "
//             + "LEFT JOIN Tenant te ON te.room = r.id"
//             + "WHERE r.id = :id")

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

    @Column(name = "LAB")
    //@NotNull
    private boolean lab;

    @Column(name = "TYPOLOGY")
    //@NotNull
    private Typology typology;

    @Column(name = "CAPACITY")
    //@NotNull
    private boolean capacity;

    public Space() {
    }

    public Space(String name, boolean blackboard, boolean interior, boolean projector, boolean platform, boolean tables, boolean lab, Typology typology, boolean capacity) {
        this.name = name;
        this.blackboard = blackboard;
        this.interior = interior;
        this.projector = projector;
        this.platform = platform;
        this.tables = tables;
        this.lab = lab;
        this.typology = typology;
        this.capacity = capacity;
    }

    public String getSpaceId() {
        return spaceId;
    }

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

    public boolean isLab() {
        return lab;
    }

    public void setLab(boolean lab) {
        this.lab = lab;
    }

    public Typology getTypology() {
        return typology;
    }

    public void setTypology(Typology typology) {
        this.typology = typology;
    }

    public boolean isCapacity() {
        return capacity;
    }

    public void setCapacity(boolean capacity) {
        this.capacity = capacity;
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
                lab == space.lab &&
                capacity == space.capacity &&
                Objects.equals(spaceId, space.spaceId) &&
                Objects.equals(name, space.name) &&
                typology == space.typology;
    }

    @Override
    public int hashCode() {
        return Objects.hash(spaceId, name, blackboard, interior, projector, platform, tables, lab, typology, capacity);
    }
}
