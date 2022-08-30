package br.ufc.mandacaru5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.mandacaru5.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
