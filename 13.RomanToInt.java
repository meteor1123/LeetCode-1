/*
    Roman to Integer
    Given a roman numeral, convert it to an integer.
    Input is guaranteed to be within the range from 1 to 3999.
    Tags: Math, String
*/
public class Solution {
    
    /*
        从后往前依次遍历每一位数，遇到I,X,C需要判断是否大于比其大一位的数，V,L,D,M直接加
    */
    public static int romanToInt(String s) {
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == 'I') {
                if (res >= 5)//如果>=5, 说明之前肯定遍历过V了，所以这个I肯定在左边，减I = 1,以次类推,I,X,C 因为只有IXC能左边
                    res += -1;
                else 
                    res += 1;
            } else if (c == 'V') {//V,L,D,M 直接加
                res += 5;
            } else if (c == 'X') {
                if (res >= 50)
                    res += -10;
                else 
                    res += 10;
            } else if (c == 'L') {
                res += 50;
            } else if (c == 'C') {
                if (res >= 500)
                    res += -100;
                else 
                    res += 100;
            } else if (c == 'D') {
                res += 500;
            } else if (c == 'M') {
                res += 1000;
            } 
        }
        return res;
    }
}