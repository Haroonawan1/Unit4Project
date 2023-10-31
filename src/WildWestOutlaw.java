import javax.print.attribute.standard.PrinterMakeAndModel;

public class WildWestOutlaw {
    private String name;
    private int health;
    private int money;
    private int honor;
    private int day;
    private int saved;
    private boolean dead;
    private int damage = 10;

    public WildWestOutlaw (String n){
        name = n;
        health = 100;
        money = 0;
        honor = 0;
        day = 1;
        saved = 0;
        dead = false;
    }

    public String help(){
        String str = "\nHELP:\n 1) The objective of the game is to raise enough money in order to pay off the bounty of your fellow gang members";
        str += "\n 2) You can obtain money by doing crimes (some are harder than others), however this will affect your honor";
        str += "\n 3) You can improve your honor by doing good deeds, thankful people may help you";
        str += "\n 4) Go to the shop to heal and get better weapons to improve your chances in fights";
        str += "\n 5) Save your members before they are hanged, each member has a certain amount of days until they are hanged";
        str += "\n 6) Your honor and the amount of people you have saved will effect your ending\n 7) GOOD LUCK!\n";
        return str;
    }

    public boolean getDead(){
        return dead;
    }

    public String fight(int x){
        String str = "";
        while (x > 0){
            int bandit = 50;
            int lawEnforcer = 75;
            int bountyHunter = 100;
            int random = (int) (Math.random() * 3) + 1;

            if (random == 1){
                str += "\nYou begin fighting a Bandit\n";
                while (health > 0 && bandit > 0) {
                    bandit -= damage;
                    health -= 1;
                }
                if (bandit <= 0){
                    str += "You won your fight against the Bandit!\nRemaining Health: " + health + "\n";
                }
                if (health <= 0){
                    dead = true;
                    //call ending(x), x is the parameter that prints death in battle ending
                }
            }
            if (random == 2){
                str += "\nYou begin fighting a Law Enforcer\n";
                while (health > 0 && lawEnforcer > 0) {
                    lawEnforcer -= damage;
                    health -= 2;
                }
                if (lawEnforcer <= 0){
                    str += "You won your fight against the Law Enforcer!\nRemaining Health: " + health + "\n";
                }
                if (health <= 0){
                    dead = true;
                    //call ending(x), x is the parameter that prints death in battle ending
                }
            }
            if (random == 3){
                str += "\nYou begin fighting a Bounty Hunter\n";
                while (health > 0 && bountyHunter > 0) {
                    bountyHunter -= damage;
                    health -= 4;
                }
                if (bountyHunter <= 0){
                    str += "You won your fight against the Bounty Hunter!\nRemaining Health: " + health + "\n";
                }
                if (health <= 0){
                    dead = true;
                    //call ending(x), x is the parameter that prints death in battle ending
                }
            }
            x--;
        }
        return str;
    }


    public String stageCoach(){
        String str = "";
        int moneyFound = 0;
        if ((int) (Math.random() * 10) + 1 > 3){
            int random = (int) (Math.random() * 3) + 1;
            str += "As you ride down a pathway you find a stagecoach. ";
            if (random == 1){
                str += "The stagecoach you find only has a driver and a few passengers, this should be an easy target, lucky you.\n";
                moneyFound = (int) (Math.random() * 10) + 10;
                str += fight(2);
            }
            else if (random == 2){
                str += "The stagecoach is not unguarded but a few men should not be a problem for a legend like you.\n";
                moneyFound = (int) (Math.random() * 30) + 20;
                str += fight(3);
            }
            else {
                str += "The stagecoach is heavily guarded, this will be a tough fight but all those men must be guarding something valuable.\n";
                moneyFound = (int) (Math.random() * 50) + 50;
                str += fight(4);
            }
        }
        else {
            str += "You search for a stagecoach and find nothing interesting except for 10 dollars on the floor.";
            moneyFound += 10;
        }
        honor -= 5;
        return str + "\n Money found: " + moneyFound;
    }

    public String crime(int x){
        int moneyFound = 0;
        String str = "";
        if (x == 1){
            str = stageCoach();
            moneyFound = Integer.parseInt(str.substring(str.length() - 2));
        }
        if (x == 2){

        }
        if (x == 3){

        }
        money += moneyFound;
        return str;
    }
}
