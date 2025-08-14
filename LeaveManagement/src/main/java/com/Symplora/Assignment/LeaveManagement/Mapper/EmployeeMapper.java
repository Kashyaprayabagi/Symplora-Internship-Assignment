package com.Symplora.Assignment.LeaveManagement.Mapper;

import com.Symplora.Assignment.LeaveManagement.Entity.Employee;
import com.Symplora.Assignment.LeaveManagement.Entity.User;
import com.Symplora.Assignment.LeaveManagement.DTO.addUserRequest;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEmployeeEntity(addUserRequest dto, User savedUser) {
        Employee emp = new Employee();
        emp.setEname(dto.getEname());
        emp.setEmail(dto.getEmail());
        emp.setDept(dto.getDept());
        emp.setPhNo(dto.getPhNo());
        emp.setJoiningDate(dto.getJoiningDate());
        emp.setUser(savedUser);
        return emp;
    }
}
