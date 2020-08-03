package com.cronos.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cronos.vote.model.MyUserDetails;
import com.cronos.vote.model.User;
import com.cronos.vote.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
		User user = userRepository.getUserByPseudo(pseudo);

		if (user == null) {
			throw new UsernameNotFoundException("Impossible de trouver l'utilisateur");
		}

		return new MyUserDetails(user);
	}
}