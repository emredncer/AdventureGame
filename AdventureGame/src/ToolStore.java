//Alışveriş yapılacak olan harita
public class ToolStore extends NormalLocation {

    public ToolStore(Player player) {
        super(player, "Tool Store",2);
    }

    @Override
    public boolean onLocation() {
        System.out.println("Welcome to Tool Store!");
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1 - For weapons");
            System.out.println("2 - For Armors");
            System.out.println("3 - For Exit");
            System.out.println();
            int selectedCase = input.nextInt();
            switch (selectedCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("You left the Tool Store.");
                    return true;
            }

        }
        return true;
    }

    //Weaponları basacak olan döngüyü içeren fonksiyon
    public void printWeapon() {
        System.out.println("----------------------------------");
        System.out.println("Weapon List: ");
        for (Weapon weaps : Weapon.weapons()) {
            System.out.println(weaps.getID() + "-> for "
                    + weaps.getName()
                    + " Damage: "
                    + weaps.getDamage()
                    + " Price: "
                    + weaps.getPrice());
        }
        System.out.println("0-> Exit weapon store.");
        System.out.print("Please enter the ID of the weapon you want to buy: ");

    }
    //Armorları basacak olan döngüyü içeren fonksiyon
    public void printArmor() {
        System.out.println("----------------------------------");
        System.out.println("Armor List: ");
        for (Armor arms : Armor.armors()) {
            System.out.println(arms.getID() + " for "
                    + arms.getName()
                    + " Defense: "
                    + arms.getDefense()
                    + " Price: "
                    + arms.getPrice());
        }
        System.out.println("0-> Exit armor store.");
        System.out.print("Please enter the ID of the armor you want to buy: ");
    }

    public void buyWeapon() {
        int selectedWeaponID = input.nextInt();
        while (selectedWeaponID < 0 || selectedWeaponID > Weapon.weapons().length) {
            System.out.print("Please enter a valid number: ");
            selectedWeaponID = input.nextInt();
        }
        if (selectedWeaponID != 0) {
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectedWeaponID);
            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Not enough money!");
                } else {
                    //satın alma işlemleri
                    System.out.println(selectedWeapon.getName() + " object purchased.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedWeapon.getPrice());
                    System.out.println("Your money: " + getPlayer().getMoney());
                    //Silahı değiştirdik.
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }
            }
        }
    }

    public void buyArmor() {
        int selectedArmorID = input.nextInt();
        while (selectedArmorID < 0 || selectedArmorID > Armor.armors().length) {
            System.out.print("Please enter a valid number: ");
            selectedArmorID = input.nextInt();
        }
        if (selectedArmorID != 0) {
            Armor selectedArmor = Armor.getArmorObjByID(selectedArmorID);
            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Not enough money!");
                } else {
                    //satın alma işlemleri
                    System.out.println(selectedArmor.getName() + " object purchased.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                    System.out.println("Your money: " + getPlayer().getMoney());
                    //Zırhı değiştirdik.
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                }
            }

        }
    }

}
