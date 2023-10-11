public class Armor {
    private int ID;
    private String name;
    private int defense;
    private int price;

    public Armor(int ID, String name, int defense, int price) {
        this.ID = ID;
        this.name = name;
        this.defense = defense;
        this.price = price;
    }
    public static Armor[] armors(){
        Armor[] armorList = new Armor[3];
        armorList[0] = new Armor(1,"Light Armor",1,15);
        armorList[1] = new Armor(2,"Middle Armor",3,25);
        armorList[2] = new Armor(3,"Heavy Armor",5,40);
        return armorList;
    }
    public static Armor getArmorObjByID(int id){
        for (Armor a : Armor.armors()){
            if (a.getID() == id){
                return a;
            }
        }
        return null;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
