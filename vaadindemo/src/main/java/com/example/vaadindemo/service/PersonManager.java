package com.example.vaadindemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.vaadindemo.domain.Person;

public class PersonManager {
	
	private List<Person> db = new ArrayList<Person>();
	
	public void addPerson(Person person,String how_long){
		Person p = new Person(person.getFirstName(), person.getYob(), person.gettitle(),person.getData_wyp(),person.getEmail());
		p.setId(UUID.randomUUID());
		p.setData_zwr(p.addDays(p.getData_wyp(),getNumber(how_long)));
		db.add(p);
	}
	
	public List<Person> findAll(){
		return db;
	}

	public void delete(Person person) {
		
		Person toRemove = null;
		for (Person p: db) {
			if (p.getId().compareTo(person.getId()) == 0){
				toRemove = p;
				break;
			}
		}
		db.remove(toRemove);		
	}

	public void updatePerson(Person person,String how_long) {
		Person p = new Person(person.getFirstName(), person.getYob(), person.gettitle(),person.getData_wyp(),person.getEmail());
		p.setId(person.getId());
		p.setData_zwr(p.addDays(p.getData_wyp(),getNumber(how_long)));
		delete(person);
		db.add(p);
		}
	public int getNumber(String how_long)
	{
		if(how_long.contentEquals("day"))
		{
			return 0; 
		}else if(how_long.contentEquals("week"))
		{
			return 1;
		}else if(how_long.contentEquals("2 weeks"))
		{
			return 2;
		}else if(how_long.contentEquals("month"))
		{
			return 5;
		}
		return -4;
	}
		

}
