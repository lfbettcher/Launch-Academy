package com.launchacademy.partyplanner.repositories;

import com.launchacademy.partyplanner.models.Party;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends PagingAndSortingRepository<Party, Integer> {

}
