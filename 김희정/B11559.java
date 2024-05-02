import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/*
 * [BOJ] 11559 Puyo Puyo
 * 	12204KB |	88ms
 * 연쇄 조건
 * - 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 한꺼번에 없어짐 => 1연쇄
 * - 아래로 떨어지고 나서 다시 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 => 1연쇄
 * - 여러 그룹이 터지더라도 1연쇄
 *
 * 중력의 영향을 밭아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어짐
 *
 * 풀이
 * 1. 뿌요 연쇄반응 터지기 => 각 정점별 BFS돌리기
 * 2. 아래로 이동 => 각 열별 stack
 */
public class B11559 {
    static char[][] map;
    static int w = 6;
    static int h = 12;
    static int answer;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[h][w];
        for(int i = 0; i < h; i++) {
            char[] s = br.readLine().toCharArray();
            for(int j = 0; j < w; j++) {
                map[i][j] = s[j];
            }
        }

        //로직
        while (puyo()) {
            //1. 뿌요 터지기 => 연쇄반응
            // 터진게 아무것도 없으면 종료
            answer++;

            //2. 아래로 이동
            down();
        }

        System.out.println(answer);


    }

    static boolean puyo() {
        boolean flag = false;
        for(int i = h-1; i >= 0; i--) {
            for(int j = 0; j < w; j++) {
                if(map[i][j] == '.') continue;
                if(BFS(i,j)) {
                    map[i][j] = '.';
                    flag = true;
                }
            }
        }
        return flag;
    }

    static void down(){
        Stack<Character> stack = new Stack<>();
        for(int j = 0; j < w; j++){
            for(int i = 0; i < h; i++){
                if(map[i][j] == '.') continue;
                stack.push(map[i][j]);
                map[i][j] = '.';
            }
            int idx = h-1;
            while(!stack.isEmpty()) {
                map[idx--][j] = stack.pop();
            }
        }
    }

    static boolean BFS(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[h][w];
        q.add(new int[] {i,j});
        visited[i][j] = true;
        List<int[]> same = new ArrayList<>();

        while(!q.isEmpty()) {
            int[] curr = q.poll();

            for(int d = 0; d < 4; d++){
                int nx = curr[0] + dx[d];
                int ny = curr[1] + dy[d];
                if(!inRange(nx,ny) || visited[nx][ny] || map[nx][ny] == '.') continue;
                if(map[i][j] == map[nx][ny]) {
                    same.add(new int[]{nx,ny});
                    q.offer(new int[] {nx,ny});
                    visited[nx][ny] = true;
                }
            }
        }
        // 연쇄반응이 가능한 경우
        if(same.size() >= 3){
            for(int[] puyo : same){
                map[puyo[0]][puyo[1]] = '.';
            }
            return true;
        }

        return false;

    }

    static boolean inRange(int x, int y){
        return 0 <= x && x < h && 0 <= y && y < w;
    }
}
