package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author SEOK BEOM LEE
 *22416kb	740ms	
 */
public class BJ_G3_1865_��Ȧ {

    static final int INF = 1_000_000_000;

    static int V, R, W;

    static class Node {
        int start;
        int end;
        int time;

        public Node(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }


    }
    static Node[] nodeList;
    static int[] distance;

    //��������
    static boolean bell(int idx) {
        distance = new int[V+1];
        Arrays.fill(distance, INF);
        distance[idx] = 0;

        //���� ������ 91%���� ����
        boolean flag = false;

        for(int t=0;t<V;t++) {
        	flag = false;
            for(int i=0;i<nodeList.length;i++) {
                Node node = nodeList[i];
                int start = node.start;
                int end = node.end;
                int time = node.time;

                //V��ŭ �ݺ��ϸ鼭 ��ü ������ ���鼭 ������ ���纸�� �������� ���� 
                if(distance[start]!=INF && distance[end] > distance[start]+time) {
                    distance[end] = distance[start]+time;
                    flag = true;
                    
                    //���� V�� ��ŭ�ߴµ� �� �������� ���� �����ϴ� ��� ���� ����Ŭ�� �����Ѵٴ� �ǹ�
                    if(t==V-1) {
                        return true;
                    }
                }
                
            }
            if (!flag) {
            	break;
            }
        }

        return false;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        
        A: for(int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            nodeList = new Node[R+R+W];
            int cnt = 0;

            //�Ϲ� ���δ� �����
            for(int i=0;i<R;i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                nodeList[cnt++] = new Node(s, e, time);
                nodeList[cnt++] = new Node(e, s, time);
            }

            //��Ȧ�� �ܹ���
            for(int i=0;i<W;i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                nodeList[cnt++] = new Node(s, e, -time);
            }
            //�������� �����Ƿ� ��� v���� �������� Ȯ��
            //0������ �� ��� 2%���� Ʋ��
            for(int i=1; i<=V;i++) {
                boolean isLoop = bell(i);
                if(isLoop) {
                    sb.append("YES").append("\n");
                    continue A;
                }
            }
            sb.append("NO").append("\n");
            //            System.out.println(Arrays.toString(distance));



//            sb.append(distance[1]<0?"YES":"NO").append("\n");
        }
        System.out.println(sb);


    }
}
