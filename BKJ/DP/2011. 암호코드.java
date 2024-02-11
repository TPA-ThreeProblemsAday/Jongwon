import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String str=in.readLine();
		
		int size=str.length();
		int[] memo= new int[size];
		
		// 맨 앞이 0인 경우는 암호가 잘못됨.
		if(str.charAt(0)=='0') {
			System.out.println(0);
			return;
		}
		
		// 초기 설정
		memo[0]=1;
		if(1 < size) {
			int temp=Integer.parseInt(str.charAt(0)+""+str.charAt(1));
			if(str.charAt(1)=='0') {
				if(temp==10 || temp==20) memo[1]=1;
				else {
					System.out.println(0);
					return;
				}
			}
			else {
				if(11<=temp && temp<=26) memo[1]=2;
				else memo[1]=1;
			}
		}
		
		// DP 계산
		for(int i=2; i<size; i++) {
			// 현재 인덱스가 0인 경우
			if(str.charAt(i)=='0') {
				// 앞에 인덱스도 0인 경우 -> 암호가 잘못된 경우
				if(str.charAt(i-1)=='0') {
					System.out.println(0);
					return;
				}
			}
			// 현재 인덱스가 0이 아닌 경우 -> 1 ~ 9
			else {
				memo[i]+=memo[i-1];
				memo[i]%=1000000;
			}
			
			// 이전 인덱스가 0이 아닐 때
			if(str.charAt(i-1)!='0') {
				int temp=Integer.parseInt(str.charAt(i-1)+""+str.charAt(i));
				// 맨뒤에 두자리 값이 암호가 가능한 경우 -> 10 ~ 26
				if(10<=temp && temp<=26) {
					memo[i]+=memo[i-2];
					memo[i]%=1000000;
				}
			}
		}
		// 출력
		System.out.println(memo[size-1]);
	}
}