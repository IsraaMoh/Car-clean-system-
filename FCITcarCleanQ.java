// Esraa Mohammed amin allouh _1616810 _ GA_ P4

package fcitcarclean;

public class FCITcarCleanQ {
   private FCITmember front;
   private FCITmember back;
   private int numCustomers;

    public FCITcarCleanQ() {
     front= null;
     back=null;
     numCustomers =0;
    }
	
	
	/* Below are MANY methods that are used on stacks.
	 * 
	 * Examples:
	 * isEmpty, PUSH, POP, PEEK, and more.
	 */
	
	
	//
	// boolean | isEmpty()
	//
	public boolean isEmpty() {
		return this.getNumCustomers()==0;
	}
	
	
	//
	// void | PrintQueue()
	//
	public void PrintQueue() {
		PrintQueue(front);
	}
	//
	// void | PrintQueue(QueueNode)
	//
	private void PrintQueue(FCITmember front) {
		// We need to traverse...so we need a help ptr
		FCITmember helpPtr = front;
		// Traverse to correct insertion point
		while (helpPtr != null) {
			// Print the data value of the node
			System.out.print(helpPtr.getFirstName() + ", ");
			// Step one node over
			helpPtr = helpPtr.getNext();
		}
		System.out.println();
	}
	

	// void | enqueue(int)
	//
        
	public void enqueue(int arrivalTime, int ID, String firstName, String lastName, String code  ) {
		if (isEmpty()) {
			front = back = enqueue(front, back, arrivalTime, ID,  firstName,  lastName,  code  );
		}
		else {
			back = enqueue(front, back,  arrivalTime, ID,  firstName,  lastName,  code);
		}
		 numCustomers++;
	}
	//
	// QueueNode | enqueue(QueueNode, QueueNode, int)
	//
	private FCITmember enqueue(FCITmember front, FCITmember back, int arrivalTime, int ID, String firstName, String lastName, String code ) {
		// Make a new QueueNode with "data" as the data value
		FCITmember temp = new FCITmember(arrivalTime,  ID,  firstName,  lastName,  code );
		
		// Now, if the list is empty, return the reference for temp
		// and save this reference into both "front" and "back"
		// Why? Since this is the only node in the queue, it will be the front and back node
		if (isEmpty()) {
			return temp;
		}
		// ELSE, the queue is not empty. We need to insert temp at the back of the queue.
		// So save the address of the new node into the next of back.
		// Then, make back "traverse" one node over, so it now points to the new back node.
		// Finally, return the updated address of back.
		else {
			back.setNext(temp);
			back = back.getNext();
			return back;
		}
	}
	
	
	//
	// QueueNode | dequeue()
	//
	public FCITmember dequeue() {
            if( !isEmpty()){
		FCITmember temp = front;
		front = front.getNext();
		if (front == null)
			back = null;
               numCustomers--;
            
		return temp;
            }
            

            return null;
	}
	//
	// QueueNode | dequeue(QueueNode)
	//
	private FCITmember dequeue(FCITmember front) {
		front = front.getNext();
		return front;
	}
	
	
	//
	// int | peek()
	//
	public FCITmember peek() {
		// Invoke the peek method with front as a parameter
		FCITmember frontValue = peek(front);
		
		// return topValue
		return frontValue;
	}
	//
	// int | peek(QueueNode)
	//
	private FCITmember peek(FCITmember front) {
		// Return the data value of the front node.
		// You can see that we do NOT dequeue. We are only returning the data value.
		return front;
	}

    public int getNumCustomers() {
        return numCustomers;
    }
        
        
}
