package sample;

public class WeaponManager extends DataManager<Weapon> {

    private final static String WEAPON_FILE_PATH = "sample/weapons.json";

    public WeaponManager(String path2json) {
        super(path2json);
    }
}
