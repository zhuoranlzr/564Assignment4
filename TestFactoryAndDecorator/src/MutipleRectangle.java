public class MutipleRectangle extends Decorator{
    public  MutipleRectangle(){
        System.out.println("constructor with MultpleRectangle");
    }
    @Override
    public void draw() {
        super.draw();
        System.out.println("add MutipleRectangle");
    }
}
