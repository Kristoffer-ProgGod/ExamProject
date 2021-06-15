package Application;

public class SingletonMediator {
    private static Mediator instance;

    /*
    Gets the current mediator instance so we are sure that only one is used
    If no instance exists yet a new Mediator instance is created
     */
    public static Mediator getInstance() {
        if (instance == null) {
            instance = new Mediator() {
            };
        }
        return instance;
    }
}
