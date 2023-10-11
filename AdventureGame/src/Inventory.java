public class Inventory {
    //yalnızca bir weapon ve bir armor tutulacak alınacak ya da kazanılacak nesneler bunların üstüne basılacak.
    private Weapon weapon;
    private Armor armor;

    //kazanmamız için gerekli olan şeyleri boolean olarak tanımladım
    private boolean food=false;
    private boolean firewood=false;
    private boolean water=false;

    public Inventory() {
        this.weapon = new Weapon(-1,"Punch",0,0);
        this.armor = new Armor(-1,"T-shirt",0,0);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isFirewood() {
        return firewood;
    }

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }
}
