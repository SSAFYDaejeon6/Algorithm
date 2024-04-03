package project;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//164204kb 736ms
public class BJ_G4_20040_사이클 {

	static int[] parent;

	static int find(int x) {
		if(parent[x]!=x) parent[x] = find(parent[x]);

		return parent[x];
	}

	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		//같을 경우 사이클임
		if(a==b) return true;
		else {
			if(a < b) parent[a] = b;
			else parent[b] = a;

			return false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		parent = new int[n];

		for(int i=0; i<n;i++) {
			parent[i] = i;
		}

		int result = 0;

		for(int i=0; i<m;i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());			
			//사이클이 아닐때만 해보기
			if(result==0) {
				if(union(a, b)) {
					result = i+1;
					//입력끝까지 안받아도 되는듯
					break;
				}
			}
		}
		System.out.println(result);
	}
}