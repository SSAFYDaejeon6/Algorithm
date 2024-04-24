package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
/* 88976kb 624ms
 * [문제 해석]
	AC : 정수 배열에 연산을 하기 위해 만든 언어
	R(뒤집기) D(버리기)
	D : 첫번째 수를 버리는 함수
	R : 뒤집기
	
	[입력]
	1. T <= 100
	2. 수행할 함수 p <=100000
	3. 배열에 들어있는 수의 개수 N <= 100000
	4. 1<=x<=100
	
	[출력]
	주어진 정수 배열에 함수를 수행한 결과
	에러가 발생한 경우 error
	
	[문제 해결 프로세스]
	1. List로 접근 -> 88560kb 2232ms
	2. deque로 접근 -> 88976kb 624ms
	List에서 첫 번째 요소 삭제 시 O(N)의 시간복잡도
		-> 1번부터 N-1번까지 한 칸씩 앞으로 당겨와야 하기 때문에
 */
public class Main_G5_5430_AC {
	static int N;
	static Deque<Integer> deque = new ArrayDeque<>();
	static char[] ac;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		A: for(int t=0; t<T; t++) {
			ac = br.readLine().toCharArray();
			N = Integer.parseInt(br.readLine());
			deque.clear();
			st = new StringTokenizer(br.readLine(), "[],");
			for(int i=0; i<N; i++) deque.offer(Integer.parseInt(st.nextToken()));
			AC();
			
		}
		
		System.out.println(sb);
		
	}

	private static void AC() {
		int idx = 1;
		for(int i=0; i<ac.length; i++) {
			if(ac[i]=='R') {
				idx *= (-1);
			}else {
				if(!deque.isEmpty() && idx == 1) {
					deque.removeFirst();
				}else if(!deque.isEmpty() && idx == -1) {
					deque.removeLast();
				}else {
					sb.append("error").append('\n');
					return;
				}
			}
		}
		sb.append('[');
		if(idx==1) {
			while(!deque.isEmpty()) {
				sb.append(deque.pollFirst());
				if(deque.size()>0) sb.append(',');
			}
		}else {
			while(!deque.isEmpty()) {
				sb.append(deque.pollLast());
				if(deque.size()>0) sb.append(',');
			}
		}
		sb.append(']').append('\n');
	}

}

//public class Main {
//	static int N;
//	static List<Integer> list = new ArrayList<>();
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		StringBuilder sb = new StringBuilder();
//		int T = Integer.parseInt(br.readLine());
//		
//		A: for(int t=0; t<T; t++) {
//			char[] ac = br.readLine().toCharArray();
//			N = Integer.parseInt(br.readLine());
//			list.clear();
//			st = new StringTokenizer(br.readLine(), "[],");
//			for(int i=0; i<N; i++) list.add(Integer.parseInt(st.nextToken()));
//			
//			int idx = 1;
//			for(int i=0; i<ac.length; i++) {
//				if(ac[i]=='R') {
//					idx *= (-1);
//				}else {
//					if(!list.isEmpty() && idx == 1) {
//						list.remove(0);
//					}else if(!list.isEmpty() && idx == -1) {
//						list.remove(list.size()-1);
//					}else {
//						sb.append("error").append('\n');
//						continue A;
//					}
//				}
//			}
//			sb.append('[');
//			if(idx==1) {
//				for(int i=0; i<list.size(); i++) {
//					sb.append(list.get(i));
//					if(i!=list.size()-1) sb.append(',');
//				}
//			}else {
//				for(int i=list.size()-1; i>=0; i--) {
//					sb.append(list.get(i));
//					if(i!=0) sb.append(',');
//				}
//			}
//			sb.append(']').append('\n');
//			
//		}
//		
//		System.out.println(sb);
//		
//	}
//
//}
