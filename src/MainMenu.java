import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    public ArrayList<Champion> champList;
    public ArrayList<Weapon> weaponList;
    private boolean quit;
    Scanner input = new Scanner(System.in);
    public int champPicked;
    private boolean quitChampionManagement = false;
    private boolean quitWeaponManagement = false;

    public MainMenu() {
        champList = new ArrayList<>();
        weaponList = new ArrayList<>();
        quit = false;
    }

    void startMessage() {
        System.out.println("Welcome to the game" + "\n");
        System.out.println("You will need to create champions the create weapons for them and then equip these champions with those created weapons");
        System.out.println("I suggest creating 2 champions, then creating 1 or 2 weapons and then equipping them to be able to fully use the game");
        System.out.println("champions by default have a weapon that deals 1-2 damage");
        System.out.println("Enter anything to continue");
        input.next();
        DamageRange defaultDamage = new DamageRange(1, 2);
        Sword defaultWeapon = new Sword();
        defaultWeapon.setDamage(defaultDamage);
        defaultWeapon.setName("Dagger");
        weaponList.add(defaultWeapon);
    }

    void showMenuOptions() {
        System.out.println("");
        System.out.println("To choose enter a number next to an option");
        System.out.println("Possible actions:");
        System.out.println("1. Enter champion management menu");
        System.out.println("2. Enter weapon management menu");
        System.out.println("3. Pick a champion");
        System.out.println("4. Create a duel");
        System.out.println("5. Move forward");
        System.out.println("6. Show your current weapon");
        System.out.println("0. Quit");
    }

    void choice() {
        switch (input.nextInt()) {
            case 0: {
                quit = true;
                System.out.println("Goodbye");
                break;
            }
            case 1: {
                championManagement();
                break;
            }
            case 2: {
                weaponManagement();
                break;
            }
            case 3: {
                int i = 0;
                for (Champion champion : champList) {
                    System.out.println(i + ". " + champion.name);
                    i++;
                }
                System.out.println("Which champion do you want to pick?");
                champPicked = input.nextInt();
                System.out.println("You picked " + champList.get(champPicked).name);
                break;
            }
            case 4: {
                System.out.println("Battle will begin between: (pick two)");
                int i = 0;
                for (Champion champion : champList) {
                    System.out.println(i + ". " + champion.name);
                    i++;
                }
                champPicked = input.nextInt();
                battle(champPicked, input.nextInt());
                break;
            }
            case 5: {
                System.out.println(champList.get(champPicked).name + ":");
                champList.get(champPicked).movementBehavior.move();
                break;
            }
            case 6: {
                champList.get(champPicked).weapon.showWeaponBehavior.showweapon();
                break;
            }

            default: {
                System.out.println("There is no such choice");
                break;
            }
        }
    }

    void battle(int first, int second) {
        System.out.println("Its a battle between: " + "\"" + champList.get(first).name + "\"" + " - " + champList.get(first).getClass().getSimpleName() + " and " + "\"" + champList.get(second).name + "\"" + " - " + champList.get(second).getClass().getSimpleName());
        int hp1 = champList.get(first).hp;
        int hp2 = champList.get(second).hp;
        System.out.println("Enter anything to continue");
        input.next();
        while (champList.get(second).hp > 0 && champList.get(first).hp > 0) {
            int roll1 = champList.get(first).weapon.dealDamage();
            int actualDamage1 = roll1 - champList.get(second).armor.getHardness();
            if (actualDamage1 < 0)
                actualDamage1 = 0;
            System.out.println(champList.get(first).name + " attacks and deals " + actualDamage1 + " damage");
            champList.get(second).hp = champList.get(second).hp - actualDamage1;
            if (champList.get(second).hp <= 0) {
                //champList.get(second).hp = 0;
                System.out.println(champList.get(first).name + " wins");
            } else {
                int roll2 = champList.get(second).weapon.dealDamage();
                int actualDamage2 = roll2 - champList.get(first).armor.getHardness();
                if (actualDamage2 < 0)
                    actualDamage2 = 0;
                System.out.println(champList.get(second).name + " attacks and deals " + actualDamage2 + " damage");
                champList.get(first).hp = champList.get(first).hp - actualDamage2;
                if (champList.get(first).hp <= 0) {
                    //champList.get(first).hp = 0;
                    System.out.println(champList.get(second).name + " wins");
                }
            }

            if (champList.get(second).hp > 0 && champList.get(first).hp > 0) {
                System.out.println("They both survived this turn");
                System.out.println(champList.get(first).name +" hp:"+ champList.get(first).hp);
                System.out.println(champList.get(second).name +" hp:"+ champList.get(second).hp);
                System.out.println("");
                System.out.println("Enter anything to proceed to next turn");
                input.next();
            }

        }
        champList.get(second).hp = hp2;
        champList.get(first).hp = hp1;
        System.out.println("Enter anything to continue");
        input.next();
    }

    void championManagement() {
        quitChampionManagement = false;
        while (!quitChampionManagement) {
            showChampionManagementOptions();
            pickChampionManagementOptions();
        }
    }

    void weaponManagement() {
        quitWeaponManagement = false;
        while (!quitWeaponManagement) {
            showWeaponManagementOptions();
            pickWeaponManagementOptions();
        }
    }

    void pickChampionManagementOptions() {
        switch (input.nextInt()) {
            case 0: {
                quitChampionManagement = true;
                break;
            }
            case 1: {
                System.out.println("champions:");
                int i = 0;
                for (Champion champion : champList) {
                    System.out.println(i + ". " + "\"" + champion.name + "\"" + " - " + champion.getClass().getSimpleName());
                    i++;
                }
                break;
            }
            case 2: {
                System.out.println("Who do you want to create:");
                System.out.println("1. Mage");
                System.out.println("2. Archer");
                System.out.println("3. Heavy Knight");
                System.out.println("4. Knight");
                switch (input.nextInt()) {
                    case 1: {
                        createChampion(1);
                        System.out.println("Creation successful");
                        break;
                    }
                    case 2: {
                        createChampion(2);
                        System.out.println("Creation successful");
                        break;
                    }
                    case 3: {
                        createChampion(3);
                        System.out.println("Creation successful");
                        break;
                    }
                    case 4: {
                        createChampion(4);
                        System.out.println("Creation successful");
                        break;
                    }
                }
                break;
            }
            case 3: {
                System.out.println("Which champion do you want to delete?");
                champList.remove(input.nextInt());
                System.out.println("Deletion successful");
                break;
            }
//            case 4: {
//                for (Champion champion : champList) {
//                    int i = 0;
//                    System.out.println(i + ". " + champion.name);
//                    i++;
//                }
//                System.out.println("Which champion do you want to pick?");
//                champPicked = input.nextInt();
//                System.out.println("You picked " + champList.get(champPicked).name);
//                break;
//            }
            default: {
                System.out.println("There is no such choice");
                break;
            }
        }
    }

    void pickWeaponManagementOptions() {
        switch (input.nextInt()) {
            case 0: {
                quitWeaponManagement = true;
                break;
            }
            case 1: {
                System.out.println("Weapons:");
                int i = 0;
                for (Weapon weapon : weaponList) {

                    System.out.println(i + ". " + "\"" + weapon.name + "\"" + " - " + weapon.getClass().getSimpleName());
                    i++;
                }
                break;
            }
            case 2: {
                System.out.println("What type of weapon do you want to create:");
                System.out.println("1. Bow");
                System.out.println("2. Sword");
                System.out.println("3. Long Sword");
                System.out.println("4. Staff");
                switch (input.nextInt()) {
                    case 1: {
                        createWeapon(1);
                        break;
                    }
                    case 2: {
                        createWeapon(2);
                        break;
                    }
                    case 3: {
                        createWeapon(3);
                        break;
                    }
                    case 4: {
                        createWeapon(4);
                        break;
                    }

                }
                System.out.println("Creation successful");
                break;
            }
            case 3: {
                System.out.println("Which weapon do you want to delete?");
                weaponList.remove(input.nextInt());
                break;
            }
            case 4: {
                System.out.println("You are currently picking a weapon for " + champList.get(champPicked).name);
                int i = 0;
                for (Weapon weapon : weaponList) {
                    System.out.println(i + ". " + weapon.name);
                    i++;
                }
                System.out.println("Which weapon do you want to equip?");
                champList.get(champPicked).setWeapon(weaponList.get(input.nextInt()));
                System.out.println("You equipped " + champList.get(champPicked).weapon.name + " to " + champList.get(champPicked).name);
                break;
            }
            case 5: {
                champList.get(champPicked).weapon.showWeaponBehavior.showweapon();
                break;
            }
            default: {
                System.out.println("There is no such choice");
                break;
            }
        }
    }

    void createWeapon(int type) {
        System.out.println("What will be its name?");
        String name = input.next();
        System.out.println("What will be its average minimal and maximal damage?");
        int mini = input.nextInt();
        int maxi = input.nextInt();
        if (type == 1) {
            Bow weapon = new Bow();
            weapon.setName(name);
            DamageRange damageRange = new DamageRange(mini, maxi);
            weapon.setDamage(damageRange);
            weaponList.add(weapon);
        }
        if (type == 2) {
            Sword weapon = new Sword();
            weapon.setName(name);
            DamageRange damageRange = new DamageRange(mini, maxi);
            weapon.setDamage(damageRange);
            weaponList.add(weapon);
        }
        if (type == 3) {
            LongSword weapon = new LongSword();
            weapon.setName(name);
            DamageRange damageRange = new DamageRange(mini, maxi);
            weapon.setDamage(damageRange);
            weaponList.add(weapon);
        }
        if (type == 4) {
            Staff weapon = new Staff();
            weapon.setName(name);
            DamageRange damageRange = new DamageRange(mini, maxi);
            weapon.setDamage(damageRange);
            weaponList.add(weapon);
        }
    }

    void createChampion(int type) {
        System.out.println("What will be his name?");
        String name = input.next();
        if(!name.equals("EasterEgg"))
            System.out.println("That's a horrible name!");
        System.out.println("What will be his starting hp?");
        int hp = input.nextInt();
        System.out.println("Will his armor be [1]light or [2]heavy?");
        Armor armor;
        switch (input.nextInt()) {
            case 1:
                //LightArmor armor=new LightArmor();
            {
                armor = new LightArmor();
                break;
            }
            case 2: {
                armor = new HeavyArmor();
                break;
            }
            default: {
                System.out.println("You have chosen other option so it defaulted to light armor");
                //LightArmor armor2=new LightArmor();
                armor = new LightArmor();
                break;
            }
        }
//        //Weapon weapon;
//        if(weaponList.size()>0){
//            System.out.println("Choose your weapon");
//                    for (Weapon weapon : weaponList) {
//                        int i = 0;
//                        System.out.println(i + ". " + weapon.name);
//                        i++;
//                    }
//        }
        if (type == 1) {
            Mage mage = new Mage();
            mage.setName(name);
            mage.setHp(hp);
            mage.setArmor(armor);
            mage.setWeapon(weaponList.get(0));
            champList.add(mage);

        }
        if (type == 2) {
            Archer archer = new Archer();
            archer.setName(name);
            archer.setHp(hp);
            archer.setArmor(armor);
            archer.setWeapon(weaponList.get(0));
            champList.add(archer);
        }
        if (type == 3) {
            HeavyKnight hKnight = new HeavyKnight();
            hKnight.setName(name);
            hKnight.setHp(hp);
            hKnight.setArmor(armor);
            hKnight.setWeapon(weaponList.get(0));
            champList.add(hKnight);
        }
        if (type == 4) {
            Knight knight = new Knight();
            knight.setName(name);
            knight.setHp(hp);
            knight.setArmor(armor);
            knight.setWeapon(weaponList.get(0));
            champList.add(knight);
        }

    }

    void showChampionManagementOptions() {
        System.out.println("");
        System.out.println("To choose enter a number next to an option");
        System.out.println("Possible actions:");
        System.out.println("1. Show all champions");
        System.out.println("2. Create a champion");
        System.out.println("3. Delete a champion");
        //System.out.println("4. Pick a champion");
        System.out.println("0. Quit");
    }

    void showWeaponManagementOptions() {
        System.out.println("");
        System.out.println("To choose enter a number next to an option");
        System.out.println("Possible actions:");
        System.out.println("1. Show all weapons");
        System.out.println("2. Create a weapon");
        System.out.println("3. Delete a weapon");
        System.out.println("4. Equip a champion with a weapon");
        System.out.println("5. Show currently picked weapon");
        System.out.println("0. Quit");
    }

    public void start() {
        startMessage();
        while (!quit) {
            showMenuOptions();
            choice();
        }
    }
}