package com.alejogalizzi.ecommerce.repository;

import com.alejogalizzi.ecommerce.model.authorization.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrivilegeRepository extends JpaRepository<Privilege, Long> {

}
