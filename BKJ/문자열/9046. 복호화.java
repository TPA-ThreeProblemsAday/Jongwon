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
			int[] arr=new int[26]; // ���ĺ� ������ 26�� �̹Ƿ�
			String str=in.readLine();
			for(int j=0;j<str.length();j++) {
				if(str.charAt(j) >= 'a' && str.charAt(j) <= 'z')
				arr[str.charAt(j)-'a']++;
			}
			
			// �ִ� �� ���ϱ�
			int max=0;
			for(int j=0;j<26;j++) {
				if(max<arr[j]) {
					max=arr[j];
				}
			}
			
			// ���� �� �ִ��� �˻�
			int answer=0;
			int count=0;
			for(int j=0;j<26;j++) {
				if(max==arr[j]) {
					count++;
					answer=j;
				}
			}	
			// ���
			System.out.println(count==1 ? (char)(answer+'a') : '?');
		}
	}
}
