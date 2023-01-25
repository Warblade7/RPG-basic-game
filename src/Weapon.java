import java.util.Random;

public abstract class Weapon {
    String name;
    ShowWeaponBehavior showWeaponBehavior;
    DamageRange damage;

    public Weapon() {
    }

    public int dealDamage() {
        Random random = new Random();
        int damageDealt = random.nextInt(damage.min, damage.max + 1);
        return damageDealt;
    }

    public Weapon(String name, ShowWeaponBehavior showWeaponBehavior, DamageRange damage) {
        this.name = name;

        this.showWeaponBehavior = showWeaponBehavior;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShowWeaponBehavior getShowWeaponBehavior() {
        return showWeaponBehavior;
    }

    public void setShowWeaponBehavior(ShowWeaponBehavior showWeaponBehavior) {
        this.showWeaponBehavior = showWeaponBehavior;
    }

    public DamageRange getDamage() {
        return damage;
    }

    public void setDamage(DamageRange damage) {
        this.damage = damage;
    }
}
