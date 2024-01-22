import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;//for debug
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
	
	static ArrayList<Integer> earningList = new ArrayList<>(1000);
	static int[][] calander;
	static int calanderLength;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		calander = new int[N][2];
		calanderLength = N;
		for(int i = 0; i < N; i++) {
			String[] k = br.readLine().split(" ");
			for(int i2 = 0; i2 < 2; i2++){ 
				calander[i][i2] = Integer.parseInt(k[i2]);
			}
			if(i + calander[i][0] > N)
				calander[i][1] = 0;
		}
		//---------입력 끝
		dfs(0, 0);
		earningList.sort(Comparator.reverseOrder());
		System.out.println(earningList.get(0));
		br.close();
	}
	
	public static void dfs(int cursor, int gathered) {
		if(cursor >= calanderLength) {
			earningList.add(gathered);
			return;
		}
		for(int i = cursor; i < calanderLength; i++) {
			int accul = gathered + calander[i][1];
			dfs(i + calander[i][0], accul);
		}
	}
}
