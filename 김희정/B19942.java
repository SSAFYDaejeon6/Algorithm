import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// [BOJ] 19942 다이어트
// 19780KB |	144ms트
// 부분집합으로 해결
// 사전순 정렬은 현재 비교하려는 배열과 정답 배열을 비교
public class B19942 {
    static class Ingredients {
        int protein;
        int fat;
        int carbohydrate;
        int vitamin;
        int price;

        public Ingredients(int protein, int fat, int carbohydrate, int vitamin, int price) {
            super();
            this.protein = protein;
            this.fat = fat;
            this.carbohydrate = carbohydrate;
            this.vitamin = vitamin;
            this.price = price;
        }

        public boolean isMinimum() {
            if(minimun.protein <= this.protein && minimun.fat <= this.fat && minimun.carbohydrate <= this.carbohydrate
                    && minimun.vitamin <= this.vitamin) {
                return true;
            }
            return false;
        }

        public void sum(Ingredients ingredients) {
            this.protein += ingredients.protein;
            this.carbohydrate += ingredients.carbohydrate;
            this.fat += ingredients.fat;
            this.vitamin += ingredients.vitamin;
            this.price += ingredients.price;
        }
    }

    static int N;
    static Ingredients minimun;
    static Ingredients[] ingredients;
    static int min;
    static List<Integer> answer;
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        minimun = new Ingredients(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
        ingredients = new Ingredients[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            ingredients[i] = new Ingredients(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        //로직
        min = Integer.MAX_VALUE;
        answer = new ArrayList<>();

        isSelected = new boolean[N+1];
        subSet(1);

        //결과 출력
        // 조건을 만족하는 답이 없다면 -1 출력 후 종료
        if(min == Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        System.out.println(min);
        for(int a : answer) {
            System.out.print(a + " ");
        }
    }


    static void subSet(int cnt) {
        if (cnt == N+1) {
            List<Integer> temp = new ArrayList<>();
            Ingredients sumIng = new Ingredients(0, 0, 0, 0, 0);
            for (int i = 1; i <= N; i++) {
                if (isSelected[i]) {
                    sumIng.sum(ingredients[i]);
                    temp.add(i);
                }
            }
            if(!sumIng.isMinimum()) return;
            if(min == sumIng.price){
                //사전순으로 가장 빠른 것
                int size = Math.min(answer.size(), temp.size());
                for(int i = 0; i < size; i++){
                    if(temp.get(i) < answer.get(i)){
                        answer = temp;
                        return;
                    }
                    if(temp.get(i) > answer.get(i)){
                        return;
                    }
                }

                if(temp.size() < answer.size()){
                    answer = temp;
                    return;
                }

            }
            if(min > sumIng.price) {
                min = sumIng.price;
                answer = temp;
            }
            return;
        }

        isSelected[cnt] = true;
        subSet(cnt + 1);
        isSelected[cnt] = false;
        subSet(cnt + 1);

    }
}