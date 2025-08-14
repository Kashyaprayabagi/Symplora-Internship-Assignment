package com.Symplora.Assignment.LeaveManagement.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Leaves")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long leaveId;

    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String urgency;
    private Boolean approvalStatus;


    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;


    public Leave(long leaveId, LocalDate startDate, LocalDate endDate, String reason, String urgency, Boolean approvalStatus, Employee employee) {
        this.leaveId = leaveId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.urgency = urgency;
        this.approvalStatus = approvalStatus;
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "leaveId=" + leaveId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", reason='" + reason + '\'' +
                ", urgency='" + urgency + '\'' +
                ", approvalStatus=" + approvalStatus +
                ", employee=" + employee +
                '}';
    }
}
