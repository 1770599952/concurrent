package concurrent_15;

public class VolatileStudy01 {

    static Person person = new Person();

    public static void main(String[] args) {

        Thread thread01 = new Thread(new Runnable() {
            @Override
            public void run() {
                person.name = "liu";
                while (!person.name.equals("fang")) {
                }
            }
        });
        thread01.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        person.name = "fang";
        System.out.println(person.name);
    }

}

class Person {
    public String name;
}