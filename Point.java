import java.util.Scanner;

public class Point {
    int x;
    int y;

    // Constructor
    // same name as class, no return type
    public Point(int x, int y) {
        this.x = x;         // "this" refers to current obj
        this.y = y;
    }

    public int sum() {      // instance method
        return this.x + this.y;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter coordinate x: ");
        int x = in.nextInt();
        System.out.print("Enter coordinate y: ");
        int y = in.nextInt();
        Point p = new Point(x, y);
        System.out.println("Sum is " + p.sum());
        in.close();
    }
}