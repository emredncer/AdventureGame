public abstract class NormalLocation extends Location {
    public NormalLocation(Player player, String name, int ID) {
        super(player,"Normal Location",-1);
    }

    @Override
    public boolean onLocation() {
        //bu haritada ölme şansım olmadığından her zaman true dönecek.
        return true;
    }
}
