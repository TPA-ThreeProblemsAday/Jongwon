import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 
(1) Comparator를 사용하여 정렬 -> return값이 음수일 경우 위치교환 x, 양수일 경우 교환
(2) compareTo() -> 두 문자열을 사전순으로 비교하는데 사용됨.
	str1.compareTo(str2)를 예시로 들면
	str1이 사전순으로 앞에 있을 경우 음수, 뒤에 있을 경우 양수, 같을 경우 0을 출력한다.
*/
public class Main {
	
	public static void main(String[] args) throws IOException {
		 BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		 int N=Integer.parseInt(in.readLine());
		 String[] words=new String[N];
		 
		 for(int i=0;i<N;i++) {
			 words[i]=in.readLine();
		 }
		 
		 // 문자열 정렬 -> 길이가 같은 경우 사전순, 다른 경우 길이가 작은 순으로
		 Arrays.sort(words, (str1,str2)->{
			 return str1.length()!=str2.length() ? str1.length()-str2.length() : str1.compareTo(str2);
		 });
		 
		 // 같은 단어가 있는 경우 하나만 출력
		 System.out.println(words[0]);
		 for(int i=1;i<N;i++) {
			 if(!words[i].equals(words[i-1])) {
				 System.out.println(words[i]);
			 }
		 }
	}
}