import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	
	static int curRow;;  // ���� �� ��ǥ 
	static int curCol;;  // ���� �� ��ǥ
	
	// ȸ�� ����: �� -> �� -> �� -> ��
	static int[] dr= {0, 1, 0, -1};
	static int[] dc= {-1, 0, 1, 0};
	
	static int answer; // ����� ��
	
	public static void main(String args[]) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		
		// �Է�
		N=Integer.parseInt(in.readLine());
		map=new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		// �˰��� ����
		start();
		System.out.println(answer);
	}
	
	
	static void start() {
		
		// ���� ��ġ ����
		curRow=N/2;
		curCol=N/2;
		
		int direction=0; // ����
		int moveCnt=1;   // ����, ���� �̵� Ƚ��
		
		// ȸ���ϴ� �� ��ŭ �ݺ�
		// ���ʰ� ���� ���� �̵� Ƚ���� ����, ���ʰ� ���� ���� �̵� Ƚ���� ����.
		for(int i=0; i<N/2; i++) {
			move(direction, moveCnt); 		// ���� ���� �̵�
			direction = (direction+1) % 4; 	// ���� ����
			
			move(direction, moveCnt); 		// ���� ���� �̵�
			direction = (direction+1) % 4; 	// ���� ����
			moveCnt++; 						// �̵� Ƚ�� ����
			
			move(direction, moveCnt);		// ���� ���� �̵�
			direction = (direction+1) % 4;	// ���� ����
			
			move(direction, moveCnt); 		// ���� ���� �̵�
			direction = (direction+1) % 4; 	// ���� ����
			moveCnt++;						// �̵� Ƚ�� ����
		}
		
		// ������ �� ���� �̵�(���� ����)
		moveCnt--;
		move(direction, moveCnt);
	}
	
	// ����̵� �ϴ� �޼���
	static void move(int direction, int moveCnt) {
		for(int j=0; j<moveCnt; j++) {
			// �̵�
			curRow+=dr[direction];
			curCol+= dc[direction];
			
			// �� �𳯸� ó��
			checkSandMove(direction);
			// ��� �𷡰� �𳯷� ���Ƿ�, ���� ��ġ�� ���� ���� 0
			map[curRow][curCol]=0; 
		}
	}
	
	// �� �𳯸��� ó���ϴ� �޼���
	static void checkSandMove(int direction) {
		int tr = curRow;
		int tc = curCol;
		int dropedSand = 0; // ������ ���� ��
		
		// �ڷ� �̵�
		tr+=dr[(direction+2)%4];		
		tc+=dc[(direction+2)%4];
		dropedSand+=moveSand(tr, tc, (direction+1)%4, 1); // ���� Ȯ��
		dropedSand+=moveSand(tr, tc, (direction+3)%4, 1); // ������ Ȯ��

		// ���� ��ġ
		tr+=dr[direction];
		tc+=dc[direction];
		dropedSand+=moveSand(tr, tc, (direction+1)%4, 7);  // ���� Ȯ��	
		dropedSand+=moveSand(tr+dr[(direction+1)%4], tc+dc[(direction+1)%4], (direction+1)%4, 2);  // ���� �ι�° Ȯ��
		dropedSand+=moveSand(tr, tc, (direction+3)%4, 7);  // ������ Ȯ��
		dropedSand+=moveSand(tr+dr[(direction+3)%4], tc+dc[(direction+3)%4], (direction+3)%4, 2);  // ������ �ι�° Ȯ��
		
		// ������ �̵�
		tr+=dr[direction];
		tc+=dc[direction];
		dropedSand+=moveSand(tr, tc, (direction+1)%4, 10);  // ���� Ȯ��	
		dropedSand+=moveSand(tr, tc, (direction+3)%4, 10);  // ������ Ȯ��
		
		// �տ� 5% ó��
		dropedSand+=moveSand(tr, tc, direction, 5);
		
		// ���� a ó��
		int remain = map[curRow][curCol]-dropedSand;
		if(tr<0 || tr>=N || tc<0 || tc>=N) { // ���� ����� ��
			answer += remain;
		}
		else { // ���� �� ����� ��
			map[tr][tc] += remain;
		}
	}
	
	// �𷡸� �̵���Ű�� �޼���
	static int moveSand(int row, int col, int direction, int percent) {
		// percent�� �𷡰� �̵��� ����
		int movedCnt=(map[curRow][curCol]*percent)/100; // �̵��� ���� ��

		// ��ǥ�� �ʿ��� ������� Ȯ��
		int tr=row+dr[direction];
		int tc=col+dc[direction];
		if(tr<0 || tr>=N || tc<0 || tc>=N) {
			answer += movedCnt; // ������ ���� �� ����
			return movedCnt;
		}
		
		// �� �̵�
		map[tr][tc] += movedCnt;
		return movedCnt;
	}
	
}