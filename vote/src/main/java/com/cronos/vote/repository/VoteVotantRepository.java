package com.cronos.vote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronos.vote.model.VoteVotant;

@Repository
public interface VoteVotantRepository extends JpaRepository<VoteVotant, Long> {

	public List<VoteVotant> findVoteVotantByVoteId(long voteId);

}