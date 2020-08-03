package com.cronos.vote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronos.vote.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

	public Vote getVoteByProcessId(String processId);

	public List<Vote> findVoteByUserId(long userId);

}
