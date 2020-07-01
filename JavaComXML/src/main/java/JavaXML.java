import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class JavaXML {
    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        try {

            //Projeto sobre escrita e leitura de arquivo XML.
            String text="Hello Luís!"; //Texto de escrita/leitura.

            // Ex: C:\\xmlfile.xml
            // Deixar o pathname igual a linha 67.
            String xmlFilePath = "C:\\Users\\luisc\\IdeaProjects\\JavaComXML\\xmlfile.xml";


            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document document;
            File file = new File(xmlFilePath);
            Element root;

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            // root element
            root = document.createElement("Posts");
            document.appendChild(root);

            // post element
            Element post = document.createElement("post");
            root.appendChild(post);
            // set an attribute to post element
            Attr attr = document.createAttribute("id");
            long currentTimeMillis = System.currentTimeMillis();
            attr.setValue("_"+currentTimeMillis);
            post.setAttributeNode(attr);
            // firstname element
            Element textReceived = document.createElement("text");
            textReceived.appendChild(document.createTextNode(text));
            post.appendChild(textReceived);

            // create the xml file
            // transform the DOM Object to an XML File

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));

            transformer.transform(domSource, streamResult);
            System.out.println("Done creating XML File");

        } catch (Exception e) {
            System.out.println("Error on load file");
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
        }

        try {
            // Deixar o pathname igual a linha 20.
            File fXmlFile = new File("C:\\Users\\luisc\\IdeaProjects\\JavaComXML\\xmlfile.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            // optional, but recommended
            // read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList2 = doc.getElementsByTagName("post");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList2.getLength(); temp++) {
                Node nNode = nList2.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("id : " + eElement.getAttribute("id"));
                    System.out.println("Text : " + eElement.getElementsByTagName("text").item(0).getTextContent());
                }
            }

        } catch (Exception e) {

        }
    }
}
