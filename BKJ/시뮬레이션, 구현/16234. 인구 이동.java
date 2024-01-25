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

		// 입력
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;
		while (true) {
			visited = new boolean[N][N]; // 국경이동이 일어날 때마다 방문 여부 초기화
			check=false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						bfs(i, j, N, L, R);
					}
				}
			}
			if (!check) break; // 국경 이동이 없으면 반복문 탈출
			answer++; // 국경 이동이 있는 경우
		}
		System.out.println(answer);
	}

	static void bfs(int row, int col, int N, int L, int R) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { row, col });
		visited[row][col] = true;

		List<int[]> list=new ArrayList<>(); // 국경 이동이 가능한 나라를 담을 list
		list.add(new int[] {row, col});
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();			
			for (int i = 0; i < 4; i++) {
				int tr = temp[0] + dr[i];
				int tc = temp[1] + dc[i];
				if (tr < 0 || tr >= N || tc < 0 || tc >= N || visited[tr][tc]) continue; // 범위를 넘거나 방문했던 경우 pass
				int dif = Math.abs(map[temp[0]][temp[1]] - map[tr][tc]); // 국경선을 공유하는 두 나라의 인구 차이 계산
				// 국경선을 공유하는 두 나라의 인구 차이가 L<=dif<=R 일 경우
				if (L <= dif && dif <= R) {
					check = true;
					visited[tr][tc] = true;
					queue.offer(new int[] { tr, tc });
					list.add(new int[] {tr,tc});
				}
			}
		}
		// 서로 국경선을 이동 가능한 나라가 있는 경우
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