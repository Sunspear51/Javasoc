// Recieves and adds message to Inbox
interface RecieveMessage{
	// returns a success/failure message
	boolean recieve(Activity activity);
}

// Removes and retrieves next message
interface ReadNextMessage{
	// returns an Activity or null if no messages
	Activity readNext();
}

// Inbox functionality
interface Inbox extends RecieveMessage, ReadNextMessage{
	//count unread messages
	int getCount();
}

// Sends message & adds it to Outbox
interface SendMessage {
	// returns a success/failure message
	boolean send(Activity activity);
}

// Removes and delivers the next message from Inbox
interface DeliverNextMessage {
	// returns an Activity or null if no messages
	Activity deliverNext();
}

// Outbox functionality
interface Outbox extends SendMessage, DeliverNextMessage {
	//returns count unsent messages
	int getCount();
}

// An Event that takes place
interface Activity {
	Person getSender(); 
    Person getReceiver(); 
    StreamObject getStream(); 
    boolean send(); 
    boolean open();
}

interface App {
    // Retrives Inbox
    Inbox getInbox();

    // Retrives Outbox
    Outbox getOutbox();

    // Prints a demo of App
    String demo();
}