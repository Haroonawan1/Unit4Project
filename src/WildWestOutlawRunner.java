import java.util.Scanner;

public class WildWestOutlawRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        WildWestOutlaw outlaw = new WildWestOutlaw("Haroon");
        String name = outlaw.getName();

        System.out.println("_________________________________________________| Wild West Outlaw |_________________________________________________________");
        System.out.println("   You, " + name + ", are part of an outlaw group that has recently been torn apart due to a heist gone wrong\n   and now you need to pay off the bounties of the members that have been caught before they are executed.\n   Gather money, buy upgrades, and help those in town, but keep an eye on your time as you are on the clock." );

        while(!outlaw.isGameOver()){
            System.out.print("\n   Actions:\n   1) Commit a crime\n   2) Help someone in need\n   3) Go the a nearby shop\n   4) Check your stats\n   5) Help\n   6) Give up\n   > ");
            String action = s.nextLine();
            if (action.equals("1")){
                System.out.print("\n   What crime do you want to commit\n     - 1 for stagecoach robbery\n     - 2 for train robbery\n     - 3 for bank robbery\n   > ");
                String robberyChoice = s.nextLine();
                System.out.print("   What time will you attempt this (XX:XX format, hours only, military time): ");
                String time = s.nextLine();
                System.out.println();
                System.out.println(outlaw.crime(Integer.parseInt(robberyChoice), time));
            }
            else if (action.equals("2")){

            }
            else if (action.equals("3")){

            }
            else if (action.equals("4")){
                System.out.println(outlaw.getStats());
            }
            else if (action.equals("5")){
                System.out.println(outlaw.help());
            }
            else if (action.equals("6")){

            }
            else {
                System.out.println("\n   That is not an acceptable input");
            }
        }
    }
}
