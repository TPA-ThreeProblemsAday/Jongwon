import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine());

		for(int i=0;i<N;i++) {
			int[] arr=new int[26]; // 알파벳 종류가 26개 이므로
			String str=in.readLine();
			for(int j=0;j<str.length();j++) {
				if(str.charAt(j) >= 'a' && str.charAt(j) <= 'z')
				arr[str.charAt(j)-'a']++;
			}
			
			// 최대 값 구하기
			int max=0;
			for(int j=0;j<26;j++) {
				if(max<arr[j]) {
					max=arr[j];
				}
			}
			
			// 같은 값 있는지 검사
			int answer=0;
			int count=0;
			for(int j=0;j<26;j++) {
				if(max==arr[j]) {
					count++;
					answer=j;
				}
			}	
			// 출력
			System.out.println(count==1 ? (char)(answer+'a') : '?');
		}
	}
}
