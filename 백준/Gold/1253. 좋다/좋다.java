import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author neogul02
 * 
 * N은 최대 1<= N <= 2000
 * arr의 원소는 최대 1,000,000,000
 * 최대 2개의 수를 합친다 = 좋은수는 최대 20억, int 범위 21억 안으로 들어옴 = int
 */
class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int[] arr;

    static int result;

    
    public static void main(String[] args) throws IOException{
        input();
        
        solve();

        System.out.print(sb);
    }

    public static void solve(){
        // 투 포인터 전에 오름차순으로 정렬
        Arrays.sort(arr);
        
        // 입력받은 각 숫자가 다른 두 수를 합쳐서 나타낼 수 있으면 result ++;
        for(int i=0; i<N; i++){
            int targetInt = arr[i];

            int leftPointer = 0;  
            int rightPointer = N-1;

            while(true){
                // 현재 타겟 숫자를 만나면 건너뛰기
                if(leftPointer == i) leftPointer++;
                if(rightPointer == i) rightPointer--;

                // 포인터끼리 겹치면 종료
                if(leftPointer >= rightPointer) break;

                // 현재 더한 값
                int sum = arr[leftPointer] + arr[rightPointer];

                if(sum == targetInt){ // hit
                    result++;
                    break;
                } else if(sum < targetInt){ // target보다 작으면 leftPointer 증가 (값 키우기)
                    leftPointer++;
                } else { // target보다 크면 rightPointer 감소 (값 줄이기)
                    rightPointer--;
                }
            }
            
        }
        sb.append(result);
    }

    public static void input() throws IOException{
        N = Integer.parseInt(br.readLine().trim());
        arr = new int[N];

        st = new StringTokenizer(br.readLine().trim());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        result = 0;
    }
}