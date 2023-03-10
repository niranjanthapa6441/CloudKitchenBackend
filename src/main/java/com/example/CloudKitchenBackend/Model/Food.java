package com.example.CloudKitchenBackend.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "food")
public class Food {

    @SequenceGenerator(
            name = "food_id_sequence",
            sequenceName = "food_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="food_sequence"
    )
    @Column(name = "food_id")
    private int id;


    @Column(name = "name",unique = true, nullable = false, length = 100)
    private String name;
}
