package cinema;
import java.util.Scanner;


public class Cinema {
    static String[][] cinema;
    static int[][] price;
    static int rows;
    static int seats;
    static int boughtTickets = 0;
    static double percentage = 0.0; 
    static int currentIncome = 0;
    static int totalIncome = 0;
    static int totalSeats;

    public static void makePrice(int totalSeats, int frontHalf) {
        if (totalSeats < 60) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < seats; j++) {
                    price[i][j] = 10;
                }
            }
        } else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < seats; j++) {
                    if (i < frontHalf) {
                        price[i][j] = 10;
                    } else {
                        price[i][j] = 8;
                    }
                }
            }
        }
    }

    public static int buyTicket(int row, int seat) {
        if (cinema[row - 1][seat - 1].equals("B")) {
            System.out.println("That ticket has already been purchased");
            return 0;
        }
        System.out.print("Ticket price: ");
        updateCinemaToBought(row, seat);
        System.out.println("$" + price[row - 1][seat - 1]);
        boughtTickets++;
        currentIncome += price[row - 1][seat - 1];
        percentage = ((1.0 * boughtTickets) / (1.0 * totalSeats)) * 100;
        return 1;
    }
    public static void makeCinema(int rows, int seats) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = "S";
            }
        }
    }

    public static void showStatistics() {
        System.out.println("Number of purchased tickets: " + boughtTickets);
        System.out.printf("Percentage: %.2f", percentage);
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static void updateCinemaToBought(int row, int seat) {
        cinema[row - 1][seat - 1] = "B";
    }
    
    public static void showSeats() {
        System.out.print("Cinema:\n ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 1; i <= rows; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(cinema[i - 1][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int cont = 3;
        int ticketRow;
        int ticketSeat;
        boolean bought = false;
        System.out.print("Enter the number of rows:");
        rows = scan.nextInt();
        System.out.print("Enter the number of seats in each row:");
        seats = scan.nextInt();

        cinema = new String [rows][seats];
        makeCinema(rows, seats);

        totalSeats = rows * seats;
        int frontHalf = rows / 2;
        int backHalf = rows - frontHalf;
        if (totalSeats < 60) {
            totalIncome = totalSeats * 10;
        } else {
            totalIncome = frontHalf * seats * 10 + backHalf * seats * 8;
        }
        price = new int[rows][seats];
        makePrice(totalSeats, frontHalf);

        while (cont != 0) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            cont = scan.nextInt();
            if (cont == 1) {
                showSeats();
            } else if (cont == 2){
                while (!bought) {
                    System.out.print("Enter a row number:");
                    ticketRow = scan.nextInt();
                    System.out.print("Enter a seat number in that row:");
                    ticketSeat = scan.nextInt();
                    if (ticketSeat > seats || ticketRow > rows) {
                        System.out.print("Wrong input!");
                    } else {
                        int temp = buyTicket(ticketRow, ticketSeat);
                        if (temp == 1) {
                            bought = true;
                        }
                    }
                }
                bought = false;
            } else if (cont == 3) {
                showStatistics();
            } else {
                
            }
        }
    }
}