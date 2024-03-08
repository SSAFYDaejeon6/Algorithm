package BaekJoon;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;



public class BOJ_N25328_문자열집합조합하기 {
	static HashMap<String, Integer> strSet = new HashMap<String, Integer>();
	static int cnt = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String X = sc.next();
		String Y = sc.next();
		String Z = sc.next();
		
		int k = sc.nextInt();
		int idx = 0;
		
		boolean[] isVisitedX = new boolean[X.length()];
		subset(k, 0, X, isVisitedX);
		
		boolean[] isVisitedY = new boolean[Y.length()];
		subset(k, 0, Y, isVisitedY);
		
		boolean[] isVisitedZ = new boolean[Z.length()];
		subset(k, 0, Z, isVisitedZ);
		
		if(cnt == 0)
			System.out.println(-1);
		else {
			String[] strArray = new String[cnt];
			for(String s : strSet.keySet()) {
				if(strSet.get(s) > 1)
					strArray[idx++] = s;
			}
			Arrays.sort(strArray);
			for(String s : strArray)
				System.out.println(s);			
		}

	}
	
	public static void subset(int k, int depth, String str, boolean[] isVisited) {
		if(k == 0) {
			String s = "";
			for(int i=0;i<str.length();i++) {
				 s += isVisited[i]? str.substring(i, i+1) : "";
			}
			if(strSet.containsKey(s)) {
				int preCnt = strSet.get(s) + 1;
				cnt += (preCnt == 2)? 1:0;
				strSet.replace(s, preCnt);
			}
			else
				strSet.put(s, 1);
			return;
		}
		if(depth == str.length())
			return;
		
		isVisited[depth] = true;
		subset(k-1, depth+1, str, isVisited);
		
		isVisited[depth] = false;
		subset(k, depth+1, str, isVisited);
		
	}

}
