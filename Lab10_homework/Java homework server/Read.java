import java.io.PrintWriter;

import java.util.List;

public class Read {
    public void resolve(String name, PrintWriter out) {
        List<String> list = Server.getMessages(name);

        if (!list.isEmpty()) {
            for (String message : list) {
                out.println(message);
            }
        } else {
            out.println("No messages available for you");
        }
        out.flush();
    }
}
