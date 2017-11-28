package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Attribute;
import logic.Weapon;
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
import java.io.StringReader;
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


    public static void main(String[] args) throws JAXBException {

        //launch(args);

        List<Weapon> weaponList = Arrays.asList(new Weapon("Axt", Attribute.Strength,5,4,3),
                new Weapon("Bow", Attribute.Agility,4,3,2));

        // Create the Marshaller Object using the JaxB Context
        Map<String, Object> properties = new HashMap<>();
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);

        //Create a Context using the properties
        JAXBContext jaxbContext =
                JAXBContextFactory.createContext(new Class[]  {
                        Weapon.class,    ObjectFactory.class}, properties);
        Marshaller marshaller = jaxbContext.createMarshaller();
        // Set the Marshaller media type to JSON or XML
        marshaller.setProperty(MarshallerProperties.MEDIA_TYPE,
                "application/json");
        // Set it to true if you need to include the JSON root element in the JSON output
        marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
        // Set it to true if you need the JSON output to formatted
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // Marshal the employee object to JSON and print the output to console

        marshaller.marshal(weaponList, System.out);

        String json = "[ {\n" +
                "   \"weapon\" : {\n" +
                "      \"name\" : \"Axt\",\n" +
                "      \"baseAttribute\" : \"Strength\"\n" +
                "   }\n" +
                "}, {\n" +
                "   \"weapon\" : {\n" +
                "      \"name\" : \"Bow\",\n" +
                "      \"baseAttribute\" : \"Agility\"\n" +
                "   }\n" +
                "} ]";

        // Create the Unmarshaller Object using the JaxB Context
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        // Set the Unmarshaller media type to JSON or XML
        unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE,
                "application/json");
        // Set it to true if you need to include the JSON root element in the
        // JSON input
        unmarshaller
                .setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
        // Create the StreamSource by creating StringReader using the JSON input
        StreamSource json2 = new StreamSource(
                new StringReader(

                json));
        // Getting the employee pojo again from the json
        List weaponList2 = unmarshaller.unmarshal(json2, List.class).getValue();

        System.out.println(weaponList2.toString());



    }
}
