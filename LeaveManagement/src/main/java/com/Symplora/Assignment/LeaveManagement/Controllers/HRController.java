package com.Symplora.Assignment.LeaveManagement.Controllers;

import com.Symplora.Assignment.LeaveManagement.DTO.addUserRequest;
import com.Symplora.Assignment.LeaveManagement.Entity.Employee;
import com.Symplora.Assignment.LeaveManagement.Entity.Leave;
import com.Symplora.Assignment.LeaveManagement.Services.HRServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hr")
public class HRController {

    @Autowired
    private HRServices hrServices;

    @PostMapping("/addUser")
    public ResponseEntity<Employee> addUser(@RequestBody addUserRequest user) {
        Employee savedEmp = hrServices.addUser(user);
        return ResponseEntity.ok(savedEmp);
    }

    @DeleteMapping("/removeUser/{employeeId}")
    public ResponseEntity<String> removeUser(@PathVariable Long employeeId) {
        hrServices.removeUser(employeeId);
        return ResponseEntity.ok("User and Employee removed successfully");
    }

    @PutMapping("/approveLeave/{leaveId}")
    public ResponseEntity<String> approveLeave(@PathVariable Long leaveId) {
        hrServices.approveLeave(leaveId);
        return ResponseEntity.ok("Leave approved successfully");
    }

    @PutMapping("/rejectLeave/{leaveId}")
    public ResponseEntity<String> rejectLeave(@PathVariable Long leaveId) {
        hrServices.rejectLeave(leaveId);
        return ResponseEntity.ok("Leave rejected successfully");
    }

    @GetMapping("/allLeaves")
    public ResponseEntity<List<Leave>> getAllLeaves() {
        return ResponseEntity.ok(hrServices.getAllLeaves());
    }

    @GetMapping("/pendingLeaves")
    public ResponseEntity<List<Leave>> getPendingLeaves() {
        return ResponseEntity.ok(hrServices.getPendingLeaves());
    }
}
