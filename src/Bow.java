public class Bow extends Weapon {

    public Bow() {
        showWeaponBehavior = new ShowBow();

    }

    public Bow(String name, ShowWeaponBehavior showWeaponBehavior, DamageRange damage) {
        super(name, showWeaponBehavior, damage);
    }
}
