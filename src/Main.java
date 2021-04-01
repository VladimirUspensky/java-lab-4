import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter data in this format `Last name, First name, Patronymic day.month.year`");
            System.out.println("Enter `exit` to exit");
            Scanner in = new Scanner(System.in);
            String data = in.nextLine();
            if (data.equals("exit")) {
                break;
            }
            ConsoleParser parser = new ConsoleParser(data);
            System.out.println(parser.getChangedData());
        }
    }
}
