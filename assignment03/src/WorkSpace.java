import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * A repository which contains the city list and route information. It extends the </tt>Observable</tt> class, so that
 * it can notify its <tt>Observer</tt> for updates i.e. when a new city is added or an existing city is moved.
 *
 * @author Zhuoran Li, Rishav Kumar
 * @version 1.0
 * @since 2021-10-02
 */
public class WorkSpace extends Observable {
    public static final int DEFAULT_CITY_HEIGHT = 20;
    public static final int DEFAULT_CITY_WIDTH = 20;

    private final List<City> cityList = new ArrayList<>();
    private ArrayList<Route> routeList;

    public WorkSpace() { }

    /**
     * Add a new city and notify the observers.
     */
    public void addNewCity(City city) {
        cityList.add(city);
        setChanged();
        notifyObservers();
    }

    /**
     * Move an existing city to the new co-ordinates and notify the observers.
     */
    public void moveExistingCity(City city, int x, int y) {
        city.move(x, y);
        setChanged();
        notifyObservers();
    }

    /**
     * Clear all the cities and notify the observers.
     */
    public void clearAllCities() {
        cityList.clear();
        setChanged();
        notifyObservers();
    }

    /**
     * Load a list of cities from a file and notify the observers.
     *
     * @param file handle to file containing data
     * @throws IOException  if the file does not exist, is a directory rather than a regular file, or for some other
     * reason cannot be opened for reading.
     */
    public void load(File file) throws IOException {
        cityList.clear();

        String fileText = readTextFile(file);
        String[] lineList = fileText.split("\n");

        for (String line : lineList) {
            String[] tokens = line.split(" ");
            City city = new City(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]),
                    DEFAULT_CITY_WIDTH, DEFAULT_CITY_HEIGHT);
            cityList.add(city);
        }

        setChanged();
        notifyObservers();
    }

    /**
     * Save the list of cities to a file.
     *
     * @param file handle to the file where the data is to be saved
     * @throws IOException  if the named file exists but is a directory rather than a regular file, does not exist but
     * cannot be created, or cannot be opened for any other reason
     */
    public void save(File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
        for (City city : cityList)
            writer.write(String.format("%s %d %d\n", city.getLabel(), city.getX(), city.getY()));
        writer.close();
    }

    /**
     * Get the list of cities.
     * @return list of cities
     */
    public List<City> getCityList() {
        return cityList;
    }

    /**
     * Get the route information.
     * @return route information
     */
    public List<Route> getRouteList() {
        return routeList;
    }

    /**
     * Set the route information.
     * @param routeList route information
     */
    public void setRouteList(ArrayList<Route> routeList) {
        this.routeList = routeList;
    }

    private String readTextFile(File file) throws IOException {
        String lineText;
        StringBuilder fileTextStringBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((lineText = br.readLine()) != null) {
            fileTextStringBuilder.append(lineText).append("\n");
        }
        return fileTextStringBuilder.toString();
    }
}
