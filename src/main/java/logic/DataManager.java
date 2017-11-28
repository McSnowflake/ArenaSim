package logic;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataManager<T> {

    private final static String WEAPON_FILE_PATH = "src/main/resources/weapons.json";
    private final static String FIGHTER_FILE_PATH = "src/main/resources/fighter.json";

    private ArrayList<T> objectList = new ArrayList<>();

    public static DataManager<Fighter> getFighterManager(String... file2path) throws JAXBException {
        if (file2path.length == 0)
            return new DataManager<>(FIGHTER_FILE_PATH);
        else
            return new DataManager<>(file2path[0]);

    }

    public static DataManager<Weapon> getWeaponManager(String... file2path) throws JAXBException {
        if (file2path.length == 0)
            return new DataManager<>(WEAPON_FILE_PATH);
        else
            return new DataManager<>(file2path[0]);

    }

    private DataManager(String path2json) throws JAXBException {
        File jsonFile = new File(path2json);
        if (jsonFile.exists())
           objectList = JsonHandler.unmarshal(jsonFile);
    }

    public ArrayList<T> getList() {
        return objectList;
    }

    public void add(T object) {
        objectList.add(object);
    }

    public void save2File(String path2json) throws IOException, JAXBException {
        File jsonFile = new File(path2json);
/*
        if (!jsonFile.exists())
            jsonFile.createNewFile();
*/
        JsonHandler.marshal((ArrayList<Weapon>) objectList, jsonFile);
    }
}
