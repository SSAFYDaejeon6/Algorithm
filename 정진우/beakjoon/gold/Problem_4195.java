import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Problem_4195 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static Map<String, Integer> pmap;
	static int[] pts;
	static int[] network;
	
	public static void main(String[] args) throws IOException {
		String str = br.readLine();
		int T = Integer.parseInt(str);
		
		for(int test_case = 0; test_case <T; test_case++) {
			
			str = br.readLine();
			int F = Integer.parseInt(str);
			pmap = new HashMap<String, Integer>(F); // Initialization
			pts = new int[250000]; // 최대값은 F*2
			network = new int[250000];
			
			int count = 1;
			int friend_max = 1;
			
			for(int i=0; i<pts.length; i++) {
				pts[i] = i; 
			}
			
			for(int i=0; i<network.length; i++) {
				network[i] = 1; // network는 부모가 가지고 있는 집합의 크기, union으로 집합의 크기를 합치거나 더하거나 한다.
			}
			
			for(int i=0; i<F; i++) {
				str = br.readLine();
				
				StringTokenizer st = new StringTokenizer(str);
								
				String f1 = st.nextToken();
				String f2 = st.nextToken();
				
				if(! pmap.containsKey(f1)) { // 키가 없다면
					pmap.put(f1, count++); // 키 추가
				} 
				
				if(! pmap.containsKey(f2)) { // 키가 없다면
					pmap.put(f2, count++); // 키 추가
				} 
				
				int i1 = pmap.get(f1);
				int i2 = pmap.get(f2);
				
				network[find(i1)] += union(i1, i2);
				System.out.println(network[find(i1)]);
			
			}
			
			
		}
		

	}
	
	public static int union(int a, int b) {
		int A = find(a);
		int B = find(b);
		
		pts[B] = A; // Union
		
		if(A == B) {
			return 0; // 이미 같다
		}else {
			return network[B];
		}
	
		
	}
	
	public static int find(int a) {
		if(pts[a] == a) return a;
	
		return pts[a] = find(pts[a]);
	}

}
