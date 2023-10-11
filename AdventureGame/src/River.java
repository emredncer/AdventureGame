public class River extends BattleLocation{
    public River(Player player) {
        super(player, "River",5,new Bear(),3);
    }
    public boolean onLocation(){
        return super.onLocation();
    }

}
