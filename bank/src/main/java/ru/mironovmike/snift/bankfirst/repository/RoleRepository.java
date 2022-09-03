package ru.mironovmike.snift.bankfirst.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mironovmike.snift.bankfirst.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
