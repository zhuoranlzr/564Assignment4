public class CityFactory implements AbstractFactory {
    @Override
    public Basic createCity() {
        return new Basic();
    }
}
