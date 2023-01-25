public abstract class Armor {

    int hardness;

    public Armor() {
    }

    public Armor(int hardness) {
        this.hardness = hardness;
    }

    public int getHardness() {
        return hardness;
    }

    public void setHardness(int hardness) {
        this.hardness = hardness;
    }
}
