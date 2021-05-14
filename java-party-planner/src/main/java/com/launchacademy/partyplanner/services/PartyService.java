package com.launchacademy.partyplanner.services;

import com.launchacademy.partyplanner.models.Party;
import com.launchacademy.partyplanner.repositories.PartyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyService {

  private PartyRepository partyRepository;

  @Autowired
  public PartyService(PartyRepository partyRepository) {
    this.partyRepository = partyRepository;
  }

  public List<Party> findAll() {
    return (List<Party>) this.partyRepository.findAll();
  }

  public Party findById(Integer id) {
    return this.partyRepository.findById(id).get();
  }

  public void add(Party party) {
    this.partyRepository.save(party);
  }
}
