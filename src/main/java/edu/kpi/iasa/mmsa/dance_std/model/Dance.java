package edu.kpi.iasa.mmsa.dance_std.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "dance")
public class Dance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return this.id;
    }

    @ManyToMany
    @JoinTable(
            name = "trainer_dance",
            joinColumns = {
                    @JoinColumn(
                            name = "id_dance",
                            referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "id_trainer",
                            referencedColumnName = "id")})
    private Collection<Account> trainers = new HashSet<>();

    public Collection<Account> getTrainers() {
        return trainers;
    }
    public void setTrainers(Collection<Account> trainers) {
        this.trainers = trainers;
    }
}

