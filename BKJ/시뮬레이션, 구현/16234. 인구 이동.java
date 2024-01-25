import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean[][] visited;
	static boolean check;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		// �Է�
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;
		while (true) {
			visited = new boolean[N][N]; // �����̵��� �Ͼ ������ �湮 ���� �ʱ�ȭ
			check=false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						bfs(i, j, N, L, R);
					}
				}
			}
			if (!check) break; // ���� �̵��� ������ �ݺ��� Ż��
			answer++; // ���� �̵��� �ִ� ���
		}
		System.out.println(answer);
	}

	static void bfs(int row, int col, int N, int L, int R) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { row, col });
		visited[row][col] = true;

		List<int[]> list=new ArrayList<>(); // ���� �̵��� ������ ���� ���� list
		list.add(new int[] {row, col});
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();			
			for (int i = 0; i < 4; i++) {
				int tr = temp[0] + dr[i];
				int tc = temp[1] + dc[i];
				if (tr < 0 || tr >= N || tc < 0 || tc >= N || visited[tr][tc]) continue; // ������ �Ѱų� �湮�ߴ� ��� pass
				int dif = Math.abs(map[temp[0]][temp[1]] - map[tr][tc]); // ���漱�� �����ϴ� �� ������ �α� ���� ���
				// ���漱�� �����ϴ� �� ������ �α� ���̰� L<=dif<=R �� ���
				if (L <= dif && dif <= R) {
					check = true;
					visited[tr][tc] = true;
					queue.offer(new int[] { tr, tc });
					list.add(new int[] {tr,tc});
				}
			}
		}
		// ���� ���漱�� �̵� ������ ���� �ִ� ���
		if(list.size()>1) {
			int sum=0;
			int count=list.size();
			for(int[] arr : list) {
				sum+=map[arr[0]][arr[1]];
			}
			int avg=sum/count;
			for(int[] arr : list) {
				map[arr[0]][arr[1]]=avg;
			}
		}
	}
}