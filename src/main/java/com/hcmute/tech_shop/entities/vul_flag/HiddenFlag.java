package com.hcmute.tech_shop.entities.vul_flag;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hidden_flags")
public class HiddenFlag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "MY_SEQUENCE", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String flag;

    @Column(nullable = false)
    private String challenge;
}
