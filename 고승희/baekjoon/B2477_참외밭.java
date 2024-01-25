import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B2477_참외밭 {
	static BufferedReader br;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// InputStreamReader와 System.in???
		int density = Integer.parseInt(br.readLine());
		int[] directionArr = new int[6];
		int[] lengthArr = new int[6];
		for(int i = 0; i < 6; i++) {
			String[] tempString = br.readLine().split(" ");
			directionArr[i] = Integer.parseInt(tempString[0]);
			lengthArr[i] = Integer.parseInt(tempString[1]);
		}
		
		int maxW = 0;
		int maxH = 0;
		int widthI = 0;
		int heightI = 0;
		int smallH, smallW;
		int ans;
		int left, right;
		
		
		for (int i=0; i<6; i++) {
			if ( directionArr[i] == 1 || directionArr[i] ==2 ) {
				if (maxH < lengthArr[i]) {
					maxH = lengthArr[i];
					heightI = i;
				}	
			} else {
				if (maxW < lengthArr[i]) {
				maxW = lengthArr[i];
				widthI = i;
				}
			}
		}
		
		if (widthI-1 ==-1) left = 5;
		else left = widthI-1;
		
		if (widthI+1==6) right = 0;
		else right = widthI+1;
		
		smallW = Math.abs(lengthArr[left] -lengthArr[right]);
		
		if (heightI-1 == -1) left = 5;
		else left = heightI-1;
		
		if (heightI+1==6) right = 0;
		else right = heightI+1;
		
		smallH = Math.abs(lengthArr[left] - lengthArr[right]);
		
		ans = (maxH * maxW - smallW * smallH) * density ;
		
		System.out.println(ans);
		
	}

}
