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
}