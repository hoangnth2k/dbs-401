package com.hcmute.tech_shop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "_hidden_flags")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HiddenFlag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String flag;
    
    @Column(nullable = false)
    private String challenge;
} 