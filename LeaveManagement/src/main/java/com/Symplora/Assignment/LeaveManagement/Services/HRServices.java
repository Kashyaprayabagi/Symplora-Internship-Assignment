package com.Symplora.Assignment.LeaveManagement.Services;

import com.Symplora.Assignment.LeaveManagement.DTO.addUserRequest;
import com.Symplora.Assignment.LeaveManagement.Entity.Employee;
import com.Symplora.Assignment.LeaveManagement.Entity.Leave;
import com.Symplora.Assignment.LeaveManagement.Entity.User;
import com.Symplora.Assignment.LeaveManagement.Mapper.EmployeeMapper;
import com.Symplora.Assignment.LeaveManagement.Mapper.UserMapper;
import com.Symplora.Assignment.LeaveManagement.Repository.EmployeeRepository;
import com.Symplora.Assignment.LeaveManagement.Repository.LeaveRepository;
import com.Symplora.Assignment.LeaveManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class HRServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper um;

    @Autowired
    private EmployeeMapper emr;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LeaveRepository leaveRepository;

    @Transactional
    public Employee addUser(addUserRequest user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        User usr = um.toEntity(user);
        User savedUser = userRepository.save(usr);
        Employee emp = emr.toEmployeeEntity(user, savedUser);
        employeeRepository.save(emp);
        return emp;
    }

    @Transactional
    public void removeUser(Long employeeId) {
        Employee emp = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        if (emp.getUser() != null) {
            userRepository.delete(emp.getUser());
        }
        employeeRepository.delete(emp);
    }

    @Transactional
    public void approveLeave(Long leaveId) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        if (Boolean.TRUE.equals(leave.getApprovalStatus())) {
            throw new RuntimeException("Leave is already approved");
        }
        leave.setApprovalStatus(true);

        Employee employee = leave.getEmployee();
        long days = ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate()) + 1;
        int balance = employee.getLeaveBalance() != null ? employee.getLeaveBalance() : 0;
        if (balance < days) {
            throw new RuntimeException("Insufficient leave balance for approval");
        }
        employee.setLeaveBalance(balance - (int) days);

        employeeRepository.save(employee);
        leaveRepository.save(leave);
    }

    @Transactional
    public void rejectLeave(Long leaveId) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        leave.setApprovalStatus(false);
        leaveRepository.save(leave);
    }

    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    public List<Leave> getPendingLeaves() {
        return leaveRepository.findByApprovalStatusIsNull();
    }
}
