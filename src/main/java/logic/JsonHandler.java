package logic;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonHandler<T> {

    // Export
    public void marshal(ArrayList<T> weapons, File selectedFile) throws IOException, JAXBException {
        JAXBContext context;
        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter(selectedFile));
        context = JAXBContext.newInstance(ElementList.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(new ElementList(T), writer);
        writer.close();
    }

    // Import
    public ArrayList<T> unmarshal(File importFile) throws JAXBException {
        ElementList elements;

        JAXBContext context = JAXBContext.newInstance(ElementList.class);
        Unmarshaller um = context.createUnmarshaller();
        elements = (ElementList) um.unmarshal(importFile);
        if (elements.getElements().size() == 0)
            throw new RuntimeException("no file found");
        return elements.getElements();
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "elements") class ElementList<T> {

        @XmlElement(name = "element", type = )
        private ArrayList<T> elements = new ArrayList<>();

        public ElementList() {
        }

        public ElementList(ArrayList<T> elements) {
            this.elements = elements;
        }

        public ArrayList<T> getElements() {
            return elements;
        }

    }
}
