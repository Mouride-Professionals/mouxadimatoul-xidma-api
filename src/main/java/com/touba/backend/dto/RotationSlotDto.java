package com.touba.backend.dto;

import com.touba.backend.model.RotationSlot;
import lombok.Builder;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Builder
public class RotationSlotDto {

    private Long id;
    private DayOfWeek dayOfWeek;
    private LocalTime fromTime;
    private LocalTime toTime;

    public static RotationSlotDto fromEntity(RotationSlot slot) {
        if (slot == null) {
            return null;
        }
        return RotationSlotDto.builder()
                .id(slot.getId())
                .dayOfWeek(slot.getDayOfWeek())
                .fromTime(slot.getFromTime())
                .toTime(slot.getToTime())
                .build();
    }

    public static RotationSlot toEntity(RotationSlotDto dto) {
        if (dto == null) {
            return null;
        }
        RotationSlot slot = new RotationSlot();
        slot.setId(dto.getId());
        slot.setDayOfWeek(dto.getDayOfWeek());
        slot.setFromTime(dto.getFromTime());
        slot.setToTime(dto.getToTime());
        return slot;
    }
}
