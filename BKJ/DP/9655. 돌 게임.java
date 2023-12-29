import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 돌을 첫번쨰로 가져가는 사람은 무조건 홀수, 두번쨰로 돌을 가져가는 사람은 무조건 짝수번째를 마지막으로 선택하게 된다.
public class Main {
		
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine());
		
		// N이 홀수인 경우
		if(N%2==1) {
			System.out.println("SK");
		} // N이 짝수인 경우
		else {
			System.out.println("CY");
		}
	}
}