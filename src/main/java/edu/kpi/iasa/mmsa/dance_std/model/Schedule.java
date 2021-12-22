package edu.kpi.iasa.mmsa.dance_std.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "training_time")
@IdClass(CompositeKey.class)
public class Schedule {
    @Id
    @NotNull
    @Column(name = "idgroup")
    private Long idgroup;

    public enum Day { Mon, Tue, Wed, Thu, Fri , Sat, Sun};
    @Id
    @NotNull
    @Column(name = "day")
    @Enumerated(EnumType.STRING)
    private Day day;
    public Day getDay(){return day;}
    public void setDay(Day t){day = t;}

    public Long getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(Long idgroup) {
        this.idgroup = idgroup;
    }

    @NotNull
    @Column(name = "time")
    private LocalTime time;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}

