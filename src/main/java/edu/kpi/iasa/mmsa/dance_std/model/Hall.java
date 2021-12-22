package edu.kpi.iasa.mmsa.dance_std.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
@Entity
@Table(name = "capacity")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "capacity")
    private Long capacity;

    public Long getId() {
        return id;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long max_amount) {
        this.capacity = capacity;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


