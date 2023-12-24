import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
	N이 10,000 까지 가능한데 시간 제한이 0.5초 이므로, 이중 포문으로 풀면 O(N^2)으로 최악의 경우 1초를 넘어간다.
 	투포인터로 풀이하면 O(N)이 된다.
 	현재 sum의 값에 따라서 end는 값을 추가하는 역할 start는 값을 없애는 역할로 생각하면 된다.
 	+ end가 마지막 인덱스인 경우도 고려해야 한다.
*/

public class Main {
	
	public static void main(String[] args) throws IOException {
		 BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st=new StringTokenizer(in.readLine()," ");
		 
		 int N=Integer.parseInt(st.nextToken()); // 수열의 크기
		 int M=Integer.parseInt(st.nextToken()); // 만들어야 하는 수
		 
		 int[] arr=new int[N];
		 st=new StringTokenizer(in.readLine()," ");
		 for(int i=0;i<N;i++) {
			 arr[i]=Integer.parseInt(st.nextToken());
		 }
		 
		 int start=0, end=0, answer=0, sum=0;
		 while(start<=end) {
			 if(sum==M) answer++;
			 // 합이 M보다 큰 경우 start 이동하면서 맨 앞의 수 빼기
			 if(sum>=M) {
				 sum-=arr[start];
				 start++;
			 }
			 // 합이 M보다 작고 end가 마지막인 경우 반복문 탈출
			 else if(end==N) {
				 break;
			 }
			 // 합이 M보다 작은 경우 end를 이동하면서 맨 뒤에 수 더하기
			 else {
				 sum+=arr[end];
				 end++;
			 }
		 }
		 System.out.println(answer);
	}
}