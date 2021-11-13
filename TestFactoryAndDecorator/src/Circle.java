public class Circle extends Decorator{
    public Circle(){
        System.out.println("constructor with circle");
    }
    @Override
    public void draw() {
        super.draw();
        System.out.println("add circle");
    }
}
