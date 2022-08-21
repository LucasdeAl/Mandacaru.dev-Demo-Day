package br.ufc.mandacaru5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.mandacaru5.model.Property;
import br.ufc.mandacaru5.model.User;
import br.ufc.mandacaru5.repository.PropertyRepository;
import br.ufc.mandacaru5.repository.UserRepository;

@Service
public class PropertyService {

	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
	UserRepository userRepository;

	public void update(int id, Property entity) {
		Property property = find(id);		
		property.setTitle(entity.getTitle());
		
		propertyRepository.save(property);				
	}
	
	public void save(int product_id, Property entity) {
		User user = userRepository.findById(product_id).get();
		entity.setUser(user);
		propertyRepository.save(entity);				
	}

	public void delete(int id) {
		Property property = find(id);
		propertyRepository.delete(property);
	}

	public Property find(int id) {
		if (id < 1) {
			return null;
		}

		Optional<Property> property = propertyRepository.findById(id);

		if (property.isPresent()) {
			return property.get();
		}

		return null;
	}

	public List<Property> findAll(int product_id) {
		return propertyRepository.findByUserId(product_id);
	}
	
	public List<Property> findAllProperties()
		{
			return propertyRepository.findAll();
		}
	
	public Property findByTitle(String title)
	{
		return propertyRepository.findByTitle(title);
	}
	
}
