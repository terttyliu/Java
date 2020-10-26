import java.awt.geom.FlatteningPathIterator;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

public class main {
    private static char[][] chars;
    private static boolean[][] flag;
    private static int hang;
    private static int lie;

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        hang = cin.nextInt();
        lie = cin.nextInt();
        chars = new char[hang][lie];
        flag = new boolean[hang][lie];
        cin.nextLine();
        for (int i = 0; i < hang; i++) {
            chars[i]= cin.nextLine().toCharArray();
        }
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < lie; j++) {
                    tryGo(i, j);
            }
        }
        int sum=0;
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < lie; j++) {
                if (flag[i][j]){
                    sum++;
                }
            }
        }
        System.out.println(sum);
    }

    public static void tryGo(int i, int j) {
        int count = 0;
        int row=i;
        int col=j;
        while (count < hang + lie) {
            if (flag[i][j]){
                flag[row][col]=true;
                break;
            }
            count++;
            char a = chars[i][j];
            switch (a) {
                case 'W':
                    i--;
                    break;
                case 'S':
                    i++;
                    break;
                case 'A':
                    j--;
                    break;
                case 'D':
                    j++;
                    break;
                default:break;
            }
            if (i<0||j<0||i>hang-1||j>lie-1){

            }
        }

    }
}
