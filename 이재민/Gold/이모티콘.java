package algo0530;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
 * 12900KB | 92ms
 */

public class 이모티콘 {
	
	static class pair{
		int x, y, z;
		public pair(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
	}
	
	static Queue<pair> q;
	static boolean visited[][] = new boolean[1001][1001];
	
	static void Emo(int s){
		q = new ArrayDeque<>();
	    visited[1][0] = true;
	    q.add(new pair(1, 0, 0));
	    while(!q.isEmpty()){
	        int m = q.peek().x;
	        int b = q.peek().y;
	        int cnt = q.peek().z;
	        q.poll();
	        
	        if(m==s){
	            System.out.println(cnt);
	            return;
	        }
	
	        if(!visited[m][m]){
	            q.add(new pair(m, m, cnt+1));
	            visited[m][m] = true;
	        }
	
	        if((m+b)<1001 && !visited[m+b][b]){
	            q.add(new pair(m+b, b, cnt+1));
	            visited[m+b][b] = true;
	        }
	
	        if(m!=0 && !visited[m-1][b]){
	            q.add(new pair(m-1, b, cnt+1));
	            visited[m-1][b] = true;
	        }
	    }
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int S = Integer.parseInt(br.readLine());
		Emo(S);
	}
	
}
	


