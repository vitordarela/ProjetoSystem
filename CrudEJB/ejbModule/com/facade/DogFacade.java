package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.Dog;

@Local
public interface DogFacade {

	public abstract void save(Dog dog);

	public abstract Dog update(Dog dog);
	
	public abstract void delete(Dog dog);

	public abstract Dog find(int entityID);

	public abstract List<Dog> findAll();
}