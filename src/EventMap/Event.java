package EventMap;

public class Event implements Comparable<Event>{
           private String name;
           private int year;
           private int month;
           private int number;
           Event(String name,int year,int month,int number)
           {
               this.name=name;
               this.year=year;
               this.month=month;
               this.number=number;
           }
           String getName(){
               return this.name;
           }
           int getYear()
           {
               return this.year;
           }
    int getMonth()
    {
        return this.month;
    }
    int getNumber()
    {
        return this.number;
    }

    void print()
    {
        System.out.print(name+" ");
        System.out.print(number+".");
        System.out.print(month+".");
        System.out.print(year+" \n");
    }

    @Override
    public int compareTo(Event o) {

        if(this.getYear()>o.getYear())
            return 1;
        else if (o.getYear()>this.getYear()) {
            return -1;
        } else if (this.getMonth()>o.getMonth()) {
            return 1;
        } else if (o.getMonth()>this.getMonth()) {
            return -1;
        } else if (this.getNumber()>o.getNumber()) {
            return 1;
        } else if (o.getNumber()>this.getNumber()) {
            return -1;
        }
        else {
            return 0;
        }
    }
}


