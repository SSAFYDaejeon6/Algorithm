/**BOJ_11559_PuyoPuyo 	11496KB 80ms
 * https://www.acmicpc.net/problem/11559
 * Idea
 * 	맵도 돌려서 보고...
 * Process
 * 	알고리즘 구성
 * 		Group
 * 			색
 * 			Group parent
 * 	동작
 * 		지워지는 Block이 생긴 줄은 가장 끝~마지막으로 지워진 block 자리 에 해당하는 visited 를 null 처리한다.(Group마다 size 1 줄이고 null처리)
 * 		그후, 땡겨진 Block들에 대해, 밑에서부터
 * 			각 Block은 자기 왼쪽 밑 아래와만 비교한다
 * 				같은 색깔이면
 * 					둘중 한쪽만 같은 색깔
 * 						visited의 자신 자리에 해당 Group 쓴다
 * 					둘다 같다
 * 						두 Group을 merge 하는 새 Group을 만들고, 해당 Group을 쓴다.
 * 				다른 색깔이면
 * 					자기 자리에 새 Group 만들어서 쓴다.
 * 	지금 안 되는 이유
 * 		이전 Iteration의 Set이 오염시키는 것 같다.
 * 			-> 최적화를 위해 남겨진 부분이지만, 지금 당장은 시간이 급하므로 일단 그냥 매번 초기화하면서 풀어본다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_11559_PuyoPuyo {
	public static void main(String[] args) throws IOException {
		final char EMPTY = '.';//, YELLOW = 'Y', GREEN = 'G', RED = 'R';
		final int WIDTH = 6;
		final int HEIGHT = 12;
		final int THRESHOLD = 3;
	 	List<Block>[] map = new List[WIDTH];
		{
			for(int i = 0; i < map.length; i++) {
				map[i] = new ArrayList<>();
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String tempStr;
			char tempChar;
			for(int r = 0; r < HEIGHT; r++) {
				tempStr = br.readLine();
				for(int c = 0; c < WIDTH; c++) {
					if((tempChar=tempStr.charAt(c)) == EMPTY) continue;
					map[c].add(0,new Block(tempChar));
				}
			}
			//입력 끝
		}
		int iteration = -1;
		int destroyed;
		//Grouping
		do{
			destroyed = 0;
			iteration++;
			for(int c = 0; c < WIDTH; c++) {
				List<Block> cL = map[c];
				int cLSize = cL.size();
				for(int rb = 0; rb < cLSize; rb++){//r from bottom
					Block cur = cL.get(rb);
					cur.parent = new Group(cur.color);
					Block left = null;
					Block below = null;
					boolean leftMatch = false;
					boolean belowMatch = false;
					int nc = c - 1;
					if(nc >= 0 && map[nc].size() > rb && (left = map[nc].get(rb)).color == cur.color) {
						leftMatch = true;
					}
					int nrb = rb - 1;
					if(nrb >= 0 && (below = cL.get(nrb)).color == cur.color) {
						belowMatch = true;
					}
					if(leftMatch != belowMatch) {
						if(leftMatch) {
							cur.parent = left.parent.merge(cur.parent);
						}else {
							cur.parent = below.parent.merge(cur.parent);
						}
					}else {
						if(leftMatch) {//둘다 같음
							left.parent.merge(cur.parent);
							cur.parent = left.parent.merge(below.parent);
						}else {
							//다 다름. (아무것도 안함)
//							if((cur.parent = cur.parent.findSet()).size == 1) continue;//나만 있음
//							//나 말고 다른 block도 있음
//							cur.parent.size--;
//							cur.parent = new Group(cur.color);
						}
					}
				}
			}
			//Destroy
			for(int c = 0; c < WIDTH; c++) {
				for(int r = map[c].size() - 1; r >= 0; r--) {
					if(map[c].get(r).parent.findSet().size <= THRESHOLD) continue;
					map[c].remove(r);
					destroyed++;
				}
			}
		} while(destroyed > 0);
		System.out.println(iteration);
	}
	
	private static class Block{
		final char color;
		Group parent;
		Block(char color) {
			this.color = color;
			this.parent=new Group(this.color);
		}
	}
	
	private static class Group{
		private static int ID;
		final int id;
		final char color;
		int size, rank;
		Group parent;
		Group(char color) {
			this.id = ID++;
			this.color = color;
			this.parent=this;
			this.size = 1;
		}
		Group merge(Group B) {
			if(this.color != B.color) return null;
			Group AP = this.findSet();
			Group BP = B.findSet();
			if(AP.equals(BP)) return AP;
			if(AP.rank >= BP.rank) {
				BP.parent = AP;
				AP.size += BP.size;
				AP.rank++;
				return AP;
			}else {
				AP.parent = BP;
				BP.size += AP.size;
				BP.rank++;
				return BP;
			}
		}
		
		Group findSet() {
			if(this.parent.equals(this)) return this;
			return this.parent = this.parent.findSet();
		}
		@Override
		public boolean equals(Object obj) {
			return this.id == ((Group) obj).id;
		}
		@Override
		public int hashCode() {
			return this.id;
		}
	}
}
