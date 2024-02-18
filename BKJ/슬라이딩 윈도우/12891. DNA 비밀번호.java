import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		int N=Integer.parseInt(st.nextToken()); 	// DNA 문자열의 길이
		int P=Integer.parseInt(st.nextToken());		// 비밀번호로 사용할 부분 문자열의 길이
		
		String DNA=in.readLine();	// DNA 문자열
		int[] minCnt=new int[4]; 	// 부분문자열에 포함되어야 할 {A,C,G,T}의 최소 개수
		int[] codeCnt=new int[4]; 	// 문자{A,C,G,T}의 개수를 저장할 배열
		int answer=0;				// 비밀번호의 종류의 수(출력할 값)
		
		st=new StringTokenizer(in.readLine()," ");
		for(int i=0;i<4;i++) {
			minCnt[i]=Integer.parseInt(st.nextToken());
		}
		
		// 1. 슬라이딩 윈도우를 실행하기 전 맨앞 문자열을 생성하고 체크해준다.
		// 1-1. 맨 앞 비밀번호 생성
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

		// 1-2. 맨 앞 비밀번호가 유효한지 체크
		boolean check=true;
		for(int i=0; i<4; i++) {
			// 한개라도 최소한의 코드수를 못맞추면
			if(codeCnt[i] < minCnt[i]) {
				check=false;
			}
		}
		if(check) answer++;
		
		
		// 2. 슬라이딩 윈도우 알고리즘
		for(int i=0; i<N-P; i++) {	// i: Start Point		
			// 2-1. Start Point의 값 빼기
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
			
			// 2-2. End Point를 한칸 이동 후 값 더하기
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
			
			// 2-3. 비밀번호가 유효한지 체크
			check=true;
			for(int j=0;j<4;j++) {
				// 한개라도 최소한의 코드수를 못맞추면
				if(codeCnt[j] < minCnt[j]) {
					check=false;
				}
			}
			if(check) {
				answer++;
			}
		}
		
		// 출력
		System.out.println(answer);
	}
}