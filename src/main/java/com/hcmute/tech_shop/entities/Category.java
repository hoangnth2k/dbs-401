package com.hcmute.tech_shop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
@ToString(exclude = "products") // Không bao gồm products trong toString()
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "MY_SEQUENCE", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(name = "active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
