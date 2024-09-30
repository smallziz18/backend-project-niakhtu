package com.niakhtu.backend.niakhtu.repository;


import com.niakhtu.backend.niakhtu.models.Complainte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//page2
@Repository // nous permet de faire des Operations CRUD
public interface ComplainteRepository extends JpaRepository<Complainte, Long> {

}
