package wtsc.letsplay10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnUserDataLoaded, OnSportsDataLoaded {

    // instance of the GetCurrentUser utility functions to get the user data from the database
    static GetCurrentUser getUser;
    private User currentUser;       // stores the current user object
    private GetSportsList getSportsList;
    private List<Sport> allSportsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentUser = new User();       // initiate currentUser object
        getUser = new GetCurrentUser(MainActivity.this);   // initiate GetCurrentUser object
        // executes AsyncTask in the getUser object - Pass the email address of user to load
      //  getUser.execute("rstambach@my.waketech.edu","LOAD");

        getUser.execute("VERIFY","rstambach1@my.waketech.edu");

       // getSportsList = new GetSportsList(MainActivity.this);
       // getSportsList.execute();

    }


    @Override
    public void onCurrentUserDataLoaded(User user) {

        currentUser = user;     // sets the currentUser object

        // the following sets textviews to display the currentUser and is
        // only a example to be deleted later
        TextView tv = (TextView)findViewById(R.id.userIDTextView);
        tv.setText(Integer.toString(currentUser.getID()));

        tv = (TextView)findViewById(R.id.userFNameTextView);
        tv.setText(currentUser.getFirstName());

        tv = (TextView)findViewById(R.id.userLNameTextView);
        tv.setText(currentUser.getLastName());

        tv = (TextView)findViewById(R.id.userGameNameTextView);
        tv.setText(currentUser.getGameName());

        tv = (TextView)findViewById(R.id.userPasswordTextView);
        tv.setText(currentUser.getPassword());

        tv = (TextView)findViewById(R.id.userEmailTextView);
        tv.setText(currentUser.getEmail());
        // end of example
    }

    @Override
    public void onUserVerify(User user) {
        if(user == null) {
            getUser = new GetCurrentUser(MainActivity.this);
            getUser.execute("ADD_NEW","Ricky","Stambach","gnameTest1",
                            "123456","rstambach1@my.waketech.edu");
        }
    }

    @Override
    public void onNewUserAdded(User user) {
        String gn = user.getGameName();
        getSportsList = new GetSportsList(MainActivity.this);
        getSportsList.execute();
    }

    @Override
    public void onSportsDataLoaded(List<Sport> sports) {
        int i = sports.size();
    }
}
