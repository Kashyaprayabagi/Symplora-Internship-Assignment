package com.Symplora.Assignment.LeaveManagement.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "Employee")
@Inheritance(strategy = InheritanceType.JOINED) // define inheritance strategy
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EId;

    private String Ename;
    private String Email;
    private String dept;
    private String phNo;
    private LocalDate joiningDate;
    private Integer leaveBalance = 25;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Leave> leaves;

    public Employee(Long EId, String ename, String email, String dept, String phNo, LocalDate joiningDate, Integer leaveBalance, User user, List<Leave> leaves) {
        this.EId = EId;
        Ename = ename;
        Email = email;
        this.dept = dept;
        this.phNo = phNo;
        this.joiningDate = joiningDate;
        this.leaveBalance = leaveBalance;
        this.user = user;
        this.leaves = leaves;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EId=" + EId +
                ", Ename='" + Ename + '\'' +
                ", Email='" + Email + '\'' +
                ", dept='" + dept + '\'' +
                ", phNo='" + phNo + '\'' +
                ", joiningDate=" + joiningDate +
                ", leaveBalance=" + leaveBalance +
                ", user=" + user +
                ", leaves=" + leaves +
                '}';
    }
}
