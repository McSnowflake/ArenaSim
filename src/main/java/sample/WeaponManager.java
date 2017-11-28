package sample;

public class WeaponManager extends DataManager<Weapon> {

    private final static String WEAPON_FILE_PATH = "sample/wepons.json";
    private static WeaponManager weaponManager = null;

    public static WeaponManager init() {
        if (weaponManager == null) {
            weaponManager = new WeaponManager();
        }
        return weaponManager;
    }

    private WeaponManager() {

    }

    p
}
