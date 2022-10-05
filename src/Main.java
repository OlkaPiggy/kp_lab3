import EventMap.EventMap;
import java.util.*;
import java.io.File;
/*Задано інформацію про події (назва, рік, місяць, число).
        1. Створити карту з ключем рік та значенням –списком всіх подій року.
        Відсортувати кожен список за датами.
        2. Вилучити з попередньої карти всі події, різниця дат яких є 1 день.
        3. З 2 різних файлів зчитати 2 вихідні набори інформації про події.
        Сформувати список дат, які містяться в першому файлі і не містяться в
        іншому. Знайти серед них всеможливі пари дат, різниця між якими є
        більше одного року.*/

public class Main {
    public static void main(String[] args) {
        System.out.println("Lab 3!");
        File f= new File("read.txt");
        EventMap map= new EventMap(f);;

        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println("What do you want to do?");
            System.out.println("1) Read data from 1 file and sort by dates\n2) Delete from map all data with difference in 1 day\n" +
                    "3) Read 2 sets from 2 different files.\n" +
                    "Form a list of dates that are contained in the 1 file and are not contained in 2\n" +
                    "Find all possible pairs of dates, the difference between which is" +
                    "more than 1 year.\n");
            int choose = in.nextInt();

            if (choose == 1) {
                File f1= new File("read.txt");
                map= new EventMap(f1);
                map.print();
                System.out.print("After sort: \n");
                map.sortByDates();
            }
            else if (choose == 2) {
                map.delDifferenceOneYear();
            }
            else if (choose == 3) {
                File f1= new File("read.txt");
                File f2= new File("read2.txt");
                map= new EventMap(f1,f2);
            }
            else
                System.out.println("Wrong input");
        }
    }
}