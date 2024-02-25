import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [BOJ] 2630 색종이 만들기
// 	13032KB |	108ms
public class B2630 {
    static int[][] map;
    static int blue;
    static int white;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //로직
        makeConfetti(0,0,N);
        System.out.println(white);
        System.out.println(blue);
    }

    static void makeConfetti(int x, int y, int size){
        if(isSameColor(x,y, size)){
            if(map[x][y] == 0)white++;
            else blue++;
            return;
        }

        int newSize = size / 2;
        makeConfetti(x,y,newSize);  //왼위
        makeConfetti(x, y + newSize, newSize);  // 오위
        makeConfetti(x + newSize, y, newSize);  //왼아래
        makeConfetti(x + newSize, y + newSize, newSize);    // 오아래
    }


    static boolean isSameColor(int x, int y, int size){ //크기에 맞게 완탐으로 색 모두 같은지 판단
        int flag = map[x][y];
        for(int i = x; i < x + size; i++){
            for(int j = y; j < y + size; j++){
                if(map[i][j] != flag){
                    return false;
                }
            }
        }
        return true;
    }
}
