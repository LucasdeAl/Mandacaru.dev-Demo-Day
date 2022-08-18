package br.ufc.mandacaru5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.mandacaru5.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findFirstByName(String name);
}
