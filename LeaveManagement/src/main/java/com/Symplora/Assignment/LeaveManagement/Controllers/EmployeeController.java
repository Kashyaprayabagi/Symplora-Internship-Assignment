package com.Symplora.Assignment.LeaveManagement.Controllers;

import com.Symplora.Assignment.LeaveManagement.DTO.LeaveRequest;
import com.Symplora.Assignment.LeaveManagement.DTO.LeaveResponse;
import com.Symplora.Assignment.LeaveManagement.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/applyLeave")
    public ResponseEntity<LeaveResponse> applyLeave(@RequestBody LeaveRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        LeaveResponse response = employeeService.applyLeave(username, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/leaveBalance")
    public ResponseEntity<Integer> getLeaveBalance() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer balance = employeeService.getLeaveBalance(username);
        return ResponseEntity.ok(balance);
    }
}
