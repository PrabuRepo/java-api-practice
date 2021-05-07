package com.practice.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This method explains about usage of equals & hashcode override. equals & hashcode: - Both method
 * is implemented to avoid the duplicate entry in Map.
 *
 */

public class EqualsAndHashCodeOverride {
	public static void main(String[] args) {
		EqualsAndHashCodeOverride ob = new EqualsAndHashCodeOverride();
		System.out.println("\n List Demo: For comparing list value, only equals() method has to implement");
		ob.listComparison();

		System.out.println("\n Set Demo: For comparing set value, both equals() & hashCode() method has to implement");
		ob.setComparison();

		System.out.println(
				"\n HashMap Demo:For avoiding duplicate key value in map, both equals() & hashCode() method has to implement");
		ob.mapComparison();

	}

	//List invokes only equals() method for contains and equals method
	public void listComparison() {
		List<Point2> list = new ArrayList<Point2>();
		list.add(new Point2(1, "aaa"));
		list.add(new Point2(1, "aaa"));
		list.add(new Point2(2, "ccc"));
		// List invokes equals only when checks existing object
		if (list.contains(new Point2(1, "aaa"))) {
			System.out.println("Yes: List contains value");
		} else {
			System.out.println("No: List doesn't contain value");
		}

		Point2 p = new Point2(2, "ccc");
		for (Point2 point : list) {
			if (point.equals(p)) System.out.println("Yes: object is present in list");
		}
		System.out.println("List:" + list);
	}

	/**
	 * Before adding the data into SET or MAP, gets the hashcode(from hashCode() method), if hashcode is
	 * equal to previously generated then invokes the equal method() to check equality. If it is not
	 * equal, then only insert the data into SET or MAP. Thats why SET/MAP doesn't have duplicate value.
	 */
	public void setComparison() {
		Set<Point2> set = new HashSet<Point2>();
		set.add(new Point2(1, "aaa"));
		set.add(new Point2(1, "aaa"));
		set.add(new Point2(2, "bbb"));
		set.add(new Point2(2, "bbb"));
		set.add(new Point2(2, "ccc"));

		if (set.contains(new Point2(1, "aaa"))) {
			System.out.println("Yes: Set contains value");
		} else {
			System.out.println("No: Set doesn't contain value");
		}

		Point2 p = new Point2(2, "ccc");
		for (Point2 point : set) {
			if (point.equals(p)) System.out.println("Yes: object is present in set");
		}

		System.out.println("Set:" + set);
	}

	public void mapComparison() {
		Map<Point2, Integer> mp = new HashMap<>();
		mp.put(new Point2(1, "aaa"), 1);
		mp.put(new Point2(1, "aaa"), 1);
		mp.put(new Point2(2, "ddd"), 2);
		mp.put(new Point2(2, "ddd"), 2);
		mp.put(new Point2(3, "ccc"), 3);
		mp.put(new Point2(4, "eee"), 4);

		if (mp.containsKey(new Point2(1, "aaa"))) {
			System.out.println("Yes: Map contains value");
		} else {
			System.out.println("No: Map doesn't contain value");
		}

		Point2 p = new Point2(3, "ccc");
		for (Entry<Point2, Integer> entry : mp.entrySet()) {
			if (entry.getKey().equals(p)) {
				System.out.println("Yes: object is present in map");
			}
		}

		System.out.println("Hash Map is : " + mp);
	}
}

class Point2 {
	int x;
	String y;

	Point2(int x, String y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "Key:" + this.x + "," + this.y;
	}

	@Override
	public int hashCode() {
		//Overridden:
		int newHashCode = 11 * (x) + y.hashCode();
		// System.out.println("newHashCode:"+newHashCode);
		return newHashCode;

		//Default:
		//return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		//Overridden:
		Point2 bObj = (Point2) obj;
		return (this.x == bObj.x) && (this.y.equals(bObj.y));

		//Default:
		//return super.equals(obj);
	}

}
