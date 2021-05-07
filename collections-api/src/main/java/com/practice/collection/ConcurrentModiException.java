package com.practice.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentModiException {

	public static void main(String[] args) {

		ConcurrentModiException ob = new ConcurrentModiException();

		System.out.println("******ArrayList Delete***********");
		ob.deleteOperations(new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60, 70)));
		//foreach throws java.util.ConcurrentModificationException

		System.out.println("\n******LinkedList Delete***********");
		ob.deleteOperations(new LinkedList<>(Arrays.asList(10, 20, 30, 40, 50, 60, 70)));
		//foreach throws java.util.ConcurrentModificationException

		System.out.println("\n******CopyOnWriteArrayList Delete***********");
		ob.deleteOperations(new CopyOnWriteArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60, 70)));
		//Iterator throws java.lang.UnsupportedOperationException 

		System.out.println("\n******Synchronized ArrayList Delete***********");
		List<Integer> arrList = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60, 70));
		ob.deleteOperations(Collections.synchronizedList(arrList));
		//Iterator throws java.lang.UnsupportedOperationException 
	}

	public void deleteOperations(List<Integer> intList) {
		intList.forEach(k -> System.out.print(k + " "));
		System.out.println("\nDelete using ForEach: ");
		try {
			for (Integer val : intList) {
				if (val == 30) {
					intList.remove(val);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception Thrown: " + e.getCause());
			e.printStackTrace();
		}
		System.out.println();
		intList.forEach(k -> System.out.print(k + " "));

		System.out.println("\nDelete using For: ");
		try {
			for (int i = 0; i < intList.size(); i++) {
				if (intList.get(i).equals(40)) {
					intList.remove(i);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception Thrown: " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println();
		intList.forEach(k -> System.out.print(k + " "));

		System.out.println("\nDelete using Iterators: ");
		try {
			Iterator<Integer> iterators = intList.iterator();
			while (iterators.hasNext()) {
				if (iterators.next() == 50) {
					iterators.remove();
				}
			}
		} catch (Exception e) {
			System.out.println("Exception Thrown: " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println();
		intList.forEach(k -> System.out.print(k + " "));
	}

}