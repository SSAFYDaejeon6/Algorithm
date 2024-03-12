package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 *구하는 것
 *1. 방의 개수
 *2. 가장 넓은 방의 길이
 *3. 하나의 벽을 제거하여 가장 넓은 방의 크기
 *
 *n, m
 *벽에 대한 정보 비트연산으로 나타냄
 *서쪽벽은 1
 *북쪽벽은 2
 *동쪽벽은 4
 *남쪽벽은 8
 *19008kb 120ms
 */
public class BJ_G3_2234_성곽 {
	static int R, C;
	
	static int[][] graph;
	
	static int[][] visited;
	
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	static int roomCnt = 0;
	static int maxRoom = 0;
	static int removeMaxRoom = 0;
	
	static class Node {
		int r;
		int c;
		
		Node(int r, int c) {
			this.r = r;
			this.c =c;
		}
	}
	
	static void BFS(int r, int c, int group) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(r, c));
		visited[r][c] = group;
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			cnt++;
			int curR = current.r;
			int curC = current.c;
//			System.out.println(curR+" "+curC);
			
			for(int i=0; i<4;i++) {
//				System.out.println((graph[curR][curC] & (1 << i)) == (1 << i));
//				System.out.println(i);
				if((graph[curR][curC] & (1 << i)) == (1 << i)) continue;
				
				int nr = curR + dr[i];
				int nc = curC + dc[i];
				
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] != 0) continue;
				
				queue.offer(new Node(nr, nc));
				visited[nr][nc] = group;
			}
		}
//		System.out.println(cnt);
		maxRoom = Math.max(cnt, maxRoom);
		
	}
	
	static class Room {
		int num;
		int room;
		
		Room(int num, int room) {
			this.num = num;
			this.room = room;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		graph = new int[R][C];
		visited = new int[R][C];
		
		for(int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int group = 1;
		for(int r=0;r<R;r++) {
			for(int c=0; c<C;c++) {
				if(visited[r][c] != 0) continue;
				BFS(r, c, group);
				
				group++;
				roomCnt++;
			}
		}
		
//		for(int[] line: visited) {
//			for(int i:line) {
//				System.out.print(i+" ");
//			}
//			System.out.println();
//		}
//		
		System.out.println(roomCnt);
		System.out.println(maxRoom);
		
		//1 2 3 4 5
		boolean[][] nearCheck = new boolean[group][group];
		int[] groupList = new int[group];
		
		for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				groupList[visited[r][c]] += 1;
				for(int i=0; i<4;i++) {
					int nr = r+dr[i];
					int nc = c+dc[i];
					if(nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] == 0) continue;
					
					int number = visited[r][c];
					int nearNumber = visited[nr][nc];
					if(number == nearNumber) continue;
					if(visited[r][c] != visited[nr][nc] || !nearCheck[number][nearNumber]) {
						nearCheck[number][nearNumber] = true;
					}
					
				}
			}
		}
		
		
		
		for(int r=1; r<group;r++) {
			for(int c=1 ;c<group;c++) {
				if(nearCheck[r][c]) {
					removeMaxRoom = Math.max(removeMaxRoom, groupList[r]+groupList[c]);
				}
			}
		}
		System.out.println(removeMaxRoom);
		
//		for(boolean[] line:nearCheck) {
//			for(boolean i:line) {
//				System.out.print(i+" ");
//			}
//			System.out.println();
//		}
		
	}
}
