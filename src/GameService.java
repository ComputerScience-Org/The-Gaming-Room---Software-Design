import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * GameService implemented as a Singleton.
 *
 * Singleton pattern explanation (for instructor comments):
 *  - Purpose: ensure only one instance of GameService exists in memory and provide a global access point.
 *  - Characteristics:
 *      * private constructor prevents external instantiation
 *      * private static instance holds the single instance
 *      * public static getInstance() returns the single instance
 *  - Why used here: the application must manage all active game instances centrally (IDs, list of games).
 *
 * Iterator pattern explanation (for instructor comments):
 *  - Purpose: iterate over the internal collection of games to find duplicates or search by name.
 *  - Characteristics:
 *      * uses Java's built-in Iterator<T> to traverse the List<Game>
 *      * keeps traversal logic decoupled from List implementation details
 *  - Why used here: when adding a new game we must check every existing game name to avoid duplicates.
 */
public class GameService {
    // List that holds Game objects (instance field)
    private final List<Game> games = new ArrayList<>();

    // nextGameId: shared unique id generator. Marked static so it's unique across any JVM-wide usage.
    private static long nextGameId = 1;

    // The singleton instance (eager initialization)
    private static final GameService instance = new GameService();

    // Private constructor prevents external instantiation.
    private GameService() { }

    /**
     * Returns the single GameService instance (global access point).
     */
    public static GameService getInstance() {
        return instance;
    }

    /**
     * Adds a new game if the name is not already in use.
     * Uses the Iterator pattern to traverse the games list and check duplication.
     *
     * @param name the name for the new game
     * @return the newly created Game, or null if name already exists
     */
    public synchronized Game addGame(String name) {
        // Iterator pattern: iterate through existing games to check for duplicate name
        Iterator<Game> it = games.iterator();
        while (it.hasNext()) {
            Game g = it.next();
            if (g.getName().equalsIgnoreCase(name)) {
                // Duplicate name found: do not create another instance with same name
                return null;
            }
        }

        // Unique name -> create and add
        Game newGame = new Game(nextGameId++, name);
        games.add(newGame);
        return newGame;
    }

    /**
     * Returns the game at the given index or null if out of range.
     */
    public synchronized Game getGame(int index) {
        if (index < 0 || index >= games.size()) return null;
        return games.get(index);
    }

    /**
     * Returns a game by name (case-insensitive). Uses Iterator pattern.
     */
    public synchronized Game getGame(String name) {
        Iterator<Game> it = games.iterator();
        while (it.hasNext()) {
            Game g = it.next();
            if (g.getName().equalsIgnoreCase(name)) {
                return g;
            }
        }
        return null;
    }

    /**
     * Returns number of games currently managed.
     */
    public synchronized int getGameCount() {
        return games.size();
    }

    /**
     * (Optional) Expose iterator if needed elsewhere.
     */
    public synchronized Iterator<Game> iterator() {
        return games.iterator();
    }
}
