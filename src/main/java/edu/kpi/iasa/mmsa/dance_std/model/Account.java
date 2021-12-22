package edu.kpi.iasa.mmsa.dance_std.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
public class Account {
    public static final Account ACCOUNT = new Account("anonymous");

    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Collection<Role> roles = new HashSet<>();

    public Account() {
    }
    public Account(String username) {
        this.username = username;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Size(
            min = 6,
            max = 30,
            message = "validation.text.error.from.six.to.thirty")
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Column(name = "password")

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Size(
            min = 3,
            max = 30,
            message = "validation.text.error.from.three.to.thirty")
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Size(
            min = 3,
            max = 40,
            message = "validation.text.error.from.three.to.forty")
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    @Transient
    public String getFullName() {
        return this.firstname + " " + this.lastname;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Size(
            min = 6,
            max = 40,
            message = "validation.text.error.from.six.to.forty")
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Pattern(regexp = "\\+\\d{12}", message = "validation.text.phone.error.sample")
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {
                    @JoinColumn(
                            name = "id_user",
                            referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "id_role",
                            referencedColumnName = "id")})
    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.addAll(getRoles());
        return authorities;
    }
    public boolean hasRole(String role) {
        for (Role r : getRoles()) {
            if (r.getName().equals(role)) return true;
        }
        return false;
    }


    //private Long id_role;
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//    public Long getId_role(){
//        return id_role;
//    }
//
//    public void setId_role(Long id_role){
//        this.id_role = id_role;
//    }
//
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
    //    @Transient
//    public GrantedAuthority getAuthority() {
//        GrantedAuthority authority = getId_role();
//        return authority;
//    }

//    public boolean hasRole(String role) {
//        Role r = getId_role();
//        if (r.getName().equals(role)) return true;
//        return false;
//    }

    public String toString() {
        return username;
    }

}
