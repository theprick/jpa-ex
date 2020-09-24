package home.adrpopescu.jpa.model;

public class C {

    public static void main(String[] args) {
        A b = new B();
        System.out.println("instanceof A = " + (b instanceof A));
        System.out.println(b.m());
    }
}
