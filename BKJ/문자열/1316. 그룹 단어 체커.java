import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 문자를 숫자로 변환하여 배열 인덱스로 활용하여 풀이하는 문제
// 총 알파벳 개수는 26개
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine()); // 그룹 단어의 개수
		
		int answer=0;
		for(int i=0;i<N;i++) {
			String str=in.readLine();
			boolean[] bo=new boolean[26]; // 알파벳 개수=26 이므로
			
			boolean check=true;
			for(int j=0;j<str.length();j++) {
				int index=str.charAt(j)-'a'; // 현재 알파벳의 인덱스
				// 이미 앞에서 있던 알파벳이고
				if(bo[index]) {
					// 연속된 값이 아닐 경우 답 x
					if(str.charAt(j)!=str.charAt(j-1)) {
						check=false;
						break;
					}
				}
				else {
					bo[index]=true;
				}
			}
			if(check) {
				answer++;
			}
		}
		System.out.println(answer);
	}
}