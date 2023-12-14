import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N; //  전체 사람의 수
	static int from,to;
	static int[][] graph;
	static boolean[] visited;
	static int result=-1;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    	N=Integer.parseInt(in.readLine());
    	
    	StringTokenizer st = new StringTokenizer(in.readLine()," ");
    	from = Integer.parseInt(st.nextToken());
    	to = Integer.parseInt(st.nextToken());
    	
    	graph=new int[N+1][N+1];
    	visited=new boolean[N+1];
    	int m=Integer.parseInt(in.readLine()); // 부모 자식들 간의 관계 수
    	for(int i=0;i<m;i++) {
    		st=new StringTokenizer(in.readLine()," ");
    		int v1=Integer.parseInt(st.nextToken());
    		int v2=Integer.parseInt(st.nextToken());
    		graph[v1][v2]=graph[v2][v1]=1;
    	}
    	dfs(from, 0);
    	System.out.println(result);
    }
    
    static void dfs(int position, int cnt) {
		if(position==to) {
			result=cnt;
			return;
		}
		
    	visited[position]=true;
    	
    	for(int i=1;i<=N;i++) {
    		if(!visited[i] && graph[position][i]==1) {
    			dfs(i, cnt+1);
    		}
    	}
    }
}