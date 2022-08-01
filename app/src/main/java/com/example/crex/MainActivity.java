package com.example.crex;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private TabLayout mainTabLayout;
    private ViewPager viewPager;
    //private MatchListFragment upcomingMatchFragment;
    //private MatchListFragment finishedMatchFragment;
    private UpcomingFragment upcomingMatchFragment;
    private FinishedFragment finishedMatchFragment;
    private UpcomingFragmentFirebase upcomingFragmentFirebase;
    private FinishedFragmentFirebase finishedFragmentFirebase;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private static final String[] tabTitles = new String[]{"Upcoming", "Finished"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);
        setupTabsInTabLayout();
        setupViewPager(viewPager);
        mainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mainTabLayout));
    }
    private void setupTabsInTabLayout() {
        TabLayout.Tab upcomingTab = mainTabLayout.newTab();
        upcomingTab.setText("Upcoming");
        TabLayout.Tab finishedTab = mainTabLayout.newTab();
        finishedTab.setText("Finished");
        mainTabLayout.addTab(upcomingTab);
        mainTabLayout.addTab(finishedTab);
    }
    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
        @Override
        public int getCount() {
            return fragmentList.size();
        }
        public void addFragment(Fragment fragment) {
            fragmentList.add(fragment);
            notifyDataSetChanged();
        }
    }
    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        upcomingMatchFragment = new UpcomingFragment();
        finishedMatchFragment = new FinishedFragment();
        //viewPagerAdapter.addFragment(upcomingMatchFragment);
        //viewPagerAdapter.addFragment(finishedMatchFragment);
        upcomingFragmentFirebase = new UpcomingFragmentFirebase();
        finishedFragmentFirebase = new FinishedFragmentFirebase();
        viewPagerAdapter.addFragment(upcomingFragmentFirebase);
        viewPagerAdapter.addFragment(finishedFragmentFirebase);
        viewPager.setAdapter(viewPagerAdapter);
    }
}












