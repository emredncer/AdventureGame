import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    //oyunun başladığı metot burası
    public void start() {
        System.out.println("Welcome to the uncharted island.");
        System.out.print("Please enter your nickname: ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println(player.getPlayerName() + ", your username has been set.");
        System.out.println("Now you have to choose a character! ");
        player.selectChar();
        while (true) {
            //location'a ilk değer verilmezse hata alınacaktır!
            Location location = null;
            int selectLocation;
            System.out.println("Please select map you want to go: ");
            System.out.println("1-Safe House \n" +
                    "2-Tool Store\n" +
                    "3-Cave\n" +
                    "4-Forest\n" +
                    "5-River\n" +
                    "6-Mine\n" +
                    "0-Exit Game");
            selectLocation = input.nextInt();
            switch (selectLocation) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    System.out.println("Please enter a valid key!");

            }
            if (location == null) {
                System.out.println("Thanks for playing. Good bye.");
                break;
            }

        if (!location.onLocation()) {
                System.out.println("Game over!!");
                break;
            }

        }

    }

}
