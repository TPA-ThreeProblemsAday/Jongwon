import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	
	static int[] dr= {-1,0,0,1};
	static int[] dc= {0,-1,1,0};
	
	static int[] shark;
	
	public static void main(String[] args) throws IOException{

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(in.readLine());
		
		map=new int[N][N];
		shark=new int[3];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					shark[0]=0;
					shark[1]=i;
					shark[2]=j;
					map[i][j]=0;
				}
			}
		}
		System.out.println(bfs());
	}
	
	static int bfs() {
		int time=0;
		
		// ������ ����: �Ÿ��� ����� ����Ⱑ ���ٸ�, ���� ���� �ִ� �����, �׷��� ����Ⱑ �����������, ���� ���ʿ� �ִ� ����⸦ �Դ´�.
		// int[3] -> 0: �Ÿ�, 1: ��, 2: ��
		PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->{
			// �Ÿ��� �ٸ� ���
			if(a[0]!=b[0]) {
				return a[0]-b[0]; // �Ÿ� ���� ��������
			}
			else {
				// ���̰� �ٸ� ���
				if(a[1]!=b[1]) {
					return a[1]-b[1]; // ����(��) ���� �������� -> ���� ����
				}
				else {
					return a[2]-b[2]; // �� ���� �������� -> ���� ����
				}
			}
		});
		
		int sharkSize=2; // �ʱ� �Ʊ� ����� ũ��
		int cntEat=0;    // ���� ����� ��
		
		while(true) {
			Queue<int[]> queue=new ArrayDeque<>();
			queue.offer(new int[] {shark[0], shark[1], shark[2]});
			
			boolean[][] isVisited=new boolean[N][N];
			isVisited[shark[1]][shark[2]]=true;
			
			// �Ʊ�� ���� ��ġ���� ���� �� �ִ� ����⸦ ���� �켱���� ť�� ��´�.
			while(!queue.isEmpty()) {
				int[] temp=queue.poll();
				for(int i=0;i<4;i++) {
					int tr=temp[1]+dr[i];
					int tc=temp[2]+dc[i];
					if(tr<0 || tr>=N || tc<0 || tc>=N || isVisited[tr][tc]) continue; // �� ������� üũ
					if(map[tr][tc]>sharkSize) continue; // ����Ⱑ �Ʊ���� ū ���
					queue.offer(new int[] {temp[0]+1,tr,tc});
					isVisited[tr][tc]=true;
					// ����Ⱑ �ְ�, �Ʊ� �� ����� ����  ū ���
					if(map[tr][tc]>0 && sharkSize > map[tr][tc]) {
						pq.offer(new int[] {temp[0]+1,tr,tc});
					}
				}
			}
			
			// ���� �� �ִ� ����Ⱑ �ִ� ���
			if(!pq.isEmpty()) {
				int[] fish=pq.poll();
				// ����� �Ա�
				cntEat++;
				map[fish[1]][fish[2]]=0;
				// ���� ����� ���� �Ʊ��� ũ��� ������
				if(sharkSize==cntEat) {
					sharkSize++;
					cntEat=0;
				}
				time+=fish[0]; //  �ð��� �̵��� �Ÿ���ŭ ����
				// ��� ��ġ �̵�
				shark[1]=fish[1];
				shark[2]=fish[2];
				pq.clear();
			}
			else { // ���� �� �ִ� ����Ⱑ ���� ���
				break;
			}
			
		}
		return time;
	}
}
