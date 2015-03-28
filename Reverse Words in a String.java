/*	
	Given an input string, reverse the string word by word.

	For example,
	Given s = "the sky is blue",
	return "blue is sky the".
*/


/*
	solution:
		step1: 检查输入s是否为空
		step2: 使用split按照空格 分隔生成字符数组res[],check该字符数组是否为空
		step3: 构造一个ArrayList,存放res[i]如果非空。
		step4: 用Collections.reverse方法逆置arraylist 
		step5: 构造string ans，将list里的字符串按照顺序加入
*/
public class Solution {
    //Solution1
    public String reverseWords(String s) {
        if (s == null || s.length() == 0)
            return s;
        String[] res = s.split(" ");
        if (res == null || res.length == 0)
            return "";
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < res.length; i++) {
            if (!res[i].isEmpty())
                list.add(res[i]);
        }
        Collections.reverse(list);
        
        String ans = "";
        for (int i = 0; i < list.size() - 1; i++) {
            ans += list.get(i) + " ";
        }
        ans += list.get(list.size() - 1);
        return ans;
    }

    //solution2
    public String reverseWords(String s) {
        if (s == null) {
            return null;
        }
        s = s.trim();
        if (s.length() == 0) {
            return "";
        }
        StringBuilder res = new StringBuilder);
        for (int i = s.length() - 1; i >= 0; i--) {
            if (i != s.length() - 1 && s.charAt(i) == ' ' && s.charAt(i) == s.charAt(i + 1)) {
                continue;
            }
            res.append(s.charAt(i));
        }
        int left = 0;
        int right = 0;
        while (right < res.length()) {
            while (right < res.length() && res.charAt(right) != ' ') {
                right++;
            }
            int next = right + 1;
            right--;
            while (left < right) {
                char temp = res.charAt(left);
                res.setCharAt(left++, res.charAt(right));
                res.setCharAt(right--, temp);
            }
            left = next;
            right = next;
        }
        return res.toString();
    }
}







