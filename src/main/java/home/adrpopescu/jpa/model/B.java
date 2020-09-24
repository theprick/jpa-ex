package home.adrpopescu.jpa.model;

public class B extends A {

    public B() {
        System.out.println("B");
    }

    public boolean m() {
        System.out.println("mB");
        return true;
    }
}
