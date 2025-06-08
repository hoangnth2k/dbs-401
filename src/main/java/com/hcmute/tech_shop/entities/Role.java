package com.hcmute.tech_shop.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "MY_SEQUENCE", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String name;

    @Column(name = "active")
    private boolean isActive;

    @OneToMany(mappedBy = "role")
    @ToString.Exclude
    private List<User> users;
}
