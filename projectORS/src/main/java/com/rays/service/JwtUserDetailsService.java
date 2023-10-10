package com.rays.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.UserDAOInt;
import com.rays.dto.UserDTO;

@Service
public class JwtUserDetailsService extends BaseServiceImpl<UserDTO, UserDAOInt> implements UserDetailsService {

	@Autowired
	private UserDAOInt userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Inside JwtUserDetailsService loadUserByUsername()......" + username);
		
		UserDTO user = userDao.findByEmail("email", username, null);
		
		System.out.println("Inside JwtUserDetailsService loadUserByUsername()...... After findByEmail run");
		if (user == null) {
			System.out.println("Inside JwtUserDetailsService loadUserByUsername()......user found null");
			throw new UsernameNotFoundException(
					"Inside JwtUserDetailsService loadUserByUsername()......User not found with username: " + username);
		}
		System.out.println("Inside JwtUserDetailsService loadUserByUsername()...... At return statement");
		return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(),
				new ArrayList<>());

	}

}