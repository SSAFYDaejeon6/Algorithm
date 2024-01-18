import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileReader;//for debug

public class Main {
	static String[] XYZ = new String[3];
//	static HashMap<String, Boolean> checkedMap = new HashMap<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("src/Main.txt"));
		
		for(int i = 0; i < 3; i++) {
			XYZ[i] = br.readLine();
		}
			
		int k = Integer.parseInt(br.readLine());
		
		//Alg start
		List<String>[] occurList = new List[k+1];//0:a~z //ignore warnings
		occurList[0] = new ArrayList<String>();
		occurList[1] = new ArrayList<String>();
		
		for(int ascii = 97; ascii <= 122; ascii++) {//make 1
			String aLetter = new String(new char[] {(char) ascii});
			occurList[0].add(aLetter);
			if(exXYZ(aLetter) >= 2)
				occurList[1].add(aLetter);
		}
		
		
		if(occurList[1].size() != 0) {
			int maxOccur = 1;
			while(occurList[k] == null) {
				List<String> appearList = null;
				if(maxOccur*2 <= k) {
					appearList = combineCheck(occurList[maxOccur], occurList[maxOccur]);
				}else{
					appearList = combineCheck(occurList[maxOccur], occurList[findMaxLengthUseable(occurList, k-maxOccur)]);
				}
				if(appearList == null)
					break;//cannot reach k
				maxOccur = appearList.get(0).length();
				occurList[maxOccur] = appearList;
			}
		}
		
		if(occurList[k] != null && occurList[k].size() > 0) {
			String[] finalStrings = (String[]) occurList[k].toArray(new String[occurList[k].size()]);
			Arrays.sort(finalStrings);//ascending order
			for(String theResult : finalStrings)
				System.out.println(theResult);
		}else {
			System.out.println("-1");
		}
	}
	
	static int findMaxLengthUseable(List<String>[] occurList, int target) {
		for(int i = target; target >= 1; i--) {
			if(occurList[i] != null)
				return i;
		}
		return 1;
	}
	
	static boolean exists(String series, String subseries) {//check if a subseries is in series
		int indexCounter = 0;
		int targetLength = subseries.length();
		for(int cursor = 0; cursor < series.length(); cursor++) {
			if(series.charAt(cursor) == subseries.charAt(indexCounter))
				indexCounter++;
			if(indexCounter == targetLength)
				return true;
		}
		return false;
	}
	
	static int exXYZ(String subseries) {
		int count = 0;
		for(String eachSereis : XYZ) {
			if(exists(eachSereis, subseries))
				count++;
		}
		return count;
	}
	
	static List<String> combineCheck(List<String> a, List<String> b){//must check substring overlap
		List<String> result = new ArrayList<String>();
		for(String substring_a : a) {
			for(String substring_b : b) {
				//no overlap
				String candidate = substring_a + substring_b;
				if(exXYZ(candidate) >= 2)
					result.add(candidate);
			}
		}
		
		if(result.size() > 0)
			return result;
		return null;
	}
	
	static boolean checkOverlap(String string1, String string2) {
		String[] combined = (string1 + string2).split("");
		Arrays.sort(combined);
		for(int cursor = 1; cursor < combined.length; cursor++) {
			if(combined[cursor - 1] == combined[cursor] )
				return true;//element overlapping detected
		}
		return false;
	}
}
