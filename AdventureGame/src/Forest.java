public class Forest extends BattleLocation{
    public Forest(Player player) {
        super(player, "Forest",4,new Vampire(),3);
    }
    public boolean onLocation(){
        return super.onLocation();
    }

}
