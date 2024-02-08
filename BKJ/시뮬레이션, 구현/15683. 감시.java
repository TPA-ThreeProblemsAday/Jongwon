import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// �簢 ������ �ּ� ũ�� -> �ʿ� 0�� ���� ���� ��츦 ���ض�!
public class Main{
	
	static int N,M;
	
	static class Cctv{
		int row; // �� ��ġ
		int col; // �� ��ġ
		int num; // ��ȣ
		public Cctv(int row, int col, int num) {
			this.row=row;
			this.col=col;
			this.num=num;
		}
	}
	
	static List<Cctv> cctvList;
	static int listSize;
	
	static int answer=64;
	
	// ����: ��, ��, ��, ��
	static int[] dr= {0, 0, 1, -1};
	static int[] dc= {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		int[][] map=new int[N][M];
		cctvList=new ArrayList<>();
		
		// �Է� ó��, cctv ��ġ ����
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(in.readLine()," ");
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(1<=map[i][j] && map[i][j]<=5) {
					cctvList.add(new Cctv(i, j, map[i][j]));
				}
			}
		}
		
		listSize=cctvList.size();
		dfs(0, map);
		System.out.println(answer);
	}
	
	private static void dfs(int cnt, int[][] map) {
		if(cnt==listSize) {
			int zeroCnt=zeroCount(map);
			if(zeroCnt < answer) {
				answer=zeroCnt;
			}
			return;
		}
		
		Cctv p=cctvList.get(cnt);
		
		switch(p.num) {
			case 1:
				for(int i=0;i<4;i++) {
					int[][] tmp=copyMap(map);
					if(i==0) 
						move(p.row, p.col, 0, tmp);	// ��
					else if(i==1) 
						move(p.row, p.col, 1, tmp);	// ��
					else if(i==2) 
						move(p.row, p.col, 2, tmp); // ��
					else 
						move(p.row, p.col, 3, tmp); // ��
					
					dfs(cnt+1, tmp);
				}
				break;
			case 2:
				for(int i=0;i<2;i++) {
					int[][] tmp=copyMap(map);
					if(i==0) {
						move(p.row, p.col, 0, tmp); // ��
						move(p.row, p.col, 1, tmp); // ��
					}
					else {
						move(p.row, p.col, 2, tmp); // ��
						move(p.row, p.col, 3, tmp); // ��
					}
					dfs(cnt+1, tmp);
				}
				break;
			case 3:
				for(int i=0;i<4;i++) {
					int[][] tmp=copyMap(map);
					if(i==0) {
						move(p.row, p.col, 3, tmp); // ��
						move(p.row, p.col, 0, tmp); // ��
					}
					else if(i==1) {
						move(p.row, p.col, 0, tmp); // ��
						move(p.row, p.col, 2, tmp); // ��
					}
					else if(i==2) {
						move(p.row, p.col, 2, tmp); // ��
						move(p.row, p.col, 1, tmp); // ��
					}
					else {
						move(p.row, p.col, 1, tmp); // ��
						move(p.row, p.col, 3, tmp); // ��
					}
					dfs(cnt+1, tmp);
				}
				break;
			case 4:
				for(int i=0;i<4;i++) {
					int[][] tmp=copyMap(map);
					if(i==0) {
						move(p.row, p.col, 1, tmp); // ��
						move(p.row, p.col, 3, tmp); // ��
						move(p.row, p.col, 0, tmp); // ��
					}
					else if(i==1) {
						move(p.row, p.col, 3, tmp); // ��
						move(p.row, p.col, 0, tmp); // ��
						move(p.row, p.col, 2, tmp); // ��
					}
					else if(i==2) {
						move(p.row, p.col, 0, tmp); // ��
						move(p.row, p.col, 2, tmp); // ��
						move(p.row, p.col, 1, tmp); // ��
					}
					else {
						move(p.row, p.col, 2, tmp); // ��
						move(p.row, p.col, 1, tmp); // ��
						move(p.row, p.col, 3, tmp); // ��
					}
					
					dfs(cnt+1, tmp);
				}
				break;
			case 5:
				int[][] tmp=copyMap(map);
				move(p.row, p.col, 0, tmp);
				move(p.row, p.col, 1, tmp);
				move(p.row, p.col, 2, tmp);
				move(p.row, p.col, 3, tmp);
				dfs(cnt+1, tmp);
				break;
		}
	}
	
	// �� ����
	static int[][] copyMap(int[][] map){
		int[][] copy=new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copy[i][j]=map[i][j];
			}
		}
		return copy;
	}
	
	// CCTV ���ø� ���� ���� �̵�
	static void move(int row, int col, int direction, int[][] map) {
		while(true) {
			row+=dr[direction];
			col+=dc[direction];
			if(row<0 || row>=N || col<0 || col>=M) break;
			if(map[row][col]==6) break;
			if(map[row][col]==0) map[row][col]=-1;
		}
	}
	
	// �ʿ��� 0�� ���� ���� ���
	static int zeroCount(int[][] map) {
		int cnt=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==0) cnt++;
			}
		}
		return cnt;
	}
}