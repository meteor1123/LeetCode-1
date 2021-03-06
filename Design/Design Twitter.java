/*
	355. Design Twitter

	Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

	postTweet(userId, tweetId): Compose a new tweet.
	
	getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
	
	follow(followerId, followeeId): Follower follows a followee.
	
	unfollow(followerId, followeeId): Follower unfollows a followee.

	Example:

		Twitter twitter = new Twitter();

		// User 1 posts a new tweet (id = 5).
		twitter.postTweet(1, 5);

		// User 1's news feed should return a list with 1 tweet id -> [5].
		twitter.getNewsFeed(1);

		// User 1 follows user 2.
		twitter.follow(1, 2);

		// User 2 posts a new tweet (id = 6).
		twitter.postTweet(2, 6);

		// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
		// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
		twitter.getNewsFeed(1);

		// User 1 unfollows user 2.
		twitter.unfollow(1, 2);

		// User 1's news feed should return a list with 1 tweet id -> [5],
		// since user 1 is no longer following user 2.
		twitter.getNewsFeed(1);
*/

/*
		1. Create Tweet Object with properties 
			  {id, timeStamp, next}
		2. Create User Object with properties 
				{id, followed, head} 
					followed -> HashSet, contains  allfollowers user id
					head     -> the newest tweet
		

*/

class Twitter {
    private static int timeStamp = 0;
    
    private Map<Integer, User> userMap;
    
    private class Tweet {
        public int id;
        public int time;
        public Tweet next;
        
        public Tweet(int id) {
            this.id = id;
            time = timeStamp++;
            next = null;
        }
    }
    
    private class User {
        public int id;
        public Set<Integer> followed;
        public Tweet head;
        
        public User(int id) {
            this.id = id;
            followed = new HashSet();
            follow(id);
            head = null;
        }
        
        public void follow(int id) {
            followed.add(id);
        }
        
        public void unfollow(int id) {
            followed.remove(id);
        }
        
        public void post(int id) {
            Tweet t = new Tweet(id);
            t.next = head;
            head = t;
        }
    }
    
    /** Initialize your data structure here. */
    public Twitter() {
        userMap = new HashMap();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            User user = new User(userId);
            userMap.put(userId, user);
        }
        userMap.get(userId).post(tweetId);
    }
    
    // Best part of this.
    // first get all tweets lists from one user including itself and all people it followed.
    // Second add all heads into a max heap. Every time we poll a tweet with 
    // largest time stamp from the heap, then we add its next tweet into the heap.
    // So after adding all heads we only need to add 9 tweets at most into this 
    // heap before we get the 10 most recent tweet.
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new LinkedList();
        if (!userMap.containsKey(userId))
            return res;
        Set<Integer> users = userMap.get(userId).followed;
        PriorityQueue<Tweet> pq = new PriorityQueue<Tweet>((a1, b1) -> (b1.time - a1.time));
        
        for (int uid : users) {
            Tweet tweet = userMap.get(uid).head;
            if (tweet != null) {
                pq.offer(tweet);
            }
        }
        int n = 0;
        while (!pq.isEmpty() && n < 10) {
            Tweet tweet = pq.poll();
            res.add(tweet.id);
            n++;
            if (tweet.next != null) {
                pq.add(tweet.next);
            }
        }
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            User user = new User(followerId);
            userMap.put(followerId, user);
        }
        if (!userMap.containsKey(followeeId)) {
            User user = new User(followeeId);
            userMap.put(followeeId, user);
        }
        
        userMap.get(followerId).follow(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId) || followerId == followeeId)
            return;
        userMap.get(followerId).unfollow(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */