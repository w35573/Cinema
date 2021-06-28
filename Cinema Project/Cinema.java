//package cinema;
import java.util.Scanner;
public class Cinema {
    static Scanner scan = new Scanner(System.in);
    static int[][] arr;
    static int rows, columns, rowNo, columnNo;
    static int currentIncome=0, totalIncome;
    static int choice, noOfTickets=0, totalTickets=0;
    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        Cinema.rows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        Cinema.columns = scan.nextInt();
        totalTickets=rows*columns;
        feed(rows,columns);
        Menu();
    }
    static void Menu () {
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
        choice = scan.nextInt();
        do {
            if(choice==0)
                break;
            switch (choice) {
                case 1:
                    displayBooked(rows,columns);
                    break;
                case 2:
                    display();
                    break;
                case 3:
                    Statistics();
                    break;
                default:
                    break;
            }
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            choice = scan.nextInt();
        }while(choice!=0);
    }
    static void Statistics() {
        System.out.println("Number of purchased tickets:" + " " + noOfTickets);
        float a = (float)noOfTickets;
        float b = (float)totalTickets;
        float percent = a/b*100;
        System.out.printf("Percentage: %.2f",percent);
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        if (totalTickets<=60) {
            totalIncome = totalTickets*10;
        }
        else {
            int firstHalf=rows/2, secondHalf=rows-firstHalf;
            totalIncome = (columns*firstHalf*10) + (columns*secondHalf*8);
        }
        System.out.println("Total income: $" + totalIncome);
    }
    static void feed(int a, int b) {
        arr = new int[a+1][b+1];
        for (int i=0; i<a+1; i++) {
            for (int j=0; j<b+1; j++) {
                if (i==0 && j==0)
                    Cinema.arr[0][0]=32;
                else if(i==0 && j!=0)
                    Cinema.arr[0][j]=j;
                else if(i!=0 && j==0)
                    Cinema.arr[i][0]=i;
                else
                    Cinema.arr[i][j]=83;
            }
        }
    }
    static void displayBooked(int a, int b) {
        System.out.println("Cinema:");
        for (int i=0; i<a+1; i++) {
            for (int j=0; j<b+1; j++) {
                if(i==0 && j!=0)
                    System.out.print(j + " ");
                else if(i!=0 && j==0)
                    System.out.print(i + " ");
                else
                    System.out.print((char)Cinema.arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void display() {
        System.out.println("Enter a row number:");
        rowNo = scan.nextInt();
        System.out.println("Enter a seat number in that row:");
        columnNo = scan.nextInt();
        checkTicket();
    }
    static void checkTicket() {
        int firstHalf=rows/2;
        if(rowNo>rows || columnNo>columns) {
            System.out.println("Wrong input!");
            display();
        }
        else if(arr[rowNo][columnNo]==66) {
            System.out.println("That ticket has already been purchased!");
            display();
        }
        else {
            arr[rowNo][columnNo] = 66;
            noOfTickets++;
            if (totalTickets<=60) {
                currentIncome+=10;
                totalIncome = totalTickets*10;
                System.out.println("Ticket price: $10");
            }
            else if (rowNo>firstHalf) {
                currentIncome += 8;
                System.out.println("Ticket price: $8");
            }
            else {
                currentIncome += 10;
                System.out.println("Ticket price: $10");
            }
        }
    }
}