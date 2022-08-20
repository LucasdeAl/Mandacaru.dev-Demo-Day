package br.ufc.mandacaru5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.mandacaru5.model.Owner;


public interface UserRepository extends JpaRepository<Owner, Integer> {

	Owner findFirstByName(String name);
	Owner findByEmail(String email);
}
