package mainPackage;

import Exceptions.InfoException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InfoCommand implements Commandable {
    public static void info() throws InfoException, IOException {
        Metadata metadata = new Metadata();
        InputStream stream;
        stream = new FileInputStream("/home/andrei/Desktop/html.html");

        Parser parser = new AutoDetectParser();
        BodyContentHandler bodyContentHandler = new BodyContentHandler();
        ParseContext parseContext = new ParseContext();
        try {
            parser.parse(stream, bodyContentHandler, metadata, parseContext);
        } catch (SAXException | TikaException ex) {
            throw new InfoException(ex);
        } finally {
            stream.close();
        }

        String[] meta = metadata.names();
        for (String name : meta) {
            System.out.println(name + ": " + metadata.get(name));
        }
    }
}
