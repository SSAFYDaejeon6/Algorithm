package main;

import java.io.*;
import java.util.*;

public class boj11559 {

    static char[][] map = new char[12][6];
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 12; i++) {
            String ip = br.readLine();
            for(int j = 0; j < ip.length(); j++) map[i][j] = ip.charAt(j);
        }
        int re =  puyo();
        System.out.println(re);
    }

    public static int puyo() {
        int cnt = 0;
        while(true) {
            int boomCnt = 0;
            // 터트리기
            for(int i = 0; i < 12; i++) {
                for(int j = 0; j < 6; j++) {
                    if(map[i][j] == '.') continue;
                    boomCnt += bfs(new Node(i, j));
                }
            }
            if(boomCnt == 0) break;
            cnt++;
            down();
        }
        return cnt;
    }

    public static void down() {
        for(int i = 0; i < 6; i++) {
            for(int j = 11; j >= 0; j--) {
                if(map[j][i] == '.') continue;
                Node cur = new Node(j, i);
                Node next = new Node(cur.y + 1, cur.x);
                if(next.y < 0 || next.y >= 12 || next.x < 0 || next.x >= 6) continue;

                while(check(next) && map[next.y][next.x] == '.') {
                    if(next.y < 0 || next.y >= 12 || next.x < 0 || next.x >= 6){
                        break;
                    }
                    next.y++;
                }

                if(j != next.y - 1) {
                    map[next.y - 1][next.x] = map[cur.y][cur.x];
                    map[cur.y][cur.x] = '.';
                }
            }
        }
    }

    public static int bfs(Node start) {
        Queue<Node> q = new LinkedList<>();
        List<Node> removeList = new ArrayList<>(); //지울 리스트
        boolean[][] visited = new boolean[12][6];

        q.offer(start);
        visited[start.y][start.x] = true;
        removeList.add(start);

        while(!q.isEmpty()) {
            Node cur = q.poll();

            for(int i = 0; i < 4; i++) {
                Node next = new Node(cur.y + dy[i], cur.x + dx[i]);
                if(next.y < 0 || next.y >= 12 || next.x < 0 || next.x >= 6) continue;
                if(visited[next.y][next.x]) continue;
                if(map[next.y][next.x] == map[start.y][start.x]) {
                    q.offer(next);
                    visited[next.y][next.x] = true;
                    removeList.add(next);
                }
            }
        }

        if(removeList.size() >= 4) {
            for(Node c : removeList) map[c.y][c.x] = '.';
            return 1;
        }
        else return 0;
    }

    public static boolean check(Node c) {
        if(0 <= c.y && c.y < 12 && 0 <= c.x && c.x < 6) return true;
        else return false;
    }

    static class Node{
        int y, x;
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}