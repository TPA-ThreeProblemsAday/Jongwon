import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* 
   **고려할 사항**
   K(K>1)세대 드래곤 커브는 K-1세대 드래곤 커브를 끝 점을 기준으로 90도 시계 방향으로 회전 시킨 다음, 그것을 끝점에 붙인 것이다.
   0<=x,y<=100 , 0<=d<=3,g<=10
       입력으로 주어지는 드래곤 커브는 격자 밖으로 벗어나지 않음.
*/
public class Main {

	private static final int SIZE=101;
	private static boolean[][] map;
	
	// 0: 오른쪽, 1: 위쪽, 2: 왼쪽, 3: 아래쪽
	private static int[] dr= {0,-1,0,1};
	private static int[] dc= {1,0,-1,0};
	
	public static void main(String args[]) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine()); // 드래곤 커브의 개수
		
		map=new boolean[SIZE][SIZE];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			int x=Integer.parseInt(st.nextToken()); // 드래곤 커브의 시작점 x(열)
			int y=Integer.parseInt(st.nextToken()); // 드래곤 커브의 시작점 y(행)
			int d=Integer.parseInt(st.nextToken()); // 시작 방향
			int g=Integer.parseInt(st.nextToken()); // 세대
			drawCurve(x, y, d, g);
		}
		System.out.println(countBox());
	}
	
	// 드래곤 커브 이동 경로 map에 그리는 함수
	private static void drawCurve(int col, int row, int d, int g) {
		List<Integer> directions=new ArrayList<>(); // 움직일 방향을 담을 리스트
		directions.add(d); // 시작 방향(0 세대)
		map[row][col]=true;
		
		// 1세대 부터 g세대 까지 반복
		for(int i=1;i<=g;i++) {
			// 끝부분이 서로  연결되므로, 뒤에서부터
			for(int j=directions.size()-1; j>=0; j--) {
				directions.add((directions.get(j)+1)%4); // 리스트에 이동 방향 추가
			}
		}
		// 방향대로 이동하면서 map 갱신
		for(int i=0; i<directions.size(); i++) {
			row+=dr[directions.get(i)];
			col+=dc[directions.get(i)];
			map[row][col]=true;
		}
	}
	
	// 정사각형 개수 세는 함수
	private static int countBox() {
		int cnt=0;
		for(int i=0;i<SIZE-1;i++) {
			for(int j=0;j<SIZE-1;j++) {
				if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) {
					cnt++;
				}
			}
		}
		return cnt;
	}

}