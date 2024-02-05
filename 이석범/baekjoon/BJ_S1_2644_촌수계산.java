import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//11576kb, 76ms
public class BJ_S1_2644_촌수계산 {

    //한사람당 부모가 최대 한개라고 하였으므로 부모를 가지는 클래스 선언
    static class Human {
        int num;
        int depth=0;
        Human parent = null;
        public Human(int num) {
            this.num = num;
        }
    }


    public static void main(String[] args) throws IOException{
        //입력값
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int compareA = Integer.parseInt(st.nextToken());
        int compareB = Integer.parseInt(st.nextToken());
        Human[] humanList = new Human[n+1];

        //각 사람마다 번호를 초기화
        for(int i=1; i<n+1;i++) {
            humanList[i] = new Human(i);
        }

        int inputCnt = Integer.parseInt(br.readLine());
        for(int i=0; i< inputCnt;i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            //부모를 정의하고 depth를 부모에 +1 추가해서 저장
            humanList[child].parent = humanList[parent];
            humanList[child].depth = humanList[parent].depth+1;
        }
        int cnt = 0;
        Human humanA = humanList[compareA];
        Human humanB = humanList[compareB];

        while(!(humanA.num == humanB.num)) {
            //만약 각 부모가 null인데 아직 반복문에 존재하면 둘이 촌수를 따질 수 없음 따라서 -1출력
            if(humanA.parent == null && humanB.parent == null) {
                System.out.println(-1);
                return;
            }
            //a의 부모가 존재하지 않으면 b의 부모를 찾고 촌수를 나타내는 cnt+1추가
            if(humanA.parent == null) {
                humanB = humanB.parent;
                cnt++;
                continue;
            }
            //b의 부모가 존재하지 않으면 a의 부모를 찾고 촌수를 나타내는 cnt+1추가
            if(humanB.parent == null) {
                humanA = humanA.parent;
                cnt++;
                continue;
            }
            //둘다 부모가 존재하면 더 밑에 있는 사람의 부모를 찾고 cnt+1
            if(humanA.depth > humanB.depth) {
                humanA = humanA.parent;
            } else {
                humanB = humanB.parent;
            }
            cnt++;
        }
        //if(cnt==0) System.out.println(-1);
        System.out.println(cnt);
    }
}