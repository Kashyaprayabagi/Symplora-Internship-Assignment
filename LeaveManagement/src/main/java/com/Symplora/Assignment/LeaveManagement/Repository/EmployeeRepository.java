package com.Symplora.Assignment.LeaveManagement.Repository;

import com.Symplora.Assignment.LeaveManagement.Entity.Employee;
import com.Symplora.Assignment.LeaveManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUser(User user);
}
