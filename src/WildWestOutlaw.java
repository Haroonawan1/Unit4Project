public class WildWestOutlaw {
    private String name;
    private int health;
    private int maxHealth;
    private int money;
    private int honor;
    private int day;
    private int saved;
    private boolean gameOver;
    private int damage = 10;

    Images i = new Images();

    public WildWestOutlaw (String n){
        name = n;
        health = 100;
        maxHealth = 100;
        money = 99999990;
        honor = 0;
        day = 1;
        saved = 0;
        gameOver = false;
    }



    public String help(){
        String str = "   _____________________________________________________________________________________________________________________";
        str += "\n    HELP:\n    1) The objective of the game is to raise enough money in order to pay off the bounty of your fellow gang members";
        str += "\n    2) You can obtain money by doing crimes (some are harder than others), however this will affect your honor";
        str += "\n    3) You can improve your honor by doing good deeds, thankful people may help you";
        str += "\n    4) Go to the shop to heal and get better weapons to improve your chances in fights";
        str += "\n    5) Save your members before they are hanged, each member has a certain amount of days until they are hanged";
        str += "\n    6) Your honor and the amount of people you have saved will effect your ending\n    7) GOOD LUCK!";
        str += "\n   _____________________________________________________________________________________________________________________";
        return str;
    }

    public boolean isGameOver(){
        return gameOver;
    }



    public String getName(){
        return name;
    }



    public String getStats(){
        String str = "";
        str += "\n    Your current statistics:\n";
        str += "    Health: " + health + "/" + maxHealth + "\n";
        str += "    Money: " + money + "\n    Honor: " + honor + "\n    Damage: " + damage + "\n    People Saved: " + saved;
        str += "\n   ____________________________";
        return str;
    }



    public String fight(int x){
        String str = "";
        boolean done = false;
        while (x > 0 && !gameOver){
            int bandit = 50;
            int lawEnforcer = 75;
            int bountyHunter = 100;
            int random = (int) (Math.random() * 3) + 1;

            if (random == 1){
                str += "\n   You begin fighting a Bandit\n";
                while (health > 0 && bandit > 0) {
                    bandit -= damage;
                    health -= 1;
                }
                if (bandit <= 0){
                    str += "   You won your fight against the Bandit!\n   Remaining Health: " + health + "\n";
                }
                if (health <= 0){
                    str += ending("died in battle");
                    gameOver = true;
                }
            }
            if (random == 2){
                str += "\n   You begin fighting a Law Enforcer\n";
                while (health > 0 && lawEnforcer > 0) {
                    lawEnforcer -= damage;
                    health -= 2;
                }
                if (lawEnforcer <= 0){
                    str += "   You won your fight against the Law Enforcer!\n   Remaining Health: " + health + "\n";
                }
                if (health <= 0){
                    str += ending("died in battle");
                    gameOver = true;
                }
            }
            if (random == 3){
                str += "\n   You begin fighting a Bounty Hunter\n";
                while (health > 0 && bountyHunter > 0) {
                    bountyHunter -= damage;
                    health -= 4;
                }
                if (bountyHunter <= 0){
                    str += "   You won your fight against the Bounty Hunter!\n   Remaining Health: " + health + "\n";
                }
                if (health <= 0){
                    str += ending("died in battle");
                    gameOver = true;
                }
            }
            x--;
        }
        return str;
    }



    public String stageCoach(){
        String str = i.stageCoach();
        int moneyFound = 0;
        if ((int) (Math.random() * 10) + 1 > 3){
            int random = (int) (Math.random() * 10) + 1;
            str += "\n   As you ride down a pathway you find a stagecoach.\n";
            if (random <= 4){
                str += "   The stagecoach you find only has a driver and a few passengers, this should be an easy target, lucky you.\n";
                moneyFound = (int) (Math.random() * 10) + 10;
                str += fight(2);
            }
            else if (random <= 8){
                str += "\n   The stagecoach is not unguarded but a few men should not be a problem for a legend like you.\n";
                moneyFound = (int) (Math.random() * 30) + 20;
                str += fight(3);
            }
            else {
                str += "   The stagecoach is heavily guarded, this will be a tough fight but all those men must be guarding something valuable.\n";
                moneyFound = (int) (Math.random() * 50) + 50;
                str += fight(4);
            }
        }
        else {
            str += "   You search for a stagecoach and find nothing interesting except for 10 dollars on the floor.";
            moneyFound += 10;
        }
        honor -= 5;
        if (!gameOver) {
            money += moneyFound;
            str += "\n   Money Found: " + moneyFound;
        }
        return str;
    }



    public String train(String t){
        String str = i.train();

        int moneyFound = 0;
        if (t.equals("03:00") || t.equals("06:00") || t.equals("09:00") || t.equals("12:00") || t.equals("15:00") || t.equals("18:00") || t.equals("21:00") || t.equals("00:00")){
            int random = (int) (Math.random() * 10) + 1;
            str += "   As you approach the railway, you hear the sound of an upcoming train.\n";
            if (random <= 2){
                str += "   You were too late to hop aboard the train, even though you are unable to rob it, a bag falls off and has some cash in it.\n";
                moneyFound = 100;
            }
            else if (random <= 4){
                str += "   You jump onto the train and it doesn't seem very fancy but doesn't seem very guarded either.\n";
                moneyFound = (int) (Math.random() * 200) + 100;
                str += fight(5);
            }
            else if (random <= 8){
                str += "   You jump onto the train, when you peer into the nearest carriage it seems like a good target... with many guards as well\n";
                moneyFound = (int) (Math.random() * 200) + 300;
                str += fight(6);
            }
            else {
                str += "   The train looks luxurious! The second you jump on people start shooting at you!.\n";
                moneyFound = (int) (Math.random() * 500) + 500;
                str += fight(8);
            }

        }
        else{
            str += "   You wait and wait but no train appears, you leave the tracks with nothing";
        }
        honor -= 10;
        if (!gameOver) {
            money += moneyFound;
            str += "\n   Money Found: " + moneyFound;
        }
        return str;
    }



    public String bank(String t){
        String str = i.bank();
        String openTime = "08:00,09:00,10:00,11:00,12:00,13:00,14:00,15:00,16:00,17:00,18:00,19:00,20:00";
        int moneyFound = 0;
        int random = (int) (Math.random() * 10) + 1;
        if (openTime.indexOf(t) != -1){
            int x = 0;
            if (openTime.substring(0, 23).indexOf(t) != -1){
                x += 2;
            }
            else if (openTime.substring(23, 53).indexOf(t) != -1){
                x += 4;
            }

            if (random <= 4){
                str += "   You enter the bank and find ";
                moneyFound = (int) (Math.random() * 3000) + 1000;
                str += fight(8 + x);
            }
            else if (random <= 8){
                str += "   ";
                moneyFound = (int) (Math.random() * 2000) + 3000;
                str += fight(10 + x);
            }
            else{
                str += "   ";
                moneyFound = (int) (Math.random() * 5000) + 5000;
                str += fight(12 + x);
            }
        }
        else {
            str += "   The bank is not open right now, you are unable to rob it, try again some other time.";
        }
        honor -= 20;
        if (!gameOver) {
            money += moneyFound;
            str += "\n   Money Found: " + moneyFound;
        }
        return str;
    }



    public String crime(int x, String t){
        int moneyFound = 0;
        String str = "";
        if (x == 1){
            str = stageCoach();
        }
        if (x == 2){
            str = train(t);
        }
        if (x == 3){
            str = bank(t);
        }
        return str;
    }



    public String shop(String s){
        String str = "";
        int coltCobra = 175;
        int m1903SpringField = 955;
        int browningAuto5 = 6500;
        int elephantRifle = 15750;
        int m198Howitzer = 112007;

        int apple = 10;
        int loafOfBread = 20;
        int steak = 35;

        int clothArmor = 150;
        int sturdyArmor = 1545;
        int medievalArmor = 26740;

        int preHealth = health;
        int preDamage = damage;
        int preMaxHealth = maxHealth;

        if (s.equals("Apple")){
            if (apple >= money){
                str += "   You do not have enough money to buy that item";
            }
            else {
                str += i.apple();
                money -= apple;
                health += 15;
                if (health > maxHealth){
                    health = maxHealth;
                }
            }
        }
        else if (s.equals("Loaf of Bread")){
            if (loafOfBread >= money){
                str += "   You do not have enough money to buy that item";
            }
            else {
                str += i.bread();
                money -= loafOfBread;
                health += 45;
                if (health > maxHealth){
                    health = maxHealth;
                }
            }
        }
        else if (s.equals("Steak")){
            if (steak >= money){
                str += "   You do not have enough money to buy that item";
            }
            else {
                str += i.steak();
                money -= steak;
                health += 90;
                if (health > maxHealth){
                    health = maxHealth;
                }
            }
        }

        else if (s.equals("Cloth Armor")){
            if (clothArmor >= money){
                str += "   You do not have enough money to buy that item";
            }
            else {
                str += i.clothArmor();
                money -= clothArmor;
                maxHealth += 35;
            }
        }
        else if (s.equals("Sturdy Armor")){
            if (sturdyArmor >= money){
                str += "   You do not have enough money to buy that item";
            }
            else {
                str += i.sturdyArmor();
                money -= sturdyArmor;
                maxHealth += 55;
            }
        }
        else if (s.equals("Medieval Armor")){
            if (medievalArmor >= money){
                str += "   You do not have enough money to buy that item";
            }
            else {
                money -= medievalArmor;
                maxHealth += 65;
            }
        }

        else if (s.equals("Colt Cobra")){
            if (coltCobra >= money){
                str += "   You do not have enough money to buy that item";
            }
            else{
                money -= coltCobra;
                damage = 15;
            }
        }
        else if (s.equals("M1903 Springfield")){
            if (m1903SpringField >= money){
                str += "   You do not have enough money to buy that item";
            }
            else{
                money -= m1903SpringField;
                damage = 20;
            }
        }
        else if (s.equals("Browning Auto-5")){
            if (browningAuto5 >= money){
                str += "   You do not have enough money to buy that item";
            }
            else{
                money -= browningAuto5;
                damage = 25;
            }
        }
        else if (s.equals("Elephant Rifle")){
            if (elephantRifle >= money){
                str += "   You do not have enough money to buy that item";
            }
            else{
                money -= elephantRifle;
                damage = 30;
            }
        }
        else if (s.equals("M198 Howitzer")){
            if (m198Howitzer >= money){
                str += "   You do not have enough money to buy that item.";
            }
            else{
                money -= m198Howitzer;
                damage = 100;
            }
        }

        else {
            str += "   That is not an item on the catalogue";
        }

        if (str.equals("   You do not have enough money to buy that item.") || str.equals("   That is not an item on the catalogue")){
            str = str;
        }
        else if (s.equals("Apple") || s.equals("Loaf of Bread") || s.equals("Steak")){
            str += "\n   Your previous health was " + preHealth + ".\n   Your current health is " + health + ".";
        }
        else if (s.equals("Cloth Armor") || s.equals("Sturdy Armor") || s.equals("Medieval Armor")){
            str += "\n   Your previous max health was " + preMaxHealth + ".\n   Your current health is " + maxHealth + ".";
        }
        else {
            str += "\n   Your previous damage was " + preDamage + ".\n   Your current damage is " + damage + ".";
        }

        return str;
    }



    public String ending(String x){
        String str = "";
        if (x.equals("died in battle")) {
            str += "\n                                                    You died fighting";
        }
        if (x.equals("ended early")) {
            str += "\n                                                  You ended your journey";
            gameOver = true;
        }
        str += "\n                                             This concludes the legend of " + name;
        str += "\n______________________________________________________________________________________________________________________________";
        return str;
    }
}