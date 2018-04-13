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

    //public static void main(String []args)
    public void test00001()
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


    @Test
    public void text099(){
//        int [] A = {0,7,8,10,10,11,12,17,19,18};
//        int [] B = {4,4,5, 7,11,14,15,16,17,20};


//        int [] A = {0,7,8,10,10,11,12,17,19,18};
//        int [] B = {4,4,6, 7,11,14,15,16,17,20};

//        int []A = {0,4,4,5,9};
//        int []B = {0,1,6,8,10};
//        int []A = {2,3,2,5,6};
//        int []B = {0,1,4,4,5};

        //           1      2
        int []A={0,1,6,9,10,13,13,16,19,26,23,24,25,27,32,31,35,36,37,39};
        int []B={2,5,8,8,10,12,14,15,22,22,28,29,30,31,30,33,33,36,37,38};


        int count = countExchange(A,B,1);

        System.out.println(count);
    }

    public int countExchange(int []a,int []b,int index){
        if(index>=a.length)return 0;


        if(a[index]>b[index-1]&&b[index]>a[index-1]) {

            if ((a[index - 1] >= a[index] || b[index - 1] >= b[index])
                    ) {
                int tmp = a[index];
                a[index] = b[index];
                b[index] = tmp;
                return countExchange(a, b, index + 1) + 1;
            }
            if ((index + 1 < a.length && (a[index] >= a[index + 1] || b[index] >= b[index + 1]))
                    ) {
                int tmp = a[index];
                a[index] = b[index];
                b[index] = tmp;
                return countExchange(a, b, index + 1) + 1;
            }
        }

        return countExchange(a, b, index + 1);
    }



}
