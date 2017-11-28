package logic;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler {

    // Export
    public static void marshal(ArrayList<Weapon> weapons, File selectedFile)
            throws IOException, JAXBException {
        JAXBContext context;
        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter(selectedFile));
        context = JAXBContext.newInstance(WeaponList.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(new WeaponList(weapons), writer);
        writer.close();
    }

    // Import
    public static WeaponList unmarshal(File importFile) throws JAXBException {
        WeaponList weapons;

        JAXBContext context = JAXBContext.newInstance(WeaponList.class);
        Unmarshaller um = context.createUnmarshaller();
        weapons = (WeaponList) um.unmarshal(importFile);

        return weapons;
    }
}
