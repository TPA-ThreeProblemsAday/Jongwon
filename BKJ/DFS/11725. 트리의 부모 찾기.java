import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 문제에서 루트를 1이라 했기 때문에 트리를 만들지 않아도 된다.
// 노드의 개수 N (2 ≤ N ≤ 100,000) 이므로, 인접 행렬을 쓰면 메모리 초과가 날 것이다. -> 인접 리스트로 풀이
// 한줄씩 출력할 값이 많으므로, StringBuilder를 사용해서 성능을 향상시켰다.
public class Main {
	
	static int N;
	static ArrayList<ArrayList<Integer>> tree;
	static boolean[] visited;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(in.readLine());
		
		tree=new ArrayList<>();
		for(int i=0;i<=N;i++) { // Node가 1부터 시작하므로
			tree.add(new ArrayList<>());
		}
		
		for(int i=0;i<N-1;i++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			tree.get(from).add(to);
			tree.get(to).add(from);
		}
		
		visited=new boolean[N+1];
		parents=new int[N+1];
		dfs(1);
		
		// 출력
		// System.out.println -> 1456ms 
		// StringBuilder -> 644ms
		StringBuilder sb=new StringBuilder();
		for(int i=2;i<=N;i++) {
			sb.append(parents[i]+"\n");
		}
		System.out.println(sb.toString());
		
	}
	
	private static void dfs(int v) {
		visited[v]=true;
		for(int a : tree.get(v)) {
			if(!visited[a]) {
				parents[a]=v;
				dfs(a);
			}
		}
	}
}