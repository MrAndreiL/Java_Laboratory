package mainPackage;

import Exceptions.ReportException;
import Exceptions.ViewException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

public class ReportCommand {
    public static void report(Catalog catalog) throws ReportException {
        Configuration configuration = new Configuration();
        try {
            configuration.setDirectoryForTemplateLoading(new File("/home/andrei/Work/Lab5/src/main/resources"));
        } catch (IOException ex) {
            throw new ReportException(ex);
        }

        try {
            Writer writer = new FileWriter(new File("/home/andrei/Desktop/html.html"));
            Template template = configuration.getTemplate("report.ftl");
            template.process(catalog, writer);
        } catch (IOException | TemplateException ex) {
            throw  new ReportException(ex);
        }

        try {
            ViewCommand.View("/home/andrei/Desktop/html.html");
        } catch (ViewException ex) {
            ex.printStackTrace();
        }
    }
}
