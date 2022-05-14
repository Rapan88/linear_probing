import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        LinearProbing<Integer,String> arr = new LinearProbing<Integer,String>();
        System.out.println(arr.isEmpty());
        System.out.println(arr.size());


        String[] names = {
                "Anton",
                "Dima",
                "Vasya",
                "Gregoriy",
                "Petya",
                "Sergay",
                "Vlad",
                "Borya",
                "Vitaly",
        };
        Random random = new Random();
        for(int i = 0; i<31; i++){
            arr.put(i,names[random.nextInt(1,8)]);
        }

        System.out.println(arr.get(3));
        System.out.println(arr.toString());

    }
}