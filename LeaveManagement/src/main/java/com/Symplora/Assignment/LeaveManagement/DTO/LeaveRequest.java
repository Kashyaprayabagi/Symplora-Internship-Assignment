package com.Symplora.Assignment.LeaveManagement.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LeaveRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String urgency;
}