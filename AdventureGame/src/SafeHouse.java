public class SafeHouse extends NormalLocation{
    public SafeHouse(Player player) {
        super(player, "Safe House",1);
    }
    public boolean onLocation(){
        this.getPlayer().setHealth(this.getPlayer().getOriginalHealth());
        if (isWin()){
            System.out.println("Congrats!! You won!!");
            return false;
        }else {
            System.out.println("You are in Safe House now. Your health point is full.");
            return true;
        }
    }

    //oyunun başarıyla tamamlanmasını sağlayacak metot
    public boolean isWin(){
        if (this.getPlayer().getInventory().isWater() && this.getPlayer().getInventory().isFood() && this.getPlayer().getInventory().isFirewood()){
            return true;
        }else
            return false;
    }

}
