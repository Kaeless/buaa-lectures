public class DHT {
    public static void main(String[] args) {
        int m = 6;
        final int space = (int)Math.pow(2, m);

        int N = 51;
        for (int n = 1; n <= m; n++) {
            final int hashValue = (int)Math.pow(2, (n - 1));
            int successor = (N + hashValue) % space;
            String N_successor = "N" + successor;
            System.out.println("[" + n + "] " + N + "+" + hashValue + " " + N_successor);
        }
    }

    static void notice() {
        /**
         * %在java中是取余而不是取模
         */
        System.out.println("取余 Remainder " + 4 % -3);
        System.out.println("取余 Remainder " + 4 % 3);
        System.out.println("取模 Modulo " + Math.floorMod(4, -3));
        System.out.println("取模 Modulo " + Math.floorMod(4, 3));

        /**
         * ^ 在java中是xor计算
         *
         * 1、按位异或，可以简单理解成：不进位加法。即：1+1=0；0+0=0；1+0 =1；
         * 2、任何数和自己异或结果为零。
         * 3、按位异或自反性。两次运算操作，可以将最后的结果还原。
         * 4、任何数和0做异或值不变，和1异或结果为原操作数取反。
         * 5、交换律。不使用中间变量，交换两个数。
         */
        System.out.println("按位异或 " + (8 ^ 7));
        System.out.println("按位异或 " + (0B0000_1000 ^ 0B0000_0111));
        System.out.println(Integer.toBinaryString(8) + " " + Integer.toBinaryString(7) + " " + Integer.toBinaryString(15));
    }
}
