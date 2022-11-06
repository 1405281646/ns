package org.zkl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DH {
    public static void main(String[] args) {
        Random random = new Random();


        int p = random.nextInt(30);
         p =29 ;
        int q = 2;
        while (!isPrime(p)) {
            p = random.nextInt(30);
        }
        System.out.println("2");
        while (!isPrimeRoot(q, p)) {
            System.out.println(q);

            q = q+1;

        }
        System.out.println("p:" + p);
        System.out.println("q:" + q);



        int x= random.nextInt(p-2)+1;
        int X= (int) Math.pow(q,x);
        int y= random.nextInt(p-2)+1;
        int Y= ((int) Math.pow(q,y))%p;
        System.out.println(x);
        System.out.println(X);
        System.out.println(y);
        System.out.println(Y);
        System.out.println((long)Math.pow(Y, x));
        System.out.println((long)Math.pow(X,y));

    }

    //判断 n是素数
    private static boolean isPrime(int n) {
        if(n<=2)return false;
        boolean flag = true;
        for (int j = 2; j < n; j++) {
            if (n % j == 0) {
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println(n + "是素数");
        }
        return flag;
    }

    //计算 a，b最大公因子
    private static int gcd(int a, int b) {

        if (a % b != 0) return gcd(b, a % b);

        else return b;

    }

    //计算 b^n mod m
    private static int ExpMod(int b, int n, int m) {
        return (int) Math.pow(b, n) % m;
    }

    private static boolean isPrimeRoot(int q, int p) {

        int[] temp = new int[p];

        for (int n = 0; n < p - 1; n++) {
            temp[n] = ExpMod(q, n, p);
        }
        Set<Integer> set = new HashSet<>();
        for (int j = 0; j < temp.length; j++) {
            set.add(temp[j]);
        }
        System.out.println("p:" + p);
        System.out.println("q:" + q);
        if (set.size() == p) {
            System.out.println(set.size());
            System.out.println(set);
            return true;

        }
        return false;
    }
}
