import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Master {

    public static Player[] addPlayers(){
        // return an array of Player class for players
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> names_list = new ArrayList<>();
        System.out.println("Если хотите остановить ввод игроков, введите пустую строку!!!");
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
        Word[] testWord = new Word[10];
        testWord[0] = new Word();
        testWord[0].title = "Субботник";
        testWord[0].description = "Добровольная коллективная бесплатная работа для выполнения какого-нибудь общественно полезного трудового задания";
        testWord[1] = new Word();
        testWord[1].title = "Самоходка";
        testWord[1].description = "Боевая машина, представляющая собой артиллерийское орудие, смонтированное на самодвижущемся шасси и предназначенное для стрельбы с закрытых позиций и непосредственной огневой поддержки танков и пехоты в бою";
        testWord[2] = new Word();
        testWord[2].title = "Чихание";
        testWord[2].description = "Человеческие способности довольно велики. Например, мы можем собственными силами разогнать воздушный поток до 150–170 км/ч. В процессе чего человек способен произвести такой воздушный поток?";
        testWord[3] = new Word();
        testWord[3].title = "Сковорода";
        testWord[3].description = "Что использовали в Китае для глажки белья вместо утюга?";
        testWord[4] = new Word();
        testWord[4].title = "Канализация";
        testWord[4].description = "Первый подобный музей появился в Париже до 1975 года. Экскурсии по нему проводились на лодке. Сейчас туристы осматривают его экспонаты со специальных решеток и пандусов. О каком музее идет речь?";
        testWord[5] = new Word();
        testWord[5].title = "Уверенность";
        testWord[5].description = "Английский писатель Киплинг говорил: «Женская интуиция намного точнее, чем мужская...»";
        testWord[6] = new Word();
        testWord[6].title = "Сарафан";
        testWord[6].description = "В XIV–XVI веках его носили мужчины. С XVII века его стали носить женщины. Названий было много: шторник, пестряк, клинник, наколоточник и др. До нас дошло лишь одно название. Какое?";
        testWord[7] = new Word();
        testWord[7].title = "Кенгуру";
        testWord[7].description = "Любой современный человек знает, как на языке австралийских аборигенов звучит фраза “Я тебя не понимаю”.";
        testWord[8] = new Word();
        testWord[8].title = "Замок";
        testWord[8].description = "Этот предмет изобрели в Древнем Египте. Тогда он полностью изготовлялся из дерева. По идее египетских мастеров в 19 веке Линиус Йейл изготовил современную металлическую модель, сохранив полностью принцип действия. У нас он обычно называется “английским”.";
        testWord[9] = new Word();
        testWord[9].title = "Силуэт";
        testWord[9].description = "Около двухсот лет назад во Франции появилось оригинальное искусство портрета. Эти портреты отличались дешевизной, поэтому такой вид искусства получил поддержку министра финансов Франции, человека жадного и неприятного. Французы стали называть эти портреты по фамилии министра-скряги. Назовите эту фамилию.";
        Random random = new Random();
        return testWord[random.nextInt(10)];
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
        // function for printing word in this format "*|*|*|*..."
        for(int i = 0; i < s.length(); i++) System.out.print(s.charAt(i) + "|");
        System.out.println("\n");
    }

    public static boolean check(String s){
        // check to *
        for(int i = 0; i < s.length(); i++)if(s.charAt(i) == '*')return false;
        return true;
    }

    public static void descriptionOutput(String s){
        // beautiful output of description of word
        // endline at first space after 70 letters
        int space = 1;
        for(int i = 0; i < 80; i++) System.out.print('-');
        System.out.println();
        for(int i = 0; i < s.length(); i++){
            if(i > space * 70 && s.charAt(i) == ' '){
                space ++;
                i ++;
                System.out.println();
            }
            System.out.print(s.charAt(i));
        }
        System.out.println();
        for(int i = 0; i < 80; i++) System.out.print('-');
        System.out.println('\n');
    }

    public static boolean isDigit(String s){
        // return "true" if letter is digit, otherwise "false"
        for(int i = 0; i < s.length(); i++){
            if(Character.isDigit(s.charAt(i))) return true;
        }
        return false;
    }

    public static String rightInput(){
        // infinity input while input is wrong
        Scanner scanner = new Scanner(System.in);
        String user_input = scanner.nextLine();
        while(true){
            if(user_input.isEmpty()){
                System.out.println("Ваша строка пустая! Ввeдите что-нибудь!");
                user_input = scanner.nextLine();
            }
            else if(isDigit(user_input)){
                System.out.println("Вы должны вводить строго буквы!!");
                user_input = scanner.nextLine();
            }
            else break;
        }
        return user_input;
    }

    public static void clearConsole(){
        // code for clearing a console in command line
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws InterruptedException {
        clearConsole();
        Scanner scanner = new Scanner(System.in);
        Player[] players = addPlayers();
        clearConsole();
        Word word = words();

        if(players.length == 0){
            System.out.println("Игроков к сожалению нет :(\n\tПридется остановить игру :(");
            return;
        }

        for(int j = 5; j >= 1; j--){
            System.out.println("Игроки будут ходить по следующей очередности ↓");
            for(int i = 0; i < players.length; i++) System.out.println("\t" + (i + 1) + ". " + players[i].name);
            System.out.println(j);
            TimeUnit.SECONDS.sleep(1);
            clearConsole();
        }

        System.out.println("\n↓ ↓ ↓ ↓ ↓                  Давайте начнем нашу игру!                  ↓ ↓ ↓ ↓ ↓");
        int move = 0;
        String field = word.title.toLowerCase(), str1 = "";
        for(int i = 0 ; i < field.length(); i++)str1 += "*";

        while(true){
            descriptionOutput(word.description);
            fieldoutput(str1);
            String player_name = players[move].name.substring(0, 1).toUpperCase() + players[move].name.substring(1);
            System.out.printf("Игрок под номером %d - %s, ваш ход ↓ \n", move + 1, player_name);
            String user_input = rightInput();

            if(user_input.length() > 1){
                if(user_input.equalsIgnoreCase(word.title)){
                    win(player_name);
                    return;
                }
                System.out.printf("\nК сожалению вы не отгадали слово!\n\tНаш игрок под номером %d - %s покидает нас :(\n\t\tПожелаем ему удачи !\n\n", move + 1, player_name);
                players[move].is_active = false;
            } else{
                while(field.contains(user_input)){
                    int index = field.indexOf(user_input);
                    players[move].points += 10;
                    // replace a letter to '*'
                    field = field.substring(0, index) + '*' + field.substring(index + 1);
                    // replace '*' to letter
                    str1 = str1.substring(0, index) + user_input + str1.substring(index + 1);

                    while(field.contains(user_input)) {
                        index = field.indexOf(user_input);
                        players[move].points += 10;
                        field = field.substring(0, index) + '*' + field.substring(index + 1);
                        str1 = str1.substring(0, index) + user_input + str1.substring(index + 1);
                    }

                    clearConsole();
                    descriptionOutput(word.description);
                    System.out.println();
                    clearConsole();
                    descriptionOutput(word.description);
                    System.out.println("Поздравляем, вы угадали букву!!!\n");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("Данное слово выглядит так: \n");
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
                        move %= players.length;
                        System.out.println("У игрока " + player_name + " больше всех очков!!!");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("\tДругим игрокам предостовляется шанс выиграть игру.");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("\t\tВы должны угадать все слово целиком↓");
                        System.out.println();
                        TimeUnit.SECONDS.sleep(2);
                        while (old != move){
                            String name = players[move].name.substring(0, 1).toUpperCase() + players[move].name.substring(1);
                            if(players[move].is_active){
                                clearConsole();
                                descriptionOutput(word.description);
                                System.out.printf("Игрок под номером %d - %s, ваш ход ↓ \n", move + 1, name);
                                user_input = rightInput();
                                if(user_input.equalsIgnoreCase(word.title)){
                                    System.out.println("Поздравляем, вы отгадали слово!!!\n");
                                    TimeUnit.SECONDS.sleep(2);
                                    clearConsole();
                                    win(players[move].name);
                                    return;
                                } else {
                                    System.out.println("К сожалению вы не отгадали слово :(");
                                    TimeUnit.SECONDS.sleep(1);
                                    System.out.printf("\tНаш игрок под номером %d - %s покидает нас :(\n", move + 1, name);
                                    TimeUnit.SECONDS.sleep(1);
                                    System.out.println("\t\tПожелаем ему удачи !\n");
                                    TimeUnit.SECONDS.sleep(2);
                                }
                            }
                            move++;
                            move %= players.length;
                        }
                        System.out.println("\nНикто из оставшихся игроков не отгадал слово!\n");
                        win(player_name);
                        return;
                    }

                    System.out.println("Вам предоставляется еще один ход ↓");
                    user_input = rightInput();

                    if(user_input.length() > 1) {
                        if (user_input.equalsIgnoreCase(word.title)) {
                            win(player_name);
                            return;
                        }
                        System.out.printf("К сожалению вы не отгадали слово :(\n\tНаш игрок под номером %d - %s покидает нас :(\n\t\tПожелаем ему удачи !\n", move + 1, player_name);
                        players[move].is_active = false;
                        break;
                    }
                }
                if(players[move].is_active){
                    clearConsole();
                    System.out.println("Такой буквы в данном слове нет :(\n");
                    TimeUnit.SECONDS.sleep(2);
                    clearConsole();
                }
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
        }
    }
}