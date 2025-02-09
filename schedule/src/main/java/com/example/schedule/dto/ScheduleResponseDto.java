package com.example.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private final Long id;
    private final String todo;

    public ScheduleResponseDto(Long id, String todo) {
        this.id = id;
        this.todo = todo;
    }
}
