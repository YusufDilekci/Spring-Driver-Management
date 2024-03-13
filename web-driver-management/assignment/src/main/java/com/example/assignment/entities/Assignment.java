package com.example.assignment.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="assignments")
@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private int driverId;

    private int vehicleId;

    private Date assignedDate;

    private Date returnDate;
}
