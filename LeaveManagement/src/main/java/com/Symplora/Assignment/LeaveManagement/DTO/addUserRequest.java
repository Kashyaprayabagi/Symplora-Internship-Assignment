package com.Symplora.Assignment.LeaveManagement.DTO;
import com.Symplora.Assignment.LeaveManagement.Entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class addUserRequest {
    private String username;
    private String password;
    private Role role = Role.EMPLOYEE;

    private String ename;
    private String email;
    private String dept;
    private String phNo;
    private LocalDate joiningDate;

}



