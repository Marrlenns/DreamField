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
        testWord.description = "Добровольная коллективная бесплатная работа для выполнения какого-нибудь общественно \nполезного трудового задания";
        return testWord;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player[] players = addPlayers();
        Word word = words();
        if(players.length == 0){
            System.out.println("Игроков к сожалению нет :(\n\tПридется остановить игру :(");
            return;
        }
        System.out.println("Игроки будут ходить по следующей очередности ↓");
        for(int i = 0; i < players.length; i++) System.out.println("\t" + (i + 1) + ". " + players[i].name);
        System.out.println("                    Давайте начнем нашу игру!\n                    ↓↓↓     ПОДСКАЗКА     ↓↓↓");
        System.out.println(word.description);
        int move = 0;
        while(true){
            System.out.printf("Игрок под номером %d - %s, ваш ход ↓ \n", move + 1, players[move].name.substring(0, 1).toUpperCase() + players[move].name.substring(1));
            String user_input = scanner.nextLine();

            move ++;
            if(move == players.length)break;
            move %= players.length;

        }
    }
}