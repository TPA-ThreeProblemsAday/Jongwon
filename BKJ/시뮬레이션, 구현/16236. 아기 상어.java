import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	
	static int[] dr= {-1,0,0,1};
	static int[] dc= {0,-1,1,0};
	
	static int[] shark;
	
	public static void main(String[] args) throws IOException{

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(in.readLine());
		
		map=new int[N][N];
		shark=new int[3];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					shark[0]=0;
					shark[1]=i;
					shark[2]=j;
					map[i][j]=0;
				}
			}
		}
		System.out.println(bfs());
	}
	
	static int bfs() {
		int time=0;
		
		// 문제의 조건: 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
		// int[3] -> 0: 거리, 1: 행, 2: 열
		PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->{
			// 거리가 다른 경우
			if(a[0]!=b[0]) {
				return a[0]-b[0]; // 거리 기준 오름차순
			}
			else {
				// 높이가 다른 경우
				if(a[1]!=b[1]) {
					return a[1]-b[1]; // 높이(행) 기준 오름차순 -> 위쪽 부터
				}
				else {
					return a[2]-b[2]; // 열 기준 오름차순 -> 왼쪽 부터
				}
			}
		});
		
		int sharkSize=2; // 초기 아기 상어의 크기
		int cntEat=0;    // 먹은 물고기 수
		
		while(true) {
			Queue<int[]> queue=new ArrayDeque<>();
			queue.offer(new int[] {shark[0], shark[1], shark[2]});
			
			boolean[][] isVisited=new boolean[N][N];
			isVisited[shark[1]][shark[2]]=true;
			
			// 아기상어가 현재 위치에서 먹을 수 있는 물고기를 전부 우선순위 큐에 담는다.
			while(!queue.isEmpty()) {
				int[] temp=queue.poll();
				for(int i=0;i<4;i++) {
					int tr=temp[1]+dr[i];
					int tc=temp[2]+dc[i];
					if(tr<0 || tr>=N || tc<0 || tc>=N || isVisited[tr][tc]) continue; // 맵 벗어나는지 체크
					if(map[tr][tc]>sharkSize) continue; // 물고기가 아기상어보다 큰 경우
					queue.offer(new int[] {temp[0]+1,tr,tc});
					isVisited[tr][tc]=true;
					// 물고기가 있고, 아기 상어가 물고기 보다  큰 경우
					if(map[tr][tc]>0 && sharkSize > map[tr][tc]) {
						pq.offer(new int[] {temp[0]+1,tr,tc});
					}
				}
			}
			
			// 먹을 수 있는 물고기가 있는 경우
			if(!pq.isEmpty()) {
				int[] fish=pq.poll();
				// 물고기 먹기
				cntEat++;
				map[fish[1]][fish[2]]=0;
				// 먹은 물고기 수가 아기상어 크기와 같으면
				if(sharkSize==cntEat) {
					sharkSize++;
					cntEat=0;
				}
				time+=fish[0]; //  시간을 이동한 거리만큼 증가
				// 상어 위치 이동
				shark[1]=fish[1];
				shark[2]=fish[2];
				pq.clear();
			}
			else { // 먹을 수 있는 물고기가 없는 경우
				break;
			}
			
		}
		return time;
	}
}
