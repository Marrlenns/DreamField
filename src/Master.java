import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
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

    public static void win(String player_name){
        System.out.println("↓ ↓ ↓ У нас победитель ↓ ↓ ↓");
        System.out.println("    ≿━━━━  ༻✦༺  ━━━━≾");
        for(int i = 0; i < 7 - player_name.length() / 2; i++) System.out.print(" ");
        System.out.println("       " + player_name);
        System.out.println("    ≿━━━━  ༻✦༺  ━━━━≾");
        System.out.println("↑ ↑ ↑ У нас победитель ↑ ↑ ↑");
    }

    public static void fieldoutput(String s){
        for(int i = 0; i < s.length(); i++) System.out.print(s.charAt(i) + "|");
        System.out.println();
    }

    public static boolean check(String s){
        for(int i = 0; i < s.length(); i++)if(s.charAt(i) == '*')return false;
        return true;
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
                    win(player_name);
                    return;
                }
                System.out.printf("Наш игрок под номером %d - %s покидает нас :(\n\tПожелаем ему удачи !\n", move + 1, player_name);
                players[move].is_active = false;
            } else{
                while(field.contains(user_input)){
                    int index = field.indexOf(user_input);
                    players[move].points += 10;
                    field = field.substring(0, index) + '*' + field.substring(index + 1);
                    str1 = str1.substring(0, index) + user_input + str1.substring(index + 1);
                    while(field.contains(user_input)) {
                        index = field.indexOf(user_input);
                        players[move].points += 10;
                        field = field.substring(0, index) + '*' + field.substring(index + 1);
                        str1 = str1.substring(0, index) + user_input + str1.substring(index + 1);
                    }
                    System.out.println("Вы угадали букву, данное слово выглядит так: ");
                    fieldoutput(str1);
                    if(check(str1)){
                        int maxx = 0, ind = 0;
                        for(int i = 0; i < players.length; i++){
                            if(players[i].points > maxx){
                                maxx = players[i].points;
                                ind = i;
                            }
                        }
                        win(players[ind].name);
                        return;
                    }
                    if(players[move].points > str1.length() / 2 * 10){
                        int old = move;
                        move++;
                        System.out.println("У игрока " + player_name + " больше всех очков!!!\nДругим игрокам предостовляется шанс выиграть игру ↓");
                        while (old != move){
                            if(players[move].is_active){
                                System.out.printf("Игрок под номером %d - %s, ваш ход ↓ \n", move + 1, players[move].name);
                                user_input = scanner.nextLine();
                                if(user_input.equalsIgnoreCase(word.title)){
                                    win(players[move].name);
                                    return;
                                } else System.out.printf("Наш игрок под номером %d - %s покидает нас :(\n\tПожелаем ему удачи !\n", move + 1, players[move].name);
                            }
                            move++;
                            move %= players.length;
                        }
                        System.out.println("Никто из оставшихся игроков не отгадал слово");
                        win(player_name);
                        return;
                    }
                    System.out.println("Вам предоставляется еще один ход ↓");
                    user_input = scanner.nextLine();
                    if(user_input.length() > 1) {
                        if (user_input.equalsIgnoreCase(word.title)) {
                            win(player_name);
                            return;
                        }
                        System.out.printf("Наш игрок под номером %d - %s покидает нас :(\n\tПожелаем ему удачи !\n", move + 1, player_name);
                        players[move].is_active = false;
                        break;
                    }
                }
                if(players[move].is_active)System.out.println(player_name + " " + players[move].points + "-->" + str1);
            }
            move ++;
            move %= players.length;
            int sum = 0;
            while(!players[move].is_active && sum <= players.length){
                move ++;
                move %= players.length;
                sum ++;
            }
            if(sum > players.length){
                System.out.println("•»»————————————————————««•");
                System.out.println("|   Все игроки выбыли!   |");
                System.out.println("•»»————————————————————««•");
                return;
            }
//            if(move + 1 == players.length)break;

        }
    }
}