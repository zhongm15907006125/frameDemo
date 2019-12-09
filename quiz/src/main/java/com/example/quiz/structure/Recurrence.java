package com.example.quiz.structure;

public class Recurrence {

    public static int feiBoNaCi(int i) {
        if (i == 1 || i == 2) {
            return 1;
        } else {
            int result = feiBoNaCi(i - 1) + feiBoNaCi(i - 2);
            System.out.println(result);
            return result;
        }
    }

    public static void hanNuoTa(int i,char from,char in,char to) {
        if (i == 1){
            System.out.println("把第1个盘子从"+from+"移到"+to);
        }else {
            hanNuoTa(i-1,from,to,in);
            System.out.println("把第"+i+"个盘子从"+from+"移到"+to);
            hanNuoTa(i-1,in,from,to);
        }
        //返回的步骤总数
//        if (i<=0){
//            return 0;
//        }
//        if (i == 1) {
//            return 1;
//        } else {
//            return hanNuoTa(i - 1) * 2 + 1;
//        }
    }
}
