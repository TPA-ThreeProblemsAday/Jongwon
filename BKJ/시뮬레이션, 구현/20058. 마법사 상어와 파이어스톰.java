import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N; 			 // 얼음판 크기 2^N X 2^N
	static int Q; 			 // 파이어스톰 시전 횟수
	static int mapSize;	     // 얼음판 크기
	
	static int[][] map;		 // 얼음판
	
	static int sumIce=0; 	 // 남아있는 얼음 합
	static int biggestIce=0; // 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수
	
	static int[] dr= {0, 1, 0, -1};
	static int[] dc= {1, 0, -1, 0};
	static boolean[][] visited;
	
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		Q=Integer.parseInt(st.nextToken());
		
		// 얼음판 크기 계산
		mapSize=1;
		for(int i=0; i<N; i++) {
			mapSize*=2;
		}

		// 얼음판 입력
		map=new int[mapSize][mapSize];
		for(int i=0; i<mapSize; i++) {
			st=new StringTokenizer(in.readLine()," ");
			for(int j=0; j<mapSize; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				sumIce+=map[i][j];
			}
		}
		
		// 파이어스톰 시전
		st = new StringTokenizer(in.readLine()," ");
		for(int i=0; i<Q ;i++) {
			int level=Integer.parseInt(st.nextToken());
			start(level);
		}
		
		// 가장 큰 얼음조각 찾기
		visited=new boolean[mapSize][mapSize];
		for(int i=0; i<mapSize; i++) {
			for(int j=0; j<mapSize; j++) {
				if(!visited[i][j] && map[i][j]>0) {
					bfs(i, j);
				}
			}
		}
		
		// 출력
		System.out.println(sumIce);
		System.out.println(biggestIce);
	}
	
	
	// 파이어스톰 시작하는 메서드
	static void start(int L) {
		// 부분 격자 크기 계산
		int subMapSize=1;
		for(int i=0; i<L; i++) {
			subMapSize*=2;
		}
		
		// 부분 격자 회전
		int[][] subMap=new int[mapSize][mapSize];
		for(int i=0; i<mapSize; i+=subMapSize) {
			for(int j=0; j<mapSize; j+=subMapSize) {
				rotateSubMap(i, j, subMapSize, subMap);
			}
		}
		
		// 회전시킨 맵으로 갱신
		map=subMap;
		
		// 얼음 양 감소 시키기
		reduceIce();
	}
	
	
	// 부분 격자를 회전시키는 메서드(매개변수: 부분 격자의 왼쪽 위 좌표, 부분 배열 크기, 회전을 입력할 배열)
	static void rotateSubMap(int row, int col, int subMapSize, int[][] subMap) {
		for(int i=0; i<subMapSize; i++) {
			for(int j=0; j<subMapSize; j++) {
				// 점화식: rotate[i][j] = origin[배열 크기-1-j][i]
				subMap[row+i][col+j]=map[row+subMapSize-1-j][col+i];
			}
		}
	}
	
	
	// 얼음 양을 감소시키는 메서드(얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양이 1 줄어든다.)
	static void reduceIce() {
		// 얼음 양을 감소시켜야 하는 위치 담기
		List<int[]> list=new ArrayList<>();
		for(int i=0; i<mapSize; i++) {
			for(int j=0; j<mapSize; j++) {
				int cnt=0;
				if(map[i][j]==0) continue;
				
				for(int k=0; k<4; k++) {
					int tr=i+dr[k];
					int tc=j+dc[k];
					if(tr<0 || tr>=mapSize || tc<0 || tc>=mapSize) continue;
					if(map[tr][tc]>0) cnt++;
				}
				
				if(cnt<3) {
					list.add(new int[] {i, j});
				}
			}
		}
		
		// 얼음 감소
		for(int[] arr : list) {
			map[arr[0]][arr[1]]-=1;
			sumIce--;
		}
	}
	
	
	// 가장 큰 얼음 조각을 찾는 메서드 - bfs
	static void bfs(int row, int col) {
		Queue<int[]> queue=new ArrayDeque<>();
		queue.offer(new int[] {row, col});
		visited[row][col]=true;
		
		int cnt=0;
		while(!queue.isEmpty()) {
			int[] temp=queue.poll();
			for(int i=0; i<4; i++) {
				int tr=temp[0]+dr[i];
				int tc=temp[1]+dc[i];
				if(tr<0 || tr>=mapSize || tc<0 || tc>=mapSize || visited[tr][tc]) continue;
				
				if(map[tr][tc] > 0) {
					visited[tr][tc]=true;
					queue.offer(new int[] {tr, tc});
				}
			}
			cnt++;
		}
		
		biggestIce=Math.max(biggestIce, cnt);
	}
}