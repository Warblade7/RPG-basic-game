public class Champion {
    String name;
    int hp;
    Weapon weapon;
    Armor armor;
    MovementBehavior movementBehavior;

    public Champion() {
    }

    public Champion(String name, int hp, Armor armor) {
        this.name = name;
        this.hp = hp;
        this.armor = armor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
}
