package br.ufc.mandacaru5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.mandacaru5.model.Person;
import br.ufc.mandacaru5.model.Role;
import br.ufc.mandacaru5.repository.UserRepository;
import br.ufc.mandacaru5.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository repository;
	
	@Autowired
	UserRepository userRepository;

	public void save(Role entity) {
		repository.save(entity);
	}
	
	public void addRole(int id, int userId) {
		Person person = userRepository.findById(userId).get();
		Role role = repository.findById(id).get();			
		
		List<Role> roles = person.getRoles();
		
		if(roles != null) {
			roles.add(role);
		} else {
			roles = new ArrayList<Role>();
			roles.add(role);
		}
		
		person.setRoles(roles);
		userRepository.save(person);
	}
	
	public List<Role> findAll() {
        return repository.findAll();
    }
}
