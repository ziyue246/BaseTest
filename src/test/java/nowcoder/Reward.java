package nowcoder;

import org.junit.Test;


import java.util.*;
/**
 * created by ziyue on ${Date}
 */
public class Reward {

   // @Test
    public  void  test(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int n = scanner.nextInt();
            int []a = new int[n];

            int []b = new int[n];
            for(int i=0;i<n;++i){
                a[i]= scanner.nextInt();

                b[i]=1;
            }

            boolean markBoolean = true;
            while(markBoolean) {
                markBoolean=false;
                int  mark=a[0];
                for (int i = 1; i < n; ++i) {
                    if (mark > a[i]&&b[i-1] < b[i] + 1) {
                        b[i-1] = b[i] + 1;
                        markBoolean = true;

                    } if (mark < a[i]&& b[i] < b[i - 1] + 1) {
                        b[i] = b[i - 1] + 1;
                        markBoolean = true;
                    }
                    mark=a[i];
                }
            }
            int sum=0;
            for(int i=0;i<n;++i){
                //System.out.print(b[i]+" ");
                sum+=b[i];
            }

            System.out.println(sum);

        }
        scanner.close();
    }

   // @Test
    public void test001(){
        binary_to_assic(4298767);
    }

    private void binary_to_assic(int value ){
        int  a=value/10;
        if(a!=0)
            binary_to_assic(a);
        System.out.println(value);
    }

    //@Test
    //public void test000()
    //@Test
    public void test0001(){
        System.out.println(1^1);
        System.out.println(1^0);
    }

    public static void main(String []args)
    {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int n = scanner.nextInt();
            int []a = new int[n];
            for (int i=0;i<n;++i){
                a[i] = scanner.nextInt();
            }

            for(int i=n-1;i>=0;--i){
                Arrays.sort(a);
                for(int j=i-1;j>=0;--j){
                    if((a[i]^a[j])<a[j]){
                        a[j]=a[i]^a[j];
                    }
                }
            }
            int cnt=0;
            for (int i:a){
                if(i>0){
                    ++cnt;
                }
            }
            System.out.println(cnt);

        }

    }

    private static void print(int []a){
        for(int i:a){
            System.out.print(i+" ");
        }
        System.out.println();
    }





}
