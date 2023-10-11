import java.util.Random;
//Savaş özelliği olan tüm sınıfların ata sınıfı bu sınıf olacak.
public abstract class BattleLocation extends Location {
    private Mob mob;
    //Haritaya özgü mob limitini tutacak variable
    private int maxMob;

    public BattleLocation(Player player, String name, int ID, Mob mob, int maxMob) {
        super(player, "Battle Location", -1);
        this.mob = mob;
        this.maxMob = maxMob;
    }

    @Override
    public boolean onLocation() {
        //Haritaya Giriş izni kontrolü
        if (accessBlock()) {
            return true;
        } else {
            int mobCount = this.randomMobCreator();
            System.out.println("You are in " + this.getName() + " now!!");
            System.out.println("Be careful! There are " + mobCount + " " + this.getMob().getName() + "s lives in the area!");
            if (combat(mobCount)) {
                System.out.println("There are no more enemies in the " + this.getName());
                return true;
            }
            if (this.getPlayer().getHealth() <= 0) {
                System.out.println("You're dead!");
                return false;
            }
        }
        return true;
    }
    //Savaşma mekanizması bu metot ile sağlandı
    public boolean combat(int mobCount) {
        for (int i = 1; i <= mobCount; i++) {
            Random rnd = new Random();
            this.getMob().setHealth(this.getMob().getOriginalHealth());
            int firstMove = rnd.nextInt(2);
            playerStats();
            mobStats(i);
            //İlk vuruşu player'ın yapması durumu
            if (firstMove == 0) {
                System.out.println(this.getPlayer().getPlayerName() + " will do the first move!");
                while (this.getPlayer().getHealth() > 0 && this.getMob().getHealth() > 0) {
                    System.out.print("Press 'F' for Fight, Press anything else for RUN: ");
                    //...nextLine() şeklinde girdi alındığında her zamam doğru çalışmayabiliyor!
                    String selectCombatCase = input.next();
                    selectCombatCase = selectCombatCase.toUpperCase();

                    //Savaş ya da kaç sorgusu
                    if (selectCombatCase.equals("F")) {
                        this.getMob().setHealth(this.getMob().getHealth() - this.getPlayer().getDamage());
                        System.out.println("You hit the " + this.getMob().getName());
                        afterHit();

                        if (this.getMob().getHealth() > 0) {
                            System.out.println(this.getMob().getName() + " hit you!");

                            //eğer gelen Mob bir Snake ise ona özgü hasar hesaplayacak metodu çağıracak.
                            if (this.getMob() instanceof Snake) {
                                this.getMob().setDamage(generateSnakeDamage());
                            }

                            int mobDamage = (this.getMob().getDamage()) - (this.getPlayer().getInventory().getArmor().getDefense());

                            if (mobDamage < 0)
                                mobDamage = 0;

                            this.getPlayer().setHealth(this.getPlayer().getHealth() - mobDamage);
                            if (this.getPlayer().getHealth() <= 0)
                                return false;
                            afterHit();
                        }
                    } else {
                        return false;
                    }
                }

                //Mob'un ilk vuruşu yapma durumu.
            } else {
                System.out.println(this.getMob().getName() + " will do the first move!");
                while (this.getPlayer().getHealth() > 0 && this.getMob().getHealth() > 0) {
                    System.out.print("Press 'F' for Fight, Press anything else for RUN: ");

                    //...nextLine() şeklinde girdi alındığında her zamam doğru çalışmayabiliyor!
                    String selectCombatCase = input.next();
                    selectCombatCase = selectCombatCase.toUpperCase();

                    if (selectCombatCase.equals("F")) {
                        if (this.getMob() instanceof Snake) {
                            this.getMob().setDamage(generateSnakeDamage());
                        }
                        int mobDamage = (this.getMob().getDamage()) - (this.getPlayer().getInventory().getArmor().getDefense());

                        if (mobDamage < 0)
                            mobDamage = 0;

                        System.out.println(this.getMob().getName() + " hit you!");
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - mobDamage);
                        afterHit();
                        System.out.println("-------------------------");
                        if (this.getPlayer().getHealth() <= 0) {
                            return false;
                        }
                        System.out.println("You hit the " + this.getMob().getName());
                        this.getMob().setHealth(this.getMob().getHealth() - this.getPlayer().getDamage());
                        afterHit();
                    } else
                        return false;
                }
            }

            if (this.getMob().getHealth() <= 0) {
                System.out.println(i + ". " + getMob().getName() + " is dead.");
                if (this.getMob() instanceof Snake) {
                    generateRandomSnakeDrop();
                    playerStats();
                } else {
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMob().getAward());
                    System.out.println("You earned " + this.getMob().getAward() + " gold.");
                    System.out.println("Your money is " + this.getPlayer().getMoney() + " now.");
                }

            }
        }

        //Tüm moblar öldüğünde haritaya özgü hediyeyi verecek olan metodu çağır.
        giveAward();
        return true;
    }

    //Yılanın hasarını belirleyecek olan fonksiyon!
    public int generateSnakeDamage() {
        Random rnd = new Random();
        return rnd.nextInt(4) + 3;
    }

    //Snake'ten rastgele gelecek olan eşyaların hesaplanması bu metot ile yapıldı
    public void generateRandomSnakeDrop() {
        Random snakeRnd = new Random();
        int chance = snakeRnd.nextInt(101);
        if ((chance >= 0) && (chance < 15)) {
            int rndWeapon = snakeRnd.nextInt(101);
            if ((rndWeapon >= 0) && (rndWeapon < 50)) {
                System.out.println("You won a Gun");
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
            } else if ((rndWeapon >= 50) && (rndWeapon < 80)) {
                System.out.println("You won a Sword");
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));
            } else {
                System.out.println("You won a Rifle");
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(3));
            }
        } else if ((chance >= 15) && (chance < 30)) {
            int rndArmor = snakeRnd.nextInt(101);
            if ((rndArmor >= 0) && (rndArmor < 50)) {
                System.out.println("You won a Light Armor");
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(1));
            } else if ((rndArmor >= 50) && (rndArmor < 80)) {
                System.out.println("You won a Middle Armor");
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(2));
            } else {
                System.out.println("You won a Heavy Armor");
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(3));
            }
        } else if ((chance >= 30) && (chance < 55)) {
            int rndMoney = snakeRnd.nextInt(101);
            if ((rndMoney >= 0) && (rndMoney < 50)) {
                System.out.println("You earned 1 gold.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
            } else if ((rndMoney >= 50) && (rndMoney < 80)) {
                System.out.println("You earned 5 gold.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
            } else {
                System.out.println("You earned 10 gold.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
            }

        } else {
            System.out.println("There was nothing inside the snake..");
        }
    }


    //Bitirildiğinde hediye veren haritalar için, hediyeyi verecek metot.
    public void giveAward() {
        if (this instanceof Cave) {
            this.getPlayer().getInventory().setFood(true);
            System.out.println("You obtained Food.");
        } else if (this instanceof Forest) {
            this.getPlayer().getInventory().setFirewood(true);
            System.out.println("You obtained Firewood.");
        } else if (this instanceof River) {
            this.getPlayer().getInventory().setWater(true);
            System.out.println("You obtained Water.");
        }
    }

    //haritaya giriş izinlerini sorgulayacak metot.
    public boolean accessBlock() {
        if (this instanceof Cave && this.getPlayer().getInventory().isFood()) {
            System.out.println("You have already finished the Cave.");
            return true;
        } else if (this instanceof Forest && this.getPlayer().getInventory().isFirewood()) {
            System.out.println("You have already finished the Forest.");
            return true;
        } else if (this instanceof River && this.getPlayer().getInventory().isWater()) {
            System.out.println("You have already finished the River.");
            return true;
        } else {
            return false;
        }
    }

    //Player'ın istatistikleri bu metot ile yazdırılacak
    public void playerStats() {
        System.out.println("-------------------------");
        System.out.println("Your Stats");
        System.out.print("Health: " + this.getPlayer().getHealth());
        System.out.print("  Your Weapon: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.print("  Your Damage: " + this.getPlayer().getDamage());
        System.out.print("  Your Armor: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.print("  Your Defense: " + this.getPlayer().getInventory().getArmor().getDefense());
        System.out.println("  Your Money: " + this.getPlayer().getMoney());
        System.out.println("-------------------------");

    }

    //Mobun istatistiklerini yazdıran metot.
    public void mobStats(int i) {
        System.out.println("-------------------------");
        System.out.println("Your enemy " + this.getMob().getName() + " Stats");
        System.out.print("  " + i + ". " + this.getMob().getName() + "'s Health: " + this.getMob().getHealth());
        System.out.println("  " + this.getMob().getName() + "'s Damage: " + this.getMob().getDamage());
        System.out.println("-------------------------");

    }

    //Combatta her turdan sonra istatistikleri yazacak olan metot
    public void afterHit() {
        System.out.println("-------------------------");
        System.out.println("Your Health: " + this.getPlayer().getHealth());
        System.out.println(this.getMob().getName() + " Health: " + this.getMob().getHealth());
        System.out.println();
    }

    //Haritada oluşturulacak mobları hesaplayan metot
    public int randomMobCreator() {
        Random rnd = new Random();
        return rnd.nextInt(this.getMaxMob()) + 1;
    }

    public Mob getMob() {
        return mob;
    }

    public void setMob(Mob mob) {
        this.mob = mob;
    }


    public int getMaxMob() {
        return maxMob;
    }

    public void setMaxMob(int maxMob) {
        this.maxMob = maxMob;
    }

}
