import java.awt.*;
import java.awt.geom.Line2D;

/**
 * This class is the data structure for storing information about a city i.e. the city name and its bounds. It also
 * defines functions to update the city bounds as well as fdr graphics operations to draw the city / draw the path
 * between two cities using a Graphics object.
 *
 * @author Zhuoran Li, Rishav Kumar
 * @version 1.0
 * @since 2021-10-02
 */
public class City {
    private final Rectangle bounds;
    private final String label;

    /**
     * The constructor to create a new city.
     *
     * @param label city name
     * @param x x-coordinate of the upper left corner of the city rectangle to be drawn
     * @param y y-coordinate of the upper left corner of the city rectangle to be drawn
     * @param w width of the city rectangle to be drawn
     * @param h height of the city rectangle to be drawn
     */
    public City(String label, int x, int y, int w, int h) {
        this.bounds = new Rectangle(x, y, w, h);
        this.label = label;
    }
    /**
     * Get the x-coordinate of the upper left corner of the city rectangle
     * @return x-coordinate of the upper left corner of the city rectangle
     */
    public int getX() { return bounds.x; }

    /**
     * Get the y-coordinate of the upper left corner the city rectangle
     * @return y-coordinate of the upper left corner the city rectangle
     */
    public int getY() { return bounds.y; }

    /**
     * Get the city name.
     * @return city name.
     */
    public String getLabel() { return label; }

    /**
     * Plots the city (as a rectangle) using a Graphics object.
     * @param g graphics object to plot the content
     */
    public void draw(Graphics2D g) {
        int x = bounds.x, y = bounds.y, h = bounds.height, w = bounds.width;
        g.setColor(Color.red);
        g.drawRect(x, y, w, h);
        Color c = g.getColor();
        g.setColor(Color.white);
        g.fillRect(x + 1, y + 1, w - 1, h - 1);
        g.setColor(Color.red);
        g.setFont(new Font("Courier", Font.PLAIN, 12));
        g.drawString(label, x + w, y);
        g.setColor(c);
    }

    /**
     * Update the bounds of this city.
     * @param x, new x-coordinate of the upper left corner of the city rectangle
     * @param y, new y-coordinate of the upper left corner the city rectangle
     */
    public void move(int x, int y) {
        bounds.x = x;
        bounds.y = y;
    }

    private Point center() {
        return new Point(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);
    }

    /**
     * Plots the route (as a line) between this city and the destination city <code>b</code> using a Graphics object.
     * @param b destination city
     * @param g graphics object to plot the content
     */
    public void drawConnect(City b, Graphics2D g) {
        g.setColor(Color.blue);
        g.setStroke(new BasicStroke(2));
        g.draw(new Line2D.Float(center().x, center().y, b.center().x, b.center().y));
    }

    /**
     * Checks if a particular co-ordinate belongs to this city and returns a boolean indicator for the same.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return whether this city contains the input co-ordinates
     */
    public boolean contains(int x, int y) {
        return bounds.contains(x, y);
    }
}
