import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;



/*================================
 Author     : Tim Schleicher
 Class Name : Groupings
 Date       : 8/17/2014     
 Purpose    : Divides a given list into a given number of nearly-equal groups.
=================================*/


public class Groupings
{	

	//for representing the text file name containing the list to be grouped
	private String fileName;	

	//for representing the number of groups to divide the list into
	private int numberOfGroups;
	
	// ArrayList of Strings to hold items in text file
	private List<String> list = new ArrayList<String>();
	
	// ArrayList of ArrayLists to hold the grouped items
	private List<List<String>> groupedList = new ArrayList<List<String>>();

	int number_of_elements = 0;
	int number_of_smaller_groups;
	int number_of_larger_groups;
	int size_of_smaller_groups;
	int size_of_larger_groups;
	int start_for_larger_groups = 0;
	int testGit;

	/**
	 * Default Constructor For Groupings Class
	 */
	 public Groupings()
	 {
 
	 };

	/**
	* Overloaded Constructor For Groupings Class
	*/
	 public Groupings(String a_file_name, int a_number_of_groups)
	 { 
		fileName = a_file_name;
		numberOfGroups = a_number_of_groups;

		try {

		// Create a File instance
		FileReader file = new FileReader(a_file_name);
		// Create a Scanner for the file
		Scanner input = new Scanner(file);
		
		// Read data from the file
		while (input.hasNext()) 
		{
			Scanner line = new Scanner(input.nextLine());
			String item = line.next();
			list.add(item);
		}
		// Close the text file
		input.close();

		System.out.println("Original List:\n" + list );
		
		long seed = System.nanoTime();
		Collections.shuffle(list, new Random(seed));
		
		number_of_elements = list.size();
		number_of_larger_groups = list.size() % numberOfGroups;
		number_of_smaller_groups = numberOfGroups - (number_of_larger_groups);
		size_of_larger_groups = (list.size() / numberOfGroups) + 1;
		size_of_smaller_groups = (list.size() / numberOfGroups);
		
		for (int start = 0; start < (number_of_smaller_groups * size_of_smaller_groups);
				start += size_of_smaller_groups )
		{
			int end = Math.min(start + size_of_smaller_groups, list.size());
			List<String> group = list.subList(start, end);
			groupedList.add(group);
//			System.out.println(group);
			start_for_larger_groups = end;
		}

		for (int start = start_for_larger_groups; start < list.size();
				start += size_of_larger_groups )
		{
			int end = Math.min(start + size_of_larger_groups, list.size());
			List<String> group = list.subList(start, end);
			groupedList.add(group);
//			System.out.println(group);
		}
		
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
	 }

	 public List<List<String>> getGroupedList()
	 {
		 return groupedList;
	 }
			

	/**
	 * Set method for the variable fileName
	 */
	public void setFileName(String a_file_name)
	{
		fileName = a_file_name;
	}

	/**
	 * Set method for the variable numberOfGroups
	 */
	public void setNumberOfGroups(int a_number_of_groups)
	{
		numberOfGroups = a_number_of_groups;
	}

	/**
	 * Get method for the variable fileName
	 */
	public String getFileName( )
	{
		return fileName;
	}

	/**
	 * Get method for the variable numberOfGroups
	 */
	public int getNumberOfGroups( )
	{
		return numberOfGroups;
	}

	/** 
	 * Returns the String representation of Groupings object 
	 */
	 public String toString()
	{
		 String temp = 
			"\nFile Name: " + fileName +
			"\nNumber of Groups: " + numberOfGroups +
			"\nList (in randomized order):\n" +
			list +
			"\nNumber of Elements: " + number_of_elements +
			"\n" + number_of_smaller_groups + " groups of " + size_of_smaller_groups +
			"\n" + 	number_of_larger_groups + " groups of " + size_of_larger_groups;

		 return temp;
	}

	/**
	* main( ) method for  Groupings Class
	*/
	public static void main(String args[])
	{ 
		Groupings groupings = new Groupings("list.txt", 6);
		System.out.println(groupings);
		System.out.println(groupings.getGroupedList());
	}
}
// Test
// Test2