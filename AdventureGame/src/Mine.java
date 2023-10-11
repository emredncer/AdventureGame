public class Mine extends BattleLocation{
    public Mine(Player player) {
        super(player, "Mine",6,new Snake(),5);
        System.out.println("Be careful, you will get random items from " +
                "the snakes you kill here, and even if the item you get is " +
                "worse than the one you already have, you will have to use it!");
    }
    public  boolean onLocation(){
        return super.onLocation();
    }
}
