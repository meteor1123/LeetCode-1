1.Question:
    (1)Is there any unvalid input such as the end time is no larger than start time?
    (2)Does a content will appear more than once?If yes, if they in same location, how to define which one is valid?, if they in different  location, if they are all valid in their location, will it work?
    (3)How to define the valid sequence? Which content come first and the later content if has overlap, so it will be unvalid content?

2. Solution1: 
    1. 首先对输入进行筛选，只有valid的time(startTime < endTime)才可以加入List
    2. 构造一个HashMap<String, List<ScheduleRequest>> locationMap; key是location， value是所有同一个location的schedule
    3. 创建一个comparator，按照startTime，从小到大排序
    4. 遍历locationMap的values：
      
      for (String location : locationMap.keySet()) {
        HashSet<String> set = new HashSet<>();//用来去重同一个地区的多个相同id的content
        List<ScheduleRequest> sameLocationReq = locationMap.get(location);
        Collections.sort(sameLocationReq, comp);//按照startTime 排序
        int index = 0;
        ScheduleRequest pre = sameLocationReq.get(index);
        set.add(pre.getId());
        index++;
        /*
           将相邻的两个content进行对比，因为按照start进行排序，只要对比cur的startTime是否大于pre的                                                            endTime,(1)如果不大于，则是冲突的schedule，加入unValidReq List,并从原先的List里remove掉这个                                                     schedule。(2)如果大于，则说明是一个有效的schedule，将其Id加入set记录以避免以后的重复，并将pre = cur,inde++，进行下一次对比
        */
        while (index < sameLocationReq.size()) {
          ScheduleRequest cur = sameLocationReq.get(index);
          if (set.contains(cur.getId()) || !checkTwoReqTime(pre, cur)) {
            unValidReq.add(cur);
            sameLocationReq.remove(cur);
          } else {
            set.add(cur.getId());
            pre = cur;
            index++;
          } 
        }
      }



