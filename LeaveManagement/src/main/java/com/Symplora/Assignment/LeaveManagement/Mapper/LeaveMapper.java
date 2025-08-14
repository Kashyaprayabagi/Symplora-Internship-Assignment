package com.Symplora.Assignment.LeaveManagement.Mapper;

import com.Symplora.Assignment.LeaveManagement.DTO.LeaveRequest;
import com.Symplora.Assignment.LeaveManagement.DTO.LeaveResponse;
import com.Symplora.Assignment.LeaveManagement.Entity.Leave;
import org.springframework.stereotype.Component;

@Component
public class LeaveMapper {
    public Leave toEntity(LeaveRequest dto) {
        Leave leave = new Leave();
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setReason(dto.getReason());
        leave.setUrgency(dto.getUrgency());
        leave.setApprovalStatus(null); // pending on creation
        return leave;
    }

    public LeaveResponse toDto(Leave leave) {
        LeaveResponse dto = new LeaveResponse();
        dto.setLeaveId(leave.getLeaveId());
        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setReason(leave.getReason());
        dto.setUrgency(leave.getUrgency());
        dto.setApprovalStatus(leave.getApprovalStatus());
        return dto;
    }
}
