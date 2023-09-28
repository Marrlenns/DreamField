import java.util.ArrayList;
import java.util.Scanner;

public class Master {

    public static ArrayList<String> addPlayers(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();
        System.out.println("Если хотите остановить игру, введите пустую строку!!!");
        int i = 1;
        while(true){
            System.out.printf("Введите имя %d игрока: ", i);
            i ++;
            String name = scanner.nextLine();
            if(name.isEmpty())break;
            names.add(name);
        }
        return names;
    }

    public static void main(String[] args) {

    }
}