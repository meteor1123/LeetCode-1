/*
	Text Justification
	Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

	You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

	Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, 
    the empty slots on the left will be assigned more spaces than the slots on the right.

	For the last line of text, it should be left justified and no extra space is inserted between words.
	For example,
	words: ["This", "is", "an", "example", "of", "text", "justification."]
	L: 16.

	Return the formatted lines as:
	[
	   "This    is    an",
	   "example  of text",
	   "justification.  "
	]
	Note: Each word is guaranteed not to exceed L in length.

	Corner Cases:
	A line other than the last line might contain only one word. What should you do in this case?
	In this case, that line should be left-justified.

	Tags: String
*/

    /*
    这道题属于纯粹的字符串操作，要把一串单词安排成多行限定长度的字符串。主要难点在于空格的安排，
思路1：
    1.首先每个单词之间必须有空格隔开，而当当前行放不下更多的单词并且字符又不能填满长度L时，我们要把空格均匀的填充在单词之间。
    2.如果剩余的空格量刚好是间隔倍数那么就均匀分配即可，否则还必须把多的一个空格放到前面的间隔里面。
    3.实现中我们维护一个count计数记录当前长度，超过之后我们计算共同的空格量以及多出一个的空格量，然后将当行字符串构造出来。
    4.最后一个细节就是最后一行不需要均匀分配空格，句尾留空就可以，所以要单独处理一下。
    5.时间上我们需要扫描单词一遍，然后在找到行尾的时候在扫描一遍当前行的单词，不过总体每个单词不会被访问超过两遍，所以总体时间复       杂度是O(n)。
    6.而空间复杂度则是结果的大小（跟单词数量和长度有关，不能准确定义，如果知道最后行数r，则是O(r*L)
    
思路2：
    1. 首先要能判断多少个word组成一行：
    这里统计读入的所有words的总长curLen，并需要计算空格的长度。假如已经读入words[0]~words[i]。
    当curLen + i <=L 且 curLen + 1 + word[i+1].size() > L时，一行结束。

    2. 知道一行的所有n个words，以及总长curLen之后要决定空格分配：
    L = 每行长度
    curLen = 一行中所有word的长度之和
    n = 一行中word的数量
    平均空格数                             ：k = (L - curLen) / (n-1)
    在同一行的前m个单词每个单词应该有的空格数k+1 ：m = (L - curLen) % (n-1)

    例子：L = 21，curLen = 14，n = 4
          k = (21 - 14) / (4-1) = 2
          m = (21 - 14) % (4-1)  = 1
          A---B--C--D

    3. 特殊情况：
        (a) 最后一行：当读入到第i = words.size()-1 个word时为最后一行。该行k = 1，m = 0
        (b) 一行只有一个word：此时n-1 = 0，计算(L - curLen)/(n-1)会出错。该行k = L-curLen, m = 0
        (c) 当word[i].size() == L时。

    */

public class Solution {
    public List<String> fullJustify(String[] words, int L) {
    	ArrayList<String> res = new ArrayList<>();
    	if (words == null || words.length == 0)
    		return res;
    	//上一次计算的单词的长度
    	int count = 0; 
    	//某一行开始的单词的下标
    	int last = 0;

    	//遍历每个单词，words[i].length()是当前尝试放的一个单词的长度

    	for (int i = 0; i < words.length; i++) {
    		// count（之前加入本行的所有单词的长度）
    		// + word[i].length (现在要加入的单词的长度)
    		// + (i - last) (代表从words[last] ~ words[i]之间的间隔数量为 i - last)
    		if (count + words[i].length() + (i - last) > L) {
    			int spaceNum = 0;
    			int extraNum = 0;
    		

	    		//因为words[i] 不满足条件，所以 i - last - 1才是真正的间隔数
	    		if (i - last - 1 > 0) {
	    			spaceNum = (L - count) / (i - last - 1);//代表一行平均间隔的空格数
	    			extraNum = (L - count) % (i - last - 1);//代表多出的空格数，要放入前面的m组单词里
	    		}

	    		//该行满足条件开始放word和空格
	    		StringBuilder str = new StringBuilder();
	    		for (int j = last; j < i; j++) {
	    			str.append(words[j]); //从words[last] 放到words[i - 1]

	    			//为什么设置j < i - 1, 因为j = i - 1的后面不能放空格 除非最后一行
	    			if (j < i - 1) {
	    				for (int k = 0; k < spaceNum; k++)
	    					str.append(" ");  // 一次在每个单词后面添加spaceNum个空格
	    				if (extraNum > 0)	  // extraNum是多出来的空格，从前到后平均增加，
	    								  	  // A___B_C_D是错的，应该是A__B__C_D
	    					str.append(" ");
	    				extraNum --; //减去额外的空格
	    			}
	    		}

	    		//该循环作用于一行只有一个单词还没填满一行的情况
	    		for (int j = str.length(); j < L; j++)
	    			str.append(" ");
	    		res.add(str.toString());

	    		count = 0;//为了下一行开始
	    		last = i;//下一个开始的单词下标
	    	}

	    	//假如加入words[i]还是小于L 就继续加
	    	count += words[i].length();// 累加单词的长度
    	}

    	//处理最后一行，注意到i从last开始
    	StringBuilder str = new StringBuilder();
    	for (int i = last; i < words.length; i++) {
    		str.append(words[i]);
    		if (str.length() < L)
    			str.append(" ");
    	}

    	//处理空字符，最后一行填充空字符
    	for (int i = str.length(); i < L; i++) 
    		str.append(" ");
    	res.add(str.toString());
    	return res;
    }
}

/*

*/
public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        //外层循环遍历所有的word
        for (int i = 0, w; i < words.length; i = w) {
            //len用来记录每一层字符的总长度
            int len = -1;//之所以初始化为1，是因为后面算len的时候每个word都自动包括1个空格的长度，而最后一个字符在非最后一行的时候是不需要加空格，-1就是为了中和最后一个字符的+1.
            //用w来遍历可以放入某一行的words的index
            //len + words[w].length() + 1 要 <= maxWidth
            for (w = i; w < words.length && len + words[w].length() + 1 <= maxWidth; w++) {
                len += words[w].length() + 1;// words[w].length 是word的长度， +1 是词后至少保留一个空格的长度
            }
            StringBuilder sb = new StringBuilder(words[i]);
            int space = 1;
            int extra = 0;
            if (w != i + 1 && w != words.length) {//not 1 char not last line
                //为什么这里space需要+1？因为我们在计算len的时候已经将每个有效范围内的单词都加上一个空格的长度
                //所以(maxWidth - len) / (w - i - 1) + 1 = maxWidth - len + w - i - 1     / w - i - 1, 
                //                                      = maxWidth - (len - (w - i - 1)) / w - i - 1,
                //我们注意到w - i - 1 是可以放空格的间隔， (len - (w - i - 1))才是字符除去空格后的真实长度。
                space = (maxWidth - len) / (w - 1 - i) + 1;
                extra = (maxWidth - len) % (w - 1 - i);
            }
            for (int j = i + 1; j < w; j++) {
                for (int s = space; s > 0; s--) {
                    sb.append(' ');
                }
                if (extra > 0) {
                    sb.append(' ');
                    extra--;
                }
                sb.append(words[j]);
            }
            int strLen = maxWidth - sb.length();
            while (strLen > 0) {
                sb.append(' ');
                strLen--;
            }
            res.add(sb.toString());
        }
        return res;
    }
}