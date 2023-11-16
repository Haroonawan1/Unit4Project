import java.util.Scanner;

public class OutlawRedemptionRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("   What is your name: ");
        String name = s.nextLine();
        OutlawRedemption outlaw = new OutlawRedemption(name);
        Images image = new Images();
        int dayCounter = 0;

        System.out.println("__________________________________________________________________________| Wild West Outlaw |_____________________________________________________________________________");
        System.out.println("   You, " + name + ", are part of an outlaw group that has recently been torn apart due to a heist gone wrong\n   and now you need to pay off the bounties of the members that have been caught before they are executed.\n   Gather money, buy upgrades, and help those in town, but keep an eye on your time as you are on the clock.\n");

        while(!outlaw.isGameOver()){
            System.out.print("   Actions (Days left " + (31 - outlaw.getDay()) + "):\n   1) Commit a crime\n   2) Help someone in need\n   3) View the store's catalogue\n   4) Check the jail\n   5) Check your stats\n   6) Help\n   7) Give up\n   > ");
            String action = s.nextLine();
            switch (action) {
                case "1" -> {
                    System.out.print("\n   What crime do you want to commit\n     - 1 for stagecoach robbery\n     - 2 for train robbery\n     - 3 for bank robbery\n   > ");
                    String robberyChoice = s.nextLine();
                    System.out.print("\n   What time will you attempt this: \n   (hours only, military time, example: 09:00)\n   > ");
                    String time = s.nextLine();
                    System.out.print((outlaw.isInputNumerical(robberyChoice)) ? "\n" + outlaw.commitCrime(Integer.parseInt(robberyChoice), time) : "\n   (The first input must be a number)");
                    dayCounter++;
                }
                case "2" -> {
                    System.out.print("\n   What good deed do you want to attempt\n   - 1 for giving someone a ride home\n   - 2 for donating money to the poor\n   - 3 for stopping a fight\n   > ");
                    String goodDeedChoice = s.nextLine();
                    if (outlaw.isInputNumerical(goodDeedChoice)){
                        if (goodDeedChoice.equals("2")) {
                            System.out.print("   How much do you want to donate: ");
                            int donation = Integer.parseInt(s.nextLine());
                            System.out.println(outlaw.doAGoodDeed(Integer.parseInt(goodDeedChoice), donation));
                        } else {
                            System.out.print
                                    (outlaw.doAGoodDeed(Integer.parseInt(goodDeedChoice), 0));
                        }
                    }
                    else {
                        System.out.println("\n   (You must input a numerical value)");
                    }
                    dayCounter++;
                }
                case "3" -> {
                    System.out.println(image.getCatalogueImage());
                    String keepShopping = "y";
                    while (keepShopping.equals("y")) {
                        System.out.print("\n   What item would you like to purchase (write the full name):\n   > ");
                        String item = s.nextLine();
                        System.out.print(outlaw.buyItem(item) + "\n\n   Would you like to keep on shopping (y/n): ");
                        keepShopping = s.nextLine();
                        if (!keepShopping.equals("n") || !keepShopping.equals("y")){
                            System.out.print("\n   (That was not an acceptable input)");
                        }
                    }
                }
                case "4" -> {
                    System.out.print(image.getJailImage() + "   What would you like to do:\n   - 1 for pay a bounty\n   - 2 to go back\n   > ");
                    String choice = s.nextLine();
                    switch (choice) {
                        case "1" -> {
                            System.out.print(outlaw.showBountyList() + "\n   > ");
                            String bountyChoice = s.nextLine();
                            switch (bountyChoice) {
                                case "1" -> System.out.println(outlaw.chooseBounty(1));
                                case "2" -> System.out.println(outlaw.chooseBounty(2));
                                case "3" -> System.out.println(outlaw.chooseBounty(3));
                                case "4" -> System.out.println(outlaw.chooseBounty(4));
                                case "5" -> System.out.println("   You leave the jail.");
                                default -> System.out.print("\n   (That is not an acceptable input)");
                            }
                        }
                        case "2" -> System.out.println("\n   You leave jail.");
                        default -> System.out.print("\n   (That is not an acceptable input)");
                    }
                }
                case "5" -> System.out.println(outlaw.getStats());
                case "6" -> System.out.print(outlaw.getHelp());
                case "7" -> System.out.println("\n" + outlaw.endGame("ended early"));
                default -> System.out.print("\n   (That is not an acceptable input)");
            }
            if (dayCounter % 2 == 0){
                outlaw.increaseDay();
            }
            if (outlaw.getDay() == 21){
                System.out.println("\n" + outlaw.endGame("day20"));
            }
            System.out.println(outlaw.executionCheck(dayCounter));
        }
    }
}