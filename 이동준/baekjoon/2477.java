import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
	
	static BufferedReader br;		
	public static void main(String[] args) throws Exception{
    br = new BufferedReader(new InputStreamReader(System.in));
		int density = Integer.parseInt(br.readLine());
		int[] directionArr = new int[6];
		int[] lengthArr = new int[6];
		for(int i = 0; i < 6; i++) {
			String[] tempString = br.readLine().split(" ");
			directionArr[i] = Integer.parseInt(tempString[0]);
			lengthArr[i] = Integer.parseInt(tempString[1]);
		}
		int maxinfo[] = {0, 0};
		for(int i = 0; i < 6; i++) {// find max(longest
			if(lengthArr[i] > maxinfo[0])
				maxinfo = new int[]{lengthArr[i], i};
		}
		int[] normalSides = {index_circle(lengthArr, maxinfo[1] + 1), index_circle(lengthArr, maxinfo[1] - 1)};
		Arrays.sort(normalSides);
		
		int large_area = maxinfo[0] * normalSides[1];
		int shortNormalInfo[] = {0, 0};
		for(int i = 0; i < 6; i++) {// find max(longest
			if(lengthArr[i]  == normalSides[0]) {
				shortNormalInfo = new int[]{lengthArr[i], i};
				break;
			}
		}
		int[] shortSides = new int[] {index_circle(lengthArr, shortNormalInfo[1] + 1), index_circle(lengthArr, shortNormalInfo[1] - 1)};
		Arrays.sort(shortSides);
		int small_area = shortSides[0] * (normalSides[1] - normalSides[0]);
		System.out.println((large_area - small_area) * density);
	}
	
	public static int index_circle(int[] arr, int index) {
		return arr[getResidue(index, arr.length)];
	}
	
	public static int getResidue(int a, int b) {
		while(a < b) {
			a += b;
			
		}
		return a%b;
	}
	
}
