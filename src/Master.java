import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Master {

    public static Player[] addPlayers(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> names_list = new ArrayList<>();
        System.out.println("Если хотите остановить игру, введите пустую строку!!!");
        int cnt = 1;
        while(true){
            System.out.printf("Введите имя %d игрока: ", cnt);
            cnt ++;
            String name = scanner.nextLine();
            if(name.isEmpty())break;
            names_list.add(name);
        }
        Collections.shuffle(names_list);
        Player[] names = new Player[names_list.size()];
        for(int i = 0; i < names_list.size(); i++){
            names[i] = new Player(names_list.get(i));
        }
        return names;
    }

    public static Word words(){
        Word testWord = new Word();
        testWord.title = "Субботник";
        testWord.description = "Добровольная коллективная бесплатная работа для выполнения какого-н. общественно полезного трудового задания";
        return testWord;
    }

    public static void main(String[] args) {
        Player[] names = addPlayers();
        Word word = words();
    }
}