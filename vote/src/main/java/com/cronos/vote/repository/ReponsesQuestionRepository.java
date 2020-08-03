package com.cronos.vote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronos.vote.model.ReponsesQuestion;

@Repository
public interface ReponsesQuestionRepository extends JpaRepository<ReponsesQuestion, Long> {

	public List<ReponsesQuestion> findReponsesQuestionByQuestion_id(long question_id);

}
