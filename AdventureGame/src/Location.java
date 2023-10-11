import java.util.Scanner;

public abstract class Location {
    private int ID;
    private Player player;
    private String name;
    protected static Scanner input = new Scanner(System.in);

    public Location(Player player,String name, int ID) {
        this.name = name;
        this.player = player;
        this.ID = ID;
    }

    //onLocation metodu alt sınıfların hepsinde kullanılsın istediğimden abstract tanımlandı.
    public abstract boolean onLocation();

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
