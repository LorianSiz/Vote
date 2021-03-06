package com.cronos.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronos.vote.model.Formulaire;

@Repository
public interface FormulaireRepository extends JpaRepository<Formulaire, Long> {

}
