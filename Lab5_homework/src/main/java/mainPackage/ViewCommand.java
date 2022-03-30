package mainPackage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ViewCommand {
    public static void View(String path) throws ViewException {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(new File(path));
        } catch (IOException ex) {
            throw new ViewException(ex);
        }
    }
}
