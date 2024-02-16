import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	
	static int N, M;
	static int[][] map;
	static boolean[][][] isVisited;
	
	static int[] dr= {0, 1, 0, -1};
	static int[] dc= {1, 0, -1, 0};
	
	static List<int[]> acList;
	
	static class Wind{
		int row;
		int col;
		int direction; // 0:동, 1:남, 2:서, 3:북
		public Wind(int row, int col, int direction) {
			this.row=row;
			this.col=col;
			this.direction=direction;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		isVisited=new boolean[N][M][4];
		
		acList=new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(in.readLine()," ");
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					acList.add(new int[] {i, j});
				}
			}
		}
		
		for(int[] arr : acList) {
			bfs(arr[0], arr[1]);
		}
		
		// 출력
		System.out.println(countVisited());
	}
	
	
	static void bfs(int row, int col) {
		
		Queue<Wind> queue=new ArrayDeque<>();
		for(int i=0;i<4;i++) {
			queue.offer(new Wind(row, col, i));
			isVisited[row][col][i]=true;
		}
		
		while(!queue.isEmpty()) {
			Wind wind = queue.poll();
			int tr = wind.row + dr[wind.direction];
			int tc= wind.col + dc[wind.direction];
			if(tr<0 || tr>=N || tc<0 || tc>=M) continue;
			
			// 처음 방문하는 경우
			if(!isVisited[tr][tc][wind.direction]) {
				
				// 방향이 바뀌지 않는 경우
				if(map[tr][tc]==0 || map[tr][tc]==9) {
					queue.offer(new Wind(tr, tc, wind.direction));
				}
				// 방향이 바뀌는 경우
				else {
					int direction=changeDirection(map[tr][tc], wind.direction);
					if(direction != -1) {
						queue.offer(new Wind(tr, tc, direction));
					}
				}
				
				isVisited[tr][tc][wind.direction]=true;
			}
		}
	}
	
	
	static int changeDirection(int value, int direction) {
		// 0:동, 1:남, 2:서, 3:북
		if(value==1) {
			if(direction==0 || direction==2) {
				direction=-1;
			}
		}
		else if(value==2) {
			if(direction==1 || direction==3) {
				direction=-1;
			}
		}
		else if(value==3) {
			if(direction==0) {
				direction=3;
			}
			else if(direction==1) {
				direction=2;
			}
			else if(direction==2) {
				direction=1;
			}
			else if(direction==3) {
				direction=0;
			}
		}
		else if(value==4) {
			if(direction==0) {
				direction=1;
			}
			else if(direction==1) {
				direction=0;
			}
			else if(direction==2) {
				direction=3;
			}
			else if(direction==3) {
				direction=2;
			}
		}
		return direction;
	}
	
	
	static int countVisited() {
		int answer=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(isVisited[i][j][0] || isVisited[i][j][1] || 
						isVisited[i][j][2] || isVisited[i][j][3] ) {
					answer++;
				}	
			}
		}
		return answer;
	}
}