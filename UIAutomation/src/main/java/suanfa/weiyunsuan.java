package suanfa;

public class weiyunsuan {
    public static  int getJishu(int[] arr){
        int result=0;
        for (int i = 0; i <arr.length ; i++) {
            result=result^arr[i];
        }
        return result;
    }
    public static int[] getTwoJishu(int[] arr){
        int or=0;
        int[] result=new int[]{};
        for (int i = 0; i <arr.length ; i++) {
            or=or^arr[i];
        }
        return  result;
    }

   /* public static void main(String[] args) {
        System.out.println(getJishu(new int[]{5,1,1,2,2,3,3,3,3,4,4}));
    }
*/}
