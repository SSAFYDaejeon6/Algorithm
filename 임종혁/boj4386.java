package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj4386 {

    private static Node[] nodes;
    private static int[] arr;
    static  double result;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int n = Integer.parseInt(br.readLine());
        nodes = new Node[n];
        arr = new int[n];

        // 입력
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            double x= Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            nodes[i] = new Node(x,y);
        }

        for(int i=0; i<n; i++){
            arr[i] = i;
        }

        List<Node2> list = new ArrayList<>();
        for(int i=0; i<n-1; i++){
            Node n1 = nodes[i]; // 현재 노드 부터 다음 노드들 거리
            for(int j= i+1; j<n; j++){
                Node n2 = nodes[j]; // 다음 노드

                double weight = Math.sqrt(Math.pow(n1.x - n2.x, 2) + Math.pow(n1.y - n2.y, 2)); // 가중치
                list.add(new Node2(i,j,weight));
            }
        }

        Collections.sort(list); // 무게 별로 정렬


        for(int i=0; i<list.size(); i++){
            Node2 node = list.get(i);

            int start = node.x;
            int end = node.y;
            double weight = node.weight;

            if(find(start) != find(end)){ // 사이클이 돌지 않으면 연결
                union(start, end);
                result += weight;

            }

        }
        String s = String.valueOf(result);
        s = s.substring(0,4);
        System.out.println(s);





    }
    private static  void union(int x, int y){

        x = find(x);
        y = find(y);
        arr[x] = y;
    }
    private static int find(int num) {
        if(num == arr[num])
            return num;

        return arr[num] = find(arr[num]);
    }



    public static class Node{

        double x;
        double y;

        Node(double x, double y) {
            this.x = x;
            this.y = y;
        }

    }

    public static class Node2 implements Comparable<Node2>{

        int x;
        int y;
        double weight;

        Node2(int x, int y, double weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node2 o) {
            return o.weight >= this.weight ? -1 : 1;
        }
    }

}
