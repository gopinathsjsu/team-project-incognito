package com.hotel.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotel.project.Model.Customer;
import com.hotel.project.Model.Role;
import com.hotel.project.repository.CustomerRepository;
import com.hotel.project.repository.RoleRepository;

@Service
public class CustomerServiceImpl  implements UserDetailsService{
	

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer findUserByEmail(String email) {
	    return customerRepository.findByEmail(email);
	}

	public void saveCustomer(Customer customer) {
		customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
	    //customer.setEnabled(true);
	    Role userRole = roleRepository.findByRole("ADMIN");
	    customer.setRoles(new HashSet<>(Arrays.asList(userRole)));
	    customerRepository.save(customer);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	    Customer customer = customerRepository.findByEmail(email);
	    if(customer != null) {
	        List<GrantedAuthority> authorities = getUserAuthority(customer.getRoles());
	        return buildUserForAuthentication(customer, authorities);
	    } else {
	        throw new UsernameNotFoundException("username not found");
	        
	    }
  }

	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
	    Set<GrantedAuthority> roles = new HashSet<>();
	    userRoles.forEach((role) -> {
	        roles.add(new SimpleGrantedAuthority(role.getRole()));
	    });

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	    return grantedAuthorities;
	}
	
	private UserDetails buildUserForAuthentication(Customer customer, List<GrantedAuthority> authorities) {
	    return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(), authorities);
	}
}
