import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	
	static int curRow;;  // 현재 행 좌표 
	static int curCol;;  // 현재 열 좌표
	
	// 회전 순서: 서 -> 남 -> 동 -> 북
	static int[] dr= {0, 1, 0, -1};
	static int[] dc= {-1, 0, 1, 0};
	
	static int answer; // 출력할 답
	
	public static void main(String args[]) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		N=Integer.parseInt(in.readLine());
		map=new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		// 알고리즘 시작
		start();
		System.out.println(answer);
	}
	
	
	static void start() {
		
		// 시작 위치 설정
		curRow=N/2;
		curCol=N/2;
		
		int direction=0; // 방향
		int moveCnt=1;   // 서쪽, 남쪽 이동 횟수
		
		// 회전하는 수 만큼 반복
		// 서쪽과 남쪽 방향 이동 횟수가 같고, 동쪽과 북쪽 방향 이동 횟수가 같다.
		for(int i=0; i<N/2; i++) {
			move(direction, moveCnt); 		// 서쪽 방향 이동
			direction = (direction+1) % 4; 	// 방향 변경
			
			move(direction, moveCnt); 		// 남쪽 방향 이동
			direction = (direction+1) % 4; 	// 방향 변경
			moveCnt++; 						// 이동 횟수 증가
			
			move(direction, moveCnt);		// 동쪽 방향 이동
			direction = (direction+1) % 4;	// 방향 변경
			
			move(direction, moveCnt); 		// 북쪽 방향 이동
			direction = (direction+1) % 4; 	// 방향 변경
			moveCnt++;						// 이동 횟수 증가
		}
		
		// 마지막 맨 윗줄 이동(서쪽 방향)
		moveCnt--;
		move(direction, moveCnt);
	}
	
	// 토네이도 하는 메서드
	static void move(int direction, int moveCnt) {
		for(int j=0; j<moveCnt; j++) {
			// 이동
			curRow+=dr[direction];
			curCol+= dc[direction];
			
			// 모래 흩날림 처리
			checkSandMove(direction);
			// 모든 모래가 흩날렸 으므로, 현재 위치의 모래의 양은 0
			map[curRow][curCol]=0; 
		}
	}
	
	// 모래 흩날림을 처리하는 메서드
	static void checkSandMove(int direction) {
		int tr = curRow;
		int tc = curCol;
		int dropedSand = 0; // 버려진 모래의 양
		
		// 뒤로 이동
		tr+=dr[(direction+2)%4];		
		tc+=dc[(direction+2)%4];
		dropedSand+=moveSand(tr, tc, (direction+1)%4, 1); // 왼쪽 확인
		dropedSand+=moveSand(tr, tc, (direction+3)%4, 1); // 오른쪽 확인

		// 원래 위치
		tr+=dr[direction];
		tc+=dc[direction];
		dropedSand+=moveSand(tr, tc, (direction+1)%4, 7);  // 왼쪽 확인	
		dropedSand+=moveSand(tr+dr[(direction+1)%4], tc+dc[(direction+1)%4], (direction+1)%4, 2);  // 왼쪽 두번째 확인
		dropedSand+=moveSand(tr, tc, (direction+3)%4, 7);  // 오른쪽 확인
		dropedSand+=moveSand(tr+dr[(direction+3)%4], tc+dc[(direction+3)%4], (direction+3)%4, 2);  // 오른쪽 두번째 확인
		
		// 앞으로 이동
		tr+=dr[direction];
		tc+=dc[direction];
		dropedSand+=moveSand(tr, tc, (direction+1)%4, 10);  // 왼쪽 확인	
		dropedSand+=moveSand(tr, tc, (direction+3)%4, 10);  // 오른쪽 확인
		
		// 앞에 5% 처리
		dropedSand+=moveSand(tr, tc, direction, 5);
		
		// 남은 a 처리
		int remain = map[curRow][curCol]-dropedSand;
		if(tr<0 || tr>=N || tc<0 || tc>=N) { // 맵을 벗어났을 때
			answer += remain;
		}
		else { // 맵을 안 벗어났을 때
			map[tr][tc] += remain;
		}
	}
	
	// 모래를 이동시키는 메서드
	static int moveSand(int row, int col, int direction, int percent) {
		// percent는 모래가 이동할 비율
		int movedCnt=(map[curRow][curCol]*percent)/100; // 이동될 모래의 양

		// 좌표가 맵에서 벗어나는지 확인
		int tr=row+dr[direction];
		int tc=col+dc[direction];
		if(tr<0 || tr>=N || tc<0 || tc>=N) {
			answer += movedCnt; // 밖으로 나간 모래 갱신
			return movedCnt;
		}
		
		// 모래 이동
		map[tr][tc] += movedCnt;
		return movedCnt;
	}
	
}