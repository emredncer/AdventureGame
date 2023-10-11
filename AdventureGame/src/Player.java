import java.util.Scanner;

public class Player {
    Scanner input = new Scanner(System.in);
    private Inventory inventory;
    private String playerName;
    private String charName;
    private int damage;
    private int health;
    private int money;
    private int originalHealth;
    private int originalDamage;

    public Player(String playerName) {
        this.playerName = playerName;
        this.inventory = new Inventory();
    }


    public void selectChar() {
        boolean isError = true;

        while (isError) {
            GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
            for (GameChar chars : charList) {
                System.out.print("ID: " + chars.getID() + "   Character: " + chars.getCharName() + "   damage: " + chars.getDamage() + " health: " + chars.getHealth() + "   money: " + chars.getMoney());
                System.out.println(" ");
            }
            System.out.println("---------------------------------");
            System.out.println("Please enter the ID number of the character you want to select: ");
            int select = input.nextInt();

            switch (select) {
                case 1:
                    initPlayer(new Samurai());
                    isError = false;
                    break;
                case 2:
                    initPlayer(new Archer());
                    isError = false;
                    break;
                case 3:
                    initPlayer(new Knight());
                    isError = false;
                    break;
                default:
                    System.out.println("Please enter a valid key.");
                    System.out.println("---------------------------------");
                    break;
            }
        }
    }

    public void initPlayer(GameChar gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setCharName(gameChar.getCharName());
        this.setMoney(gameChar.getMoney());
        this.setHealth(gameChar.getHealth());
        this.setOriginalHealth(gameChar.getHealth());
        this.setOriginalDamage(gameChar.getDamage());
        System.out.println("---------------------------------");
        System.out.println("You are a/an " + gameChar.getCharName() + " now!");
        System.out.println("Your damage: " + gameChar.getDamage() + " Your Health point: " + gameChar.getHealth() + " Your money: " + gameChar.getMoney());
    }

    public void printPlayerInfo() {
        System.out.println("Your health: " + this.getHealth());
        System.out.println("Your weapon: " + this.getInventory().getWeapon().getName());
        System.out.println("Your armor: " + this.getInventory().getArmor().getName());
        System.out.println("Your damage: " + this.getDamage());
        System.out.println("Your defense: " + this.getInventory().getArmor().getDefense());
        System.out.println("Your money: " + this.getMoney());
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getDamage() {
        return damage + this.inventory.getWeapon().getDamage();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getOriginalDamage() {
        return originalDamage;
    }

    public void setOriginalDamage(int originalDamage) {
        this.originalDamage = originalDamage;
    }

    public int getHealth() {
        if (health < 0)
            health = 0;
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }
}
