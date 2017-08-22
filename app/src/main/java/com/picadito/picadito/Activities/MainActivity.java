package com.picadito.picadito.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.picadito.picadito.Activities.Displayers.FriendsDisplayable;
import com.picadito.picadito.Activities.Fragments.FriendsFragment;
import com.picadito.picadito.Activities.Fragments.UserFragment;
import com.picadito.picadito.Activities.Fragments.WallFragment;
import com.picadito.picadito.GUI.FriendGUI;
import com.picadito.picadito.GUI.UserGUI;
import com.picadito.picadito.Model.Match;
import com.picadito.picadito.Model.User;
import com.picadito.picadito.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class MainActivity extends AppCompatActivity implements FriendsDisplayable {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;


    private UserGUI userGUI;
    private User user;


    public User getUser() {
        return user;
    }

    public UserGUI getUserGUI() {
        return userGUI;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        user = (User) getIntent().getSerializableExtra("user");
        userGUI = user.getGUI();

    }

    private void setupViewPager(ViewPager viewPager){
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter.addFragment(new UserFragment(), "UserFragment");
        mSectionsPagerAdapter.addFragment(new WallFragment(), "WallFragment");
        mSectionsPagerAdapter.addFragment(new FriendsFragment(), "FriendsFragment");
        viewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public SortedSet<FriendGUI> getFriends() {
        return userGUI.getFriends();
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> fragmentsTitle = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            fragmentsTitle.add(title);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentsTitle.get(position);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
          if (requestCode == 1) {
                    // Make sure the request was successful
                    if (resultCode == RESULT_OK) {
                        user.setName(data.getStringExtra("newUserName"));
                user.setStatus(data.getStringExtra("newUserStatus"));
                userGUI = user.getGUI();
                try {
                    ((UserFragment)mSectionsPagerAdapter.getItem(0)).displayContent();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (requestCode == 2) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                user.addMatch((Match) data.getSerializableExtra("match"));
                userGUI = user.getGUI();

            }
        }
    }
}
