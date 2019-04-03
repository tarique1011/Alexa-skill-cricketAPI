package org.cricketapi.batsmen;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BatsmenRepository extends CrudRepository<Batsmen, Integer>{

	public Batsmen findByName(String name);
	
	public List<Batsmen> findByCountry(String countryname);
	
}
