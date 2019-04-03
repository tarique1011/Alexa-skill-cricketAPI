package org.cricketapi.batsmen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatsmenController {
	
	@Autowired
	private BatsmenService batsmenService;
	
	
	@RequestMapping("/batsmen")
	public List<Batsmen> getAllBatsmen()
	{
		return batsmenService.getAllBatsmen();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/batsmen")
	public void addBatsmen(@RequestBody Batsmen batsmen)
	{
		batsmenService.addBatsmen(batsmen);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/batsmen/{id}")
	public void deleteBatsmen(@PathVariable int id) {
		
		batsmenService.deleteBatsmen(id);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT,value="/batsmen/{id}")
	public void updateBatsmen(@PathVariable int id,@RequestBody Batsmen batsmen) {
		
		batsmenService.updateBatsmen(batsmen);
	}
	
	
	
	@RequestMapping("/batsmen/{name}")
	public Batsmen getBatsmenByName(@PathVariable String name) {
		
		return batsmenService.getBatsmenByName(name);
	}
	
	@RequestMapping("/batsmen/country/{countryName}")
	public List<Batsmen> getBatsmenByCountry(@PathVariable String countryName) {
		
		return batsmenService.getBatsmenByCountry(countryName);
		
	}
	
	
	@RequestMapping("/batsmen/age/{age}")
	public List<Batsmen> getBatsmenByAge(@PathVariable int age){
		
		return batsmenService.getBatsmenByAge(age);
	}
	
	@RequestMapping("/batsmen/runs")
	public Batsmen getBatsmenByRun()
	{
		return batsmenService.getBatsmenByRun();
		
	}
	
	@RequestMapping("/batsmen/{countryName}/runs")
	public Batsmen getBatsmenByCountryByRun(@PathVariable String countryName)
	{
		return batsmenService.getBatsmenByCountryByRun(countryName);
	}
	
	
	
}
