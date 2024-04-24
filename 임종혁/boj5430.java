package main;

import java.io.*;
import java.util.*;
class boj5430{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for(int t=0; t<tc; t++){

            String s = br.readLine();
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(),"[],");
            boolean check = true;
            boolean isFront = true;

            Deque<Integer> queue = new LinkedList();

            //  String 배열에서 꺼내 arr 배열에 삽입
            for(int i = 0; i < n; i++) {
                queue.add(Integer.parseInt(st.nextToken()));
            }
            for(int i=0; i<s.length(); i++){
                if(s.charAt(i) == 'R'){
                    if (isFront) isFront = false;
                    else isFront = true;
                }
                if(s.charAt(i) == 'D'){
                    if(queue.isEmpty()){
                        check = false;
                        break;
                    }
                    if (isFront) {
                        queue.poll();
                    } else {
                        queue.pollLast();
                    }
                }
            }

            if (!isFront) {
                Deque<Integer> tmpQueue = new LinkedList<>();
                int size = queue.size();

                for (int i=0; i<size; i++) {
                    tmpQueue.addLast(queue.pollLast());
                }
                for (int i=0; i<size; i++) {
                    queue.addLast(tmpQueue.poll());
                }
            }


            if(check){
                StringBuilder sb = new StringBuilder();
                sb.append('[');	// 여는 대괄호 먼저 StringBuilder에 저장

                if(queue.size() > 0) {	//만약 출력 할 원소가 한 개 이상일 경우
                    sb.append(queue.pollFirst());
                    while(!queue.isEmpty()) {
                        sb.append(',').append(queue.pollFirst());
                    }
                }

                sb.append(']');
                System.out.println(sb);

            }else{
                System.out.println("error");
            }

        }
    }
}