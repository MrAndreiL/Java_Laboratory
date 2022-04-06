package config;

public class Config {
    final private int padding;
    final private int circleRadius;

    private int rows = 2;
    private int columns = 2;

    private int canvasWidth = 400;
    private int canvasHeight = 400;

    private final int startX;
    private final int startY;

    private int endX;
    private int endY;

    private int sticks = 2;

    public final int DEF  = 0;
    public final int BLUE = 1;
    public final int RED  = 2;

    public Config() {
        padding      = 20;
        circleRadius = 20;
        startX       = padding + circleRadius;
        startY       = padding + circleRadius;
    }

    public int getPadding() { return padding; }

    public int getCircleRadius() { return circleRadius; }

    /***
     * Get row number from input and compute canvas height.
     * @param rows Row number got from user.
     */
    public void setRows(int rows) {
        this.rows = rows;

        canvasHeight = 2 * padding + (2 * this.rows * circleRadius);
        endY = canvasHeight - padding - circleRadius;
    }

    /***
     * Get column number from input and compute canvas width.
     * @param columns Column number got from user.
     */
    public void setColumns(int columns) {
        this.columns = columns;

        canvasWidth = 2 * padding + (2 * this.columns * circleRadius);
        endX = canvasWidth - padding - circleRadius;
    }

    /***
     * Set the number of sticks to be drawn using rows and columns.
     */
    public void setSticks() {
        this.sticks = (columns * rows) / 2;
    }

    public int getSticks() {
        return sticks;
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }
}
