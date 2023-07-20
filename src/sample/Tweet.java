package sample;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tweet implements Comparable<Tweet>
{
    private String tweet;
    private String id;
    private String date;
    private String time;
    private int likes = 0;

    // users those have liked this tweet
    private ArrayList<String> names = new ArrayList<>();

    public ArrayList<String> getNames()
    {
        return names;
    }

    /**
     * this func likes tweet and saves user's name who has liked this tweet
     * @param name
     */
    public void like(String name)
    {
        likes++;
        names.add(name);
    }

    public String getLikes()
    {
        return Integer.toString(likes);
    }

    public String getTweet()
    {
        return tweet;
    }

    public String getId()
    {
        return id;
    }

    public String getDate()
    {
        return date;
    }

    public String getTime()
    {
        return time;
    }

    /**
     * this constructor is used when data is read from data base
     * @param tweet
     * @param id
     * @param date
     * @param time
     * @param likes
     */
    public Tweet(String tweet, String id, String date, String time, String likes)
    {
        this.tweet = tweet;
        this.id = id;
        this.date = date;
        this.time = time;
        this.likes = Integer.parseInt(likes);
    }

    /**
     * this constructor is used when a new tweet creates
     * @param tweet
     */
    public Tweet(String tweet)
    {
        this.tweet = tweet;
        newID();
        date = LocalDate.now().toString();
        time = LocalTime.now().toString();
    }

    /**
     * this func creates an id from 1 digit until 8 digits
     */
    private void newID()
    {
        int tmp = 0;
        Random random = new Random();

        for(int i = 0; i < 8; i++)
        {
            tmp = (10 * tmp) + random.nextInt(10);
        }

        if(!checkUnique(tmp))
            newID();

        id = Integer.toString(tmp);

    }

    /**
     * this func checks if id is uses before or not
     * @param id
     */
    private boolean checkUnique(int id)
    {
        for (UserDatas userData : Main.userDatas)
        {
            if(userData.getUserTweets().contains(id))
            {
                return false;
            }
        }

        return true;
    }


    /**
     * this func is used for Collections.sort() method
     * @param o from Tweet Class
     */
    @Override
    public int compareTo(Tweet o)
    {
        if(this.date.compareTo(o.date) > 0)
        {
            return 1;
        }

        else if(this.date.compareTo(o.date) < 0)
        {
            return -1;
        }

        else if(this.date.compareTo(o.date) == 0)
        {
            if(this.time.compareTo(o.time) > 0)
            {
                return 1;
            }

            else if(this.time.compareTo(o.time) < 0)
            {
                return -1;
            }
        }

        return 0;
    }

    /**
     * this func is used for contains() method in ArrayList
     * @param obj
     */
    @Override
    public boolean equals(Object obj)
    {
        return this.id.equals(((Tweet)obj).id);
    }
}
