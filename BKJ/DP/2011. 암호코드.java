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
		
		// �� ���� 0�� ���� ��ȣ�� �߸���.
		if(str.charAt(0)=='0') {
			System.out.println(0);
			return;
		}
		
		// �ʱ� ����
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
		
		// DP ���
		for(int i=2; i<size; i++) {
			// ���� �ε����� 0�� ���
			if(str.charAt(i)=='0') {
				// �տ� �ε����� 0�� ��� -> ��ȣ�� �߸��� ���
				if(str.charAt(i-1)=='0') {
					System.out.println(0);
					return;
				}
			}
			// ���� �ε����� 0�� �ƴ� ��� -> 1 ~ 9
			else {
				memo[i]+=memo[i-1];
				memo[i]%=1000000;
			}
			
			// ���� �ε����� 0�� �ƴ� ��
			if(str.charAt(i-1)!='0') {
				int temp=Integer.parseInt(str.charAt(i-1)+""+str.charAt(i));
				// �ǵڿ� ���ڸ� ���� ��ȣ�� ������ ��� -> 10 ~ 26
				if(10<=temp && temp<=26) {
					memo[i]+=memo[i-2];
					memo[i]%=1000000;
				}
			}
		}
		// ���
		System.out.println(memo[size-1]);
	}
}