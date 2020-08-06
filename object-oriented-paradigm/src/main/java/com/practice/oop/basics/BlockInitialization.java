package com.practice.oop.basics;

/*
 * Initialization Blocks - Code which runs when an object is created or a class is loaded
 * Types of Initialization Blocks:
 * 		- Blocks Static Initializer: Code that runs when a class is loaded. 
 * 		- Instance Initializer: Code that runs when a new object is created.
 */
public class BlockInitialization {
	public static void main(String[] args) {
		// Even though three instances are created static initializer is run only once.
		System.out.println("Static Block Initializer:  ");
		StaticBlockInitializer ob1 = new StaticBlockInitializer();
		StaticBlockInitializer ob2 = new StaticBlockInitializer();
		StaticBlockInitializer ob3 = new StaticBlockInitializer();

		//Code within instance initializer is run every time an instance of the class is created.
		System.out.println("\nInstance Block Initializer:  ");
		InstanceBlockInitializer obj1 = new InstanceBlockInitializer();
		InstanceBlockInitializer obj2 = new InstanceBlockInitializer();
		InstanceBlockInitializer obj3 = new InstanceBlockInitializer();
	}
}

class StaticBlockInitializer {
	static int count;
	int i;

	static {
		//This is a static initializers. Run only when Class is first loaded.
		//Only static variables can be accessed
		System.out.println("Static Initializer");
		//i = 6;//COMPILER ERROR
		System.out.println("Count when Static Initializer is run is " + count);
	}
}

class InstanceBlockInitializer {
	static int count;
	int i;
	{
		//This is an instance initializers. Run every time an object is created.
		//static and instance variables can be accessed
		System.out.println("Instance Initializer");
		i = 6;
		count = count + 1;
		System.out.println("Count when Instance Initializer is run is " + count);
	}

}