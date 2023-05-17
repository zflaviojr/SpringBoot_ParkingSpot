package edu.ufcg.br.parkingspot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.ufcg.br.parkingspot.model.UserModel;
import edu.ufcg.br.parkingspot.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserRepository userModelRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserModel userModel = userModelRepository.findByUserName(userName).orElseThrow(
			() -> new UsernameNotFoundException("User not found with username " + userName));
		return userModel;
	}

}
