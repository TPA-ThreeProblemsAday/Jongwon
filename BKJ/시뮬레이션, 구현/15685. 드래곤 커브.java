import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* 
   **����� ����**
   K(K>1)���� �巡�� Ŀ��� K-1���� �巡�� Ŀ�긦 �� ���� �������� 90�� �ð� �������� ȸ�� ��Ų ����, �װ��� ������ ���� ���̴�.
   0<=x,y<=100 , 0<=d<=3,g<=10
       �Է����� �־����� �巡�� Ŀ��� ���� ������ ����� ����.
*/
public class Main {

	private static final int SIZE=101;
	private static boolean[][] map;
	
	// 0: ������, 1: ����, 2: ����, 3: �Ʒ���
	private static int[] dr= {0,-1,0,1};
	private static int[] dc= {1,0,-1,0};
	
	public static void main(String args[]) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine()); // �巡�� Ŀ���� ����
		
		map=new boolean[SIZE][SIZE];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			int x=Integer.parseInt(st.nextToken()); // �巡�� Ŀ���� ������ x(��)
			int y=Integer.parseInt(st.nextToken()); // �巡�� Ŀ���� ������ y(��)
			int d=Integer.parseInt(st.nextToken()); // ���� ����
			int g=Integer.parseInt(st.nextToken()); // ����
			drawCurve(x, y, d, g);
		}
		System.out.println(countBox());
	}
	
	// �巡�� Ŀ�� �̵� ��� map�� �׸��� �Լ�
	private static void drawCurve(int col, int row, int d, int g) {
		List<Integer> directions=new ArrayList<>(); // ������ ������ ���� ����Ʈ
		directions.add(d); // ���� ����(0 ����)
		map[row][col]=true;
		
		// 1���� ���� g���� ���� �ݺ�
		for(int i=1;i<=g;i++) {
			// ���κ��� ����  ����ǹǷ�, �ڿ�������
			for(int j=directions.size()-1; j>=0; j--) {
				directions.add((directions.get(j)+1)%4); // ����Ʈ�� �̵� ���� �߰�
			}
		}
		// ������ �̵��ϸ鼭 map ����
		for(int i=0; i<directions.size(); i++) {
			row+=dr[directions.get(i)];
			col+=dc[directions.get(i)];
			map[row][col]=true;
		}
	}
	
	// ���簢�� ���� ���� �Լ�
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