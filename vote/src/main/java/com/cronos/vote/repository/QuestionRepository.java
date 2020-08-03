package com.cronos.vote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronos.vote.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	public List<Question> findByFormulaire_id(long formulaire_id);

}
