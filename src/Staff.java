public class Staff extends Weapon {

    public Staff() {
        showWeaponBehavior = new ShowStaff();

    }

    public Staff(String name, ShowWeaponBehavior showWeaponBehavior, DamageRange damage) {
        super(name, showWeaponBehavior, damage);
    }
}
