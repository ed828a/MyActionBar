package com.ed828a.myactionbar;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set ActionBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        myToolbar.setTitleTextColor(Color.RED);
        setSupportActionBar(myToolbar);

        SpannableString title = new SpannableString("NewTube");
        title.setSpan(new TypefaceSpan(this, "DroidSerif-Bold.ttf"),
                 0, title.length(),
                 Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setTitle(title);
        myActionBar.setIcon(R.drawable.ic_play_circle_filled_red_24dp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Create customized menu layout from menu folder.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        // Get the MenuItem for the action item
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        // Configure the search info and add any event listeners...
        // get the text entered in search in action bar to a string
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (null != searchView) {
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(true);
//            searchView.setSubmitButtonEnabled(true);

        }
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered
//                Toast.makeText(getApplicationContext(),
//                        "Typing Input text!", Toast.LENGTH_SHORT).show();
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                //Here u can get the value "query" which is entered in the search box.
                Toast.makeText(getApplicationContext(),
                        "Input text submitted.", Toast.LENGTH_SHORT).show();
                // Collapse searchView
//                if (searchView != null) {
//                    searchView.onActionViewCollapsed();
//                }
                searchItem.collapseActionView(); //better than above

                return  true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);


        // Define the listener
        MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when action item collapses
                Toast.makeText(getApplicationContext(),
                        "Collapsed!", Toast.LENGTH_SHORT).show();
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                Toast.makeText(getApplicationContext(),
                        "Item is expended.", Toast.LENGTH_SHORT).show();


                return true;  // Return true to expand action view
            }
        };

        // Assign the listener to an action item
        searchItem.setOnActionExpandListener(expandListener);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.action_search:
                super.onOptionsItemSelected(item);
                // customize the search view
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
