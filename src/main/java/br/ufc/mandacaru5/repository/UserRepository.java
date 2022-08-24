package br.ufc.mandacaru5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.mandacaru5.model.Person;

public interface UserRepository extends JpaRepository<Person, Integer> {

	Person findFirstByName(String name);
	Person findByEmail(String email);
}
