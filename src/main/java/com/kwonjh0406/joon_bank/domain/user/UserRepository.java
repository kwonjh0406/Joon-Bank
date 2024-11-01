package com.kwonjh0406.joon_bank.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    boolean existsByPhoneNumber(String phoneNumber);

    User findByUsername(String username);

}
