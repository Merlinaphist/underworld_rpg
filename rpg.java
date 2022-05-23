import java.util.*;
import java.io.*;

class Underworld{
    public static void main(String[] args){ // Main Interface
        Char player = new Char();
        player.coordinate[0]=0;
        player.coordinate[1]=0;
        player = character_design(player);
        System.out.print("\nHello "+player.name+", your character has been initialized:\n\n");
        view_character(player);
        promptEnterKey();
        storybackground();
        promptEnterKey();
        player = choose_career(player);
        promptEnterKey();
        show_vision(player.coordinate);
        Boolean game_clear = false;
        String cmd;
        Scanner scanner= new Scanner(System.in);
        while (game_clear==false){
            System.out.print("What do you like to do? (Type 'help' to see player manual.)\n\n");
            cmd = scanner.nextLine();
            if (cmd.equals("help")){
                print_manual();
            }if (cmd.equals("mission")){
                if ((player.coordinate[0]==0)&&(player.coordinate[1]==0)){
                    player=mission(player);  //TBD
                }else{
                    System.out.print("Not in village!");
                }
            }else if (cmd.equals("heal")){
                player.HP=player.HP_lim;
                player.MP=player.MP_lim;
                System.out.println("You have been treated!");
            }else if(cmd.equals("east")){
                player.coordinate[0]+=1;
                show_vision(player.coordinate);
                player=encounter_monster(player);
            }else if(cmd.equals("west")){
                player.coordinate[0]-=1;
                show_vision(player.coordinate);
                player=encounter_monster(player);
            }else if(cmd.equals("north")){
                player.coordinate[1]+=1;
                show_vision(player.coordinate);
                player=encounter_monster(player);
            }else if(cmd.equals("south")){
                player.coordinate[1]-=1;
                show_vision(player.coordinate);
                player=encounter_monster(player);
            }else if(cmd.equals("vision")){
                show_vision(player.coordinate);
            }else if(cmd.equals("status")){
                view_character(player);
            }else if(cmd.equals("location")){
                System.out.print("("+player.coordinate[0]+", "+player.coordinate[1]+")\n");
            }else if(cmd.equals("map")){
                System.out.print("Forest-Forest--Forest\n  |      |      |\nForest-Village-Forest-River-Mountain-Cave\n  |      |      |\nForest-Forest--Forest\n\n");
            }else if(cmd.equals("exit")){
                System.out.print("You sure you want to quit? (Your current progress will not be saved.)\n");
                Scanner sc= new Scanner(System.in);
                Boolean invalid = true;
                String choice;
                while (true){
                    System.out.print("ENTER: yes or no.\n");
                    choice = scanner.nextLine();
                    if (choice.equals("yes")){
                        System.out.print("Thank you so much for playing our game! The authors want to parody the game UNDERTALE through this project, to show admire on this kind of 2D RPG game.");
                        System.exit(0);
                    }else if (choice.equals("no")){
                        break;
                    }else {
                        System.out.print("Invalid input.\n");
                    }
                }
            }
        }
        ending(player);
        return;
    }

    public static void show_vision(int[] args){
        if ((args[0]==0)&&(args[1]==0)){
            System.out.print("Welcome to Village\n");
            System.out.print("##################################-----------------#######################################\n#        #          #                                           #       #       #        #\n#        #          #         #####               #####         #       #       #        #\n#        #          #         #   #               #   #         #       #       #        #\n#        ############         #####               #####         ##########################\n#########                                                                                |\n|                                                                                        |\n|                                 #################                                      |\n|               #####             #               #             #####                    |\n|               #   #             # Baron's House #             #   #                    |\n|               #####             #               #             #####                    |\n|                                 #################                                      |\n#########                                                                                |\n#       ############          #####               #####         ##########################\n#       #          #          #   #               #   #         #       #       #        #\n#       #          #          #####               #####         #       #       #        #\n#       #          #                                            #       #       #        #\n##################################-----------------#######################################\n\n");
        }else if ((args[0] == 2)&&(args[1]==0)){
            System.out.print("Welcome to Riverside\n");
            System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ River Bank ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n                                                                                                                                                    ^^^^                   \n                                                                                                                                                    ^^^^                   \n                                                            ^^^^^^^^^^^                                                                                                  \n         ^^^^^^^^^                                    ^^^^^^^^^^^                                                                                                 \n         ^^^^^^^^^                                                                                                   ^^^^^^^                                         \n                                                                                                                           ^^^^^^^                                         \n                                                                                                                                                                                \n###########################——————#############—————————#####################\n\n");
            // print river vision
        }else if ((args[0] == 3)&&(args[1]==0)){
            System.out.print("Welcome to Mountain\n");
            System.out.print("                                                                                 / |                                                                                                    \n                                                                                /   |                                                         / |                                       \n                                                                               /     |                                                      /    |                                       \n                                                                              /        |                          / |                     /       |                                      \n                                                                            /           |                       /    |                  /          |                                     \n                                                                           /             |                    /       |               /             |                                   \n                                                                          /               |                /           |            /                |                                  \n             ^^^^^^^^^^^^^^^^^^^^                          /                 |             /               |        /                   |                                 \n             |________________|                        /                    |         /                    |     /                     |                                \n            /                                  |                     /                       |      /                       |_/                        |                              \n           /                                   |                    /                         |   /                                                      |                             \n          /                                    |                 /                             |/                                                         |                            \n         /                                      |              /                                                                                           |                           \n        /                                       |            /                                                                                              |_______            \n       /                                         |        /                                                                                                                 |          \n      /                                           |     /                                         Mountain                                                            |         \n     /                                             |_/                                                                                                                      |_         \n\n");
            // print mountain vision
        }else if ((args[0] == 4)&&(args[1]==0)){
            System.out.print("Welcome to Cave\n");
            System.out.print("######################################—————————————####################################\n######################################                                               ####################################\n########## ############ ###############                                                  ############ ######### ############\n######### ############ ##############                                                        ############ ######### ###########\n######## ############ #############                                                               ########### ######### ###########\n####### ############ ############                                                                   ############ ######### ##########\n###### ############ ############                                                                       ############ ######### #########\n##### ############# ############                                 Cave                               ############ ######### ########\n##### ############# ############                                                                        ############# ######### ########\n##### ############# ############                                                                        ############# ######### ########\n##### ############## ############                                                                      ############# ######### ########\n###### ############## ############                                                                    ############ ######### #########\n####### ############## ############                                                                 ############ ######### #########\n####### ############## #############                                                             ############ ######### ##########\n######## ############### #############                                                         ########### ########## ##########\n######## ############### #############                                                     ############ ######### ############\n######### ############### #############                                                 ############ ######### #############\n######################################—————————————####################################\n\n");
            // print cave vision
        }else{
            System.out.print("Welcome to Forest\n");
            System.out.print("######################################—————————————####################################\n                                                                                                                                                                                                  \n                ******                        *******                                    *******                                                 ********                *******     \n                ******                        *******                                    *******                                                 ********                *******     \n                ******                        *******                                    *******                                                 ********                *******     \n                                                                                                                                                                                                   \n                                                                                                                                                                                                   \n                ******                        *******                                    *******                                                 ********                *******     \n                ******                        *******                                    *******                                                 ********                *******     \n                ******                        *******                                    *******                   Forest                    ********                *******     \n                                                                                                                                                                                                   \n                ******                        *******                                    *******                                                 ********                *******     \n                ******                        *******                                    *******                                                 ********                *******     \n                ******                        *******                                    *******                                                 ********                *******     \n                                                                                                                                                                                                   \n                ******                        *******                                    *******                                                 ********                *******     \n                ******                        *******                                    *******                                                 ********                *******     \n                ******                        *******                                    *******                                                 ********                *******     \n                                                                                                                                                                                                   \n######################################—————————————####################################\n\n");
            // print forest vision
        }
        return;
    }

    public static Char character_design(Char args){ // Custom function for design character
        System.out.print("\n##############################\n# Welcome to Underworld 101! #\n##############################\n\n");
        Scanner sc= new Scanner(System.in);
        Char player=args;
        System.out.print("Enter character name: ");  
        player.name= sc.nextLine();
        return player;
    }

    public static void view_character(Char args){ // Custom function for player
        Char player=args;
        String frame = "*";
        for (int i=-1;i<player.name.length();i++){
            frame+="*";
        }
        System.out.print(frame+"**\n"+"* "+player.name+" *\n"+frame+"**\n\n"+"Career: "+player.career+"\nlevel: "+player.level+"\nHP: "+player.HP+"/"+player.HP_lim+"\nMP: "+player.MP+"/"+player.MP_lim+"\nAP: "+player.AP+"\nSTR: "+player.STR+"\nDEX: "+player.DEX+"\nDEF: "+player.DEF+"\nMDF: "+player.MDF+"\n\n");
        return;
    }

    public static void storybackground(){
        System.out.print("You accidentally fell into a hole, and you passed out... \n...\nYou opened your eyes, found yourself alive, lying on a bed in a wooden house.\n...\nSomething came closer to you, it introduced itself as the village leader. And you, you are in the underworld now.\n\nSince the village leader saved your little cutie life, you were asked to kill 10 Goblins in the underworld as the pay-back. \n\nThese Goblins often come to the village, harrass residents lives.\n\nA trouble for this village.\n\n...\n...\n\nNow you are sober, you find out the village leader seems not a human being.\nBut that doesnt matter at all.\nIt does not hurt you anyway.\n...\n...\nThe village leader interrupts your thinking:\n\nThere is treasure somewhere in the underworld. I also hope you can go and find it.\n\n...\n...\n\n");
        return;
    }

    public static Char choose_career(Char args){ // choose career here
        Char player=args; // TBF
        Scanner scanner = new Scanner(System.in);
        String choice;
        System.out.println("You show interest in searching for the treasure. The villiage leader delightedly offers the best weapons in the village as an investment, but you will need to divide the treasure with him. He asks which weapon would suit you better?");
        while (true){
            System.out.print("Choose your weapon: Sword or Wand?\n");
            choice = scanner.nextLine();
            if (choice.equals("Sword")){
                player.career="Warrior";
                player.HP_lim+=50;
                player.HP+=50;
                player.STR+=10;
                player.DEX+=1;
                System.out.print("Now that you've chosen sword, you career is warrior.\n");
                break;
            }else if (choice.equals("Wand")){
                player.career="Wizard";
                player.MP_lim+=50;
                player.MP+=50;
                player.AP+=10;
                player.DEX+=1;
                System.out.print("Now that you've chosen wand, you career is wizard.\n");
                break;
            }else {
                System.out.print("Invalid input.\n");
            }
        }
        System.out.println("It is time for you to start your mission!");
        view_character(player);
        return player;
    }

    public static void promptEnterKey(){
        System.out.println("Press any key to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void print_manual(){
        System.out.print("Type 'help' to show the manual.\n\n Type 'mission' to see the background and your mission. *** Mission is only visible when you are at village *** \n\n Type 'vision' to show your current spot. \n\n Type 'heal' in village to recover your HP & MP. \n\n Type 'map' to show the overall map of this game. \n\n Type 'status' to show your character's attributions. \n\n Type 'location' to show your current location throughout the whole map. \n\n Type 'east' to go toward east. \n\n Type 'west' to go toward west. \n\n Type 'north' to go toward north. \n\n Type 'south' to go south. \n\n Type 'exit' to quit the game. \n\n");
        return;
    }

    public static Char encounter_monster(Char args){ 
        Char player=args;
        if (player.boss_killed == false){
            if ((args.coordinate[0]==0)&&(args.coordinate[1]==0)){
                return player;
            // no monster in villiage
            }else if ((args.coordinate[0] == 2)&&(args.coordinate[1]==0)){
                Char monster1 = new Char();
                Char monster2 = new Char();
                Char[] rivals= new Char[3];
                System.out.print("You encounter 2 monsters!\n");
                rivals[0]=player;
                rivals[1]=monster1;
                rivals[2]=monster2;
                player = battle(rivals); // TBF
                // riverside 2 monster
            }else if ((args.coordinate[0] == 3)&&(args.coordinate[1]==0)){
                Char monster1 = new Char();
                Char monster2 = new Char();
                Char monster3 = new Char();
                System.out.print("You encounter 3 monsters!\n");
                Char[] rivals= new Char[4];
                rivals[0]=player;
                rivals[1]=monster1;
                rivals[2]=monster2;
                rivals[3]=monster3;
                player = battle(rivals);
                // mountain 3 monster
            }else if ((args.coordinate[0] == 4)&&(args.coordinate[1]==0)){
                Char monster1 = new Char();
                Char monster2 = new Char();
                Char monster3 = new Char();
                Char monster4 = new Char();
                Char boss = new Char();
                System.out.print("You encounter 4 monsters and a Boss!\n");
                Char[] rivals= new Char[6];
                rivals[0]=player;
                rivals[1]=monster1;
                rivals[2]=monster2;
                rivals[3]=monster3;
                rivals[4]=boss;
                boss.HP_lim = 200;
                boss.HP = 200;
                boss.MP_lim = 200;
                boss.MP = 200;
                boss.AP = 15;
                boss.STR = 15;
                boss.DEF = 5;
                boss.MDF = 5;
                boss.name = "kuntukutakatikaotesuwaxilasong";
                player = battle(rivals);
                // cave 4 monsters and 1 boss
            }else{
                Char monster1 = new Char();
                System.out.print("You encounter 1 monsters!\n");
                Char[] rivals= new Char[2];
                rivals[0]=player;
                rivals[1]=monster1;
                player = battle(rivals);
                // forest 1 monster
            }
        }
        return player;
    }
    public static Char mission(Char args){
        Char player=args; // TBF
        if (player.mission1_complete==false){
            if (player.goblin_killed == 10){
                player.mission1_complete=true;
                System.out.println("The village leader is really grateful to you for killing the goblins. He provides you with alcoholic drinks.");
                player.exp+=100;
                int needExp = 0;
                for (int i = 1; i < player.level; i++) {
                    needExp += i * 50;
                }
                if (player.exp > needExp) {
                    player = addLevel(player);
                }
            }else{
                System.out.println("Please continue killing up to 10 Goblins.");
            }
        }else if (player.boss_killed==true){
            System.out.println("The village leader is surprised that you truly find the treasure. Then, all of a sudden, he starts to attack you!\n\n......\n\n");
            player.game_clear = true;
        }
        return player;
    }
    
    public static Char battle(Char[] args){
        Char player=args[0];
        int i = 1;
        Char enemy = args[i];
        enemy.name = "Goblin";
        int damage = 0;
        Random generator=new Random();
        double key = generator.nextDouble();
        Scanner scanner = new Scanner(System.in);
        String cmd;
        while (true){
            // player's round
            while (true){
                System.out.println("What's your next action?");
                System.out.println("You can choose: attack, skill, defend, escape, heal.");
                cmd = scanner.nextLine();
                damage = 0;
                if (cmd.equals("attack")){
                    if (key <= 0.05){
                        if (player.career.equals("Warrior")){
                            enemy.HP-=player.STR*1.5;
                        }else{
                            enemy.HP-=player.AP*1.5;
                        }
                        System.out.println("Crit!");
                    }else{
                        if (player.career.equals("Warrior")){
                            enemy.HP-=player.STR;
                        }else{
                            enemy.HP-=player.AP;
                        }
                    }
                    break;
                }else if (cmd.equals("skill")){
                    if (player.MP>=5){
                        if (key <= 0.05){
                            if (player.career.equals("Warrior")){
                                enemy.HP-=player.STR*3;
                            }else{
                                enemy.HP-=player.AP*3;
                            }
                            System.out.println("Crit!");
                        }else{
                            if (player.career.equals("Warrior")){
                                enemy.HP-=player.STR*2;
                            }else{
                                enemy.HP-=player.AP*2;
                            }
                        }
                        player.MP-=5;
                        break;
                    }else{
                        System.out.println("MP Inadequate!");
                    }
                }else if (cmd.equals("defend")){
                    damage-=(player.DEF+player.MDF)*0.5;
                    break;
                }else if (cmd.equals("escape")){
                    if (player.DEX>enemy.DEX){
                        System.out.println("You escape back to the village.");
                        player.coordinate[0]=0;
                        player.coordinate[1]=0;
                        show_vision(player.coordinate);
                        return player;
                    }else{
                        System.out.println("Escape failed!");
                    }
                    break;
                }else if (cmd.equals("heal")){
                    if (player.MP>=10){
                        player.HP+=20;
                        player.MP-=10;
                        if (player.HP>player.HP_lim){
                            player.HP=player.HP_lim;
                        }
                    }else{
                        System.out.println("MP Inadequate!");
                    }
                    break;
                }else{
                    System.out.println("Invalid Input.");
                }
            }
            // monster action
            key = generator.nextDouble();
            if (key<=0.33){
                key = generator.nextDouble();
                if (key <= 0.05){
                    damage+=(enemy.STR+enemy.AP)*1.5*0.5;
                    player.HP-=damage;
                    System.out.println("Enemy Attack Crit!");
                }else{
                    damage+=(enemy.STR+enemy.AP)*0.5;
                    player.HP-=damage;
                    System.out.println("Enemy Attack!");
                }
            }else if (key <= 0.67){
                key = generator.nextDouble();
                if (key <= 0.05){
                    damage+=(enemy.STR+enemy.AP)*1.5;
                    player.HP-=damage;
                    System.out.println("Enemy Skill Crit!");
                }else{
                    damage+=(enemy.STR+enemy.AP);
                    player.HP-=damage;
                    System.out.println("Enemy Skill!");
                }
            }else{
                enemy.HP+=5;
                System.out.println("Enemy Heal +5 HP!");
                if (enemy.HP>enemy.HP_lim){
                    enemy.HP=enemy.HP_lim;
                }
            }
            if (player.HP<=0){
                System.out.println("You are defeated!");
                System.out.println("Your body lying on the ground, passing-by villagers find you and take you back to the village.");
                Char newplayer = new Char();
                newplayer.name = player.name;
                newplayer.career = player.career;
                newplayer.coordinate[0]=0;
                newplayer.coordinate[1]=0;
                show_vision(newplayer.coordinate);
                return newplayer;
            }
            if (enemy.HP<=0){
                i++;
                if (i == args.length){
                    if (enemy.name.equals("kuntukutakatikaotesuwaxilasong")){
                        player.boss_killed=true;
                        System.out.println("Now you defeat the boss. You find the treasure, a shiny ring made of unknown metal. Lettering on it says: 'The One Ring.'\nIt is time to take it back to village.");
                    }else if (enemy.name.equals("Villiage Leader")){
                        System.out.println("You are dead.");
                        System.exit(0);
                    }
                    break;
                }else if (i == 4){
                    System.out.println("Boss!");
                    enemy = args[i];
                }else{
                    player.goblin_killed++;
                    System.out.println("Next Monster!");
                    enemy = args[i];
                    enemy.name = "Goblin";
                }
                continue;
            }
            view_character(player);
            view_character(enemy);
        }
        System.out.println("Success!");
        player = addExp(args);
        return player;
    }

    public static Char addLevel(Char args) {
        Char player = args;
        player.HP_lim+=5; 
        player.MP_lim+=5;
        player.level++;
        player.HP = player.HP_lim;
        player.MP = player.MP_lim;
        player.AP++;
        player.STR++;
        player.DEF++;
        player.MDF++;
        view_character(player);
        System.out.println("[-]" + "level Up! The current level is:" + player.level);
        return player;
    }


    public static Char addExp(Char[] args) {
        Char player = args[0];
        if (args.length <= 4){
            player.exp += (args.length-1)*20;
        }else{
            player.exp += 100;
        }
        int needExp = 0;
        for (int i = 1; i < player.level; i++) {
            needExp += i * 50;
        }
        if (player.exp > needExp) {
            player = addLevel(player);
        }
        return player;
    }

    public static void ending(Char player){
        Char baron = new Char();
        baron.HP_lim = 300;
        baron.HP = 300;
        baron.MP_lim = 300;
        baron.MP = 300;
        baron.AP = 20;
        baron.STR = 20;
        baron.DEX = 50;
        baron.DEF = 10;
        baron.MDF = 10;
        baron.name = "Villiage Leader";
        Char[] rivals= new Char[2];
        rivals[0] = player;
        rivals[1] = baron;
        battle(rivals);
        System.out.println("The village leader is defeated. It turns out that he is the servant of Satan. Five years ago, to save his badly-illed daughter, he made a deal with the devil. The devil demands him to hand in treasure in the mountain annually. That's why he attacks you: he wants to rob the ring, instead of sharing with you.");
        System.out.println("Do you want to kill him? Type 'yes' or 'no'.");
        Scanner scanner = new Scanner(System.in);
        String cmd;
        while (true){
            cmd = scanner.nextLine();
            if (cmd.equals("yes")){
                System.out.println("You killed the village leader, and started your journey to find your way home. That is another story...(Please wait for the next game).");
                System.exit(0);
            }else if (cmd.equals("no")){
                System.out.println("You forgive the leader, and promise to defeat the devil and save his daughter. The leader engaged you with his daughter, and then you start your new journey to find the devil. That is another story...(Please wait for the next game).");
                System.exit(0);
            }
        }
    }
}

class Char{
    int HP=80;
    int HP_lim=80;
    int MP = 80;
    int MP_lim=80;
    int AP=5;
    int STR=5;
    int DEX=5;
    int DEF=1;
    int MDF=1;
    int exp=0;
    int level=0;
    String career="Undecided";
    String name;
    int[] coordinate = new int[2];
    Boolean mission1_complete=false;
    Boolean game_clear=false;
    int goblin_killed = 0;
    Boolean boss_killed = false;
}
