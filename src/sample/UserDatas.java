package sample;

import java.util.ArrayList;

public class UserDatas
{
    private String username, password, bio;

    // user's tweets
    private ArrayList<Tweet> userTweets = new ArrayList<>();

    // following names
    private ArrayList<String> following = new ArrayList<>();

    // followers names
    private ArrayList<String> followers = new ArrayList<>();

    private ArrayList<UserDatas> followerUsers = new ArrayList<>();
    private ArrayList<UserDatas> followingUsers = new ArrayList<>();

    public void setFollowerUsers()
    {
        for (String follower : followers)
        {
            for (UserDatas userData : Main.userDatas)
            {
                if(follower.equals(userData.username))
                {
                    followerUsers.add(userData);
                }
            }
        }
    }

    public void setFollowingUsers()
    {
        for (String s : following)
        {
            for (UserDatas userData : Main.userDatas)
            {
                if(s.equals(userData.username))
                {
                    followingUsers.add(userData);
                }
            }
        }
    }

    public ArrayList<UserDatas> getFollowerUsers()
    {
        return followerUsers;
    }

    public ArrayList<UserDatas> getFollowingUsers()
    {
        return followingUsers;
    }

    public ArrayList<String> getFollowing() {
        return following;
    }

    public ArrayList<String> getFollowers()
    {
        return followers;
    }

    public ArrayList<Tweet> getUserTweets()
    {
        return userTweets;
    }

    public UserDatas(String username, String password, String bio)
    {
        this.username = username;
        this.password = password;
        this.bio = bio;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getBio()
    {
        return bio;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }

    @Override
    public boolean equals(Object obj)
    {
        return this.username.equals(((UserDatas)obj).username);
    }
}
