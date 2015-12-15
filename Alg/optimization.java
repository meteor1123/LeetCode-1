1. First Algorithm 
    Shortest Job First : 
        Selecting the shortest insert job every time, if has same len, select the highest weight value one.
    Highest Location Weight Value First && : 
        Insert the job to location base on the location weight, from high to low.
    Most Fit Len:
        When insert a job to one location, find the most fit interval to insert.
        
    Advantages: 
    Disadvantages: the shortest content isn't the highest weight value content, and occupy the most part of the high weight                     location
    
2. Second Algorithm
   Highest Weight Value Job first:
        Every time select the highest weight value job first, if has same weight value select the shortest one.
   Highest Location Weight Value First &&:
        Insert the job to location base on the location weight, from high to low.
   Most Fit Len:
        When insert a job to one location, find the most fit interval to insert.
   Advantages: 
   Disadvantages: the highest weight value content maybe occupy many intervals, lead to some content can't not be inserted
        
3. Third Algorithm
    Averge (Weight Value / Len) Highest Job first:
        We notice that, even a Job has high weight, if we insert this job, still will get the low value, 
        for example, a content has 10 weight value, but the len of this content is 5, that is mean we only get 2 score per 1 len. 
        On the contrary if another job's weight value is just 4, but the len is 1, that is mean we can get 4 score per 1 len. So I have a idea,           every time we get the content from highest (weight value / len).
    Highest Location Weight Value First &&: same with above
    Most Fit Len: same with above
    
    Advantages: 
    Disadvantages: 



Question: 
     1. If there is not content in the location, can the content insert to this location?If yes, how is the startTime of this inserted content?
     2. If there is only one content in a location, can this location still insert content?If yes, there will introduce a issue, we can just              insert all the content to the highest value location, if no suitable interval, just insert to the end of this queue,
     3. 