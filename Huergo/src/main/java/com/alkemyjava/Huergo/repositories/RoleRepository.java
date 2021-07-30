package com.alkemyjava.Huergo.repositories;

import com.alkemyjava.Huergo.entities.Role;
import com.alkemyjava.Huergo.entities.enumerations.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
