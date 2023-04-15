import java.util.LinkedList;

class Person {
    private final String name;
    private String username;
    private String summary;
    private LinkedList<Person> liked;
    private LinkedList<Person> disliked;
    private LinkedList<Person> following;
    private LinkedList<Person> follower;
    private UserInbox inbox;
    private UserOutbox outbox;

    // Constructors
    Person(String name, String username, String summary) {
        this.name = name;
        this.username = username;
        this.summary = summary;
        liked = new LinkedList();
        disliked = new LinkedList();
        following = new LinkedList();
        follower = new LinkedList();
        inbox = new UserInbox();
        outbox = new UserOutbox();
    }

    // Getters & Setters
    public String toString() {
        return username;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getSummary() {
        return summary;
    }

    public LinkedList<Person> getLiked() {
        return liked;
    }

    public LinkedList<Person> getDisliked() {
        return disliked;
    }

    public LinkedList<Person> getFollowing() {
        return following;
    }

    public LinkedList<Person> getFollower() {
        return follower;
    }

    public Inbox getInbox() {
        return inbox;
    }

    public Outbox getOutbox() {
        return outbox;
    }

    // Methods
    public boolean draft(Activity activity) {
        try {
            outbox.send(activity);
            return(true);
        }
        catch(Exception e) {
            return(false);
        }
    }

    public boolean sendLatest() {
        try {
            outbox.deliverNext().send();
            return(true);
        }
        catch(Exception e) {
            return(false);
        }
    }

   public boolean readLatest() {
        try {
            inbox.readNext().open();
            return(true);
        }
        catch(Exception e) {
            return(false);
        }
    }
}
