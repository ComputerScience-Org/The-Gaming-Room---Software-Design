/**
 * ProgramDriver: entry point. Demonstrates use of SingletonTester and simple listing.
 */
public class ProgramDriver {
    public static void main(String[] args) {
        // Run the singleton/iterator demo
        SingletonTester.testSingleton();

        // Show the list of games by index
        GameService gs = GameService.getInstance();
        System.out.println("\nList games by index:");
        for (int i = 0; i < gs.getGameCount(); i++) {
            System.out.println("Index " + i + ": " + gs.getGame(i));
        }
    }
}
