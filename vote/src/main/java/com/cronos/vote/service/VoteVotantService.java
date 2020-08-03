package com.cronos.vote.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronos.vote.model.User;
import com.cronos.vote.model.VoteVotant;
import com.cronos.vote.repository.VoteVotantRepository;

@Service
@Transactional
public class VoteVotantService {

	private static final Logger logger = Logger.getLogger(VoteVotantService.class);

	@Autowired
	private VoteVotantRepository votevotantrepository;

	public List<User> findUserByVoteId(long voteId) {
		logger.info("Récupération des users du vote: " + voteId);
		List<User> users = new ArrayList<User>();
		List<VoteVotant> votesvotants = votevotantrepository.findVoteVotantByVoteId(voteId);

		votesvotants.forEach(votevotant -> {
			users.add(votevotant.getUser());
		});

		return users;
	}

}
