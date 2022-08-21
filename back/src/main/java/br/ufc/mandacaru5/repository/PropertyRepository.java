package br.ufc.mandacaru5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.mandacaru5.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Integer> {

	List<Property> findByUserId(int id);
	
	Property findByTitle(String title);
	
	
}
