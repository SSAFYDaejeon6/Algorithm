import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N10157 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int C=0, R=0, k=0;
		int lens;
		int idx = 0;
		int[][] deltas = {
				{0, 1}, {1, 0}, {0, -1}, {-1, 0}	
		};
		
		try {
			String[] R_C = reader.readLine().split(" ");
			C = Integer.parseInt(R_C[0]);
			R = Integer.parseInt(R_C[1]);
			k = Integer.parseInt(reader.readLine());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(k > C*R) {
			System.out.println(0);
			return;
		}
		
		lens = R;
		int i=1, j=0;
		while(lens > 0) {
			for(int x=0;x<lens;x++) {
				i += deltas[idx%4][0];
				j += deltas[idx%4][1];
				if(--k == 0) {
					System.out.println(i+ " " + j);
					return;
				}
			}
			idx++;
			lens = (idx%2==1? --C:--R);
		}
	}

}
