package com.picadito.picadito.Activities;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.picadito.picadito.Activities.Displayers.FriendsDisplayable;
import com.picadito.picadito.Activities.Displayers.MatchesDisplayable;
import com.picadito.picadito.Activities.Fragments.FriendFragment;
import com.picadito.picadito.Activities.Fragments.FriendMatchesFragment;
import com.picadito.picadito.Activities.Fragments.FriendsFragment;
import com.picadito.picadito.GUI.FriendGUI;
import com.picadito.picadito.GUI.MatchGUI;
import com.picadito.picadito.R;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class FriendMainActivity extends AppCompatActivity implements FriendsDisplayable, MatchesDisplayable {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private FriendGUI friendGUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_main);

        friendGUI = (FriendGUI) getIntent().getSerializableExtra("friend");

        mSectionsPagerAdapter = new FriendMainActivity.SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R .id.friendMainActivity_containerViewPager);
        setupViewPager(mViewPager);



    }




    private void setupViewPager(ViewPager viewPager){
        FriendMainActivity.SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.addFragment(new FriendFragment(), "friendFragment");
         mSectionsPagerAdapter.addFragment(new FriendsFragment(), "friendsFragment");
        mSectionsPagerAdapter.addFragment(new FriendMatchesFragment(), "friendMatchesFragment");
        viewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public SortedSet<FriendGUI> getFriends() {
        return friendGUI.getFriends();
    }

    public FriendGUI getFriendGUI(){
        return friendGUI;
    }

    @Override
    public SortedSet<MatchGUI> getMatches() {
        return friendGUI.getMatcheses();
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
}
