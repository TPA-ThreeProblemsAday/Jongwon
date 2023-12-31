import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  이 문제를 부루트 포스로 풀이하게 되면 N개의 집에 대하여 칠할 수 있는 모든 조합을 탐색해야한다.
 *  이러한 경우 경우의 수가 2의 거듭제곱으로 늘어 시간 복잡도는 3*2^(N-1) 이된다.
 *  따라서, 전부 탐색하는 것은 최악의 경우 시간 초과가 발생할 것이므로, DP로 풀이해야 한다.
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(in.readLine());
        
        int[][] arr=new int[N][3];
        
        // 입력: N개의 줄에 빨강,초록,파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다.
        for(int i=0;i<N;i++) {
        	StringTokenizer st=new StringTokenizer(in.readLine()," ");
        	arr[i][0]=Integer.parseInt(st.nextToken());
        	arr[i][1]=Integer.parseInt(st.nextToken());
        	arr[i][2]=Integer.parseInt(st.nextToken());
        }
        
        for(int i=1;i<N;i++) {
        	// arr[i][0] : i번째 집이 r로 칠해질 때 i번째 집의 최소 비용
        	arr[i][0]+=Math.min(arr[i-1][1], arr[i-1][2]);
        	
        	// arr[i][1] : i번째 집이 g로 칠해질 때 i번째 집의 최소 비용
        	arr[i][1]+=Math.min(arr[i-1][0], arr[i-1][2]);
        	
        	// arr[i][2] : i번째 집이 b로 칠해질 때 i번째 집의 최소 비용
        	arr[i][2]+=Math.min(arr[i-1][0], arr[i-1][1]);
        }
        
        // 출력
        System.out.println(Math.min(Math.min(arr[N-1][0], arr[N-1][1]), arr[N-1][2]));
    }
}