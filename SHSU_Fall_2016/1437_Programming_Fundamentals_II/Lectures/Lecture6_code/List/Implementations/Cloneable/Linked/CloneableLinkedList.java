/**
   A class that implements the ADT list by using a chain of
   linked nodes that has a head reference.
   Entries in a list have positions that begin with 1.
   Duplicate entries are allowed.
   Cloneable version.
 
   @author Frank M. Carrano
   @version 4.0
*/
public class CloneableLinkedList<T extends Copyable> implements CloneableListInterface<T>
{
	private Node firstNode;            // Reference to first node of chain
	private int  numberOfEntries;
   
	public CloneableLinkedList()
	{
		initializeDataFields();
	} // end default constructor
   
	public Object clone()
	{
      CloneableLinkedList<T> theCopy = null;
      
      try
      {
         @SuppressWarnings("unchecked")
         CloneableLinkedList<T> temp = (CloneableLinkedList<T>)super.clone();
         theCopy = temp;
      }
      catch (CloneNotSupportedException e)
      {
         throw new Error(e.toString());
      }
      
      // Copy underlying chain of nodes
      if (firstNode == null) // If chain is empty
      {
         theCopy.firstNode = null;
      }
      else
      {
         // Make a copy of the first node
         @SuppressWarnings("unchecked")
         Node temp = (Node)firstNode.clone();
         theCopy.firstNode = temp;
         
         // Make a copy of the rest of chain
         Node newRef = theCopy.firstNode;
         Node oldRef = firstNode.getNextNode();
         
         for (int count = 2; count <= numberOfEntries; count++)
         {
            // Clone node and its data; link clone to new chain
            @SuppressWarnings("unchecked")
            Node temp2 = (Node)oldRef.clone();
            newRef.setNextNode(temp2);
            newRef = newRef.getNextNode();
            oldRef = oldRef.getNextNode();
         } // end for
      } // end if
      
      return theCopy;
	} // end clone

	public void add(T newEntry) 	      // OutOfMemoryError possible
	{
		Node newNode = new Node(newEntry);
      
		if (isEmpty())
			firstNode = newNode;
		else                              // Add to end of non-empty list
		{
			Node lastNode = getNodeAt(numberOfEntries);
			lastNode.setNextNode(newNode); // Make last node reference new node
		} // end if
		
		numberOfEntries++;
	}  // end add
   
   public void add(int newPosition, T newEntry) // OutOfMemoryError possible
	{
 		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
		{
			Node newNode = new Node(newEntry);
         
			if (newPosition == 1)                  // Case 1
			{
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}
			else									         // Case 2: list is not empty
			{                                      // and newPosition > 1
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			} // end if
         
			numberOfEntries++;
		}
      else
         throw new IndexOutOfBoundsException(
                                             "Illegal position given to add operation.");
   } // end add
   
	public T remove(int givenPosition)
	{
      T result = null;                           // Return value
      
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         assert !isEmpty();
         
         if (givenPosition == 1)                 // Case 1: Remove first entry
         {
            result = firstNode.getData();        // Save entry to be removed
            firstNode = firstNode.getNextNode(); // Remove entry
         }
         else                                    // Case 2: Not first entry
         {
            Node nodeBefore = getNodeAt(givenPosition - 1);
            Node nodeToRemove = nodeBefore.getNextNode();
            result = nodeToRemove.getData();    // Save entry to be removed
            Node nodeAfter = nodeToRemove.getNextNode();
            nodeBefore.setNextNode(nodeAfter);  // Remove entry
         } // end if
         
         numberOfEntries--;                     // Update count
         return result;                         // Return removed entry
      }
      else
         throw new IndexOutOfBoundsException(
                                             "Illegal position given to remove operation.");
	} // end remove
   
	public void clear()
	{
		initializeDataFields();
	} // end clear
   
	public T replace(int givenPosition, T newEntry)
	{
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
      	assert !isEmpty();
         
			Node desiredNode = getNodeAt(givenPosition);
         T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);
         return originalEntry;
      }
		else
         throw new IndexOutOfBoundsException(
                                             "Illegal position given to replace operation.");
   } // end replace
   
   public T getEntry(int givenPosition)
   {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty();
         return getNodeAt(givenPosition).getData();
     	}
      else
         throw new IndexOutOfBoundsException(
                                             "Illegal position given to getEntry operation.");
   } // end getEntry
   
   public T[] toArray()
   {
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries];
      
      int index = 0;
      Node currentNode = firstNode;
      while ((index < numberOfEntries) && (currentNode != null))
      {
         result[index] = currentNode.getData();
         currentNode = currentNode.getNextNode();
         index++;
      } // end while
      
      return result;
   } // end toArray
   
	public boolean contains(T anEntry)
	{
		boolean found = false;
		Node currentNode = firstNode;
		
		while (!found && (currentNode != null))
		{
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} // end while
		
		return found;
	} // end contains
   
   public int getLength()
   {
      return numberOfEntries;
   } // end getLength
   
   public boolean isEmpty()
   {
      boolean result;
      
      if (numberOfEntries == 0) // Or getLength() == 0
      {
         assert firstNode == null;
         result = true;
      }
      else
      {
         assert firstNode != null;
         result = false;
      } // end if
      
      return result;
   } // end isEmpty
	
   // Initializes the class's data fields to indicate an empty list.
   private void initializeDataFields()
   {
		firstNode = null;
		numberOfEntries = 0;
   } // end initializeDataFields
	
   // Returns a reference to the node at a given position.
   // Precondition: List is not empty;
   //               1 <= givenPosition <= numberOfEntries.
	private Node getNodeAt(int givenPosition)
	{
		assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;
		
      // Traverse the chain to locate the desired node (skipped
      // if givenPosition is 1)
		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();
		
		assert currentNode != null;
      
		return currentNode;
	} // end getNodeAt
   
	private class Node implements Cloneable
	{
      private T    data; // Entry in list
      private Node next; // Link to next node
      
      private Node(T dataPortion)
      {
         data = dataPortion;
         next = null;
      } // end constructor
      
      private Node(T dataPortion, Node nextNode)
      {
         data = dataPortion;
         next = nextNode;
      } // end constructor
      
      private T getData()
      {
         return data;
      } // end getData
      
      private void setData(T newData)
      {
         data = newData;
      } // end setData
      
      private Node getNextNode()
      {
         return next;
      } // end getNextNode
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode

      protected Object clone()
      {
         Node theCopy = null;
         try
         {
            @SuppressWarnings("unchecked")
            Node temp = (Node)super.clone();
            theCopy = temp;
         }
         catch (CloneNotSupportedException e)
         {
            throw new Error(e.toString());
         }
         
         @SuppressWarnings("unchecked")
         T temp = (T)data.clone();
         theCopy.data = temp;
         theCopy.next = null; // Don't clone link; it’s set later
         
         return theCopy;
      } // end clone
	} // end Node
} // end CloneableLinkedList



