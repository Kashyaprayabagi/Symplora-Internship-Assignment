package com.Symplora.Assignment.LeaveManagement.Services;

import com.Symplora.Assignment.LeaveManagement.DTO.LeaveRequest;
import com.Symplora.Assignment.LeaveManagement.DTO.LeaveResponse;
import com.Symplora.Assignment.LeaveManagement.Entity.Employee;
import com.Symplora.Assignment.LeaveManagement.Entity.Leave;
import com.Symplora.Assignment.LeaveManagement.Entity.User;
import com.Symplora.Assignment.LeaveManagement.Mapper.LeaveMapper;
import com.Symplora.Assignment.LeaveManagement.Repository.EmployeeRepository;
import com.Symplora.Assignment.LeaveManagement.Repository.LeaveRepository;
import com.Symplora.Assignment.LeaveManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  EmployeeRepository employeeRepository;

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private LeaveMapper leaveMapper;


    @Transactional
    public LeaveResponse applyLeave(String username, LeaveRequest dto) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Employee employee = employeeRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        validateLeaveRequest(employee, dto);

        Leave leave = leaveMapper.toEntity(dto);
        leave.setEmployee(employee);
        Leave savedLeave = leaveRepository.save(leave);
        return leaveMapper.toDto(savedLeave);
    }

    private void validateLeaveRequest(Employee employee, LeaveRequest dto) {
        if (dto.getEndDate().isBefore(dto.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        if (dto.getStartDate().isBefore(employee.getJoiningDate())) {
            throw new IllegalArgumentException("Cannot apply leave before joining date");
        }

        long daysRequested = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate()) + 1;

        if (employee.getLeaveBalance() == null || employee.getLeaveBalance() < daysRequested) {
            throw new IllegalArgumentException("Insufficient leave balance");
        }

        List<Leave> overlapping = leaveRepository.findOverlappingLeaves(
                employee.getEId(),
                dto.getStartDate(),
                dto.getEndDate()
        );
        if (!overlapping.isEmpty()) {
            throw new IllegalArgumentException("Overlapping leave request exists");
        }
    }

    public Integer getLeaveBalance(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Employee employee = employeeRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return employee.getLeaveBalance();
    }
}
