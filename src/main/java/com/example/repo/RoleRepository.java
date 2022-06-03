package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role getRoleByRoleName(@Param("name") String name);

}
