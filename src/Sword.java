public class Sword extends Weapon {

    public Sword() {
        showWeaponBehavior = new ShowSword();

    }

    public Sword(String name, ShowWeaponBehavior showWeaponBehavior, DamageRange damage) {
        super(name, showWeaponBehavior, damage);
    }
}
