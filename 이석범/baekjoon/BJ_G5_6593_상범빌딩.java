import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시작점과 끝점을 저장하기 위한 노드 클래스
class Node {
    public int x = 0;
    public int y = 0;
    public int z = 0;
}


public class BJ_G5_6593_상범빌딩 {

    //시작과 끝 노드
    static Node start;
    static Node end;

    static int l, r, c;

    //입력값이 char형이기 때문에 char[][]배열로 선언
    //visited는 기본적으로 0으로 초기화 하고 시간이 얼마나 지나는지 체크하기 위해 int형으로 선언
    static char[][] graph;
    static int[][] visited;

    public static void BFS(int x, int y) {

        //동, 서, 남, 북, 상, 하로 움직이므로 -r, r 추가
        int[] dx = {-1, 1, 0, 0, -r, r};
        int[] dy = {0, 0, -1, 1, 0, 0};

        //BFS로 하기때문에 큐 추가
        Queue<int[]> queue = new LinkedList<>();

        //시작 큐에 넣기
        queue.offer(new int[] {x, y});
        visited[x][y] = 1;

        //큐가 비어있지 않을 때 까지 반복
        while(!(queue.isEmpty())) {
            //큐에서 빼기
            int[] tmp = queue.poll();

            for(int i=0; i< 6; i++) {

                int nx = 0;
                int ny = 0;

                //입력값을 배열로 만들때 이차원 배열로 구성하였다.
                //z값을 표현하기 위해 부득이 하게 한층의 배열이 끝나고 다음 층의 배열을 뒤에 추가 하였다
                //그래서 한 층에서 0번째와 마지막 배열의 경우 x의 값이 상 또는 하로 갈경우 다음층으로 가는 경우가 생긴다
                //이를 방지하기 위해 아래와 같은 조건을 사용하여 이를 방지 하였다.
                if((tmp[0] % r == r-1 && i==1) || (tmp[0] % r == 0 && i==0)) continue;


                nx = dx[i] + tmp[0];
                ny = dy[i] + tmp[1];


                //이차원 배열의 처음 배열의 크기는 l*r로 하였다.
                if(nx >= 0 && nx <l * r && ny >= 0 && ny < c) {
                    //.은 갈수 있는 공간이므로 방문하지 않은 .을 큐에 넣고 지금까지 지난 시간에 1을 더한다
                    if(graph[nx][ny] == '.' && visited[nx][ny] == 0) {
                        queue.offer(new int[] {nx, ny});
                        visited[nx][ny] = visited[tmp[0]][tmp[1]] + 1;
                    }

                    //끝나는 지점이 되면 끝낸다
                    //주의 사항으로는 다음 테스트 케이스도 있기 때문에 큐를 비워야함
                    if(graph[nx][ny] == 'E') {
                        visited[nx][ny] = visited[tmp[0]][tmp[1]] + 1;
                        queue.clear();
                        return;
                    }
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        //입력값 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            //이차원 배열로 하기 때문에 처음 크기를 l*r, c로함
            visited = new int[l*r][c];
            graph = new char[l*r][c];

            //종료 시점 체크
            if(l == 0 && r == 0 && c == 0) break;


            start = new Node();
            end = new Node();

            for(int i=0; i< l; i++) {
                for(int j=0; j< r; j++) {
                    String input = br.readLine();
                    //층과 x의 값을 계산
                    int idx = (i*r)+j;
                    //char[] 배열로 변경
                    graph[idx] = input.toCharArray();

                    //시작 시점과 끝지점 노드 저장
                    if(input.contains("S")) {
                        start.z = i;
                        start.x = j;
                        start.y = input.indexOf("S");

                    }
                    if(input.contains("E")) {
                        end.z = i;
                        end.x = j;
                        end.y = input.indexOf("E");
                    }

                }
                //중간에 스페이스 부분이 있으므로 사용하지 않지만 입력 처리
                String space = br.readLine();
            }

            BFS(start.x + (start.z *r), start.y);

            //시작시점 방문 구분을 위해 1부터 시작을 하였으므로 끝에 1을 빼야함
            if(visited[end.x + (end.z * r)][end.y] == 0) System.out.println("Trapped!");
            else System.out.printf("Escaped in %d minute(s).\n",visited[end.x + (end.z * r)][end.y] - 1);
        }
    }
}
