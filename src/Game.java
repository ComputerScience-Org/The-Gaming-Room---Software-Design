/**
 * Simple Game class (id + name).
 */
public class Game {
    private long id;
    private String name;

    public Game(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Game{id=" + id + ", name='" + name + "'}";
    }
}
