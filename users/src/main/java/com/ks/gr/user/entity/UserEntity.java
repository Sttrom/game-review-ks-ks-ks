package com.ks.gr.user.entity;

import com.ks.gr.user.entity.enumeration.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;

    private String username;

    private String email;

    private String password;

    private String steamLink;

    private String about;

    private String avatar; //FIXME avatar type
}
