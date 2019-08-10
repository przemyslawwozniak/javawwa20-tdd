package pl.sda.javawwa20;

import java.util.Scanner;

public class FizzBuzz {

    // /3 -> fizz
    // /5 -> buzz
    // /3 i /5 -> fizzbuzz

    public static void main(String[] args) {
        //pobrac wartosc od uzytkownika
        Scanner sc = new Scanner(System.in);
        int no = 0;
        do {
            System.out.println("Podaj liczbe: \n");
            no = sc.nextInt();
            System.out.println(play(no));
        } while(no != -100); //zakonczyc program dla wartosci '-100'
    }

    public static String play(int number) {
        if(number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";
        }
        else if(number % 3 == 0) {
            return "Fizz";
        }
        else if(number % 5 == 0) {
            return "Buzz";
        }
        return "";
    }

}
