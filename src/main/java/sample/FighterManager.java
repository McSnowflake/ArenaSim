package sample;

public class FighterManager extends DataManager<Fighter> {

    private final static String WEAPON_FILE_PATH = "sample/fighter.json";

    public FighterManager(String path2json) {
        super(path2json);
    }
}
