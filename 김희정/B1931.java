import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
[BOJ] 1931 회의실 배정
43416KB | 	476ms
 */
public class B1931 {
    static class Meeting implements Comparable<Meeting>{
        int start;
        int end;

        public Meeting(int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if(this.end == o.end) return this.start - o.start;
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        Meeting[] meetings = new Meeting[n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        //로직
        Arrays.sort(meetings);	//종료시각이 빠른 순으로 정렬

        int answer = 1;
        Meeting curr = meetings[0];
        for(int i = 1; i < meetings.length; i++) {
            if(curr.end <= meetings[i].start) {	//이전회의의 종료 시각보다 다음 회의 시작 시각이 크거나 같으면, 회의 가능
                answer++;
                curr = meetings[i];
            }

        }

        System.out.println(answer);
    }
}