package org.zkl;

public class RSA {


    public static void main(String[] args) {
        int e = 31;
        int n = 3599;
//把n因式分解推导出脾p，q
//    n接近3600，故p，q接近60
//    取59*61=3599
        int p = 59, q = 61;
        int nn = (p - 1) * (q - 1);
//    与n互质的整数的个数为3480

//        gcd()
    }

    int gcd(int a, int b) {
        int t = 0;
        while (b != 0) {
            t = a % b;
            a = b;
            b = t;
        }

return t;
    }
}
