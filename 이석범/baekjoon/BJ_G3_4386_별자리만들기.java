package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//12636kb 100ms
public class BJ_G3_4386_별자리만들기 {
    
    static int N;
    static double[][] list;
    static double[][] graph;
    
    static class Node implements Comparable<Node> {
        int from;
        int to;
        double cost;
       
        public Node(int from, int to, double cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
        
		@Override
		public String toString() {
			return "Node [from=" + from + ", to=" + to + ", cost=" + cost + "]";
		}



		@Override
        public int compareTo(Node o) {
            return Double.compare(this.cost, o.cost);
        }
        
    }
    
    static int[] parent;
    
    static int find(int x) {
    	if(parent[x] != x) x = find(parent[x]);
    	
    	return parent[x];
    }
    
    static boolean union(int a, int b) {
    	a = find(a);
    	b = find(b);
    	
    	if(a < b) parent[b] = a;
    	
    	else if(a>b) parent[a] = b;
    	else {
    		return false;
    	}
    	return true;
    }
    
    static double result = 0;
    
    //최소신장 트리로 최소값찾으면 됨
    static void mst() {
    	//모든 경로 넣고
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	for(int r=0; r<N;r++) {
    		for(int c=r+1; c<N;c++) {
    			pq.offer(new Node(r, c, graph[r][c]));
    		}
    	}
    	//모든 정점이 이어질때까지 반복
    	//카운트 세서 하면 더 효율적일 수도
    	while(!pq.isEmpty()) {
    		Node node = pq.poll();
    		if(union(node.from, node.to)) {
    			result += node.cost;
    		}
    		
    	}
    	
    }
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        list = new double[N][2];
        graph = new double[N][N];
        parent = new int[N];
        
        for(int i=0; i<N;i++) {
        	parent[i] = i;
        }
        
        for(int i=0; i<N;i++) {
            st = new StringTokenizer(br.readLine());
            list[i][0] = Double.parseDouble(st.nextToken());
            list[i][1] = Double.parseDouble(st.nextToken());
        }
        
        for(int r=0; r<N;r++) {
            for(int c=0; c<N;c++) {
                if(r==c) continue;
                double a = Math.abs(list[r][0]-list[c][0]);
                double b = Math.abs(list[r][1]-list[c][1]);
                double tmp = Math.sqrt((a*a)+(b*b));
                
                
                graph[r][c] = Math.round(tmp*100) / 100.0;
            }
        }
//        print();
        
        mst();
        
        System.out.println(result);
    }
    
    static void print() {
        for(double[] r:graph) {
            for(double c:r) {
                System.out.print(c+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
