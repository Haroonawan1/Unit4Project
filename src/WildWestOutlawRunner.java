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
                System.out.print("\n   What time will you attempt this: \n   (hours only, military time, example: 09:00)\n   >  ");
                String time = s.nextLine();
                System.out.println();
                System.out.println(outlaw.crime(Integer.parseInt(robberyChoice), time));
            }
            else if (action.equals("2")){

            }
            else if (action.equals("3")){
                System.out.println("                                                         ,,,,,,gggggggggggg,,,                                                       ,,,,,,,,,,,");
                System.out.println("                                                 ,gpN*\"\"``                   `\"\"*MNgg,                                   ,,,gp&NM**\"*\"`'   ` `\"'\"\"\"*MNmg,");
                System.out.println("                                             ,g@}@                                    \"\"MNwg,                     ,ggNM*\"'                               \"*Nwg,");
                System.out.println("                                           jP% $ @              Weapons for Sale:           '\"*Nmg,         ,gmN*\"`            Food and Armor:                '*$Ng");
                System.out.println("                                           ]C] $ @M%                                               \"*Ng,g@P*`                                                  $U[$BK");
                System.out.println("                                           ]P] $P  $    Colt Cobra - 175 dollars - 15 damage           ]L      Apple - 10 dollar - Recovers 15 health          $ @]jK");
                System.out.println("                                           ]@]P-  gP      Experience the legacy of American            ]L        Fresh and crunchy apples gathered from a      $ K]j@");
                System.out.println("                                           ]@F   @F       craftsmanship Sleek, precise, and reliable,  ]L        local orchid.                                 $ K]jK");
                System.out.println("                                           $\"  ]K]       a testament to Colt's excellence              ]P                                                      $ K]j@");
                System.out.println("                                          @    @ ]                                                     ]P      Loaf of Bread - 20 dollars -Recovers 45 health  $ K$ @");
                System.out.println("                                         @`   $  @F       M1903 Springfield - 955 dollars - 20 damage  ]P        Baked fresh every morning.                    $ P$ @");
                System.out.println("                                        @    #\" ]$L  g@@   Discover the M1903 Springfield rifle.       ]L                                                      $]L$ @");
                System.out.println("                                      ,@   ,@` )F]KgP ,F]   A timeless design and unmatched precision  ]L      Steak - 35 dollars - Recovers 90 health         $] $jK");
                System.out.println("                                     ,@ ,,gP ,gF $P'%N\"]   embody Springfield's enduring legacy.       ]L       Made from some yummy cows, make sure to give   $] @]L");
                System.out.println("                                     @    -\"%@,,@'    @F                                               ]L       the chef your compliments!                      $] @]");
                System.out.println("                                    $`       ,@\"    ,@   Browning Auto-5 - 6500 dollars - 25 damage    ]P                                                       $]J@$");
                System.out.println("                                    @       '\"     y@      Introducing the Browning Auto-5 shotgun     ]P     Cloth armor - 150 dollars - Max health +35        $]j@$");
                System.out.println("                                   ]@             g@        a triumph of engineering. Its iconic       ]@        Lightweight and fashionable while still        @]j@$");
                System.out.println("                                   ]P            ]@         humpback design and flawless performance    @        providing necessary protection                 @]jC$");
                System.out.println("                                   $P           #MK         define Browning's legacy.                   @                                                       @]jP$");
                System.out.println("                                   @            @ @                                                     @     Sturdy armor - 1545 dollars - Max Health + 55     @]]P$");
                System.out.println("                                  ]@            @ @    Elephant Rifle - 15750 dollars - 30 damage       $        Tough and strong, made with leather and        @]]P$");
                System.out.println("                                  @F           ]@ @      Unleash unparalleled power with our Elephant   $        chain-mail                                     jK]]P$");
                System.out.println("                                 gP            @$ $      King rifle. Crafted for the mightiest pursuits,$                                                       ]g$jP$");
                System.out.println("                               ,@\"            @L]F$     Command respect and conquer.                    $    Medieval armor - 26740 dollars - Max Health +65  ]@'\"\"\"\"@");
                System.out.println("                             ,@P             gML]F$                                                     $        Self-explanatory                       ,g,   ]@      %p");
                System.out.println("                           ,@P'            ,@@]P]P$F   M198 Howitzer - 112007 dollars - 100 damage      $                                              #FF*N, ]P\"**$,  ]g");
                System.out.println("                         ,@P-           ,g@P]P]K]P]P     ???                                            $                 ,,,gggmm@NNMMMMMNNNmgwg,,,   ]w],,$N]MNwg %,  '@");
                System.out.println("                         '             gP   ]P @]P]P   ,,,ggmNM***\"\"\"\"\"\"\"\"\"\"\"\"\"***MMNNmgg,,              @        ,,gpNM*\"\"`       ,,,,,,,,,,,,     `'\"\"*$ `- \"@g, ]g $   %g");
                System.out.println("                                    ,@\"    ]K @]KJMM\"\"`  ,,gggmmNNNNNMMMMMMMMMNmwwg,,,,  `'**MNwg,     @   ,gN**`     ,,gmNM**\"\"''``'` ,, `,,\"```\"\"\"*Mm $,    %@%, @P     ]@");
                System.out.println("                                    g@      ]K @ @ gmM**\",,,ggwwWmNNNNNNNNNNNmwwggg,,, `\"\"*MNNwg,  ^*Mwg@@P\"     ,gNM\"\" ,,ggpNNMM***\"*\"\"\"\"'''''\"\"\"\"**MM   %w    ]g]N`       ]L");
                System.out.println("                                  ,@\"       ]K $  ,gmM*\"\"`  ,,,,ggggggggggggg,,,,     '\"\"**Nmgg,-\"\"*        ,mM*',ggN*\"\"-                ,,,,,,,,,         ]g    ]@         ]@");
                System.out.println("                                 g@         ]@ `  ,,g@NMP*\"\"`\"`'             `\"`\"\"\"**MMN@ggg,, `'             m*\"    ,,,,ggmNMMM**\"\"\"\"\"\"'```````'\"\"\"\"\"**MNNmg@    `          $");
                System.out.println("                               ,@C                                                           `*%MN@          mmmmMM*\"\"`                                      ]@              ]@");
                System.out.println("                              g@                                                                  Mmmmwggm,gmM                                               $L              @");
                System.out.println("                            ,@F                                                                                                                               @              @L");
                System.out.println("                           g@                                                                                                                                %L            $@");
                System.out.println("                         ,@P                                                                                                                                  %g           @     ");
                System.out.println("                         ?                                                                                                                                     ]@          @");
                System.out.println("                                                                                                                                                                '@C        @");
                System.out.println("                                                                                                                                                                 $@        $r");
                System.out.println("                                                                                                                                                                 $K        ]@");

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
