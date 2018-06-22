package domain;

public class Singleton {
    private static Singleton sInstance;

    private int id;

    public static Singleton getInstance() {
        if (sInstance == null) {
            sInstance = new Singleton();
        }

        return sInstance;
    }

    // Prevent duplicate objects
    private Singleton() {

    }

    public int getID() {
        System.out.println("ID = " + id);
        return this.id;
    }

    public void setID(int id){
        this.id = id;
        System.out.println("setID = " + id);

    }
}