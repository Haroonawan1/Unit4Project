import java.util.Scanner;

public class WildWestOutlawRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("   What is your name: ");
        String name = s.nextLine();
        WildWestOutlaw outlaw = new WildWestOutlaw(name);
        Images i = new Images();
        int dayCounter = 0;

        System.out.println("_________________________________________________| Wild West Outlaw |_________________________________________________________");
        System.out.println("   You, " + name + ", are part of an outlaw group that has recently been torn apart due to a heist gone wrong\n   and now you need to pay off the bounties of the members that have been caught before they are executed.\n   Gather money, buy upgrades, and help those in town, but keep an eye on your time as you are on the clock." );

        while(!outlaw.isGameOver()){
            System.out.print("\n   Actions (Day " + outlaw.getDay() + "):\n   1) Commit a crime\n   2) Help someone in need\n   3) View the store's catalogue\n   4) Check the jail\n   5) Check your stats\n   6) Help\n   7) Give up\n   > ");
            String action = s.nextLine();
            switch (action) {
                case "1" -> {
                    System.out.print("\n   What crime do you want to commit\n     - 1 for stagecoach robbery\n     - 2 for train robbery\n     - 3 for bank robbery\n   > ");
                    String robberyChoice = s.nextLine();
                    System.out.print("\n   What time will you attempt this: \n   (hours only, military time, example: 09:00)\n   > ");
                    String time = s.nextLine();
                    System.out.println("\n" + outlaw.crime(Integer.parseInt(robberyChoice), time));
                }
                case "2" -> {
                    System.out.print("\n   What good deed do you want to attempt\n   - 1 for giving someone a ride home\n   - 2 for donating money to the poor\n   - 3 for stopping a fight\n   > ");
                    String goodDeedChoice = s.nextLine();
                    if (goodDeedChoice.equals("2")) {
                        System.out.print("   How much do you want to donate: ");
                        int donation = Integer.parseInt(s.nextLine());
                        System.out.println(outlaw.goodDeed(Integer.parseInt(goodDeedChoice), donation));
                    } else {
                        System.out.println(outlaw.goodDeed(Integer.parseInt(goodDeedChoice), 0));
                    }
                }
                case "3" -> {
                    System.out.println(i.catalogue());
                    String keepShopping = "y";
                    while (keepShopping.equals("y")) {
                        System.out.print("\n   What item would you like to purchase (write the full name):\n   > ");
                        String item = s.nextLine();
                        System.out.print(outlaw.shop(item) + "\n\n   Would you like to keep on shopping (y/n): ");
                        keepShopping = s.nextLine();
                    }
                }
                case "4" -> {
                    System.out.print(i.jail() + "   What would you like to do:\n   - 1 for pay a bounty\n   - 2 to go back\n   > ");
                    String choice = s.nextLine();
                    if (choice.equals("1")) {
                        System.out.print(i.bountyList() + "   > ");
                        String bountyChoice = s.nextLine();
                        switch (bountyChoice) {
                            case "1" -> System.out.println(outlaw.payBounty(1));
                            case "2" -> System.out.println(outlaw.payBounty(2));
                            case "3" -> System.out.println(outlaw.payBounty(3));
                            case "4" -> System.out.println(outlaw.payBounty(4));
                            case "5" -> System.out.println("   You leave the jail.");
                        }
                    } else if (choice.equals("2")) {
                        System.out.println("\n   You leave jail.");
                    } else {
                        System.out.println("That is not an accepted output");
                    }
                }
                case "5" -> System.out.println(outlaw.getStats());
                case "6" -> System.out.println(outlaw.help());
                case "7" -> System.out.println(outlaw.ending("ended early"));
                default -> System.out.println("\n   That is not an acceptable input");
            }
            dayCounter++;
            if (dayCounter % 2 == 0){
                outlaw.increaseDay();
            }
        }
    }
}