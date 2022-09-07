package com.zurum.lanefinance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zurum.lanefinance.constants.KycLevel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "users", uniqueConstraints= @UniqueConstraint(columnNames = "email"))
public class AppUser extends BaseEntity {

    @Column(name = "firstname", length = 100)
    private String firstName;

    @Column(name = "lastname", length = 100)
    private String lastName;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "kyc_level")
    private KycLevel kycLevel;

    @JsonIgnore
    @Column(name = "password", length = 200)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id") })
    private Set<Role> roles;

    @Column(name = "is_verified")
    @Builder.Default
    private Boolean isVerified = false;

    @Column(name = "phone_number", length = 16)
    private String phoneNumber;

    @Column(name = "locked")
    private Boolean isLocked = false;

}