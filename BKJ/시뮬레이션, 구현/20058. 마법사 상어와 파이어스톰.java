import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N; 			 // ������ ũ�� 2^N X 2^N
	static int Q; 			 // ���̾�� ���� Ƚ��
	static int mapSize;	     // ������ ũ��
	
	static int[][] map;		 // ������
	
	static int sumIce=0; 	 // �����ִ� ���� ��
	static int biggestIce=0; // �����ִ� ���� �� ���� ū ����� �����ϴ� ĭ�� ����
	
	static int[] dr= {0, 1, 0, -1};
	static int[] dc= {1, 0, -1, 0};
	static boolean[][] visited;
	
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		Q=Integer.parseInt(st.nextToken());
		
		// ������ ũ�� ���
		mapSize=1;
		for(int i=0; i<N; i++) {
			mapSize*=2;
		}

		// ������ �Է�
		map=new int[mapSize][mapSize];
		for(int i=0; i<mapSize; i++) {
			st=new StringTokenizer(in.readLine()," ");
			for(int j=0; j<mapSize; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				sumIce+=map[i][j];
			}
		}
		
		// ���̾�� ����
		st = new StringTokenizer(in.readLine()," ");
		for(int i=0; i<Q ;i++) {
			int level=Integer.parseInt(st.nextToken());
			start(level);
		}
		
		// ���� ū �������� ã��
		visited=new boolean[mapSize][mapSize];
		for(int i=0; i<mapSize; i++) {
			for(int j=0; j<mapSize; j++) {
				if(!visited[i][j] && map[i][j]>0) {
					bfs(i, j);
				}
			}
		}
		
		// ���
		System.out.println(sumIce);
		System.out.println(biggestIce);
	}
	
	
	// ���̾�� �����ϴ� �޼���
	static void start(int L) {
		// �κ� ���� ũ�� ���
		int subMapSize=1;
		for(int i=0; i<L; i++) {
			subMapSize*=2;
		}
		
		// �κ� ���� ȸ��
		int[][] subMap=new int[mapSize][mapSize];
		for(int i=0; i<mapSize; i+=subMapSize) {
			for(int j=0; j<mapSize; j+=subMapSize) {
				rotateSubMap(i, j, subMapSize, subMap);
			}
		}
		
		// ȸ����Ų ������ ����
		map=subMap;
		
		// ���� �� ���� ��Ű��
		reduceIce();
	}
	
	
	// �κ� ���ڸ� ȸ����Ű�� �޼���(�Ű�����: �κ� ������ ���� �� ��ǥ, �κ� �迭 ũ��, ȸ���� �Է��� �迭)
	static void rotateSubMap(int row, int col, int subMapSize, int[][] subMap) {
		for(int i=0; i<subMapSize; i++) {
			for(int j=0; j<subMapSize; j++) {
				// ��ȭ��: rotate[i][j] = origin[�迭 ũ��-1-j][i]
				subMap[row+i][col+j]=map[row+subMapSize-1-j][col+i];
			}
		}
	}
	
	
	// ���� ���� ���ҽ�Ű�� �޼���(������ �ִ� ĭ 3�� �Ǵ� �� �̻�� ���������� ���� ĭ�� ������ ���� 1 �پ���.)
	static void reduceIce() {
		// ���� ���� ���ҽ��Ѿ� �ϴ� ��ġ ���
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
		
		// ���� ����
		for(int[] arr : list) {
			map[arr[0]][arr[1]]-=1;
			sumIce--;
		}
	}
	
	
	// ���� ū ���� ������ ã�� �޼��� - bfs
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