package com.spring.jpa.mvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jpa.mvc.entity.Customer;
import com.spring.jpa.mvc.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository repo;

	public void save(Customer customer) {
		repo.save(customer);
	}

	public List<Customer> listAll() {
		return (List<Customer>) repo.findAll();
	}

	public Customer get(Long id) {
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public List<Customer> search(String keyword) {
		return repo.search(keyword);
	}

}