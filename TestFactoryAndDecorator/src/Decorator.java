public abstract class Decorator implements City{
    protected City city;

    public void add(City city){
        this.city = city;
    }

    @Override
    public void draw() {
        if (city != null)
        city.draw();
    }
}
