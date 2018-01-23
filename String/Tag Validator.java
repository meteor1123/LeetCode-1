/*
	Tag Validator

	Given a string representing a code snippet, you need to implement a tag validator to parse the code and return whether it is valid. A code snippet is valid if all the following rules hold:

	The code must be wrapped in a valid closed tag. Otherwise, the code is invalid.
	A closed tag (not necessarily valid) has exactly the following format : <TAG_NAME>TAG_CONTENT</TAG_NAME>. Among them, <TAG_NAME> is the start tag, and </TAG_NAME> is the end tag. The TAG_NAME in start and end tags should be the same. A closed tag is valid if and only if the TAG_NAME and TAG_CONTENT are valid.
	A valid TAG_NAME only contain upper-case letters, and has length in range [1,9]. Otherwise, the TAG_NAME is invalid.
	A valid TAG_CONTENT may contain other valid closed tags, cdata and any characters (see note1) EXCEPT unmatched <, unmatched start and end tag, and unmatched or closed tags with invalid TAG_NAME. Otherwise, the TAG_CONTENT is invalid.
	A start tag is unmatched if no end tag exists with the same TAG_NAME, and vice versa. However, you also need to consider the issue of unbalanced when tags are nested.
	A < is unmatched if you cannot find a subsequent >. And when you find a < or </, all the subsequent characters until the next > should be parsed as TAG_NAME (not necessarily valid).
	The cdata has the following format : <![CDATA[CDATA_CONTENT]]>. The range of CDATA_CONTENT is defined as the characters between <![CDATA[ and the first subsequent ]]>.
	CDATA_CONTENT may contain any characters. The function of cdata is to forbid the validator to parse CDATA_CONTENT, so even it has some characters that can be parsed as tag (no matter valid or invalid), you should treat it as regular characters.
	Valid Code Examples:
	Input: "<DIV>This is the first line <![CDATA[<div>]]></DIV>"

	Output: True

	Explanation: 

	The code is wrapped in a closed tag : <DIV> and </DIV>. 

	The TAG_NAME is valid, the TAG_CONTENT consists of some characters and cdata. 

	Although CDATA_CONTENT has unmatched start tag with invalid TAG_NAME, it should be considered as plain text, not parsed as tag.

	So TAG_CONTENT is valid, and then the code is valid. Thus return true.


	Input: "<DIV>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>"

	Output: True

	Explanation:

	We first separate the code into : start_tag|tag_content|end_tag.

	start_tag -> "<DIV>"

	end_tag -> "</DIV>"

	tag_content could also be separated into : text1|cdata|text2.

	text1 -> ">>  ![cdata[]] "

	cdata -> "<![CDATA[<div>]>]]>", where the CDATA_CONTENT is "<div>]>"

	text2 -> "]]>>]"


	The reason why start_tag is NOT "<DIV>>>" is because of the rule 6.
	The reason why cdata is NOT "<![CDATA[<div>]>]]>]]>" is because of the rule 7.
	Invalid Code Examples:
	Input: "<A>  <B> </A>   </B>"
	Output: False
	Explanation: Unbalanced. If "<A>" is closed, then "<B>" must be unmatched, and vice versa.

	Input: "<DIV>  div tag is not closed  <DIV>"
	Output: False

	Input: "<DIV>  unmatched <  </DIV>"
	Output: False

	Input: "<DIV> closed tags with invalid tag name  <b>123</b> </DIV>"
	Output: False

	Input: "<DIV> unmatched tags with invalid tag name  </1234567890> and <CDATA[[]]>  </DIV>"
	Output: False

	Input: "<DIV>  unmatched start tag <B>  and unmatched end tag </C>  </DIV>"
	Output: False
	Note:
	For simplicity, you could assume the input code (including the any characters mentioned above) only contain letters, digits, '<','>','/','!','[',']' and ' '.
*/

/*
	Summarizing the given problem, we can say that we need to determine whether a tag is valid or not, by checking the following properties.

	The code should be wrapped in valid closed tag.

	The TAG_NAME should be valid.

	The TAG_CONTENT should be valid.

	The cdata should be valid.

	All the tags should be closed. i.e. each start-tag should have a corresponding end-tag and vice-versa and the order of the tags should be correct as well.

	1. 先check "<![CDATA[" 再到 "</" 再到 ">" 是关键
*/


class Solution {
    public boolean isValid(String code) {
        Stack<String> stack = new Stack();
        for (int i = 0; i < code.length(); ) {
            if (i > 0 && stack.isEmpty())
                return false;
            if (code.startsWith("<![CDATA[", i)) {
                int j = i + 9;
                i = code.indexOf("]]>", j);
                if (i < 0) 
                    return false;
                i += 3;
            } else if (code.startsWith("</", i)) {
                int j = i + 2;
                i = code.indexOf('>', j);
                String s = code.substring(j, i++);
                if (stack.isEmpty() || !stack.pop().equals(s))
                    return false;                
            } else if (code.startsWith("<", i)) {
                int j = i + 1;
                i = code.indexOf(">", j);
                if (i < 0 || i == j || i - j > 9)  // i == j is for the case "<>""
                    return false;
                for (int k = j; k < i; k++) {
                    if(!Character.isUpperCase(code.charAt(k)))
                        return false;
                }
                String s = code.substring(j, i++);
                stack.push(s);
            } else {
                i++;
            }
        }
        return stack.isEmpty();
    }
}