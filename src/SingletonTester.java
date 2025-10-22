/**
 * Simple tester for singleton behavior.
 */
public class SingletonTester {

    public static void testSingleton() {
        System.out.println("=== SingletonTester: start ===");

        GameService s1 = GameService.getInstance();
        GameService s2 = GameService.getInstance();

        // They should be the same instance (reference equality)
        System.out.println("Singleton test: s1 == s2? " + (s1 == s2));

        // Add games via s1 / s2 and show that both references mutate the same underlying state
        Game g1 = s1.addGame("Battle");
        Game g2 = s2.addGame("Battle"); // should be rejected (duplicate name) -> null
        Game g3 = s1.addGame("Adventure");

        System.out.println("Added g1: " + g1);
        System.out.println("Attempt duplicate g2: " + g2);
        System.out.println("Added g3: " + g3);

        System.out.println("Total games (from s1): " + s1.getGameCount());
        System.out.println("Get by name 'Adventure': " + s1.getGame("Adventure"));

        System.out.println("=== SingletonTester: end ===");
    }
}
