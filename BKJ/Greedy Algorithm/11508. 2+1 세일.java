import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 제품의 가격을 입력한 배열을 오름차순으로 정렬 후, 뒤에서부터 3개씩 묶어서 계산하면 최소비용
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine()); // 유제품의 수
		
		int[] arr=new int[N];
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(in.readLine());
		}
		Arrays.sort(arr);
		
		int answer=0;
		int cnt=0;
		for(int i=N-1;i>=0;i--) {
			cnt++;
			if(cnt!=3) {
				answer+=arr[i];
			}
			else {
				cnt=0;
			}
		}
		System.out.println(answer);

	}
}