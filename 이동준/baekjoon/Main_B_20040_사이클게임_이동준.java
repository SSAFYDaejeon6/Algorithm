/**Main_B_20040_사이클게임_이동준 143820KB 480ms
 * Facts
 * 	사이클을 감지해야 한다.
 * IDEA
 * 	Union-find를 사용한다. 주어진 선분을 연결하려 하는데 선분의 두 점의 집합이 일치하면 return
	rank 최적화 믿 path compression 최적화
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_20040_사이클게임_이동준 {
	static BufferedReader br;
	static int N, M;
	static DisjointSet[] sets;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//점 수 
		M = Integer.parseInt(st.nextToken());//선분 수
		sets = new DisjointSet[N];
		int aIdx, bIdx;
		int turn = 1;
		for(; turn <= M; turn++) {
			st= new StringTokenizer(br.readLine());
			aIdx = Integer.parseInt(st.nextToken());
			bIdx = Integer.parseInt(st.nextToken());
			if(sets[aIdx] == null) sets[aIdx] = new DisjointSet();
			if(sets[bIdx] == null) sets[bIdx] = new DisjointSet();
			if(sets[aIdx].merge(sets[bIdx]) == null) break;
		}
		if(turn > M) System.out.println(0);
		else System.out.println(turn);
	}
	
	private static class DisjointSet implements Comparable<DisjointSet>{
		private static int ID;
		int id, rank;
		DisjointSet parent;
		
		DisjointSet() {
			super();
			this.id = ID++;
			this.rank = 0;
			this.parent = this;//MakeSet
		}
		
		DisjointSet findSet() {
			if(parent == this) return this;
			return this.parent = parent.findSet();
		}
		
		DisjointSet merge(DisjointSet b) {
			DisjointSet aP = findSet();
			DisjointSet bP = b.findSet();
			if(aP.equals(bP)) return null;//unmergeable(cycle detected)
			if(aP.rank == bP.rank) {
				bP.parent = aP;
				aP.rank++;
				return aP;
			}
			else if(aP.rank < bP.rank){
				aP.parent = bP;
				return bP;
			}else {
				bP.parent = aP;
				return aP;
			}
		}
		
		@Override
		public int compareTo(DisjointSet o) {
			return Integer.compare(rank, o.rank);
		}
		
		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			if(!(obj instanceof DisjointSet)) return false;
			
			return id == ((DisjointSet) obj).id;
		}
		
		@Override
		public int hashCode() {
			return id;
		}
	}
}
