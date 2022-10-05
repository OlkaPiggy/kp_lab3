package EventMap;
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/*Задано інформацію про події (назва, рік, місяць, число).
        1. Створити карту з ключем рік та значенням –списком всіх подій року.
        Відсортувати кожен список за датами.
        2. Вилучити з попередньої карти всі події, різниця дат яких є 1 день.
        3. З 2 різних файлів зчитати 2 вихідні набори інформації про події.
        Сформувати список дат, які містяться в першому файлі і не містяться в
        іншому. Знайти серед них всеможливі пари дат, різниця між якими є
        більше одного року.*/

public class EventMap {
    private HashMap<Integer, ArrayList<Event>> event;

    HashMap<Integer, ArrayList<Event>> getEvent() {
        return this.event;
    }

    public EventMap(File f1) {

        event=new HashMap<Integer,ArrayList<Event>>();
        try (FileReader reader = new FileReader(f1)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String tmp = scan.nextLine();
                String[] arr = tmp.split(" ");
                Event eventRead = new Event(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
                if (!this.event.containsKey(eventRead.getYear())) {
                    this.event.put(eventRead.getYear(), new ArrayList<Event>());
                }
                this.event.get(eventRead.getYear()).add(eventRead);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public EventMap(File f1, File f2) {

        ArrayList<Event> event1 = new ArrayList<Event>();
        ArrayList<Event> event2 = new ArrayList<Event>();

        try (FileReader reader = new FileReader(f1)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String tmp = scan.nextLine();
                String[] arr = tmp.split(" ");
                Event eventRead = new Event(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
                event1.add(eventRead);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try (FileReader reader = new FileReader(f2)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String tmp = scan.nextLine();
                String[] arr = tmp.split(" ");
                Event eventRead = new Event(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
                event2.add(eventRead);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        //знайти ті, що містяться в 1 файлі, але не містяться в 2
        this.event = new HashMap<Integer, ArrayList<Event>>();

                for (int i=0;i<event1.size();i++) {
                    for (int j=0;j<event2.size();j++) {
                        Event e1=event1.get(i);
                       Event e2=event2.get(j);

                        if (e1.getName().compareTo(e2.getName())==0&& e1.getYear()==e2.getYear()
                        &&e1.getMonth()==e2.getMonth()&&e1.getNumber()==e2.getNumber()) {
                            event1.remove(e1);
                            event2.remove(e2);
                            j--;
                            continue;
                        } else {
                            int key = e1.getYear();
                            if (!this.event.containsKey(key)) {
                                this.event.put(key, new ArrayList<Event>());
                            }
                            this.event.get(key).add(e1);
                            if(i<event1.size()-1)
                                i++;
                            else
                                break;
                            j=0;
                        }
            }
        }
        print();

        //Знайти серед них всеможливі пари дат, різниця між якими є
        //        більше одного року
        System.out.print("Pairs with difference more than year\n");
        for(int i=0;i<event1.size();i++)
        {
            for(int j=0;j<event1.size();j++) {
                Event e1=event1.get(i);
                Event e2=event1.get(j);

                if (e1.getYear() != e2.getYear()) {
                    if (e1.getYear() - e2.getYear() == 1 || e1.getYear() - e2.getYear() == -1) {
                        if (e1.getMonth() != e2.getMonth()) {
                            if (e1.getMonth() > e2.getMonth() && e1.getYear() > e2.getYear()) {
                                e1.print();e2.print();
                                System.out.print("\n");
                            } else if (e1.getMonth() < e2.getMonth() && e1.getYear() < e2.getYear()) {
                                e1.print();e2.print();
                                System.out.print("\n");
                            } else
                                continue;
                        } else {
                            if (e1.getNumber() > e2.getNumber() && e1.getYear() > e2.getYear()) {
                                e1.print();e2.print();
                                System.out.print("\n");
                            } else if (e1.getNumber() < e2.getNumber() && e1.getYear() < e2.getYear()) {
                                e1.print();e2.print();
                                System.out.print("\n");
                            } else
                                break;
                        }
                    } else {
                        e1.print();e2.print();
                        System.out.print("\n");
                    }
                } else {
                    continue;
                }
            }
        }
        System.out.print("\n");

    }
    public void print() {
        for (int i : this.event.keySet()) {
            System.out.println(i);
            ArrayList<Event> e = this.event.get(i);
            for (int j = 0; j < e.size(); j++) {
                e.get(j).print();
            }
        }
        System.out.print("\n");
    }

    public void sortByDates ()
    {
        for(int key :this.event.keySet()) {
            Collections.sort(this.event.get(key));
        }
        print();
    }

    public void delDifferenceOneYear(){
        for(int key :this.event.keySet()) {
            int s = event.get(key).size();
            for (int k = 0; k < s - 1; k++) {
                if (event.get(key).get(k).getNumber() - event.get(key).get(k + 1).getNumber() == 1 || event.get(key).get(k).getNumber() - event.get(key).get(k + 1).getNumber() == -1) {
                    event.get(key).remove(k);
                    event.get(key).remove(k + 1);
                    s--;
                    s--;
                    k--;
                }
            }
        }
        print();
    }
}
