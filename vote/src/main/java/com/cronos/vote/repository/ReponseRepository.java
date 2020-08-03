package com.cronos.vote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronos.vote.model.Reponse;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse, Long> {

	public List<Reponse> findReponseByQuestion_idAndUser_id(long question_id, long user_id);

	public List<Reponse> findReponseByQuestion_id(long question_id);

}
