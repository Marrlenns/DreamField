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
        String field = word.title.toLowerCase(), str1 = "";
        for(int i = 0 ; i < field.length(); i++)str1 += "*";
        while(true){
            String player_name = players[move].name.substring(0, 1).toUpperCase() + players[move].name.substring(1);
            System.out.printf("Игрок под номером %d - %s, ваш ход ↓ \n", move + 1, player_name);
            String user_input = scanner.nextLine();
            if(user_input.length() > 1){
                if(user_input.equalsIgnoreCase(word.title)){
                    System.out.println("У нас победитель!!!\nИгрок "  + player_name + " угадал слово!!!");
                    return;
                }
                System.out.printf("Наш игрок под номером %d - %s покидает нас :(\n\tПожелаем ему удачи !\n", move + 1, player_name);
                players[move].is_active = false;
            } else{
                while(field.contains(user_input)){
                    int index = field.indexOf(user_input);
                    players[move].points += 10;
                    field = field.substring(0, index) + '*' + field.substring(index + 1);
                    while(field.contains(user_input)) {
                        index = field.indexOf(user_input);
                        players[move].points += 10;
                        field = field.substring(0, index) + '*' + field.substring(index + 1);
                    }
                    System.out.println("Вы угадали букву, данное слово выглядит так: " + field + "\nВам предоставляется еще один ход ↓");
                    user_input = scanner.nextLine();
                }
                System.out.println(player_name + " " + players[move].points + "-->" + field);
            }
            move ++;
            move %= players.length;
            while(!players[move].is_active){
                move ++;
                move %= players.length;
            }
//            if(move + 1 == players.length)break;

        }
    }
}