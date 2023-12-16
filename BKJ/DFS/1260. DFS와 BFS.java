import java.io.*;
import java.util.*;

// 문제에서 정점 번호가 작은 것을 먼저 방문하라 했으므로 인접 리스트보다 인접 행렬이 효율적이다.
// 리스트인 경우는 각각의 리스트를 모두 정렬후 탐색해야한다!
public class Main {
	
	static int N;
	static int[][] graph;
	static boolean[] dfs_visited;
	static boolean[] bfs_visited;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st=new StringTokenizer(in.readLine()," ");
    	
    	N=Integer.parseInt(st.nextToken()); 		// 정점의 개수
    	int M=Integer.parseInt(st.nextToken()); 	// 간선의 개수
    	int V=Integer.parseInt(st.nextToken());		// 시작할 정점의 번호
    	
    	graph=new int[N+1][N+1];
    	dfs_visited=new boolean[N+1];
    	bfs_visited=new boolean[N+1];
    	
    	for(int i=0;i<M;i++) {
    		st=new StringTokenizer(in.readLine()," ");
    		int from=Integer.parseInt(st.nextToken());
    		int to=Integer.parseInt(st.nextToken());
    		graph[from][to]=graph[to][from]=1;
    	}
    	dfs(V);
    	System.out.println();
    	bfs(V);
    }
    
    static void dfs(int v) {
    	dfs_visited[v]=true;
    	System.out.print(v+" ");
    	
    	for(int i=1;i<=N;i++) {
    		if(graph[v][i]==1 && !dfs_visited[i]) {
    			dfs(i);
    		}
    	}
    	
    }
    
    static void bfs(int v) {
    	Queue<Integer> queue=new ArrayDeque<>();
    	queue.offer(v);
    	bfs_visited[v]=true;
    	
    	while(!queue.isEmpty()) {
    		int temp=queue.poll();
    		System.out.print(temp+" ");
    		for(int i=1;i<=N;i++) {
    			if(graph[temp][i]==1 && !bfs_visited[i]) {
    				queue.offer(i);
    				bfs_visited[i]=true;
    			}
    		}
    	}
    }
}