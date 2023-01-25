public class LongSword extends Weapon {

    public LongSword() {
        showWeaponBehavior = new ShowSword();

    }

    public LongSword(String name, ShowWeaponBehavior showWeaponBehavior, DamageRange damage) {
        super(name, showWeaponBehavior, damage);
    }
}
