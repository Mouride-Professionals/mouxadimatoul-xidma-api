package com.touba.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RotationSlot extends AbstractModel {

    @ManyToOne(optional = false)
    private Assignment assignment;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek; // null = every day

    private LocalTime fromTime;

    private LocalTime toTime;
}
