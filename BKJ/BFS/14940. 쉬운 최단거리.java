import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 이 문제에서 System.out.print로 답을 출력했을떄의 성능은  시간  2552ms, 메모리 68468KB
// StringBuilder를 썼을때는 시간 760ms, 메모리 58120KB 로 큰 차이가 있었다.
// -> 문자열 출력 값이 자주 변하는 경우 StringBuilder로 출력하는게 성능이 좋다.

public class Main {
	
	static int n,m;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dr= {0,1,0,-1};
	static int[] dc= {1,0,-1,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine(), " ");
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
        
		map=new int[n][m];
		int[] start_index=new int[2];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(in.readLine()," ");
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					start_index[0]=i;
					start_index[1]=j;
					map[i][j]=0;
				}
			}
		}
		
		bfs(start_index);
		
		// 출력
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]==1 && !visited[i][j]) {
					sb.append("-1 ");
				}
				else {
					sb.append(map[i][j]+" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void bfs(int[] start) {
		Queue<int[]> queue=new ArrayDeque<>();
		queue.offer(start);
		
		visited=new boolean[n][m];
		visited[start[0]][start[1]]=true;
		
		while(!queue.isEmpty()) {
			int[] temp=queue.poll();
			for(int i=0;i<4;i++) {
				int tr=temp[0]+dr[i];
				int tc=temp[1]+dc[i];
				if(tr<0 || tr>=n || tc<0 || tc>=m) continue; // 맵에서 벗어나는 경우 처리
				if(map[tr][tc]!=0 && !visited[tr][tc]) {
					queue.offer(new int[] {tr,tc});
					map[tr][tc]=map[temp[0]][temp[1]]+1;
					visited[tr][tc]=true;
				}
			}
		}
	}
}