package it.cgmconsulting.picozzi.repository;

import it.cgmconsulting.picozzi.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}