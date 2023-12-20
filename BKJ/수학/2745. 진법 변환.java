import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.SynchronousQueue;

// B진수 -> 10진수 변환 방법 
// char(문자형)에서 int(정수형)로 형 변환 방법
// 아스키코드를 활용한 계산 방법

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		String N=st.nextToken();
		int B=Integer.parseInt(st.nextToken()); // B진법 수 N
		
		int size=N.length();
		int answer=0;
		int cnt=0;
		for(int i=size-1; i>=0; i--) {
			char c=N.charAt(i);
			// 문자일 때
			if('A'<=c && c<='Z') {
				// 원래 유니코드 보다 55 작으므로
				answer+=Math.pow(B, cnt)*(c-55);
			}
			// 숫자인 경우
			else {
				// 자바에서 문자는 계산할 때 정수가 된다.(유니코드)
				answer+=Math.pow(B, cnt)*(c-'0');
			}
			cnt++;
		}
		System.out.println(answer);
	}
}