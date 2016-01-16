/*
	Integer to Roman
	Given an integer, convert it to a roman numeral.
	Input is guaranteed to be within the range from 1 to 3999.
	Tags: Math , String
*/

/*
        1    = I
        4    = IV
        5    = V
        9    = IX
        10   = X
        40   = XL
        50   = L
        90   = XC
        100  = C
        400  = CD
        500  = D
        900  = CM
        1000 = M
*/

//Best
public class Solution {
    public static String intToRoman(int num) {
            String M[] = {"", "M", "MM", "MMM"};//1000, 2000, 3000
            //                100,  200,  300,   400, 500,  600,  700,   800,   900
            String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
            //                10,   20,   30,    40,  50,   60,   70,    80,    90  
            String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
            //                1,    2,    3,     4,   5,    6,    7,     8,     9
            String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
            return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }
}
public class Solution {
    //We notice that, once we meet 4 or 40 or 400, they are difficult to solve,
    //like IIII is not roman four, so we need to convert to IV,
    //like VIIII is not roman nine, so we need to convert to IX
    //注意到，每逢4就不好处理，比如IIII不可行，需要转换成IV。9也不好处理VIIII所以要转成IX，以此类推
    //40 90 400 900 也应该预先初始化，方便遍历
    public String intToRoman(int num) {
        String res = "";
        
        //数组应该从大到小顺序排列，因为要从最大的能表示的数开始减
        int[] value = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbol = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V","IV", "I"};
        
        for (int i = 0; num != 0; i++) {
            //while number is larger than the value[i], we minus the maximum value that we can minus
            while (num >= value[i]) {
                num -= value[i];
                //and add  the symbol[i] to the according symbol.
                res += symbol[i];
            }
        }
        return res;
    }
}
