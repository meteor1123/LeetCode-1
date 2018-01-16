1. Solution
    1.1 Define a HashMap<String, List<ScheduleRequest>> idScheduleMap,
        the Key is content id, the value is the location list of same id content
    1.2 定义一个idArr数组，长度为所有不同id的content个数，将所有的content id存进去
        作用是用来遍历所有content的location。
    1.3 对HashMap的所有value List 进行dfs，寻找一切可能的组合，我们注意到同一个id的content，在一个时刻
        只能出现在一个location，这就是我们为什么用id作为key，content location作为value的原因，这样我们每一个
        id只会选取一次。
        
        public static void dfs(List<List<ScheduleRequest>> res, List<ScheduleRequest> item, String[] idArr,               int index, HashMap<String, List<ScheduleRequest>> idScheduleMap) {
            if (index == idArr.length) {
                res.add(new ArrayList<>(item));
                return;
            }
            String contentId = idArr[index];
            for (int i = 0; i < idScheduleMap.get(contentId).size(); i++) {
                ScheduleRequest ad = idScheduleMap.get(contentId).get(i);
                if (!checkSameIdContent(item, ad)) {
                    continue;
                } else {
                  item.add(ad);
                  dfs(res, item, idArr, index + 1, idScheduleMap);        
                  item.remove(item.size() - 1);
                }     
            }
        }
        
        //Use to check Same Id Content, we only retain unique id content
        public static boolean checkSameIdContent(List<ScheduleRequest> list, ScheduleRequest sr1) {
            for (ScheduleRequest sr : list) {
              if (sr.getId().equals(sr1.getId())) {
                return false;
              }
            }
            return true;
        }
  //Since when we using dfs, if two different id have same location,there will lead to duplicate location content, how to avoid that, we just compare all the same location value, and select the maximum weight value from the contentScoreMap.
  public static List<List<ScheduleRequest>> removeDuplicate(List<List<ScheduleRequest>> selectionListRes,           final HashMap<String, Integer> contentScoreMap) {
        List<List<ScheduleRequest>> res = new ArrayList<>();
        for (List<ScheduleRequest> list : selectionListRes) {
          HashMap<String, ScheduleRequest> map = new HashMap<>();
          for (ScheduleRequest req : list) {
            if (!map.containsKey(req.getLocation())) {
              map.put(req.getLocation(), req);
            } else {
              if (contentScoreMap.get(req.getId()) >= contentScoreMap.get(map.get(req.getLocation()).getId())) {
                map.put(req.getLocation(), req);
              }
            }
          }
          List<ScheduleRequest> newRes = new ArrayList<>();
          for (ScheduleRequest sr : map.values()) {
            newRes.add(sr);
          }
          res.add(newRes);
        }
        return res;
  }
  
  