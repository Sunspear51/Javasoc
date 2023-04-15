import java.util.LinkedList;
import java.util.NoSuchElementException;

class UserInbox implements Inbox {
    LinkedList<Activity> inbox;

    //Constructor
    public UserInbox() {
        inbox = new LinkedList<Activity>();
    }

    //Methods
    @Override
    public boolean receive(Activity activity) {
        try {
            inbox.add(activity);
            return(true);
        } catch(Exception e) {
            return(false);
        }
    }

    @Override
    public Activity readNext() {
        try {
            Activity first = inbox.getFirst();
            inbox.removeFirst();
            return(first);
        } catch(NoSuchElementException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int getCount() {
        return(inbox.size());
    }
}