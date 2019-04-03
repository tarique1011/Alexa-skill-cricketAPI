package org.cricketapi.batsmen;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatsmenService {
	
	@Autowired
	private BatsmenRepository batsmenRepository;
	
	
	
	public List<Batsmen> getAllBatsmen()
	{
		List <Batsmen> batsmen = new ArrayList<>();
		batsmenRepository.findAll()
						.forEach(batsmen::add);
		return batsmen;
		
	}

	public void addBatsmen(Batsmen batsmen) {
		
		batsmenRepository.save(batsmen);
	}

	public void deleteBatsmen(int id) {
		
		batsmenRepository.deleteById(id);
	}

	public Batsmen getBatsmenByName(String name) {
		
		Batsmen batsmen = new Batsmen();
		batsmen=batsmenRepository.findByName(name);
		return batsmen;
	}

	public void updateBatsmen(Batsmen batsmen) {
		
		batsmenRepository.save(batsmen);
		
	}

	public List<Batsmen> getBatsmenByCountry(String countryName) {
		
		List<Batsmen> batsmen = new ArrayList<>();
		batsmenRepository.findByCountry(countryName)
						.forEach(batsmen::add);
		return batsmen;
	}

	public List<Batsmen> getBatsmenByAge(int age) {
		
		List<Batsmen> batsmen = new ArrayList<>();
		batsmenRepository.findAll()
						.forEach(batsmen::add);
		
		List<Batsmen> batsmenAge= new ArrayList<>();
		
		for(int i=0;i<batsmen.size();i++)
		{
			Batsmen b= new Batsmen();
			b=batsmen.get(i);
			if(b.getAge()>age)
				batsmenAge.add(b);
		}
		
		return batsmenAge;
		
	}

	public Batsmen getBatsmenByRun() {
		
		List<Batsmen> batsmen= new ArrayList<>();
		batsmenRepository.findAll()
						.forEach(batsmen::add);
		
		int maxRuns=0;
		Batsmen b= new Batsmen();
		
		
		for(int i=0;i<batsmen.size();i++)
		{
			if(batsmen.get(i).getRuns()>maxRuns)
			{
				b=batsmen.get(i);
				maxRuns=b.getRuns();
			}
		}
		
		return b;
		
	}

	public Batsmen getBatsmenByCountryByRun(String countryName) {
		List<Batsmen> batsmen = new ArrayList<>();
		batsmenRepository.findByCountry(countryName)
						.forEach(batsmen::add);
		
		int maxRuns=0;
		Batsmen b= new Batsmen();
		
		
		for(int i=0;i<batsmen.size();i++)
		{
			if(batsmen.get(i).getRuns()>maxRuns)
			{
				b=batsmen.get(i);
				maxRuns=b.getRuns();
			}
		}
		
		return b;
	}
	
	
	
	
	
}
