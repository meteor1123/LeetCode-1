public int ladderLength(String start, String end, HashSet<String> dict) {
        if(start==null || end==null || start.length()==0 || end.length()==0 || start.length()!=end.length())  
        return 0; 
        
        LinkedList<String> wordQueue = new LinkedList<String>();
        int level = 1; //the start string already count for 1
        int curnum = 1;//the candidate num on current level
        int nextnum = 0;//counter for next level
        
        wordQueue.add(start);
        
        while(!wordQueue.isEmpty()){
            String word = wordQueue.poll();
            curnum--;
            
            for(int i = 0; i < word.length(); i++){
                char[] wordunit = word.toCharArray();
                for(char j = 'a'; j <= 'z'; j++){
                    wordunit[i] = j;
                    String temp = new String(wordunit);  
                    
                    if(temp.equals(end))
                        return level+1;
                    if(dict.contains(temp)){
                        wordQueue.add(temp);
                        nextnum++;
                        dict.remove(temp);
                    }
                }
            }
            
            if(curnum == 0){
                curnum = nextnum;
                nextnum = 0;
                level++;
            }
        }
        
        return 0;
    }