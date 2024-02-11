import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//	14256KB | 	124ms
// [BOJ] 2578  빙고
public class B2578 {
    static int[][] arr;
    static int bingo;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        arr = new int[5][5];
        for(int i = 0; i < 5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //사회자가 부르는 수
        for(int i = 0; i < 5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++){
                int curr = Integer.parseInt(st.nextToken());
                answer++;
                //현재 수에 해당하는 값 arr에서 0으로 만들기
                for(int row = 0; row < 5; row++){
                    for(int col = 0; col < 5; col++){
                        if(curr == arr[row][col]){
                            arr[row][col] = 0;
                            //현재 row, col 기준으로 가로, 세로, 대각선 빙고 확인
                            if(isBingoForRow(row)) bingo++;
                            if(isBingoForCol(col)) bingo++;
                            if(isBingoForLeftCross(row,col)) bingo++;
                            if(isBingoForRightCross(row,col)) bingo++;
                            if(bingo >= 3){
                                System.out.println(answer);
                                return;
                            }

                        }
                    }
                }



            }
        }
    }

    static boolean isBingoForRow(int row){
        for(int j = 0; j < 5; j++){
            if(arr[row][j] != 0) return false;
        }
        return true;
    }

    static boolean isBingoForCol(int col){
        for(int i = 0; i < 5; i++){
            if(arr[i][col] != 0) return false;
        }
        return true;
    }

    static boolean isBingoForLeftCross(int row, int col){   //왼쪽 대각선
        if(row != col) return false;
        for(int i = 0; i < 5; i++){
            if(arr[i][i] != 0) return false;
        }
        return true;
    }

    static boolean isBingoForRightCross(int row, int col){
        if(col != 4-row) return false;
        for(int i = 0; i < 5; i++){
            if(arr[i][4-i] != 0) return false;
        }
        return true;
    }
}
