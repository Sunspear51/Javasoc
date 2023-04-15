import java.util.LinkedList;
import java.util.NoSuchElementException;

class UserOutbox implements Outbox {
    LinkedList<Activity> outbox;

    //Constructor
    public UserOutbox() {
        outbox = new LinkedList<Activity>();
    }

    //Methods
    @Override
    public boolean send(Activity activity) {
        try {
            outbox.add(activity);
            return(true);
        } catch(Exception e) {
            return(false);
        }
    }

    @Override
    public Activity deliverNext() {
        try {
            Activity first = outbox.getFirst(); // Fetch the oldest item
            outbox.removeFirst(); // Remove it
            return(first);
        } catch(NoSuchElementException e) { // If there's nothing in the outbox
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int getCount() {
        return(outbox.size());
    }
}
