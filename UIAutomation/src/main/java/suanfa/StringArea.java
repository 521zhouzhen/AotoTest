package suanfa;

import java.util.ArrayList;
import java.util.List;

public class StringArea {
    public String replace(String s){
        String replaceAll = s.replaceAll("\\s", "%20");
        return replaceAll;
    }
    public String reverseWords(String s,int n){
        String result;
        if (n<=s.length()){
          String s1=s.substring(0,n);
          String s2=s.substring(n,s.length());
          result =s2+s1;
          System.out.println(s1);
          System.out.println(s2);
          return result;
        }
        return s;
    }
    public Boolean isNumber(String s){
        String paramter=s.replaceAll(" ","");
        if (paramter.contains(".")){
            try {
                Float.parseFloat(paramter);
                return true;
            }catch (Exception e){
                return false;
            }
        }
        if (paramter.contains("e")){
            String[] es = paramter.split("e");
            if (es.length==2){
                try {
                    Integer.parseInt(es[0]);
                    Integer.parseInt(es[1]);
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
            return false;

        }
        if (paramter.contains("E")){
            String[] es = paramter.split("E");
            if (es.length==2){
                try {
                    Integer.parseInt(es[0]);
                    Integer.parseInt(es[1]);
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
            return false;

        }
        else try {
            Integer.parseInt(paramter);
            return true;
        }catch (Exception e){
            return  false;
        }

    }
    public Boolean isNumber2(String str){
        char[] chars = str.toCharArray();
        int start=-1,end=-1;
        for (int i = 0; i < chars.length; i++) {

            if(chars[i]!=' '){
                if(end>=0){
                    return false;
                }
                if(start<0){
                    start=i;
                }
            }
            if(start>=0&&chars[i]==' '&&end<0){
                end=i-1;
            }

        }
        if(start<0||chars[0]=='e'||chars[0]=='E'){
            return false;
        }
        end=end<0?chars.length-1:end;
        int eIndex = str.indexOf("e");
        eIndex=eIndex>0?eIndex:str.indexOf("E");
        if(eIndex>0){
            return checkNum(str,chars,start,eIndex-1)&&checkIsNumber(chars,eIndex+1,end,true);
        }
        return checkNum(str,chars,start,end);
    }

    private boolean checkNum(String str, char[] chars, int start, int end) {

        int index = str.indexOf(".");
        if(index>=0&&start==end){
            return false;
        }
        boolean result=true,left=false,right=false;
        if(index>=0){
            if(index-1>=start){
                left=checkIsNumber(chars,start,index-1, true);
                result&=left;
            }
            if(index+1<=end){
                right=checkIsNumber(chars,index+1,end, false);
                result&=right;
            }
            if(right&&start==index-1&&(chars[start]=='-'||chars[start]=='+')){
                result=true;
            }
            return result;
        }else {
            return checkIsNumber(chars,start,end, true);
        }
    }

    private boolean checkIsNumber(char[] chars, int start, int end, boolean isInteger) {

        if(start<chars.length&&isInteger&&(chars[start]=='-'||chars[start]=='+')){
            start++;
        }
        if(start>end){
            return false;
        }

        for (int i = start; i <=end ; i++) {
            if(chars[i]-'0'>9||chars[i]-'0'<0){
                return false;
            }
        }
        return true;

    }

   /* public static void main(String[] args) {
       *//* String replace = new replaceBlankArea().replace("abc 123");
        System.out.println("abc 123".replaceAll("\\s","%20"));*//*
       // System.out.println(new StringArea().reverseWords("12345679",2));
       // System.out.println(new StringArea().isNumber("-1E-16"));
      //  System.out.println(new StringArea().findContinuousSequence(10).toString());
        System.out.println(new StringArea().countDigitOne(10));
    }*/

    public int[][] findContinuousSequence(int target) {
        List<int[]> vec = new ArrayList<int[]>();
        int sum = 0, limit = (target - 1) / 2; // (target - 1) / 2 等效于 target / 2 下取整
        for (int i = 1; i <= limit; ++i) {
            for (int j = i;; ++j) {
                sum += j;
                if (sum > target) {
                    sum = 0;
                    break;
                } else if (sum == target) {
                    int[] res = new int[j - i + 1];
                    for (int k = i; k <= j; ++k) {
                        res[k - i] = k;
                    }
                    vec.add(res);
                    sum = 0;
                    break;
                }
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }

    public int countDigitOne(int n) {
        int result=0;
        for (int i = 1; i <=n; i++) {
            if (n==1|i%10==1|i/10==1){
                result++;
            }
        }
        return result;
    }


}
