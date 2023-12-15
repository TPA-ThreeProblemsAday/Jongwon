import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 문제 조건에서 큰 단위가 항상 작은 단위의 배수라는 조건이 있기 때문에 그리디로 구현하면 된다.
	// 위에 조건이 아닌 경우 DP로 풀어야함.
    public static void main(String[] args) throws IOException {
    	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st=new StringTokenizer(in.readLine()," ");
    	int N=Integer.parseInt(st.nextToken()); // 동전의 총 종류
    	int K=Integer.parseInt(st.nextToken()); // 가치의 합
    	
    	int[] arr=new int[N];
    	for(int i=0;i<N;i++) {
    		arr[i]=Integer.parseInt(in.readLine());
    	}
    	
    	// 큰 값부터 순서대로 가능한 개수만큼 더하면 된다.
    	int cnt=0;
    	for(int i=N-1;i>=0;i--) {
    		if(arr[i]>K) continue;
    		cnt+=K/arr[i];
    		K%=arr[i];
    	}
    	System.out.println(cnt);
    }
}