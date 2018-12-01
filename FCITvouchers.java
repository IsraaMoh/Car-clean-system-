// Esraa Mohammed amin allouh _1616810 _ GA_ P4

package fcitcarclean;

public class FCITvouchers {
    private FCITvoucher top;

    public FCITvouchers() {
        this.top=null;
    }
// boolean | isEmpty()
	//
	public boolean isEmpty() {
		return top == null;
	}
	
	
	//
	// void | PrintStack()
	//
	public void PrintStack() {
		PrintStack(top);
	}
	//
	// void | PrintStack(StackNode)
	//
	private void PrintStack(FCITvoucher top) {
		// We need to traverse...so we need a help ptr
		FCITvoucher helpPtr = top;
		// Traverse to correct insertion point
		while (helpPtr != null) {
			// Print the data value of the node
			System.out.print(helpPtr.getFirstName() + ", ");
			// Step one node over
			helpPtr = helpPtr.getNext();
		}
		System.out.println();
	}
	
	

	// void | push(int)
	//
	public void push( int arrivalTime, int ID, String firstName, String lastName, String code, int timeFinished ,int startsurv ) {
		top = push( arrivalTime,  ID,  firstName,  lastName,  code,  timeFinished , startsurv, top);
	}
	//
	// StackNode | push(StackNode, int)
	//
	private FCITvoucher push( int arrivalTime, int ID, String firstName, String lastName, String code, int timeFinished ,int startsurv ,FCITvoucher top) {
		// Make a new StackNode with "data" as the data value
		// and set the "next" of this new node to the same address as top
		// * This is the same as addToFront() method for Linked Lists.
		top = new FCITvoucher( arrivalTime,  ID,  firstName,  lastName,  code,  timeFinished , startsurv, top);
		
		// Now, return the newly updated top.
		return top;
	}
	
	
	//
	// StackNode | pop()
	//
	public FCITvoucher pop() {
		// Save a reference to the current top node (because we will change where top points to)
		FCITvoucher temp = top;
		
		// Now, invoke the pop method with top as a parameter.
		// This method will return a new top node.
		top = pop(top);
		
		// Finally, return temp, which is the previous top node that we just "popped" off the list.
		return temp;
	}
	//
	// StackNode | pop(StackNode)
	//
	private FCITvoucher pop(FCITvoucher top) {
		// Set top equal to the next node.
		// This will make top point to the 2nd node instead of the first node.
		top = top.getNext();
		
		// return the address/reference of the new top node
		return top;
	}
	
	
	//
	// int | peek()
	//
	public FCITvoucher peek() {
		// Invoke the peek method with top as a parameter
		FCITvoucher topValue = peek(top);
		
		// return topValue
		return topValue;
	}
	//
	// int | peek(StackNode)
	//
	private FCITvoucher peek(FCITvoucher top) {
		// Return the data value of the top node.
		// You can see that we do NOT pop. We are only returning the data value.
		return top;
	}
}