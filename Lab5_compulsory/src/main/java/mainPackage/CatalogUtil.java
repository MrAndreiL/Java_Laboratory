package mainPackage;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class CatalogUtil {
    public static void save(String path, Catalog catalog) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            objectMapper.writeValue(new File(path), catalog);
        } catch (StreamWriteException ex) {
            ex.printStackTrace();
        }
    }

    public static Catalog load(String path) throws InvalidCatalogExpression {
        ObjectMapper objectMapper = new ObjectMapper();

        Catalog catalog;
        try {
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            catalog = objectMapper.readValue(new File(path), Catalog.class);
        } catch (IOException ex) {
            throw new InvalidCatalogExpression(ex);
        }
        return catalog;
    }
}
