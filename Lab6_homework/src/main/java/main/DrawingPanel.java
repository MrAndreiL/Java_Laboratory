package main;

import config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import static java.lang.Math.pow;

public class DrawingPanel extends JPanel {
    private Circle[][] grid;
    private Circle currentCircle;
    private final MainFrame frame;
    private final Config config;
    private final Game game;

    BufferedImage image;
    Graphics2D offscreen;

    public DrawingPanel(MainFrame frame, Config config) {
        this.frame  = frame;
        this.config = config;
        this.game   = new Game(this.config);
        setCircles();
        createOffscreenImage();
        init();
    }

    private void setCircles() {
        int x = config.getStartX();
        int y = config.getStartY();
        grid = game.getGrid();
        for (int i = 0; i < config.getRows(); i++) {
            int tempX = x;
            for (int j = 0; j < config.getColumns(); j++) {
                grid[i][j] = new Circle(tempX, y, this.config);
                tempX += (2 * config.getCircleRadius());
            }
            y += (2 * config.getCircleRadius());
        }
    }

    private boolean inCircle(int x, int y, int cX, int cY) {
        return pow(x - cX, 2) + pow(y - cY, 2) <= pow(config.getCircleRadius(), 2);
    }

    private Circle isValidPress(MouseEvent e) {
        // Check to see if it's in a circle.
        for (int i = 0; i < config.getRows(); i++) {
            for (int j = 0; j < config.getColumns(); j++) {
                if (inCircle(e.getX(), e.getY(), grid[i][j].getX(), grid[i][j].getY())) {
                    if (grid[i][j].getColor() != config.DEF) {
                        return null;
                    }
                    return grid[i][j];
                }
            }
        }
        return null;
    }


    private void paintBlue(int x, int y, int radius) {
        offscreen.setColor(Color.BLUE);
        offscreen.fillOval(x - radius / 2, y - radius / 2, radius, radius);
        offscreen.drawOval(x - radius / 2, y - radius / 2, radius, radius);
        offscreen.setColor(Color.DARK_GRAY);
        repaint();
    }

    private void paintRed(int x, int y, int radius) {
        offscreen.setColor(Color.RED);
        offscreen.fillOval(x - radius / 2, y - radius / 2, radius, radius);
        offscreen.drawOval(x - radius / 2, y - radius / 2, radius, radius);
        offscreen.setColor(Color.DARK_GRAY);
        repaint();
    }

    private void isGameOver(Circle currentCircle) {
        int nr = 0;
        for (Circle circle : currentCircle.getNeighbors()) {
            if (circle.getColor() != config.DEF)
                nr++;
        }
        if (nr == currentCircle.getNeighbors().size()) {
            // Display the end screen with the winning player.
            frame.addEndPanel(game.getTurn());
        }
    }

    private Circle isValid(MouseEvent e, Circle currentCircle) {
        Circle newCircle = isValidPress(e);
        if (newCircle == null)
            return null;
        if (currentCircle.isNeighbor(newCircle) && !currentCircle.equals(newCircle))
            return newCircle;
        return null;
    }

    final void init() {
        setPreferredSize(new Dimension(config.getCanvasWidth(), config.getCanvasHeight()));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                    if (game.getTurn() == 1) { // Game start
                        currentCircle = isValidPress(e);
                        if (currentCircle != null) {
                            paintBlue(currentCircle.getX(), currentCircle.getY(), config.getCircleRadius());
                            currentCircle.setColor(config.BLUE);
                            game.setTurn(2);
                        }
                    } else { // take turns
                        Circle prevCircle = currentCircle;
                        currentCircle = isValid(e, currentCircle);
                        if (currentCircle != null) {
                            if (game.getTurn() % 2 == 0) {
                                paintRed(currentCircle.getX(), currentCircle.getY(), config.getCircleRadius());
                                currentCircle.setColor(config.RED);
                            } else {
                                paintBlue(currentCircle.getX(), currentCircle.getY(), config.getCircleRadius());
                                currentCircle.setColor(config.BLUE);
                            }
                            isGameOver(currentCircle);
                            game.setTurn(game.getTurn() + 1);
                        } else {
                            currentCircle = prevCircle;
                        }
                    }
                }
        });
        /* Randomly placing the sticks */
        config.setSticks();
        placeSticks(grid);
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
        for (int i = 0; i < config.getRows(); i++) {
            for (int j = 0; j < config.getColumns(); j++) {
                int drawX  = grid[i][j].getX();
                int drawY  = grid[i][j].getY();
                int radius = config.getCircleRadius();
                if (grid[i][j].getColor() == config.DEF) {
                    offscreen.drawOval(drawX - radius / 2, drawY - radius / 2, radius, radius);
                } else if (grid[i][j].getColor() == config.BLUE) {
                    offscreen.setColor(Color.BLUE);
                    offscreen.fillOval(drawX - radius / 2, drawY - radius / 2, radius, radius);
                    offscreen.drawOval(drawX - radius / 2, drawY - radius / 2, radius, radius);
                    offscreen.setColor(Color.DARK_GRAY);
                } else if (grid[i][j].getColor() == config.RED) {
                    offscreen.setColor(Color.RED);
                    offscreen.fillOval(drawX - radius / 2, drawY - radius / 2, radius, radius);
                    offscreen.drawOval(drawX - radius / 2, drawY - radius / 2, radius, radius);
                    offscreen.setColor(Color.DARK_GRAY);
                }
            }
        }
    }

    private void placeSticks(Circle[][] grid) {
        offscreen.setColor(Color.BLACK);
        offscreen.setStroke(new BasicStroke(8));
        int[] set = new int[]{-1, 0, 1};
        Random rand = new Random();

        int placed = 0;
        while (placed < config.getSticks()) {
           // Randomly choose i and j.
           int si = rand.nextInt(config.getRows());
           int sj = rand.nextInt(config.getColumns());

           // Verify if the circle has available neighbors.
           if (grid[si][sj].getAvailableNeighbors() > 0 && !grid[si][sj].isMarginal()) {
                int fi = set[rand.nextInt(set.length)];
                int fj = set[rand.nextInt(set.length)];

                if (!(fi != 0 && fj != 0)) {
                    fi = si + fi;
                    fj = sj + fj;
                    if (!grid[si][sj].isNeighbor(grid[fi][fj])) {
                        grid[si][sj].addNeighbor(grid[fi][fj]);
                        grid[fi][fj].addNeighbor(grid[si][sj]);

                        int sx = grid[si][sj].getX();
                        int sy = grid[si][sj].getY();
                        int fx = grid[fi][fj].getX();
                        int fy = grid[fi][fj].getY();
                        offscreen.drawLine(sx, sy, fx, fy);
                        placed++;
                    }
                }
           }
        }
        offscreen.setColor(Color.DARK_GRAY);
        offscreen.setStroke(new BasicStroke(1));
    }
}
