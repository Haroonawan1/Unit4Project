import java.util.Scanner;

public class WildWestOutlawRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        WildWestOutlaw outlaw = new WildWestOutlaw("har");
        Images i = new Images();
        String name = outlaw.getName();

        System.out.println("_________________________________________________| Wild West Outlaw |_________________________________________________________");
        System.out.println("   You, " + name + ", are part of an outlaw group that has recently been torn apart due to a heist gone wrong\n   and now you need to pay off the bounties of the members that have been caught before they are executed.\n   Gather money, buy upgrades, and help those in town, but keep an eye on your time as you are on the clock." );

        while(!outlaw.isGameOver()){
            System.out.print("\n   Actions:\n   1) Commit a crime\n   2) Help someone in need\n   3) View the store's catalogue\n   4) Check your stats\n   5) Help\n   6) Give up\n   > ");
            String action = s.nextLine();
            if (action.equals("1")){
                System.out.print("\n   What crime do you want to commit\n     - 1 for stagecoach robbery\n     - 2 for train robbery\n     - 3 for bank robbery\n   > ");
                String robberyChoice = s.nextLine();
                System.out.print("\n   What time will you attempt this: \n   (hours only, military time, example: 09:00)\n   > ");
                String time = s.nextLine();
                System.out.println();
                System.out.println(outlaw.crime(Integer.parseInt(robberyChoice), time));
            }
            else if (action.equals("2")){
                System.out.print("\n   What good deed do you want to attempt\n   - 1 for giving someone a ride home\n   - 2 for donating money to the poor\n   - 3 for stopping a fight\n   > ");
                String goodDeedChoice = s.nextLine();
                if (goodDeedChoice.equals("2")){
                    System.out.print("   How much do you want to donate: ");
                    int donation = Integer.parseInt(s.nextLine());
                    System.out.println(outlaw.goodDeed(Integer.parseInt(goodDeedChoice), donation));
                }
                else {
                    System.out.println(outlaw.goodDeed(Integer.parseInt(goodDeedChoice), 0));
                }
            }
            else if (action.equals("3")){
                System.out.println(i.catalogue());
                String keepShopping = "y";
                while (keepShopping.equals("y")){
                    System.out.print("\n   What item would you like to purchase (write the full name):\n   > ");
                    String item = s.nextLine();
                    System.out.println(outlaw.shop(item));
                    System.out.print("\n   Would you like to keep on shopping (y/n): ");
                    keepShopping = s.nextLine();
                }
            }
            else if (action.equals("4")){
                System.out.println(outlaw.getStats());
            }
            else if (action.equals("5")){
                System.out.println(outlaw.help());
            }
            else if (action.equals("6")){
                System.out.println(outlaw.ending("ended early"));
            }
            else {
                System.out.println("\n   That is not an acceptable input");
            }
        }
    }
}
