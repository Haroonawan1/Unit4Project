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

    private boolean member1Saved;
    private boolean member2Saved;
    private boolean member3Saved;
    private boolean member4Saved;

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
        damage = 10;

        member1Saved = false;
        member2Saved = false;
        member3Saved = false;
        member4Saved = false;
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
        if (openTime.indexOf(t) != -1){
            int x = 0;
            if (openTime.substring(0, 23).indexOf(t) != -1){
                x += 2;
            }
            else if (openTime.substring(23, 53).indexOf(t) != -1){
                x += 4;
            }

            if (random <= 4){
                str += "   You enter the bank and find that it is not that crowded, but of course still guarded.\n";
                moneyFound = (int) (Math.random() * 3000) + 1000;
                str += fight(8 + x);
            }
            else if (random <= 8){
                str += "   You enter and see the bank full of people, this will not be easy.\n";
                moneyFound = (int) (Math.random() * 2000) + 3000;
                str += fight(10 + x);
            }
            else{
                str += "   The bank is full of guards right now, this usually means the bank is full of money too!\n";
                moneyFound = (int) (Math.random() * 5000) + 5000;
                str += fight(12 + x);
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

    public String hint(){
        String str = "";
        int random = (int) (Math.random() * 5) + 1;
        if (random == 1){
            str += "   The stranger says: Thank you! I am not sure if I should tell you this but the bank is usually least guarded at night.\n";
        }
        if (random == 2){
            str += "   The stranger says: Thanks! Your end is affected by the actions you do.\n   You think: What a weirdo.\n";
        }
        if (random == 3){
            str += "   The stranger says: I think some of your buddies are locked up right now. Helping them can make you more honorable.\n";
        }
        if (random == 4){
            str += "   The stranger says: Attempting to do a crime is dishonorable, but not as dishonorable as carrying through with your intentions.\n";
        }
        if (random == 5){
            str += "   The stranger says: Go touch grass\n   You think: What a weirdo\n";
        }
        return str;
    }

    public String giveARide(){
        String str = "";
        if ((int) (Math.random() * 10) + 1 >= 6){
            str += "\n   You find someone in need of a ride home\n";
            int random = (int) (Math.random() * 3) + 1;
            if (random == 1){
                str += "   The person thanks you for the ride and you go on with your day.\n";
                str += hint();
            }
            if (random == 2){
                int moneyLost = (int) (Math.random() * (money/5));
                money -= moneyLost;
                str += "   The person thank you for the ride but as you leave your pockets seem a little lighter.\n   Money lost: " + moneyLost + "\n";
            }
            if (random == 3){
                str += "   The person gets on your horse... and then... kicks you off! You run after him and get into a fight!\n";
                str += fight(1);
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
        if (x <= money){
            money -= x;
            return true;
        }
        return false;
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
                str += "\n   Before you get the chance to leave a homeless guy approaches you.\n";
                str += hint();
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
        if (random >= 8){
            str += "\n   You are able to make the two men stop fighting pretty quickly. They both walk of angry.\n";
        }
        else if (random >= 5){
            str += "\n   You break up the fight and help the man left on the floor, he is really beat up. Before you can leave he says something.\n";
            str += hint();
        }
        else {
            str += "\n   Both men decide to stop fighting with a little bit of convincing... and then decide to fight you!\n";
            str += fight(2);
        }
        setHonor(8);
        return str;
    }

    public String goodDeed(int x, int m){
        String str = "";
        if (x == 1){
            str = giveARide();
        }
        if (x == 2){
            str = giveMoneyToThePoor(m);
        }
        if (x == 3){
            str = stopAFight();
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
                money -= clothArmor;
                maxHealth += 35;
            }
        }
        else if (s.equals("Sturdy Armor")){
            if (sturdyArmor >= money){
                str += "   You do not have enough money to buy that item";
            }
            else {
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

    public String printBounties(){
        String str = "print the correct bounty page";
        return str;
    }

    public String payBounty(int x){
        String str = "";
        int member1 = 1250;
        int member2 = 7500;
        int member3 = 25000;
        int member4 = 55000;

        if (x == 1){
            if (member1Saved){
                str += "   You have already paid this bounty";
            }
            else {
                if (money < member1){
                    str += "   You do not have enough money to pay off this bounty";
                }
                else {
                    str += "   You buy the bounty of Member 1, he is very happy. You become more honorable";
                    money -= member1;
                    setHonor(25);
                    saved++;
                    member1Saved = true;
                }
            }
        }

        if (x == 2){
            if (member1Saved){
                str += "   You have already paid this bounty";
            }
            else {
                if (money < member2){
                    str += "   You do not have enough money to pay off this bounty";
                }
                else {
                    str += "   You buy the bounty of Member 2, he is very happy. You become more honorable";
                    money -= member2;
                    setHonor(35);
                    saved++;
                    member2Saved = true;
                }
            }
        }

        if (x == 3){
            if (member1Saved){
                str += "   You have already paid this bounty";
            }
            else {
                if (money < member3){
                    str += "   You do not have enough money to pay off this bounty";
                }
                else {
                    str += "   You buy the bounty of Member 3, he is very happy. You become more honorable";
                    money -= member3;
                    setHonor(50);
                    saved++;
                    member3Saved = true;
                }
            }
        }

        if (x == 4){
            if (member1Saved){
                str += "   You have already paid this bounty";
            }
            else {
                if (money < member4){
                    str += "   You do not have enough money to pay off this bounty";
                }
                else {
                    str += "   You buy the bounty of Member 4, he is very happy. You become more honorable";
                    money -= member4;
                    setHonor(75);
                    saved++;
                    member4Saved = true;
                }
            }
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