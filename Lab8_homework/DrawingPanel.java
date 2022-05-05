package com.example;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.List;
import java.lang.Math;

public class DrawingPanel extends JPanel {
    private final JFrame frame;
    private final List<City> list;

    public DrawingPanel(JFrame frame, List<City> list) {
        this.frame = frame;
        this.list = list;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
        g.setColor(Color.RED);
        int width = frame.getWidth();
        int height = frame.getHeight();
        for (City city : list) {
            double latitude = city.getLatitude();
            double longitude = city.getLongitude();
            double x = (longitude + 180) * (width / 360);
            double latRad = Math.toRadians(latitude);
            double mercN = Math.log(Math.tan((Math.PI / 4) + (latRad / 2)));
            double y = (height / 2) - (width * mercN / (2 * Math.PI));
            System.out.println(x + " " + y);
            g.drawLine((int) x, (int) y, (int) x, (int) y);
        }
    }
}
