public class OutlawRedemption {
    private String name;
    private int health;
    private int maxHealth;
    private int money;
    private int honor;
    private int day;
    private int dayCounter;
    private int peopleSaved;
    private boolean gameOver;
    private int damage;
    private String itemsBought;
    private String bountyInfo;

    Images image = new Images();


    /**
     * Constructor for the OutlawRedemption class. This creates a new instance of the OutlawRedemption class with the below parameters.
     *
     * @param userName represents the name of the user's character
     */
    public OutlawRedemption (String userName){
        name = userName;
        health = 100;
        maxHealth = 100;
        money = 0;
        honor = 0;
        day = 1;
        dayCounter = 0;
        peopleSaved = 0;
        gameOver = false;
        damage = 10;
        itemsBought = "";
        bountyInfo = "";
    }

    /**
     * Constructor for the OutlawRedemption class. This creates a new instance of the OutlawRedemption class with the below parameters.
     * This constructor has a default name instead of a custom one.
     */
    public OutlawRedemption (){
        name = "anon";
        health = 100;
        maxHealth = 100;
        money = 0;
        honor = 0;
        day = 1;
        dayCounter = 0;
        peopleSaved = 0;
        gameOver = false;
        damage = 10;
        itemsBought = "";
        bountyInfo = "";
    }

    /**
     * The isInputNumerical method will check if the String given represents a numerical value using a try catch block
     *
     * @param input represents the input a user has already made
     * @return returns a boolean regarding if the input is numerical
     */
    public boolean isInputNumerical(String input){
        //If the input is not numerical, the Integer.parseInt() method will error
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * The getHelp method will assist the user with the game by giving them some information to start out
     *
     * @return returns a string that contains information to help someone start out in the game
     */
    public String getHelp(){
        return """
                   _____________________________________________________________________________________________________________________
                    HELP:
                    1) The objective of the game is to raise enough money in order to pay off the bounty of your fellow gang members
                    2) You can obtain money by doing crimes (some are harder than others), however this will affect your honor
                    3) You can improve your honor by doing good deeds, thankful people may help you
                    4) Go to the shop to heal and get better weapons to improve your chances in fights
                    5) Save your members before they are hanged, each member has a certain amount of days until they are hanged
                    6) Your honor and the amount of people you have saved will effect your ending
                    7) GOOD LUCK!
                   _____________________________________________________________________________________________________________________
                """;
    }

    /**
     * The isGameOver method allows the instance variable gameOver to be checked outside the class
     *
     * @return returns the value of gameOver, which is a boolean
     */
    public boolean isGameOver(){
        return gameOver;
    }

    /**
     * The getDay method allows the instance variable day to be checked outside the class
     *
     * @return returns the value of day, which is an integer
     */
    public int getDay(){
        return day;
    }

    /**
     * The getName method allows the instance variable name to be checked outside the class
     *
     * @return returns the value of name, which is a String
     */
    public String getName(){
        return name;
    }

    /**
     * The increaseDay method increases the value of day by one if dayCounter is even and then increases dayCounter by 1
     */
    public void increaseDay(){
        day += (dayCounter % 2 == 0) ? 1: 0;
        dayCounter++;
    }

    /**
     * The getStats method allows the user to see their stats, useful especially for stats not shown in other methods
     * throughout the game such as your honor
     *
     * @return returns a formatted String that contains all the statistics of the user's character at the moment ran
     */
    public String getStats(){
        String str = "\n   ____________________________";
        str += "\n    Your current statistics:\n";
        str += "    Health: " + health + "/" + maxHealth + "\n";
        str += "    Money: " + money + "\n    Honor: " + honor + "\n    Damage: " + damage + "\n    People Saved: " + peopleSaved;
        str += "\n   ____________________________";
        return str;
    }

    /**
     * The changeHonor method changes the value of the instance variable honor
     *
     * @param change represents the change that will occur to the instance variable honor
     */
    public void changeHonor(int change){
        honor += change;
        if (honor > 100){
            honor = 100;
        }
        else if (honor < -100){
            honor = -100;
        }
    }

    /**
     * The fightEnemy method simulates a fight between the character of the user and an enemy using the given information
     * about the enemy
     *
     * @param name represents the name of the enemy being fought
     * @param enemyHealth - represents the health of the enemy being fought
     * @param enemyDamage - represents the damage the enemy being fought does
     * @return returns a String that has information regarding the outcome of the fight
     */
    public String fightEnemy(String name, int enemyHealth, int enemyDamage){
        String str = "\n   You begin fighting a " + name + "\n";
        while (health > 0 && enemyHealth > 0) {
            enemyHealth -= damage;
            health -= enemyDamage;
        }
        if (enemyHealth <= 0){
            str += "   You murdered a " + name + "!\n   Remaining Health: " + health + "\n";
        }
        if (health <= 0){
            str += endGame("died in battle");
            gameOver = true;
        }
        return str;
    }

    /**
     * The fightGroup method simulates a fight between the character of the user and a group of enemies, it calls the
     * fightEnemy method for each enemy in the group
     *
     * @param groupSize represents the amount of enemies that need to be fought
     * @return returns a String that contains all the results of fighting each enemy
     */
    public String fightGroup(int groupSize){
        String str = "";
        while (groupSize > 0 && !gameOver){
            //Decided to use switch blocks some place instead of if statements in order to have neater code and try out something new
            switch ((int) (Math.random() * 3) + 1){
                case 1 -> str += fightEnemy("Bandit", 50, 1);
                case 2 -> str += fightEnemy("Law Enforcer", 75, 2);
                case 3 -> str += fightEnemy("Bounty Hunter", 100, 4);
            }
            groupSize--;
        }
        return str;
    }

    /**
     * The robStageCoach method simulates the player attempting to rob a stagecoach. There a few outcomes that may occur,
     * the one that occurs is added to a String
     *
     * @return returns a String that contains all results from attempting to rob a stagecoach
     */
    public String robStageCoach(){
        String str = image.getRobStageCoachImage();
        int moneyFound = 0;
        if ((int) (Math.random() * 10) + 1 > 3){
            int random = (int) (Math.random() * 10) + 1;
            str += "\n   As you ride down a pathway you find a stagecoach.\n";
            if (random <= 4){
                str += "   The stagecoach you find only has a driver and a few passengers, this should be an easy target, lucky you.\n" + fightGroup(2);
                moneyFound = (int) (Math.random() * 10) + 10;
            }
            else if (random <= 8){
                str += "\n   The stagecoach is not unguarded but a few men should not be a problem for a legend like you.\n" + fightGroup(3);
                moneyFound = (int) (Math.random() * 30) + 20;
            }
            else {
                str += "   The stagecoach is heavily guarded, this will be a tough fight but all those men must be guarding something valuable.\n" + fightGroup(4);
                moneyFound = (int) (Math.random() * 50) + 50;
            }
            changeHonor(-5);
        }
        else {
            str += "   You search for a stagecoach and find nothing interesting except for 45 dollars on the floor.\n";
            moneyFound += 10;
            changeHonor(-3);
        }
        if (!gameOver) {
            money += moneyFound + 35;
            str += "\n   Money Found: " + (moneyFound + 35) + "\n";
        }
        return str;
    }

    /**
     * The robTrain method simulates the attempted robbery of a train by the user, there are different outcomes that may
     * occur in addition to differences that occur due to the time selected. The results are added to a String.
     *
     * @param time represents the time the user will attempt to rob a train
     * @return returns a String that contains all the results about the attempted train robbery
     */
    public String robTrain(String time){
        String str = image.getRobTrainImage();
        int moneyFound = 0;
        String possibleTimes = "03:00,06:00,09:00,12:00,15:00,18:00,21:00,00:00";
        if (possibleTimes.contains(time)){
            int random = (int) (Math.random() * 10) + 1;
            str += "   As you approach the railway, you hear the sound of an upcoming train.\n";
            if (random <= 2){
                str += "   You were too late to hop aboard the train, even though you are unable to rob it, a bag falls off and has some cash in it.\n";
                moneyFound = 100;
            }
            else if (random <= 4){
                str += "   You jump onto the train and it doesn't seem very fancy but doesn't seem very guarded either.\n" + fightGroup(5);
                moneyFound = (int) (Math.random() * 200) + 100;
            }
            else if (random <= 8){
                str += "   You jump onto the train, when you peer into the nearest carriage it seems like a good target... with many guards as well\n" + fightGroup(6);
                moneyFound = (int) (Math.random() * 200) + 300;
            }
            else {
                str += "   The train looks luxurious! The second you jump on people start shooting at you!.\n" + fightGroup(8);
                moneyFound = (int) (Math.random() * 500) + 500;
            }
            changeHonor(-10);
        }
        else{
            str += "   You wait and wait but no train appears, you leave the tracks with nothing";
            changeHonor(-5);
        }
        if (!gameOver) {
            money += moneyFound;
            str += "\n   Money Found: " + moneyFound;
        }
        return str;
    }

    /**
     * The robTrain method simulates the attempted robbery of a bank by the user, there are different outcomes that may
     * occur in addition to differences that occur due to the time selected. The results are added to a String.
     *
     * @param time represents the time the user will attempt to rob a bank
     * @return returns a String that contains all the results about the attempted bank robbery
     */
    public String robBank(String time){
        String str = image.getRobBankImage();
        String openTime = "08:00,09:00,10:00,11:00,12:00,13:00,14:00,15:00,16:00,17:00,18:00,19:00,20:00";
        int moneyFound = 0;
        int random = (int) (Math.random() * 10) + 1;
        if (openTime.contains(time)){
            int x = 0;
            if (openTime.substring(0, 23).contains(time)){
                x += 2;
            }
            else if (openTime.substring(23, 53).contains(time)){
                x += 4;
            }

            if (random <= 4){
                str += "   You enter the bank and find that it is not that crowded, but of course still guarded.\n" + fightGroup(8 + x);
                moneyFound = (int) (Math.random() * 3000) + 1000;
            }
            else if (random <= 8){
                str += "   You enter and see the bank full of people, this will not be easy.\n" + fightGroup(10 + x);
                moneyFound = (int) (Math.random() * 2000) + 3000;
            }
            else{
                str += "   The bank is full of guards right now, this usually means the bank is full of money too!\n" + fightGroup(12 + x);
                moneyFound = (int) (Math.random() * 5000) + 5000;
            }
            changeHonor(-20);
        }
        else {
            str += "   The bank is not open right now, you are unable to rob it, try again some other time.";
            changeHonor(-10);
        }
        if (!gameOver) {
            money += moneyFound;
            str += "\n   Money Found: " + moneyFound;
        }
        return str;
    }

    /**
     * The commitCrime method allows the user to select which crime they want to commit, then runs the appropriate method
     *
     * @param choice represents what crime the user wants to attempt
     * @param time represents what time the user will attempt the crime
     * @return returns a String that contains all the results of the crime attempted
     */
    public String commitCrime(int choice, String time){
        String str = "";
        switch (choice){
            case 1 -> str = robStageCoach();
            case 2 -> str = robTrain(time);
            case 3 -> str = robBank(time);
            default -> System.out.print("\n   (One of the two is not an acceptable input)");
        }
        return str;
    }

    /**
     * The giveHint method will give the user a hint about how the game works when they decide to do good deeds
     *
     * @return returns a String that contains a hint about how the game works
     */
    public String giveHint(){
        String str = "";
        int random = (int) (Math.random() * 5) + 1;
        switch(random){
            case 1 -> str += "   The stranger says: Thank you! I am not sure if I should tell you this but the bank is usually least guarded at night.\n";
            case 2 -> str += "   The stranger says: Thanks! Your end is affected by the actions you do.\n   You think: What a weirdo.\n";
            case 3 -> str += "   The stranger says: I think some of your buddies are locked up right now. Helping them can make you more honorable.\n";
            case 4 -> str += "   The stranger says: Attempting to do a crime is dishonorable, but not as dishonorable as carrying through with your intentions.\n";
            case 5 -> str += "   The stranger says: A friend of mine at the train station says they come every three hours\n";
        }
        return str;
    }

    /**
     * The giveARide method simulates the user's attempt to find someone in need of a ride home and giving it to them
     *
     * @return returns a String that contains the results of the user's attempt to help someone in need
     */
    public String giveARide(){
        String str = "";
        if ((int) (Math.random() * 10) + 1 >= 6){
            str += "\n" + image.getGiveARideImage() + "\n   You find someone in need of a ride home";
            switch ((int) (Math.random() * 3) + 1) {
                case 1 -> {
                    str += "\n" + giveHint() + "   Expressing gratitude for the ride, the person bids farewell, leaving you to seamlessly resume your day.";
                    str += "\n   The exchange, brief yet appreciative, lingers in the air as you continue on your path, the shared moment of goodwill resonating through the routine of daily life.";
                }
                case 2 -> {
                    int moneyLost = (int) (Math.random() * (money / 5));
                    money -= moneyLost;
                    str += "\n   As gratitude is expressed for the ride, a subtle unease sets in as you depart. You realize that in the exchange, your pockets have lightened.";
                    str += "\n   A hint of realization dawns, an unintended toll exacted by the act of kindness, leaving a bittersweet note to the concluded journey.\n   Money lost: " + moneyLost + "\n";
                }
                case 3 -> {
                    str += "\n   Mounting your horse, the person's gratitude takes an unexpected turn as a forceful kick sends you sprawling. Determined, you sprint after your horse who hasn't gone far.";
                    str += "\n   You catch up and start a fierce brawl, the fight reminding you of the unforgiving nature of the frontier.\n" + fightGroup(1);
                }
            }
            changeHonor(10);
        }
        else {
            str += image.getGiveARideImage2() + "\n   You did not find anyone to help.";
            changeHonor(5);
        }
        return str;
    }

    /**
     * The isDonatePossible method checks if the donation the user wants to give is possible given the amount of money
     * they have. If it is possible, the money is taken
     *
     * @param donation represents the donation the user is attempting to donate
     * @return returns a boolean value for if the donation is possible or not
     */
    public boolean isDonatePossible(int donation){
        money -= (donation <= money) ? donation : 0;
        return donation <= money;
    }

    /**
     * The donateMoney method will inform the user of their attempts to donate money to teh poor using a String
     *
     * @param donation represents the donation the user wants to attempt
     * @return returns a String with the results of the user's attempted donation
     */
    public String donateMoney(int donation){
        String str = "";
        if (isDonatePossible(donation)){
            str += image.getDonateMoneyImage() + "\n   You choose to donate " + donation + " dollars to those in need. Gratitude and honor light up the faces of the recipients as they express heartfelt thanks.";
            str += "\n   Their eyes reflect a mix of surprise and appreciation for the unexpected act of kindness.";
            if (donation > 10000){
                changeHonor(10);
            }
            else if (donation > 1000){
                changeHonor(5);
            }
            else if (donation > 100){
                changeHonor(3);
            }
            else {
                changeHonor(1);
            }
            if ((int) (Math.random() * 10) + 1 >= 8){
                str += "\n   As you prepare to leave, a weather-beaten homeless man, cloaked in tattered layers, weaves through the bustling corner.";
                str += "\n   Their eyes reflecting both resilience and hardship, they catch your attention before you can leave.\n" + giveHint();
            }
        }
        else{
            str += "\n   You do not have the amount of money you want to donate.";
        }
        return str;
    }

    /**
     * The stopAFight method simulates the user stopping a fight going on in town. This has multiple outcomes and the results
     * of their attempt are stored on a String
     *
     * @return returns a String with the results of stopping a fight
     */
    public String stopAFight(){
        String str = image.getStopAFightImage() + "\n   Amidst the sunlit street, the distant murmur of laughter gives way to a brewing brawl. You, maybe compelled by a sense of duty, stride toward the commotion.";
        str += "\n   Dust swirls in the air as the two men fight and you can barely tell who is winning, but it doesn't matter as you walk toward the center of the scene.";
        int random = (int) (Math.random() * 10) + 1;
        if (random >= 7){
            str += "\n   You are able to make the two men stop fighting pretty quickly. They both walk of angry.\n";
        }
        else if (random >= 4){
            str += "\n   You break up the fight and help the man left on the floor, he is really beat up. Before you can leave he says something.\n" + giveHint();
        }
        else {
            str += "\n   Both men decide to stop fighting with a little bit of convincing... and then decide to fight you!\n" + fightGroup(2);
        }
        changeHonor(8);
        return str;
    }

    /**
     * The doAGoodDeed method allows the user to select which good deed they want to do, then runs the appropriate method
     *
     * @param choice represents which good deed the user wants to do
     * @param donation represents how much the user wants to donate
     * @return returns a String with the results of the good deed the user choose to do
     */
    public String doAGoodDeed(int choice, int donation){
        String str = "";
        switch (choice){
            case 1 -> str = giveARide();
            case 2 -> str = donateMoney(donation);
            case 3 -> str = stopAFight();
        }
        return str;
    }

    /**
     * The useItem method takes in the given information about an item and then determines the effect that item has on
     * the user if they are even able to purchase it
     *
     * @param price represents the price of the item
     * @param statAffected represents which stat of the character is affected by the item
     * @param change represent how much of a change is made to the stat affected
     * @param itemName represents the name of the item the user is trying to buy
     * @return returns a String, which only has content if the user cannot purchase the item
     */
    public String useItem(int price, String statAffected, int change, String itemName){
        String str = "";
        if (price >= money){
            str += "   You do not have enough money to buy that item";
        }
        else if (itemsBought.contains(itemName)){
            str += "\n   (You already bought this item and were not charged, duplicate armors and weapons cannot be obtained)";
        }
        else {
            //Change is only used for health because healing items can be bought multiple times, therefore I did not need to
            //add unique item names and so use the change variable, there should be a better way to do this, but I did not
            //attempt to find it
            if (statAffected.equals("health")){
                health = Math.min(health + change, maxHealth);
            }
            if (statAffected.equals("maxHealth")){
                switch (itemName) {
                    case "Cloth armor" -> {
                        maxHealth += 35;
                        itemsBought += "Cloth armor";
                    }
                    case "Sturdy armor" -> {
                        maxHealth += 55;
                        itemsBought += "Sturdy armor";
                    }
                    case "Medieval armor" -> {
                        maxHealth += 65;
                        itemsBought += "Medieval armor";
                    }
                }
            }
            if (statAffected.equals("damage")){
                switch (itemName) {
                    case "Colt Cobra" -> {
                        damage = 15;
                        itemsBought += "Colt Cobra";
                    }
                    case "M1903 Springfield" -> {
                        damage = 20;
                        itemsBought += "M1903 Springfield";
                    }
                    case "Browning Auto-5" -> {
                        damage = 25;
                        itemsBought += "Browning Auto-5";
                    }
                    case "Elephant Rifle" -> {
                        damage = 30;
                        itemsBought += "Elephant Rifle";
                    }
                    case "M198 Howitzer" -> {
                        damage = 100;
                        itemsBought += "M198 Howitzer";
                    }
                }
            }
        }
        money -= (str.contains("already")) ? 0 : price;
        return str;
    }

    /**
     * The buyItem method takes the name of the item the user wants to purchase and then runs teh appropriate version of
     * the useItem method. Then it informs the user of the effect that item had on their character
     *
     * @param choice represents which item the user wants to buy from the shop
     * @return returns a String that contains the results of their purchase and the effect the item had on their character
     */
    public String buyItem(String choice){
        String str = "";
        int preHealth = health;
        int preDamage = damage;
        int preMaxHealth = maxHealth;

        switch (choice) {
            case "Apple" -> str += useItem(10, "health", 15, "Apple");
            case "Loaf of Bread" -> str += useItem(20, "health", 45, "Loaf of Bread");
            case "Steak" -> str += useItem(35, "health", 90, "Steak");
            case "Cloth armor" -> str += useItem(150, "maxHealth", 35, "Cloth armor");
            case "Sturdy armor" -> str += useItem(1545, "maxHealth", 55, "Sturdy armor");
            case "Medieval armor" -> str += useItem(26740, "maxHealth", 65, "Medieval armor");
            case "Colt Cobra" -> str += useItem(175, "damage", 15, "Colt Cobra");
            case "M1903 Springfield" -> str += useItem(955, "damage", 20, "M1903 Springfield");
            case "Browning Auto-5" -> str += useItem(6500, "damage", 25, "Browning Auto-5");
            case "Elephant Rifle" -> str += useItem(15750, "damage", 30, "Elephant Rifle");
            case "M198 Howitzer" -> str += useItem(112007, "damage", 100, "M198 Howitzer");
            default -> str += "   That is not an item on the catalogue";
        }

        if (str.equals("   You do not have enough money to buy that item.") || str.equals("   That is not an item on the catalogue")){
            str += "";
        }
        else if (choice.equals("Apple") || choice.equals("Loaf of Bread") || choice.equals("Steak")){
            str += "\n   Your previous health was " + preHealth + ".\n   Your current health is " + health + ".";
        }
        else if (choice.equals("Cloth armor") || choice.equals("Sturdy armor") || choice.equals("Medieval armor")){
            str += "\n   Your previous max health was " + preMaxHealth + ".\n   Your current health is " + maxHealth + ".";
        }
        else {
            str += "\n   Your previous damage was " + preDamage + ".\n   Your current damage is " + damage + ".";
        }
        return str;
    }

    /**
     * The showBountyList method references the bountyInfo instance variable and uses the words in that String to determine
     * which version of the bounty list to print based on what had happened in the game so far
     *
     * @return returns a String which is the bounty list
     */
    public String showBountyList(){
        //I use a shorthand for an if - elseif - else block here to try out something new, this is equivalent to doing so otherwise
        String str = "   _____________________________________\n   |  Open Bounties:                   |\n";
        str += bountyInfo.contains("Member 1") ? "   | Member 1         -           PAID |\n" : (bountyInfo.contains("Member dead1") ? "   | Member 1         -       EXECUTED |\n" :  "   | Member 1         -   1250 dollars |\n");
        str += bountyInfo.contains("Member 2") ? "   | Member 2         -           PAID |\n" : (bountyInfo.contains("Member dead2") ? "   | Member 2         -       EXECUTED |\n" :  "   | Member 2         -   7500 dollars |\n");
        str += bountyInfo.contains("Member 3") ? "   | Member 3         -           PAID |\n" : (bountyInfo.contains("Member dead3") ? "   | Member 3         -       EXECUTED |\n" :  "   | Member 3         -  25000 dollars |\n");
        str += bountyInfo.contains("Member 4") ? "   | Member 4         -           PAID |\n" : (bountyInfo.contains("Member dead4") ? "   | Member 4         -       EXECUTED |\n" :  "   | Member 4         -  55000 dollars |\n");
        str += "   _____________________________________\n   (5 to go back)";
        return str;
    }

    /**
     * The payBounty method references the instance variable bountyInfo and money to determine if the user can buy the
     * bounty of the member he is trying to buy
     *
     * @param price represents the price of the member
     * @param memberName represents the name of the member
     * @param gainedHonor represents how much honor the user gains from buying the bounty of the member
     * @return returns a String containing the results of the user's attempt to buy the bounty of a member
     */
    public String payBounty(int price, String memberName, int gainedHonor){
        String str = "";
        if ((bountyInfo.contains(memberName))){
            str += "   You have already paid this bounty";
        }
        //A member is remembered as executed by using a format, memberdead1, where 1 can be any number
        //this condition turns the name of the member and puts it into that format
        else if (bountyInfo.contains(memberName.substring(0, 7) + "dead" + memberName.substring(7))){
            str += "   This character has been executed";
        }
        else {
            if (money < price){
                str += "   You do not have enough money to pay off this bounty";
            }
            else {
                str += "   You buy the bounty of " + memberName + ", he is very happy. You become more honorable";
                money -= price;
                changeHonor(gainedHonor);
                peopleSaved++;
                switch (memberName){
                    case "Member 1" -> bountyInfo += "Member 1";
                    case "Member 2" -> bountyInfo += "Member 2";
                    case "Member 3" -> bountyInfo += "Member 3";
                    case "Member 4" -> bountyInfo += "Member 4";
                }
            }
        }
        return str;
    }

    /**
     * The chooseBounty method allows the user to choose a bounty of pay off, it then runs the appropriate version of
     * the payBounty method
     *
     * @param choice represents which bounty the user wants to pay off
     * @return returns a String with the results of the user's attempt to pay a bounty
     */
    public String chooseBounty(int choice){
        String str = "";
        switch (choice){
            case 1 -> str += payBounty(1250, "Member 1", 25);
            case 2 -> str += payBounty(7500, "Member 2", 35);
            case 3 -> str += payBounty(25000, "Member 3", 50);
            case 4 -> str += payBounty(55000, "Member 4", 75);
        }
        return str;
    }

    /**
     * The executionCheck method checks if a member should be executed based on the day and then informs the user of
     * their execution white remembering the character has been executed by adding to the instance variable bountyInfo
     *
     * @param dayCounter represents the value of the dayCounter variable located in the Runner class
     * @return returns a String detailing the execution of a certain member
     */
    public String executionCheck(int dayCounter){
        String str = "";
        if (!bountyInfo.contains("Member 1") && day == 9){
            bountyInfo += "Member dead1";
            str += "\n\n   In the unforgiving expanse of the Wild West, Member 1 met his grim fate as the hangman's noose tightened around his neck.";
            str += "\n   Swaying in the dusty wind, a silent testament to the justice sought for his crimes.";
        }
        else if (!bountyInfo.contains("Member 2") && day == 14){
            bountyInfo += "Member dead2";
            str += "\n\n   Amidst the rugged backdrop of the Wild West, Member 2 faced the relentless punishment of a firing squad.";
            str += "\n   The crackling gunfire echoing through the vast, arid landscape, sealing the fate of a man condemned for his transgressions.";
        }
        else if (!bountyInfo.contains("Member 3") && day == 22){
            bountyInfo += "Member dead3";
            str += "\n\n   Beneath the scorching sun of the lawless frontier, Member 3 stood stoically before a hastily assembled makeshift gallows.";
            str += "\n   There a cold-hearted marshal delivered justice with a swift and final swing of the frontier's unforgiving axe.";
        }
        else if (!bountyInfo.contains("Member 4") && day == 28){
            bountyInfo += "Member dead4";
            str += "\n\n   In the harsh desolation of the Wild West, Member 4 found himself bound to a weathered post, his fate sealed by the ominous rattle of a hangman's roulette.";
            str += "\n   The metallic clicks echoing the irreversible countdown to the ultimate punishment for his sins.";
        }
        if (dayCounter % 2 != 0){
            str = "";
        }
        return str + "\n";
    }

    /**
     * The toString will print once the game has ended and will compare the user's stats to my best stats to give users
     * something to compare too
     *
     * @return returns a String comparing my best stats with the user's current stats
     */
    public String toString() {
        String str = "\n   ____________________________\n    My best stats:\n    Health: 255/255\n    Money: 34527\n    Honor: 100\n    Damage: 100\n    People Saved: 3";
        str += getStats() + "\n    How did you do?";
        return str;
    }

    /**
     * The endGame method will return a specific ending based on the user's honor and how many people they have saved. The
     * value of endCondition is used to determine what type of ending to display
     *
     * @param endCondition represents under what condition the game has ended
     * @return returns a String detailing the end of the character's journey
     */
    public String endGame(String endCondition){
        String str = "";
        if (endCondition.equals("died in battle")) {
            str += "\n                                                                You died fighting";
        }
        if (endCondition.equals("ended early") || endCondition.equals("day20")) {
            if (honor <= -50){
                switch (peopleSaved){
                    case 0 -> str = """
                           The Forsaken Path
                           - Honor: Low, Gang Members Saved: 0
                                                
                           The relentless pursuit of a dishonorable life catches up with you. Cornered in a desolate canyon, former allies and the law close in.
                           In the final confrontation, you fight fiercely but are overwhelmed, meeting a bitter end.
                           The once-feared outlaw becomes a cautionary tale of the consequences of treachery and betrayal.
                        """;
                    case 1 -> str = """
                           Fleeting Hope
                           - Honor: Low, Gang Members Saved: 1
                                                
                           With low honor, you manage to save one gang member, but the rest meet a grim fate.
                           As you disappear into the wilderness, the echoes of lost camaraderie haunt your every step.
                           The fleeting moment of redemption becomes a distant memory, forever out of reach as you wander alone.
                        """;
                    case 2 -> str = """
                           Shattered Allegiance
                           - Honor: Low, Gang Members Saved: 2
                           
                           The low honor and attempt at loyalty lead to a tragic conclusion. Betrayed by those you spared, the town descends into chaos
                           The legacy of your outlaw life is forever stained with the blood of both friends and enemies, leaving behind a shattered brotherhood and a town scarred by your actions.
                        """;
                    case 3 -> str = """
                           Lone Wolf's Lament
                           - Honor: Low, Gang Members Saved: 3
                        
                           With low honor and three gang members saved, you become a solitary figure, wandering the unforgiving landscape.
                           The scars of your past weigh heavily as you reflect on the cost of your choices, forever lamenting the friends lost in a life of lawlessness, becoming a lone wolf with no pack left.
                        """;
                    case 4 -> str = """
                           The Desperado's Reckoning
                           - Honor: Low, Gang Members Saved: 4
                        
                           Embracing the dark side with low honor, you manage to save all gang members, solidifying your status as a notorious outlaw.
                           Chaos reigns in the town as you ride off into the sunset, leaving behind a legacy of destruction and defiance, a true desperado in every sense.
                        """;
                }
            }
            if (-50 < honor && honor < 50){
                switch (peopleSaved){
                    case 0 -> str = """
                           A Tarnished Legacy
                           - Honor: Medium, Gang Members Saved: 0
                           
                           Struggling with a lack of honor and unable to save any gang members, your character faces a bleak destiny.
                           The law catches up, and your story ends with a public execution. Your legacy is one of infamy and regret, a stark reminder of the consequences of a life lived without honor.
                        """;
                    case 1 -> str = """
                           A Fractured Brotherhood
                           - Honor: Medium, Gang Members Saved: 1
                           
                           Balancing on the edge of morality, you save one gang member, but the fragile loyalty among your comrades shatters.
                           The town becomes a battleground, and you are left to roam the wild west, forever haunted by the betrayal and the solitary figure you've become, a fractured soul in a fractured world.
                        """;
                    case 2 -> str = """
                           A Bitter Victory
                           - Honor: Medium, Gang Members Saved: 2
                        
                           Amidst moderate honor, saving two gang members brings a bitter victory.
                           The surviving members part ways, and you fade into obscurity, a hollow triumph echoing through the vast, unforgiving landscape.
                           The bitterness of the victory lingers, a constant reminder of the high cost of survival.
                        """;
                    case 3 -> str = """
                           The Crossroads
                           - Honor: Medium, Gang Members Saved: 3
                           
                           Navigating the murky waters of moderate honor and the salvation of three gang members, your character stands at a crossroads.
                           A final showdown with the law determines whether you continue down the path of redemption or succumb to the darkness that lingers within.
                           Your future is left open to the uncertain winds of fate.
                        """;
                    case 4 -> str = """
                           The Price of Redemption
                           - Honor: Medium, Gang Members Saved: 4
                           
                           With moderate honor and all gang members saved, you pay a heavy price for redemption.
                           Sacrifices are made, but the town begins to heal. Your character, forever changed, seeks a new life, leaving behind the outlaw persona.
                           The price paid for redemption becomes a defining chapter in your character's story.
                        """;
                }
            }
            if (honor >= 50){
                switch (peopleSaved){
                    case 0 -> str = """
                           An Honorable End
                           - Honor: High, Gang Members Saved: 0
                           
                           Despite high honor, the inability to save any gang members leads to a tragic end.
                           The townsfolk, though acknowledging your morality, cannot forgive the crimes of the past.
                           The law closes in, and your story concludes with an honorable but somber farewell.
                        """;
                    case 1 -> str = """
                           The Lone Paragon
                           - Honor: High, Gang Members Saved: 1
                           
                           With high honor and only one gang member saved, your character becomes a lone paragon of justice.
                           The town flourishes, but the loneliness becomes overwhelming as you grapple with the high cost of your ideals.
                           The path of honor, while noble, leaves your character standing alone at the pinnacle of righteousness.
                        """;
                    case 2 -> str = """
                           A Noble Sacrifice
                           - Honor: High, Gang Members Saved: 2
                           
                           In a selfless act of redemption, you save two gang members with high honor. However, the sacrifices made lead to a bittersweet ending.
                           The town prospers, but your character faces a difficult choice that leaves a lasting impact on the wild west.
                           The noble sacrifice becomes a beacon of inspiration for the town, a symbol of hope in the face of adversity.
                        """;
                    case 3 -> str = """
                           A Beacon of Light
                           - Honor: High, Gang Members Saved: 3
                           
                           Guided by high honor, saving three gang members transforms your character into a beacon of light in the lawless west.
                           The town thrives, and your legacy becomes one of inspiration and hope for future generations.
                           Your character's journey inspires others to choose the path of honor, marking the beginning of a new era in the wild west.
                        """;
                    case 4 -> str = """
                           The Hero's Triumph
                           - Honor: High, Gang Members Saved: 4
                           
                           With high honor and all gang members saved, your character achieves a heroic triumph.
                           The town blossoms under your influence, and you retire as a celebrated figure, leaving behind a legacy of redemption and justice in the once lawless land.
                           The hero's triumph becomes a beacon of hope for the wild west, proving that even the most hardened outlaw can find redemption.
                        """;
                }
            }
            gameOver = true;
        }
        str += "\n                                                             This concludes the legend of " + name;
        str += "\n___________________________________________________________________________________________________________________________________________________________________________";
        str += "\n\n" + toString();
        return str;
    }
}