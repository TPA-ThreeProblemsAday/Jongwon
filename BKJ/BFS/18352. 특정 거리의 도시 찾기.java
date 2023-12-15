import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 인접 행렬로 풀었을 때 메모리 초과 문제 발생!
// 도시의 개수가 300,000개 까지 가능하고, 간선이 적은 희소 그래프이기 때문에 인접 리스트로 푸는 것이 효율적이다!
class Node{
	int vertex;
	Node link;
	public Node(int vertex, Node link) {
		this.vertex=vertex;
		this.link=link;
	}
}

public class Main {
	static int N, M, K, X;
	static Node[] adjList;
	static List<Integer> list;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st=new StringTokenizer(in.readLine()," ");
    	N=Integer.parseInt(st.nextToken()); // 도시의 개수
    	M=Integer.parseInt(st.nextToken()); // 도로의 개수
    	K=Integer.parseInt(st.nextToken()); // 거리 정보
    	X=Integer.parseInt(st.nextToken()); // 출발 도시의 번호
    	
    	adjList=new Node[N+1];
    	for(int i=0;i<M;i++) {
    		st=new StringTokenizer(in.readLine()," ");
    		int from=Integer.parseInt(st.nextToken()); 
    		int to=Integer.parseInt(st.nextToken());
    		adjList[from]=new Node(to, adjList[from]);
    		
    	}
    	
    	list=new ArrayList<>();
    	bfs();
    	
    	if(list.size()==0) {
    		System.out.println(-1);
    	}
    	else {
        	Collections.sort(list);
        	for(int a:list) {
        		System.out.println(a);
        	}
    	}
    }
    
    // bfs 특성상 가까운 거리 순서대로 큐에서 출력되기 때문에, 거리 K보다 커지는 경우 반복문을 빠져나오는 코드를 작성하면 된다.
    // 중복값이나 최소 거리인 경우를 판별하기 위해 boolean 배열을 사용함.
    static void bfs() {
    	Queue<Integer> queue=new ArrayDeque<>();
    	boolean[] visited=new boolean[N+1];
    	int[] dist=new int[N+1];
    	queue.offer(X); // 시작 위치를 큐에 삽입
    	visited[X]=true;
    	
    	while(!queue.isEmpty()) {
    		int cur=queue.poll();
    		if(dist[cur]==K) {
    			list.add(cur);
    		}
    		if(dist[cur]>K) {
    			break;
    		}
    		for(Node temp=adjList[cur]; temp!=null; temp=temp.link) {
    			if(!visited[temp.vertex]) {
    				queue.offer(temp.vertex);
    				dist[temp.vertex]=dist[cur]+1;
    				visited[temp.vertex]=true;
    			}
    		}
    	}
    }
}