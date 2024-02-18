import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		int N=Integer.parseInt(st.nextToken()); 	// DNA ���ڿ��� ����
		int P=Integer.parseInt(st.nextToken());		// ��й�ȣ�� ����� �κ� ���ڿ��� ����
		
		String DNA=in.readLine();	// DNA ���ڿ�
		int[] minCnt=new int[4]; 	// �κй��ڿ��� ���ԵǾ�� �� {A,C,G,T}�� �ּ� ����
		int[] codeCnt=new int[4]; 	// ����{A,C,G,T}�� ������ ������ �迭
		int answer=0;				// ��й�ȣ�� ������ ��(����� ��)
		
		st=new StringTokenizer(in.readLine()," ");
		for(int i=0;i<4;i++) {
			minCnt[i]=Integer.parseInt(st.nextToken());
		}
		
		// 1. �����̵� �����츦 �����ϱ� �� �Ǿ� ���ڿ��� �����ϰ� üũ���ش�.
		// 1-1. �� �� ��й�ȣ ����
		for(int i=0; i<P; i++) {
			if(DNA.charAt(i)=='A') {
				codeCnt[0]++;
			}
			else if(DNA.charAt(i)=='C') {
				codeCnt[1]++;
			}
			else if(DNA.charAt(i)=='G') {
				codeCnt[2]++;
			}
			else if(DNA.charAt(i)=='T') {
				codeCnt[3]++;
			}
		}

		// 1-2. �� �� ��й�ȣ�� ��ȿ���� üũ
		boolean check=true;
		for(int i=0; i<4; i++) {
			// �Ѱ��� �ּ����� �ڵ���� �����߸�
			if(codeCnt[i] < minCnt[i]) {
				check=false;
			}
		}
		if(check) answer++;
		
		
		// 2. �����̵� ������ �˰���
		for(int i=0; i<N-P; i++) {	// i: Start Point		
			// 2-1. Start Point�� �� ����
			if(DNA.charAt(i)=='A') {
				codeCnt[0]--;
			}
			else if(DNA.charAt(i)=='C') {
				codeCnt[1]--;
			}
			else if(DNA.charAt(i)=='G') {
				codeCnt[2]--;
			}
			else if(DNA.charAt(i)=='T') {
				codeCnt[3]--;
			}
			
			// 2-2. End Point�� ��ĭ �̵� �� �� ���ϱ�
			if(DNA.charAt(i+P)=='A') {
				codeCnt[0]++;
			}
			else if(DNA.charAt(i+P)=='C') {
				codeCnt[1]++;
			}
			else if(DNA.charAt(i+P)=='G') {
				codeCnt[2]++;
			}
			else if(DNA.charAt(i+P)=='T') {
				codeCnt[3]++;
			}
			
			// 2-3. ��й�ȣ�� ��ȿ���� üũ
			check=true;
			for(int j=0;j<4;j++) {
				// �Ѱ��� �ּ����� �ڵ���� �����߸�
				if(codeCnt[j] < minCnt[j]) {
					check=false;
				}
			}
			if(check) {
				answer++;
			}
		}
		
		// ���
		System.out.println(answer);
	}
}