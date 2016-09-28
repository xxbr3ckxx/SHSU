/**  * A driver that demonstrates cloning by the class CloneableLinkedList.   @author Frank M. Carrano   @author Timothy M. Henry   @version 4.0*/public class Driver {	public static void main(String[] args) 	{		testClone();		System.out.println("\n\nDone.");	}  // end main	public static void testClone()	{		// Create names for a list		Name april = new Name("April", "Showers");		Name may   = new Name("May", "Flowers");		Name luke  = new Name("Luke", "Lexter");		Name brent = new Name("Brent", "Barka");		// Create a list   	CloneableListInterface<Name> myList = new CloneableLinkedList<>();			myList.add(april);		myList.add(may);		myList.add(luke);		myList.add(brent);				// Display the list		System.out.println("List should contain\nApril Showers, May Flowers, Luke Lexter, Brent Barka");		System.out.println("\n\nList actually contains:");		displayList(myList);		System.out.println();				// Clone the list      @SuppressWarnings("unchecked")		CloneableLinkedList<Name> cloneOfList = (CloneableLinkedList<Name>)myList.clone();				// Display the clone		System.out.println("Cloned list should contain\nApril Showers, May Flowers, Luke Lexter, Brent Barka");		System.out.println("\n\nCloned list actually contains:");		displayList(cloneOfList);		System.out.println();				System.out.println("\nOriginal list has " + myList.getLength() + " entries.");		System.out.println("Cloned list has " + cloneOfList.getLength() + " entries.\n\n");		// Now alter original list (mylist)		myList.replace(2, new Name("Tom", "Towers"));		myList.add(new Name("Java", "Bean"));				// Also change a name and see if it changes in clone		Name nameRef = myList.getEntry(3);		nameRef.setLast("Smith");				System.out.println("\nModify the list, as follows:\n" +		                   "Replace 2nd name, change 3rd person's last name, and add a name to end.\n" +		                   "See if the clone changes.");		System.out.println("\n\nModified list should contain\nApril Showers, Tom Towers, Luke Smith, Brent Barka, Java Bean");		System.out.println("\n\nModified list actually contains:");		displayList(myList);		System.out.println();			// Check clone		System.out.println("\nCloned list should contain\nApril Showers, May Flowers, Luke Lexter, Brent Barka");		System.out.println("\n\nCloned list actually contains:");		displayList(cloneOfList);		System.out.println();				System.out.println("\nChange Brent's last name and look at clone again.");		System.out.println("Clone should not change.");		brent.setLast("Jones");      		System.out.println("\n\nChanged list contains:");		displayList(myList);		System.out.println("\n\nCloned list contains:");		displayList(cloneOfList);		System.out.println();	} // end testClone	public static void displayList(CloneableListInterface<Name> list)	{	  int numberOfEntries = list.getLength();	  	  System.out.println("\nThe list contains " + numberOfEntries +	                     " entries, as follows:");	  for (int position = 1; position <= numberOfEntries; position++)	    System.out.println(list.getEntry(position) + 	                       " is entry " + position);	  System.out.println();	} // end displayList}  // end Driver/* List should contain April Showers, May Flowers, Luke Lexter, Brent Barka   List actually contains:  The list contains 4 entries, as follows: April Showers is entry 1 May Flowers is entry 2 Luke Lexter is entry 3 Brent Barka is entry 4   Cloned list should contain April Showers, May Flowers, Luke Lexter, Brent Barka   Cloned list actually contains:  The list contains 4 entries, as follows: April Showers is entry 1 May Flowers is entry 2 Luke Lexter is entry 3 Brent Barka is entry 4    Original list has 4 entries. Cloned list has 4 entries.    Modify the list, as follows: Replace 2nd name, change 3rd person's last name, and add a name to end. See if the clone changes.   Modified list should contain April Showers, Tom Towers, Luke Smith, Brent Barka, Java Bean   Modified list actually contains:  The list contains 5 entries, as follows: April Showers is entry 1 Tom Towers is entry 2 Luke Smith is entry 3 Brent Barka is entry 4 Java Bean is entry 5    Cloned list should contain April Showers, May Flowers, Luke Lexter, Brent Barka   Cloned list actually contains:  The list contains 4 entries, as follows: April Showers is entry 1 May Flowers is entry 2 Luke Lexter is entry 3 Brent Barka is entry 4    Change Brent's last name and look at clone again. Clone should not change.   Changed list contains:  The list contains 5 entries, as follows: April Showers is entry 1 Tom Towers is entry 2 Luke Smith is entry 3 Brent Jones is entry 4 Java Bean is entry 5    Cloned list contains:  The list contains 4 entries, as follows: April Showers is entry 1 May Flowers is entry 2 Luke Lexter is entry 3 Brent Barka is entry 4     Done. */