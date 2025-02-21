package it.cgmconsulting.picozzi.repository;

import it.cgmconsulting.picozzi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}