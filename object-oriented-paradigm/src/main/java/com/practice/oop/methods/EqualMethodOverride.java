package com.practice.oop.methods;

/* Equals Method:
 * equals method is used to compare if two objects are having the same content.
 * 		Default implementation of equals method is defined in Object class. The implementation is similar to == operator.
 * 		By default, two object references are equal only if they are pointing to the same object.
 * 		However, we can override equals method and provide a custom implementation to compare the contents for an object.
 */
public class EqualMethodOverride {
	public static void main(String[] args) {

		/************* Test with Client Class(Without equals() override): **********/
		// == comparison operator checks if the object references are pointing to the same object. 
		// It does NOT look at the content of the object.
		Client client1 = new Client(25);
		Client client2 = new Client(25);
		Client client3 = client1;

		//client1 and client2 are pointing to different client objects.
		System.out.println("== test: ");
		System.out.println(client1 == client2);//false

		//client3 and client1 refer to the same client objects.
		System.out.println(client1 == client3);//true

		//similar output to ==
		System.out.println("equals(wo override) test: ");
		System.out.println(client1.equals(client2));//false
		System.out.println(client1.equals(client3));//true
		System.out.println();

		/************* Test with ClientOverride Class: **********/
		System.out.println("equals(with override) test: ");
		ClientOverride ob1 = new ClientOverride(25);
		ClientOverride ob2 = new ClientOverride(25);
		ClientOverride ob3 = ob1;

		//both id's are 25
		System.out.println(ob1.equals(ob2));//true

		//both id's are 25
		System.out.println(ob1.equals(ob3));//true
	}
}

class Client {
	private int id;

	public Client(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
}

//overriding equals method
class ClientOverride {
	private int id;

	public ClientOverride(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		ClientOverride other = (ClientOverride) obj;
		if (id != other.id)
			return false;
		return true;
	}
}