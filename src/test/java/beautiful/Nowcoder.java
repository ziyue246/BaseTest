package beautiful;

import java.util.*;

/**
 * Created by ziyue on 2018/4/4.
 */
public class Nowcoder {

    private  static int []a;
    public static void main(String []args){


        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            int n = scanner.nextInt();
            a = new int[n];

            int sum=0;
            for(int i=0;i<n;++i){
                a[i] = scanner.nextInt();
                sum+=a[i];
            }
            Arrays.sort(a);

            for(int i=0;i<=sum+1;++i){

                if(Arrays.binarySearch(a,i)>0)continue;
                if(!isProduct(0,i,0,false)) {
                    System.out.println(i);
                    break;
                }
            }
        }

    }

    public static boolean isProduct(int index,int b,int sum,boolean result){

        for(int i=index;i<a.length;++i){
            sum+=a[i];
            if(sum>b){
                return false|result;

            }else if(sum==b){
                return true|result;
            } else{
                result = isProduct(i+1,b,sum,result);
            }
            sum-=a[i];
        }
        return result;
    }

}
