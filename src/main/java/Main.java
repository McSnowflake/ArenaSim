import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Attribute;
import logic.JsonHandler;
import logic.Weapon;
import logic.WeaponList;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.eclipse.persistence.jaxb.xmlmodel.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../app/ArenaApp.fxml"));
        primaryStage.setTitle("Arena");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws JAXBException, IOException {

        //launch(args);

        List<Weapon> weaponList = Arrays.asList(new Weapon("Axt", Attribute.Strength,1,1,1),
                new Weapon("Bow", Attribute.Agility,1,1,1));

        ArrayList<Weapon> weaponList2 = new ArrayList<>();
        weaponList2.addAll(weaponList);


        JsonHandler.marshal(weaponList2, new File("tmp.txt"));


        WeaponList weapons = JsonHandler.unmarshal(new File("tmp.txt"));

        weapons.getWeapons();


    }
}
