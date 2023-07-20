package sample;

import controllers.MouseController;
import controllers.PagesController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Main extends Application
{
    /**
     * keeping data from data base
     * public access for using it in each class
     */
    public static ArrayList<UserDatas> userDatas;

    public static void main(String[] args) throws Exception
    {
        launch(args);
    }

    @Override
    public void init()
    {
        userDatas = new ArrayList<>();

        try
        {
            /**
             * making a connection and a statement
             */
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/userdatas?user=root";
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();

            String query = "select * from user";
            ResultSet resultSet = statement.executeQuery(query);

            /**
             * filling userDatas ArrayList
             */
            while(resultSet.next())
            {
                String[] tweets, ids, dates, times, likes_names, followers, following;

                UserDatas userData = new UserDatas(resultSet.getString("userName"),
                        resultSet.getString("password"), resultSet.getString("bio"));

                try
                {
                    tweets = resultSet.getString("Tweets").split("<flag>");
                    ids = resultSet.getString("Ids").split("<flag>");
                    dates = resultSet.getString("Dates").split("<flag>");
                    times = resultSet.getString("Times").split("<flag>");
                    likes_names = resultSet.getString("Likes").split("<flag>");


                    for(int i = 0; i < tweets.length; i++)
                    {
                        Tweet newTweet = null;

                        if(likes_names[0].contains("<nameFlag>"))
                        {
                            newTweet = new Tweet(tweets[i], ids[i], dates[i], times[i],
                                    likes_names[i].split("<nameFlag>")[0]);

                            for (int j = 1; j < likes_names[i].split("<nameFlag>").length; j++)
                            {
                                newTweet.getNames().add(likes_names[i].split("<nameFlag>")[j]);
                            }
                        }

                        else
                        {
                            newTweet = new Tweet(tweets[i], ids[i], dates[i], times[i], likes_names[i]);
                        }

                        userData.getUserTweets().add(newTweet);
                    }

                }

                catch (Exception e)
                {
                }

                try
                {
                    followers = resultSet.getString("Followers").split("<flag>");

                    for(int i = 0; i < followers.length; i++)
                    {
                        userData.getFollowers().add(followers[i]);
                    }
                }

                catch(Exception e)
                {
                }

                try
                {
                    following = resultSet.getString("Following").split("<flag>");

                    for(int i = 0; i < following.length; i++)
                    {
                        userData.getFollowing().add(following[i]);
                    }

                }

                catch(Exception e)
                {
                }

                userDatas.add(userData);
            }

            statement.close();
            connection.close();
        }

        catch(Exception e)
        {
        }
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        PagesController.openPage("welcoming menu");
    }
}