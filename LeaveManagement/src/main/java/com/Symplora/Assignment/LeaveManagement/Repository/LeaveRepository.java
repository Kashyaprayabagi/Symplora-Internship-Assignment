package com.Symplora.Assignment.LeaveManagement.Repository;

import com.Symplora.Assignment.LeaveManagement.Entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRepository extends JpaRepository <Leave, Long> {
    @Query("SELECT l FROM Leave l WHERE l.employee.EId = :employeeId AND l.approvalStatus <> false " +
            "AND (:startDate <= l.endDate AND :endDate >= l.startDate)")
    List<Leave> findOverlappingLeaves(@Param("employeeId") Long employeeId,
                                      @Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate);

    List<Leave> findByApprovalStatusIsNull();
}
