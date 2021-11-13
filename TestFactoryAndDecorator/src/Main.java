public class Main {
    public static void main(String[] args) {
        AbstractFactory factory = new CityFactory();
        City city = factory.createCity();
        Circle circle = new Circle();
        circle.add(city);

        MutipleRectangle mutipleRectangle = new MutipleRectangle();
        mutipleRectangle.add(circle);
        mutipleRectangle.draw();
    }
}
