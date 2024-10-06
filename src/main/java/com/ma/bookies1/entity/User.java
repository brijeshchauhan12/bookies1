package com.ma.bookies1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ma.bookies1.entity.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Table(name = "users")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String sex;

    @Column(updatable = true, name="date_of_birth", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateOfBirth;


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;



    @OneToMany(mappedBy = "user",fetch =FetchType.EAGER ,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Book> books;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }

    public User setCountry(String country) {
        this.country = country;
        return this;
    }
    public User setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public User setSex(String sex){
        this.sex=sex;
        return this;
    }

    public User setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth=dateOfBirth;
        return this;
    }


    public User setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public User setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    // public Object setPhoneNumber(String phoneNumber) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'setPhoneNumber'");
    // }
}