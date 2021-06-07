import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class solution {
	static int N; // 회차수
	static int people; // 사람 수
	static int list[][]; // 명단
	static int K; // 참가 회식 수
	static boolean canMeet[]; // 만날수 있는 사람 
	static boolean visited[]; // 참석하는 회식
	static int result = 0; // 최종 결과
	static void comb(int depth) {
		if(depth == K) {
			int cnt = 0;					
			for(int i = 0 ; i < N ; i++){
				if(!visited[i]) continue;
				for(int j = 1 ; j <= list[i][0] ; j++) {
					if(!canMeet[list[i][j]]){
						canMeet[list[i][j]] = true;
						cnt++;
					}
				}
			}
			result = Math.max(result, cnt);
			for(int i = 0 ; i < N ; i++){
				if(!visited[i]) continue;
				for(int j = 1 ; j <= list[i][0] ; j++) {
					canMeet[list[i][j]] = false;
				}
			}
		}else {
			for(int i = 0  ; i < N ; i++) {
				if(!visited[i] && list[i][0] < 4) {
					visited[i] = true;
					comb(depth + 1);
					visited[i] = false;
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 회식 개수 
		st = new StringTokenizer(br.readLine());
		people = Integer.parseInt(st.nextToken()); // 사람 수
		canMeet = new boolean[people + 1];
		visited = new boolean[N];
		list = new int[N][5]; // 회식 차수별 명단
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			list[i][0] = Integer.parseInt(st.nextToken());
			for(int j = 1 ; j <= list[i][0] ; j++) {
				list[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken()); // 참석하려는 회식 수
		comb(0);
		System.out.println(result);
	}
}
