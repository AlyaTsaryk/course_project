package edu.kpi.iasa.mmsa.dance_std.repository;

import edu.kpi.iasa.mmsa.dance_std.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findById(Long id);
}
