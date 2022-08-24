package br.ufc.mandacaru5.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import br.ufc.mandacaru5.model.Person;

import br.ufc.mandacaru5.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Person person = userRepository.findByEmail(email);
		return new User(person.getUsername(), person.getPassword(), true, true, true, true, person.getAuthorities());
	

	}
}
