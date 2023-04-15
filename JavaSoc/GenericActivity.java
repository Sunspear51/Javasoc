import java.io.Serializable;

public interface Activity {
    public Person getSender();
    public Person getReceiver();
    public StreamObject getStream();
    public boolean send();
    public boolean open();
}

public class StreamObject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String content;

    //Constructor
    public StreamObject(String content) {
        this.content = content;
    }

    //Getter
    public String getContent() {
        return content;
    }
}

public class Inbox implements Serializable {
    private static final long serialVersionUID = 1L;

    private Person owner;
    private ArrayList<Activity> activities;

    //Constructor
    public Inbox(Person owner) {
        this.owner = owner;
        this.activities = new ArrayList<Activity>();
    }

    //Getters
    public Person getOwner() {
        return owner;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    //Methods
    public boolean receive(Activity activity) {
        activities.add(activity);
        return true;
    }
}

public abstract class GenericActivity implements Activity, Serializable {
    private static final long serialVersionUID = 1L;
    
    private Person sender;
    private Person receiver;
    private StreamObject stream;

    //Constructor
    public GenericActivity(Person sender, Person receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.stream = new StreamObject(content);
    }

    //Getters
    @Override
    public Person getSender() {
        return sender;
    }

    @Override
    public Person getReceiver() {
        return receiver;
    }

    @Override
    public StreamObject getStream() {
        return stream;
    }

    //Methods
    @Override
    public boolean send() {
        try {
            receiver.getInbox().receive(this);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    public boolean open() {
        try {
            System.out.println(sender + ": " + stream.getContent());
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}


class Like extends GenericActivity {

    //Constructors
    public Like(Person sender, Person receiver, String content) {
        super(sender, receiver, content);
    }

    public Like(Person sender, Person receiver) {
        super(sender, receiver, sender + " likes your profile!");
    }

    //Method
    @Override
    public boolean open() {
        receiver.getLiked().add(sender);
        return super.open();
    }
}

class Dislike extends GenericActivity {

    //Constructors
    public Dislike(Person sender, Person receiver, String content) {
        super(sender, receiver, content);
    }

    public Dislike(Person sender, Person receiver) {
        super(sender, receiver, sender + " dislikes your profile!");
    }

    //Method
    @Override
    public boolean open() {
        receiver.getDisliked().add(sender);
        return super.open();
    }
}

class Follow extends GenericActivity {

    public Follow(Person sender, Person receiver, String content) {
        super(sender, receiver, content);
    }

    //Constructor
    public Follow(Person sender, Person receiver) {
        super(sender, receiver, sender + " is following you!");
    }

    @Override
    public boolean send() {
        if(sender.getFollowing().contains(receiver)) { // If you're already following
            System.out.println("You're following " + receiver + "!");
            return false;
        }
        else {
            sender.getFollowing().add(receiver);
            return super.send();
        }
    }
}

public class Person implements Serializable