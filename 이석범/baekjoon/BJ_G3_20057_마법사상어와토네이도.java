package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * The type Bj g 3 20057.
 * 1. 이동
 * N은 3~499 홀수
 * N / 2, N / 2  : 5 / 2 -> 2, 2 에서 시작 0,0으로 이동
 *
 *
 * 2. 모래 이동
 *     2
 *  10 7 1
 *5 a y x  -> a로 가는 비율은 55%
 *  10 7 1
 *     2
 * 3. 격자 밖으로 나가는 것 카운트
 *
 * 41672kb 624ms -> dx를 따로 생성해 모래가 날리는 비율을 반복문으로 처리할 경우 쉽게 되는 것 같음
 * 조심해야할 점은 남은 모래는 보낸 모래의 나머지이다
 * 
 */
public class BJ_G3_20057_마법사상어와토네이도 {
    static int[][] graph;
    static int n;
    static final int LEFT = 0, DOWN = 1, RIGHT = 2, UP = 3;

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};
    
    static int result;

    //왼  아  오 위
    //1 -> 1 -> 2-> 2 -> 3 -> ... -> 6
    static void move(int r, int c) {
        int count = 1;
        int dir = 0;
        for(int i=0; i<n;i++) {
            for(int k=0; k<2;k++) {
                for (int j = 0; j < count; j++) {
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];
//                    System.out.println(nr+" "+nc);
//                    System.out.println(count);
                    sendSand(nr, nc, dir);
                    graph[nr][nc] = 0;
                    
                    int tmp = result;
//                    for(int a=0; a<n;a++) {
//                    	tmp -= Arrays.stream(graph[a]).sum();
//                    }
//                    System.out.println(tmp);
                    
                    r = nr;
                    c = nc;
                    if (r == 0 && c == 0) return;
//                    for (int[] line : graph) {
//                    	for (int i1 : line) {
//                    		System.out.print(i1+" ");
//                    	}
//                    	System.out.println();
//                    }
//                    System.out.println();
                }
                dir = (dir + 1) % 4;
                }
	            count++;
            }


    }


    //격자 밖으로 나가는지 체크 나가면 소멸 아니면 모래 추가
    static void check(int r, int c, int sand) {
//        System.out.println(r+" "+c+" "+sand);
        if(r < 0 || r >= n || c < 0 || c >= n) return;
        else graph[r][c] += sand;
    }

    /*
     * 2. 모래 이동
     *     2
     *  10 7 1
     *5 a  y x  -> y의 모래가 a로 가는 비율은 55%
     *  10 7 1
     *     2
     *
     * 방향에 따라 달라지는 듯
     */
    //상하좌우 보정
    static void sendSand(int r, int c, int dir) {
        int sand = graph[r][c];
        int div = 0;
        switch (dir) {
            case UP:
                //1% 비율
                check(r+1, c-1, (int)(sand * 0.01));
                check(r+1, c+1, (int)(sand * 0.01));

                //2% 비율
                check(r, c+2, (int)(sand * 0.02));
                check(r, c-2, (int)(sand * 0.02));

                //7% 비율
                check(r, c-1, (int)(sand * 0.07));
                check(r, c+1, (int)(sand * 0.07));

                //10% 비율
                check(r-1, c-1, (int)(sand * 0.1));
                check(r-1, c+1, (int)(sand * 0.1));

                //5% 비율
                check(r-2, c, (int)(sand * 0.05));
                //check(r-1, c, (int)(sand * 0.55));
                
                div =  ((int)(sand * 0.01) * 2) + ((int)(sand * 0.02) * 2) + ((int)(sand * 0.07) * 2) + ((int)(sand * 0.1) * 2) + ((int)(sand * 0.05));

                //나머지
                check(r-1, c, sand - div);
                break;

            case DOWN:
                //1% 비율
                check(r-1, c-1, (int)(sand * 0.01));
                check(r-1, c+1, (int)(sand * 0.01));

                //2% 비율
                check(r, c-2, (int)(sand * 0.02));
                check(r, c+2, (int)(sand * 0.02));

                //7% 비율
                check(r, c-1, (int)(sand * 0.07));
                check(r, c+1, (int)(sand * 0.07));

                //10% 비율
                check(r+1, c-1, (int)(sand * 0.1));
                check(r+1, c+1, (int)(sand * 0.1));

                //5% 비율
                check(r+2, c, (int)(sand * 0.05));

                //55% 비율
                //check(r+1, c, (int)(sand * 0.55));
                
                div = ((int)(sand * 0.01) * 2) + ((int)(sand * 0.02) * 2) + ((int)(sand * 0.07) * 2) + ((int)(sand * 0.1) * 2) + ((int)(sand * 0.05));


                //나머지
                check(r+1, c, sand - div);
                break;

            case LEFT:
                //1% 비율
                check(r-1, c+1, (int)(sand * 0.01));
                check(r+1, c+1, (int)(sand * 0.01));

                //2% 비율
                check(r-2, c, (int)(sand * 0.02));
                check(r+2, c, (int)(sand * 0.02));

                //7% 비율
                check(r-1, c, (int)(sand * 0.07));
                check(r+1, c, (int)(sand * 0.07));

                //10% 비율
                check(r-1, c-1, (int)(sand * 0.1));
                check(r+1, c-1, (int)(sand * 0.1));

                //5% 비율
                check(r, c-2, (int)(sand * 0.05));

                //55% 비율
//                check(r, c-1, (int)(sand * 0.55));
                
                div = ((int)(sand * 0.01) * 2) + ((int)(sand * 0.02) * 2) + ((int)(sand * 0.07) * 2) + ((int)(sand * 0.1) * 2) + ((int)(sand * 0.05));

//                System.out.println(sand+"나머지");
                //나머지
                check(r, c-1, sand - div);
                break;

            case RIGHT:
                //1% 비율
                check(r-1, c-1, (int)(sand * 0.01));
                check(r+1, c-1, (int)(sand * 0.01));

                //2% 비율
                check(r-2, c, (int)(sand * 0.02));
                check(r+2, c, (int)(sand * 0.02));

                //7% 비율
                check(r-1, c, (int)(sand * 0.07));
                check(r+1, c, (int)(sand * 0.07));

                //10% 비율
                check(r-1, c+1, (int)(sand * 0.1));
                check(r+1, c+1, (int)(sand * 0.1));

                //5% 비율
                check(r, c+2, (int)(sand * 0.05));

                //55% 비율
//                check(r, c+1, (int)(sand * 0.55));
                div = ((int)(sand * 0.01) * 2) + ((int)(sand * 0.02) * 2) + ((int)(sand * 0.07) * 2) + ((int)(sand * 0.1) * 2) + ((int)(sand * 0.05));
                //나머지
                check(r, c+1, sand - div);
                break;
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        graph = new int[n][n];

        for(int r=0; r<n;r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0;c<n;c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
                result += graph[r][c];
            }
        }

        int start = n /2;
        move(start, start);
//        for (int[] line : graph) {
//        	for (int i1 : line) {
//        		System.out.print(i1+" ");
//        	}
//        	System.out.println();
//        }
//        System.out.println();
        for(int i=0; i<n;i++) {
        	result -= Arrays.stream(graph[i]).sum();
        }
        System.out.println(result);
    }
}
