public int execTime(int[] task, int N) {
    if(task==null || task.length==0)
        return 0;
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    int res = 0;
    int i =0;
    while(i < task.length) {
        if(!map.containsKey(task[i])) {
            map.put(task[i], i + res);
            i++;
        }else {
            int index = map.get(task[i]);
            if(i + res - index <= N) {
                res++;
            } else {
                map.put(task[i],i + res);
                i++;
            }
        }
    }
    return i + res;
}