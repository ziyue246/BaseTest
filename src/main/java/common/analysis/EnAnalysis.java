package common.analysis;

public class EnAnalysis {


    public static double getSimilarity(String aStr,String bStr){

        int[]aStati = statisticsLetters(aStr);
        int[]bStati = statisticsLetters(bStr);
        if(aStati==null||bStati==null)return 0.0;

        int diff=0;
        for(int i=0;i<aStati.length;i++){
            diff+=Math.abs(aStati[i]-bStati[i]);
        }
        int aSum=0;
        int bSum=0;
        for(int i=0;i<aStati.length;i++){
            aSum+=aStati[i];
            bSum+=bStati[i];
        }
        return  (1.0*(aSum+bSum)/2.0-diff)/(1.0*(aSum+bSum)/2.0);
    }

    private static int[] statisticsLetters(String aStr){
        if(aStr==null||aStr.length()==0)return null;
        char[] aChs = aStr.toLowerCase().toCharArray();


        int [] stati = new int[26];
        for(int i=0;i<stati.length;i++){
            stati[i]=0;
        }
        for(char ch:aChs){
            if(ch>='a'&&ch<='z') {
                int index = ch - 'a';
                ++stati[index];
            }
        }
        return stati;
    }


}
