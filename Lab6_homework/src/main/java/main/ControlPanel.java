package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    final MainFrame frame;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        JButton load = new JButton("Load");
        JButton save = new JButton("Save");
        JButton exit = new JButton("Exit");
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
            }
        };
        exit.addActionListener(al);


        add(load);
        add(save);
        add(exit);
    }
}
