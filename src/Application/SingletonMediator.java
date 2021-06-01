package Application;

public class SingletonMediator {
    private static Mediator instance;

    public static Mediator getInstance() {
        if (instance == null) {
            instance = new Mediator() {
            };
        }
        return instance;
    }
}
