import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 풀이: 배열을 정렬한 뒤, 배열의 가장 앞과 가장 뒤의 값을 더해가면서 비교하여 가장 큰 값을 출력하면 된다.
public class Main {

	public static void main(String args[]) throws IOException{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine());
		
		long[] arr=new long[N];
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		for(int i=0;i<N;i++) {
			arr[i]=Long.parseLong(st.nextToken()); // 문자열을 Long타입으로 변경할때 사용하는 메서드
		}
		
		Arrays.sort(arr);
		
		long answer=0;	
		// 짝수개인 경우
		if(N%2==0) {
			for(int i=0;i<N/2;i++) {
				if(arr[i]+arr[N-i-1] > answer) {
					answer=arr[i]+arr[N-i-1];
				}
			}
		}
		// 홀수개인 경우
		else {
			answer=arr[N-1];
			N-=1;
			for(int i=0;i<N/2;i++) {
				if(arr[i]+arr[N-i-1] > answer) {
					answer=arr[i]+arr[N-i-1];
				}
			}
		}
		System.out.println(answer);
	}
}
