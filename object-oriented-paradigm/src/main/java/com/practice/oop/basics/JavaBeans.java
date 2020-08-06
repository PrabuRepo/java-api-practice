package com.practice.oop.basics;

/*
 * Java Beans:
 * 	Private Member Variables: Good practice is to have all member variables in a class declared as private.
 * 	Naming setter and getter methods:
 * 		To modify and access values of properties we use setter and getter methods. Getters and setters should be public.
 * 		Getters should not have any arguments passed.
 * 		Setters should take one argument (the property value) with same type as the return value of getter.
 * 		Non boolean getter name should be (get + PropertyName)
 * 		boolean getter name can be (get + PropertyName) or (is + PropertyName)
 * 		All setters should be named (set + PropertyName)
 */
public class JavaBeans {
	private String name;
	private boolean good;

	public String getName() {
		return name;
	}

	public boolean isGood() {
		return good;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGood(boolean isGood) {
		this.good = isGood;
	}
}