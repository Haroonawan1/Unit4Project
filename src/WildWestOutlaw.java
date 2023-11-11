public class WildWestOutlaw {
    private String name;
    private int health;
    private int maxHealth;
    private int money;
    private int honor;
    private int day;
    private int saved;
    private boolean gameOver;
    private int damage;
    private String tasksCompleted;

    Images i = new Images();

    public WildWestOutlaw (String n){
        name = n;
        health = 1099990;
        maxHealth = 1999900;
        money = 999990;
        honor = 0;
        day = 1;
        saved = 0;
        gameOver = false;
        damage = 10;
        tasksCompleted = "";
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

    public int getDay(){
        return day;
    }

    public void increaseDay(){
        day++;
    }

    public String getStats(){
        String str = "\n   ____________________________";
        str += "\n    Your current statistics:\n";
        str += "    Health: " + health + "/" + maxHealth + "\n";
        str += "    Money: " + money + "\n    Honor: " + honor + "\n    Damage: " + damage + "\n    People Saved: " + saved;
        str += "\n   ____________________________";
        return str;
    }

    public void setHonor(int x){
        honor += x;
        if (honor > 100){
            honor = 100;
        }
        else if (honor < -100){
            honor = -100;
        }
    }

    public String enemy(String name, int enemyHealth, int enemyDamage){
        String str = "\n   You begin fighting a " + name + "\n";
        while (health > 0 && enemyHealth > 0) {
            enemyHealth -= damage;
            health -= enemyDamage;
        }
        if (enemyHealth <= 0){
            str += "   You won your fight against the " + name + "!\n   Remaining Health: " + health + "\n";
        }
        if (health <= 0){
            str += ending("died in battle");
            gameOver = true;
        }
        return str;
    }

    public String fight(int x){
        String str = "";
        while (x > 0 && !gameOver){
            switch ((int) (Math.random() * 3) + 1){
                case 1 -> str += enemy("Bandit", 50, 1);
                case 2 -> str += enemy("Law Enforcer", 75, 2);
                case 3 -> str += enemy("Bounty Hunter", 100, 4);
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
                str += "   The stagecoach you find only has a driver and a few passengers, this should be an easy target, lucky you.\n" + fight(2);
                moneyFound = (int) (Math.random() * 10) + 10;
            }
            else if (random <= 8){
                str += "\n   The stagecoach is not unguarded but a few men should not be a problem for a legend like you.\n" + fight(3);
                moneyFound = (int) (Math.random() * 30) + 20;
            }
            else {
                str += "   The stagecoach is heavily guarded, this will be a tough fight but all those men must be guarding something valuable.\n" + fight(4);
                moneyFound = (int) (Math.random() * 50) + 50;
            }
            setHonor(-5);
        }
        else {
            str += "   You search for a stagecoach and find nothing interesting except for 10 dollars on the floor.";
            moneyFound += 10;
            setHonor(-3);
        }
        if (!gameOver) {
            money += moneyFound;
            str += "\n   Money Found: " + moneyFound;
        }
        return str;
    }

    public String train(String t){
        String str = i.train();
        int moneyFound = 0;
        String possibleTimes = "03:00,06:00,09:00,12:00,15:00,18:00,21:00,00:00";
        if (possibleTimes.contains(t)){
            int random = (int) (Math.random() * 10) + 1;
            str += "   As you approach the railway, you hear the sound of an upcoming train.\n";
            if (random <= 2){
                str += "   You were too late to hop aboard the train, even though you are unable to rob it, a bag falls off and has some cash in it.\n";
                moneyFound = 100;
            }
            else if (random <= 4){
                str += "   You jump onto the train and it doesn't seem very fancy but doesn't seem very guarded either.\n" + fight(5);
                moneyFound = (int) (Math.random() * 200) + 100;
            }
            else if (random <= 8){
                str += "   You jump onto the train, when you peer into the nearest carriage it seems like a good target... with many guards as well\n" + fight(6);
                moneyFound = (int) (Math.random() * 200) + 300;
            }
            else {
                str += "   The train looks luxurious! The second you jump on people start shooting at you!.\n" + fight(8);
                moneyFound = (int) (Math.random() * 500) + 500;
            }
            setHonor(-10);
        }
        else{
            str += "   You wait and wait but no train appears, you leave the tracks with nothing";
            setHonor(-5);
        }
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
        if (openTime.contains(t)){
            int x = 0;
            if (openTime.substring(0, 23).contains(t)){
                x += 2;
            }
            else if (openTime.substring(23, 53).contains(t)){
                x += 4;
            }

            if (random <= 4){
                str += "   You enter the bank and find that it is not that crowded, but of course still guarded.\n" + fight(8 + x);
                moneyFound = (int) (Math.random() * 3000) + 1000;
            }
            else if (random <= 8){
                str += "   You enter and see the bank full of people, this will not be easy.\n" + fight(10 + x);
                moneyFound = (int) (Math.random() * 2000) + 3000;
            }
            else{
                str += "   The bank is full of guards right now, this usually means the bank is full of money too!\n" + fight(12 + x);
                moneyFound = (int) (Math.random() * 5000) + 5000;
            }
            setHonor(-20);
        }
        else {
            str += "   The bank is not open right now, you are unable to rob it, try again some other time.";
            setHonor(-10);
        }
        if (!gameOver) {
            money += moneyFound;
            str += "\n   Money Found: " + moneyFound;
        }
        return str;
    }

    public String crime(int x, String t){
        String str = "";
        switch (x){
            case 1 -> str = stageCoach();
            case 2 -> str = train(t);
            case 3 -> str = bank(t);
        }
        return str;
    }

    public String hint(){
        String str = "";
        int random = (int) (Math.random() * 5) + 1;
        switch(random){
            case 1 -> str += "   The stranger says: Thank you! I am not sure if I should tell you this but the bank is usually least guarded at night.\n";
            case 2 -> str += "   The stranger says: Thanks! Your end is affected by the actions you do.\n   You think: What a weirdo.\n";
            case 3 -> str += "   The stranger says: I think some of your buddies are locked up right now. Helping them can make you more honorable.\n";
            case 4 -> str += "   The stranger says: Attempting to do a crime is dishonorable, but not as dishonorable as carrying through with your intentions.\n";
            case 5 -> str += "   The stranger says: Go touch grass\n   You think: What a weirdo\n";
        }
        return str;
    }

    public String giveARide(){
        String str = "";
        if ((int) (Math.random() * 10) + 1 >= 6){
            str += "\n   You find someone in need of a ride home\n";
            //int random = (int) (Math.random() * 3) + 1;
            switch ((int) (Math.random() * 3) + 1) {
                case 1 -> str += "   The person thanks you for the ride and you go on with your day.\n" + hint();
                case 2 -> {
                    int moneyLost = (int) (Math.random() * (money / 5));
                    money -= moneyLost;
                    str += "   The person thank you for the ride but as you leave your pockets seem a little lighter.\n   Money lost: " + moneyLost + "\n";
                }
                case 3 -> str += "   The person gets on your horse... and then... kicks you off! You run after him and get into a fight!\n" + fight(1);
            }
            setHonor(10);
        }
        else {
            str += "\n   You did not find anyone to help.";
            setHonor(5);
        }
        return str;
    }

    public boolean donatePossible(int x){
        money -= (x <= money) ? x : 0;
        return x <= money;
    }

    public String giveMoneyToThePoor(int x){
        String str = "";
        if (donatePossible(x)){
            str = "\n   You decide to donate " + x + " to the poor. You are thanked and honored.";
            if (x > 10000){
                setHonor(10);
            }
            else if (x > 1000){
                setHonor(5);
            }
            else if (x > 100){
                setHonor(3);
            }
            else {
                setHonor(1);
            }
            if ((int) (Math.random() * 10) + 1 >= 8){
                str += "\n   Before you get the chance to leave a homeless guy approaches you.\n" + hint();
            }
        }
        else{
            str += "\n   You do not have the amount of money you want to donate.";
        }
        return str;
    }

    public String stopAFight(){
        String str = "\n   As you walk down a street in town you see a fight going on an go to intervene.";
        int random = (int) (Math.random() * 10) + 1;
        if (random >= 7){
            str += "\n   You are able to make the two men stop fighting pretty quickly. They both walk of angry.\n";
        }
        else if (random >= 4){
            str += "\n   You break up the fight and help the man left on the floor, he is really beat up. Before you can leave he says something.\n" + hint();
        }
        else {
            str += "\n   Both men decide to stop fighting with a little bit of convincing... and then decide to fight you!\n" + fight(2);
        }
        setHonor(8);
        return str;
    }

    public String goodDeed(int x, int m){
        String str = "";
        switch (x){
            case 1 -> str = giveARide();
            case 2 -> str = giveMoneyToThePoor(m);
            case 3 -> str = stopAFight();
        }
        return str;
    }

    public String item(int price, String statAffected, int change, String itemName){
        String str = "";
        if (price >= money){
            str += "   You do not have enough money to buy that item";
        }
        else {
            if (statAffected.equals("health")){
                health = Math.min(health + change, maxHealth);
            }
            if (statAffected.equals("maxHealth")){
                if (tasksCompleted.contains(itemName)){
                    str += "\n   (You already bought this armor upgrade)";
                }
                else {
                    switch (itemName){
                        case "Cloth armor" -> {
                            maxHealth += 35;
                            tasksCompleted += "Cloth armor";
                        }
                        case "Sturdy armor" -> {
                            maxHealth += 55;
                            tasksCompleted += "Sturdy armor";
                        }
                        case "Medieval armor" -> {
                            maxHealth += 65;
                            tasksCompleted += "Medieval armor";
                        }
                    }
                }
            }
            if (statAffected.equals("damage")){
                if (tasksCompleted.contains(itemName)){
                    str += "\n   (You already own this weapon and was not charged)";
                }
                switch (itemName) {
                    case "Colt Cobra" -> {
                        damage = 15;
                        tasksCompleted += "Colt Cobra";
                    }
                    case "M1903 Springfield" -> {
                        damage = 20;
                        tasksCompleted += "M1903 Springfield";
                    }
                    case "Browning Auto-5" -> {
                        damage = 25;
                        tasksCompleted += "Browning Auto-5";
                    }
                    case "Elephant Rifle" -> {
                        damage = 30;
                        tasksCompleted += "Elephant Rifle";
                    }
                    case "M198 Howitzer" -> {
                        damage = 100;
                        tasksCompleted += "M198 Howitzer";
                    }
                }
            }
            money -= (str.contains("already")) ? 0 : price;
        }
        return str;
    }

    public String shop(String s){
        String str = "";
        int preHealth = health;
        int preDamage = damage;
        int preMaxHealth = maxHealth;

        switch (s) {
            case "Apple" -> str += item(10, "health", 15, "Apple");
            case "Loaf of Bread" -> str += item(20, "health", 45, "Loaf of Bread");
            case "Steak" -> str += item(35, "health", 90, "Steak");
            case "Cloth armor" -> str += item(150, "maxHealth", 35, "Cloth armor");
            case "Sturdy armor" -> str += item(1545, "maxHealth", 55, "Sturdy armor");
            case "Medieval armor" -> str += item(26740, "maxHealth", 65, "Medieval armor");
            case "Colt Cobra" -> str += item(175, "damage", 15, "Colt Cobra");
            case "M1903 Springfield" -> str += item(955, "damage", 20, "M1903 Springfield");
            case "Browning Auto-5" -> str += item(6500, "damage", 25, "Browning Auto-5");
            case "Elephant Rifle" -> str += item(15750, "damage", 30, "Elephant Rifle");
            case "M198 Howitzer" -> str += item(112007, "damage", 100, "M198 Howitzer");
            default -> str += "   That is not an item on the catalogue";
        }

        if (str.equals("   You do not have enough money to buy that item.") || str.equals("   That is not an item on the catalogue")){
            str += "";
        }
        else if (s.equals("Apple") || s.equals("Loaf of Bread") || s.equals("Steak")){
            str += "\n   Your previous health was " + preHealth + ".\n   Your current health is " + health + ".";
        }
        else if (s.equals("Cloth armor") || s.equals("Sturdy armor") || s.equals("Medieval armor")){
            str += "\n   Your previous max health was " + preMaxHealth + ".\n   Your current health is " + maxHealth + ".";
        }
        else {
            str += "\n   Your previous damage was " + preDamage + ".\n   Your current damage is " + damage + ".";
        }
        return str;
    }

    public String bounty(int price, String member, int gainedHonor){
        String str = "";
        if ((tasksCompleted.contains(member))){
            str += "   You have already paid this bounty";
        }
        else {
            if (money < price){
                str += "   You do not have enough money to pay off this bounty";
            }
            else {
                str += "   You buy the bounty of " + member + ", he is very happy. You become more honorable";
                money -= price;
                setHonor(gainedHonor);
                saved++;
                switch (member){
                    case "Member 1" -> tasksCompleted += "Member 1";
                    case "Member 2" -> tasksCompleted += "Member 2";
                    case "Member 3" -> tasksCompleted += "Member 3";
                    case "Member 4" -> tasksCompleted += "Member 4";
                }
            }
        }
        return str;
    }

    public String payBounty(int x){
        String str = "";
        switch (x){
            case 1 -> str += bounty(1250, "Member 1", 25);
            case 2 -> str += bounty(7500, "Member 2", 35);
            case 3 -> str += bounty(25000, "Member 3", 50);
            case 4 -> str += bounty(55000, "Member 4", 75);
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