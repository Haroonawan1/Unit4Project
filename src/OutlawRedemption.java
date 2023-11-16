public class OutlawRedemption {
    private String name;
    private int health;
    private int maxHealth;
    private int money;
    private int honor;
    private int day;
    private int saved;
    private boolean gameOver;
    private int damage;
    private String itemsBought;
    private String bountyInfo;

    Images image = new Images();

    public OutlawRedemption (String n){
        name = n;
        health = 100;
        maxHealth = 100;
        money = 0;
        honor = 0;
        day = 1;
        saved = 0;
        gameOver = false;
        damage = 10;
        itemsBought = "";
        bountyInfo = "";
    }

    public boolean isInputNumerical(String input){
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

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

    public String fightEnemy(String name, int enemyHealth, int enemyDamage){
        String str = "\n   You begin fighting a " + name + "\n";
        while (health > 0 && enemyHealth > 0) {
            enemyHealth -= damage;
            health -= enemyDamage;
        }
        if (enemyHealth <= 0){
            str += "   You won your fight against the " + name + "!\n   Remaining Health: " + health + "\n";
        }
        if (health <= 0){
            str += endGame("died in battle");
            gameOver = true;
        }
        return str;
    }

    public String fightGroup(int x){
        String str = "";
        while (x > 0 && !gameOver){
            switch ((int) (Math.random() * 3) + 1){
                case 1 -> str += fightEnemy("Bandit", 50, 1);
                case 2 -> str += fightEnemy("Law Enforcer", 75, 2);
                case 3 -> str += fightEnemy("Bounty Hunter", 100, 4);
            }
            x--;
        }
        return str;
    }

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
            setHonor(-5);
        }
        else {
            str += "   You search for a stagecoach and find nothing interesting except for 45 dollars on the floor.\n";
            moneyFound += 10;
            setHonor(-3);
        }
        if (!gameOver) {
            money += moneyFound + 35;
            str += "\n   Money Found: " + (moneyFound + 35) + "\n";
        }
        return str;
    }

    public String robTrain(String t){
        String str = image.getRobTrainImage();
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

    public String robBank(String t){
        String str = image.getRobBankImage();
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

    public String commitCrime(int x, String t){
        String str = "";
        switch (x){
            case 1 -> str = robStageCoach();
            case 2 -> str = robTrain(t);
            case 3 -> str = robBank(t);
            default -> System.out.print("\n   (One the two is not an acceptable input)");
        }
        return str;
    }

    public String giveHint(){
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
            setHonor(10);
        }
        else {
            str += image.getGiveARideImage2() + "\n   You did not find anyone to help.";
            setHonor(5);
        }
        return str;
    }

    public boolean isDonatePossible(int x){
        money -= (x <= money) ? x : 0;
        return x <= money;
    }

    public String donateMoney(int x){
        String str = "";
        if (isDonatePossible(x)){
            str += image.getDonateMoneyImage() + "\n   You choose to donate " + x + " dollars to those in need. Gratitude and honor light up the faces of the recipients as they express heartfelt thanks.";
            str += "\n   Their eyes reflect a mix of surprise and appreciation for the unexpected act of kindness.";
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
                str += "\n   As you prepare to leave, a weather-beaten homeless man, cloaked in tattered layers, weaves through the bustling corner.";
                str += "\n   Their eyes reflecting both resilience and hardship, they catch your attention before you can leave.\n" + giveHint();
            }
        }
        else{
            str += "\n   You do not have the amount of money you want to donate.";
        }
        return str;
    }

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
        setHonor(8);
        return str;
    }

    public String doAGoodDeed(int x, int m){
        String str = "";
        switch (x){
            case 1 -> str = giveARide();
            case 2 -> str = donateMoney(m);
            case 3 -> str = stopAFight();
        }
        return str;
    }

    public String useItem(int price, String statAffected, int change, String itemName){
        String str = "";
        if (price >= money){
            str += "   You do not have enough money to buy that item";
        }
        else if (itemsBought.contains(itemName)){
            str += "\n   (You already bought this item and were not charged, duplicate armors and weapons cannot be obtained)";
        }
        else {
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

    public String buyItem(String s){
        String str = "";
        int preHealth = health;
        int preDamage = damage;
        int preMaxHealth = maxHealth;

        switch (s) {
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

    public String showBountyList(){
        String str = "   _____________________________________\n   |  Open Bounties:                   |\n";
        str += bountyInfo.contains("Member 1") ? "   | Member 1         -           PAID |\n" : (bountyInfo.contains("Member dead1") ? "   | Member 1         -       EXECUTED |\n" :  "   | Member 1         -   1250 dollars |\n");
        str += bountyInfo.contains("Member 2") ? "   | Member 2         -           PAID |\n" : (bountyInfo.contains("Member dead2") ? "   | Member 2         -       EXECUTED |\n" :  "   | Member 2         -   7500 dollars |\n");
        str += bountyInfo.contains("Member 3") ? "   | Member 3         -           PAID |\n" : (bountyInfo.contains("Member dead3") ? "   | Member 3         -       EXECUTED |\n" :  "   | Member 3         -  25000 dollars |\n");
        str += bountyInfo.contains("Member 4") ? "   | Member 4         -           PAID |\n" : (bountyInfo.contains("Member dead4") ? "   | Member 4         -       EXECUTED |\n" :  "   | Member 4         -  55000 dollars |\n");
        str += "   _____________________________________\n   (5 to go back)";
        return str;
    }

    public String payBounty(int price, String member, int gainedHonor){
        String str = "";
        if ((bountyInfo.contains(member))){
            str += "   You have already paid this bounty";
        }
        else if (bountyInfo.contains(member.substring(0, 7) + "dead" + member.substring(7))){
            str += "   This character has been executed";
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
                    case "Member 1" -> bountyInfo += "Member 1";
                    case "Member 2" -> bountyInfo += "Member 2";
                    case "Member 3" -> bountyInfo += "Member 3";
                    case "Member 4" -> bountyInfo += "Member 4";
                }
            }
        }
        return str;
    }

    public String chooseBounty(int x){
        String str = "";
        switch (x){
            case 1 -> str += payBounty(1250, "Member 1", 25);
            case 2 -> str += payBounty(7500, "Member 2", 35);
            case 3 -> str += payBounty(25000, "Member 3", 50);
            case 4 -> str += payBounty(55000, "Member 4", 75);
        }
        return str;
    }

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

    public String endGame(String x){
        String str = "";
        if (x.equals("died in battle")) {
            str += "\n                                                                You died fighting";
        }
        if (x.equals("ended early") || x.equals("day20")) {
            if (honor <= -50){
                switch (saved){
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
                switch (saved){
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
                switch (saved){
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
        return str;
    }
}