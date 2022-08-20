package br.ufc.mandacaru5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.mandacaru5.model.Property;
import br.ufc.mandacaru5.model.Owner;
import br.ufc.mandacaru5.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public void save(int id, Owner entity) {
		if (id != 0) {
			entity.setId(id);
		}

		userRepository.save(entity);
	}
	
	public void update(int id, Owner entity) {
		Owner owner = find(id);		
		owner.setName(entity.getName());
		
		userRepository.save(owner);				
	}

	public void delete(int id) {
		Owner owner = find(id);
		userRepository.delete(owner);
	}

	public Owner find(int id) {
		if (id < 1) {
			return null;
		}

		Optional<Owner> owner = userRepository.findById(id);

		if (owner.isPresent()) {
			return owner.get();
		}

		return null;
	}

	public List<Owner> findAll() {
		return userRepository.findAll();
	}

	public Owner findByName(String str) {
		if (str.length() < 1) {
			return null;
		}
		return userRepository.findFirstByName(str);
	}
	

}
