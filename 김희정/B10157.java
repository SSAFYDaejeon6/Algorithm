import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
[BOJ] 10157 자리배정
 */
public class B10157 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        if(k > c*r){
            System.out.println(0);
            return;
        }

        if(k == 1){
            System.out.println(1 + " " + 1);
            return;
        }

        int[][] arr = new int[c+2][r+2];
        int[] dx = new int[]{0,1,0,-1};
        int[] dy = new int[]{1, 0, -1,0};

        int x = 1;
        int y = 1;
        arr[x][y] = 1;
        int tmp = 1; // tmp가 K가 될 때까지
        A : for(int d = 0; d < r*c; d++){
            d = d % 4;
            if(d == 0 || d == 2){
                while(true){
                    int newX = x + dx[d];
                    int newY = y + dy[d];
                    if(newY > r || newY <= 0 ||arr[newX][newY] == 1 ){
                        break;
                    }
                    x = newX;
                    y = newY;
                    arr[newX][newY] = 1;
                    tmp++;
                    if(tmp == k) break A;
                }
            }
            else{
                while(true){
                    int newX = x + dx[d];
                    int newY = y + dy[d];
                    if(newX > c || newX <= 0||arr[newX][newY] == 1 ){
                        break;
                    }
                    x = newX;
                    y = newY;
                    arr[newX][newY] = 1;
                    tmp++;
                    if(tmp == k) break A;
                }
            }
        }

        System.out.println(x + " " + y);
    }
}
