package algorithms.recursion;

public class RecursionTest {
    public static void main(String[] args) {
        //通过打印问题回顾递归的调用机制
//        test(5);

        System.out.println(factorial(5));

    }

    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n=" + n);
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
