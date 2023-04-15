public class ClientApp implements App {
    private Person user;

    @Override
    public Inbox getInbox() {
        return user.getInbox();
    }

    @Override
    public Outbox getOutbox() {
        return user.getOutbox();
    }

    @Override
    public String demo() {
        user = new Person("Dermot", "Born in 1995");
        Person userTwo = new Person("Tom", "Born in 2000");

        //Create Messages
        System.out.println("\n\tSending Check...");
        user.draft(new CreateActivity(user, userTwo, "Hello World"));
        user.sendLatest();

        userTwo.readLatest();

        //Delete Messages
        System.out.println("\n\tDeleting Check...");
        userTwo.draft(new DeleteActivity(userTwo, user));
        userTwo.sendLatest();

        user.readLatest();

        //Test Summary
        System.out.println("\tSummary Check...");
        System.out.println("Summary: " + user.getSummary());

        //Following function
        System.out.println("\n\tFollowing Check...");
        user.draft(new FollowActivity(user, userTwo));
        user.sendLatest();
        System.out.println("First User is following: " + user.getFollowing());
        System.out.println("Second User's followers: " + userTwo.getFollower());

        userTwo.readLatest();
        System.out.println("Second User's followers: " + userTwo.getFollower());

        //Unfollowing function
        System.out.println("\n\tUnfollowing Check...");
        user.draft(new UnfollowActivity(user, userTwo));
        user.sendLatest();
        System.out.println("First User is following: " + user.getFollowing());
        System.out.println("Second User's followers: " + userTwo.getFollower());

        userTwo.readLatest();
        System.out.println("Second User's followers: " + userTwo.getFollower());

        //Liking function
        System.out.println("\n\tLiking Users Check...");
        user.draft(new LikeActivity(user, userTwo));
        user.sendLatest();
        userTwo.readLatest();
        System.out.println("Second User likes: " + userTwo.getLiked());

        //Disliking function
        System.out.println("\n\tDisliking Users Check...");
        user.draft(new LikeActivity(user, userTwo));
        user.sendLatest();
        userTwo.readLatest();
        System.out.println("Second User dislikes: " + userTwo.getDisliked());

        //Messages function
        System.out.println("\n\tMessages Check...");
        userTwo.draft(new LikeActivity(userTwo, user, "liked you!"));
        userTwo.sendLatest();
        user.readLatest();
        System.out.println("First User likes: " + user.getLiked());

        // Inbox & Outbox Stacking
        System.out.println("\n\tInbox & Outbox Stacking Test!");
        user.draft(new CreateActivity(user, userTwo, "1"));
        user.draft(new CreateActivity(user, userTwo, "2"));
        user.draft(new CreateActivity(user, userTwo, "3"));

        user.sendLatest();
        user.sendLatest();
        user.sendLatest();

        userTwo.readLatest();
        userTwo.readLatest();
        userTwo.readLatest();

        return("\n\nFinished!");
    }

    public static void main(String[] args) {
        ClientApp app = new ClientApp();
        System.out.println(app.demo());
    }
}
