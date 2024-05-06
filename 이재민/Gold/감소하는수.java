package algo0506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
/*
 * 13032KB | 92ms
 */
public class 감소하는수 {
	static int N;
	static Queue<Integer> q;
	static int cnt = 9;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		if(N >= 1023) System.out.println(-1);
		
		else if(N < 10) {
			System.out.println(N);
		}
		
		else if(N == 1022) System.out.println("9876543210");
		
		else {
			q = new ArrayDeque<Integer>();
			for(int i=1; i<=9; i++) {
				q.add(i);
			}
			while(cnt<N) {
				int x = q.poll();
				for(int i=0; i<(x%10); i++) {
					cnt++;
					if(cnt == N) {
						System.out.println(x*10+i);
						System.exit(0);;
					}
					q.add(x*10+i);
				}
			}
		}
	}
}
