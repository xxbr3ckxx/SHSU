/**    A driver that uses a list to track the runners in a race   as they cross the finish line.   @author Frank M. Carrano   @author Timothy M. Henry   @version 4.0*/public class ListClient2 {  public static void main(String[] args)   {    testList();  }  // end main  public static void testList()   {      ListInterface<String> alphaList = new AList<>();  //  runnerList has only methods in ListInterface      alphaList.add(1, "Amy"); // Winner      alphaList.add(2, "Ellen"); // Second place      alphaList.add(3, "Bob"); // Third place      alphaList.add(2, "Drew"); // Fourth place      alphaList.add(4, "Aaron");      alphaList.add(3, "Sam");            displayList(alphaList);   } // end testList      public static void displayList(ListInterface<String> list)   {      int numberOfEntries = list.getLength();      System.out.println("The list contains " + numberOfEntries +                         " entries, as follows:");      for (int position = 1; position <= numberOfEntries; position++)         System.out.println(list.getEntry(position) +                            " is entry " + position);      System.out.println();   } // end displayList} // end ListClient/* The list contains 4 entries, as follows: 16 is entry 1 4 is entry 2 33 is entry 3 27 is entry 4 */