package main;

import config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    private final MainFrame frame;
    private final Config config;
    private final Game game;

    BufferedImage image;
    Graphics2D offscreen;

    public DrawingPanel(MainFrame frame, Config config) {
        this.frame  = frame;
        this.config = config;
        this.game   = new Game(this.config);
        createOffscreenImage();
        init();
    }

    final void init() {
        setPreferredSize(new Dimension(config.getCanvasWidth(), config.getCanvasHeight()));
    }

    private void createOffscreenImage() {
        image = new BufferedImage(config.getCanvasWidth(), config.getCanvasHeight(), BufferedImage.TYPE_INT_ARGB);
        offscreen = image.createGraphics();
        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, config.getCanvasWidth(), config.getCanvasHeight());
    }

    @Override
    public void update(Graphics g) {}

    @Override
    protected void paintComponent (Graphics graphics) {
        paintGrid();
        graphics.drawImage(image, 0, 0, this);
    }

    private void paintGrid() {
        offscreen.setColor(Color.DARK_GRAY);
        int x = config.getStartX();
        int y = config.getStartY();
        int fx = x + (2 * config.getColumns() * config.getCircleRadius()) - (2 * config.getCircleRadius());
        int fy = y + (2 * config.getRows() * config.getCircleRadius()) - (2 * config.getCircleRadius());

        // Draw lines horizontally
        int tempY = y;
        for (int i = 0; i < config.getRows(); i++) {
            offscreen.drawLine(x, tempY, fx, tempY);
            tempY += (2 * config.getCircleRadius());
        }

        // Draw lines vertically
        int tempX = x;
        for (int i = 0; i < config.getColumns(); i++) {
            offscreen.drawLine(tempX, y, tempX, fy);
            tempX += (2 * config.getCircleRadius());
        }

        // Draw circles and memorize their position.
        Circle[][] grid = game.getGrid();
        for (int i = 0; i < config.getRows(); i++) {
            tempX = x;
            for (int j = 0; j < config.getColumns(); j++) {
                offscreen.drawOval(tempX - config.getCircleRadius() / 2, y - config.getCircleRadius() / 2, config.getCircleRadius(), config.getCircleRadius());
                grid[i][j] = new Circle(tempX, y);
                tempX += (2 * config.getCircleRadius());
            }
            y += (2 * config.getCircleRadius());
        }
    }
}
