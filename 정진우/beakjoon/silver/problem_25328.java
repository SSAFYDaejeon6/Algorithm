package com.ssafy.baekjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

class problem_25328 {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		
		StringTokenizer st;
		char charArray[][] = new char[3][];			
		
		ArrayList<String>[] arrayList = new ArrayList[3];
		ArrayList<String> resultList = new ArrayList<String>();
		
 		for(int i=0; i<3; i++) {
			st = new StringTokenizer(bf.readLine());
			charArray[i] = st.nextToken().toCharArray();
			
			arrayList[i] = new ArrayList<String>(30000);
		}
		
		st = new StringTokenizer(bf.readLine());
		int k = Integer.parseInt(st.nextToken());
		
				
		for(int l=0; l<3; l++) {
			boolean[] visited = new boolean[charArray[l].length];
			
			combination(arrayList[l], charArray[l], visited, 0, charArray[l].length, k);			

		}
		
		TreeMap<String,Integer> map = new TreeMap<>();//map1의 모든 값을 가진 HashMap생성
		
		for(int i=0; i<3; i++) {
			for(String s: arrayList[i]) {
				if(map.containsKey(s))
					map.put(s, map.get(s) + 1);
				else
					map.put(s, 1);
			}
		}
		
		boolean flag = false;
		for(String key: map.keySet()) {
			if (map.get(key) >= 2) {
				flag = true;
				System.out.println(key);
			}			
		}
		if(!flag) System.out.println(-1);
		
	}
	
	public static void combination(List<String> listArray, char[] arr, boolean[] visited, int start, int n, int r) {
		if(r == 0) {
//			for(String s: listArray) {
//				System.out.println(s);
//			}
			String a = sub_array(arr, visited, n);
			listArray.add(a);
//			System.out.println("a String : " + a);
		}
		
		for(int i=start; i<n; i++) {
			visited[i] = true;
//			System.out.println("i = " + i);
			combination(listArray, arr, visited, i+1, n, r-1);			
			visited[i] = false;
		}
	}
	
   public static String sub_array(char[] arr, boolean[] visited, int n) {
	   String result = "";
       for (int i = 0; i < n; i++) {
           if (visited[i]) {
               result += arr[i];
           }
       }
//       System.out.println(result);
       return result;
    }
}
