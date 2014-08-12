package com.dao;

import javax.ejb.Stateless;

import com.model.Dog;

@Stateless
public class DogDAO extends GenericDAO<Dog> {

    public DogDAO() {
	super(Dog.class);
    }
    
    public void delete(Dog dog) {
        super.delete(dog.getId(), Dog.class);
    }
}