package com.Symplora.Assignment.LeaveManagement.Repository;

import com.Symplora.Assignment.LeaveManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String admin);
}
