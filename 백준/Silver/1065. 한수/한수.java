import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int cnt = 0;

        for (int i = 1; i <= N; i++) {
            if (i < 100) {
                cnt++;
            } else if (i == 1000) {
                break;
            } else {
                int hun = i / 100;
                int ten = (i / 10) % 10; 
                int one = i % 10;
                if ((hun - ten) == (ten - one)) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
        sc.close();
    }
}