/*
	Anagrams
	Given an array of strings, return all groups of strings that are anagrams.
	Note: All inputs will be in lower-case.
	Tags:HashTable String
*/


public class Solution {
	    public ArrayList<String> anagrams(String[] strs) {
        ArrayList<String> result = new ArrayList<String>();
        
        if(strs == null || strs.length == 0)
            return result;
        HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
        for (String s:strs) {
            char[] temp = s.toCharArray();
            Arrays.sort(temp);//排序该字符数组，keypoint
            String tempStr = new String(temp);
            
            if (hm.containsKey(tempStr)) {
                if (hm.get(tempStr).size() == 1)//为什么要有这个判断，只有当hashmap里已经存放有某项值，必须找到与其排序值相等的字符时，才能将其加入result                                                                        
                    result.add(hm.get(tempStr).get(0));//两次result.add操作，一次是原来的，一次是新加入的
                hm.get(tempStr).add(s);
                result.add(s);
            } else {
                ArrayList<String> tempList = new ArrayList<String>();//新建一个ArrayList 存放s
                tempList.add(s);
                hm.put(tempStr, tempList);
            }
        }
        return result;
    }
}

