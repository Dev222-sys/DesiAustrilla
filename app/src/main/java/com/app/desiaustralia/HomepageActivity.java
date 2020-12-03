package com.app.desiaustralia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.app.desiaustralia.ui.home.DesiTVFragment;
import com.app.desiaustralia.ui.home.EMagazineFragment;
import com.app.desiaustralia.ui.home.ENewsletterFragment;
import com.app.desiaustralia.ui.home.HomeFragment;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomepageActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private ExpandableListView expListView;
    private HashMap<String, List<String>> listDataChild;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    View view_Group;
    private DrawerLayout mDrawerLayout;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.open();
            }
        });
        setUpDrawer();

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.navigation_notifications);
        loadFragment(new HomeFragment());


        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {

            int id = menuItem.getItemId();
            Fragment fragment = null;

            if (id == R.id.navigation_desiTv) {

                    fragment = new DesiTVFragment();
                toolbar.setTitle(getString(R.string.desi_tv));
            }
            else if (id == R.id.navigation_notifications) {
                fragment = new HomeFragment();
                toolbar.setTitle(getString(R.string.news));
            }
            else if (id == R.id.navigation_newsletter) {
                fragment = new ENewsletterFragment();
                toolbar.setTitle(getString(R.string.e_news_letter));
            }
            else if (id == R.id.navigation_magazine) {
                fragment = new EMagazineFragment();
                toolbar.setTitle(getString(R.string.e_magazine));
            }
            return loadFragment(fragment);
        });


    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Home");
        listDataHeader.add("Desi TV");
        listDataHeader.add("Events");
        listDataHeader.add("Desi Cinema");
        listDataHeader.add("News");
        listDataHeader.add("e-Magazine/Newsletter");
        listDataHeader.add("Mag Corner");
        listDataHeader.add("Life Style");
        listDataHeader.add("Legal/Finance");
        listDataHeader.add("Food");
        listDataHeader.add("Arts/Technology");
        listDataHeader.add("Biz Directory");

        ArrayList<String> home = new ArrayList<>();
        home.add("Home");
        ArrayList<String> desiTv = new ArrayList<>();
        desiTv.add("Desi TV");
        ArrayList<String> events = new ArrayList<>();
        events.add("Events Calendar");
        events.add("Events Gallery");
        events.add("Promote your events For Free");
        events.add("Promoters Spot");

        ArrayList<String> desiCinema = new ArrayList<>();
        desiCinema.add("Upcoming Movies");
        desiCinema.add("Top 10 Bollywood Songs");
        desiCinema.add("First Day First Show");
        ArrayList<String> news = new ArrayList<>();
        news.add("Amazing Images");
        news.add("Australia News");
        news.add("Bollywood News");
        news.add("Community News");
        news.add("Desi News");
        news.add("Sports News");
        news.add("World News");
        news.add("Hollywood News");
        news.add("NSW Police Updates");
        news.add("News Desk");
        ArrayList<String> eMagazine = new ArrayList<>();
        eMagazine.add("Monthly e-Magazine");
        eMagazine.add("Weekly e-Magazine");
        eMagazine.add("First Day First Show");
        ArrayList<String> magCorner = new ArrayList<>();
        magCorner.add("Beauty");
        magCorner.add("Fashion");
        magCorner.add("Mag Corner");
        magCorner.add("View Point");

        ArrayList<String> lifeStyle = new ArrayList<>();
        lifeStyle.add("Art of living");
        lifeStyle.add("Astrology");
        lifeStyle.add("Horoscope");
        lifeStyle.add("Life Style");
        lifeStyle.add("Travel");
        lifeStyle.add("Yoga");

        ArrayList<String> legalFinance = new ArrayList<>();
        legalFinance.add("Legal");
        legalFinance.add("Migration");
        legalFinance.add("Mortgage");
        legalFinance.add("Properties");

        ArrayList<String> food = new ArrayList<>();
        food.add("Receipe of the week");
        food.add("Discount food coupons");

        ArrayList<String> artsTech = new ArrayList<>();
        artsTech.add("Technology");
        artsTech.add("Arts");

        ArrayList<String> bizDirectory = new ArrayList<>();
        bizDirectory.add("Business Directory");

        listDataChild.put(listDataHeader.get(0), home);
        listDataChild.put(listDataHeader.get(1), desiTv);
        listDataChild.put(listDataHeader.get(2), events);
        listDataChild.put(listDataHeader.get(3), desiCinema);
        listDataChild.put(listDataHeader.get(4), news);
        listDataChild.put(listDataHeader.get(5), eMagazine);
        listDataChild.put(listDataHeader.get(6), magCorner);
        listDataChild.put(listDataHeader.get(7), lifeStyle);
        listDataChild.put(listDataHeader.get(8), legalFinance);
        listDataChild.put(listDataHeader.get(9), food);
        listDataChild.put(listDataHeader.get(10), artsTech);
        listDataChild.put(listDataHeader.get(11), bizDirectory);

    }

    private void setUpDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*
         * mDrawerLayout.setScrimColor(getResources().getColor(
         * android.R.color.transparent));
         */
//        mDrawerLayout.setDrawerListener(mDrawerListener);
        expListView = (ExpandableListView) findViewById(R.id.list_slidermenu);
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader,
                listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        // expandable list view click listener

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                v.setSelected(true);
                Fragment fragment = null;
                switch (groupPosition) {
                    case 0: {
                        fragment = new HomeFragment();
                        mDrawerLayout.close();
                        toolbar.setTitle(getString(R.string.news));
                        loadFragment(fragment);
                        bottomNavigationView.setSelectedItemId(R.id.navigation_notifications);
                        break;
                    }
                    case 1: {
                        fragment = new DesiTVFragment();
                        mDrawerLayout.close();
                        toolbar.setTitle(getString(R.string.desi_tv));
                        loadFragment(fragment);
                        bottomNavigationView.setSelectedItemId(R.id.navigation_desiTv);
                        break;
                    }
                    // my profile
                    case 2:
                        switch (childPosition) {
                            case 0: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "eventscalendar");
                                intent.putExtra("title", "EVENT CALENDAR");
                                startActivity(intent);
                                break;
                            }
                            case 1: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "eventsgallery");
                                intent.putExtra("title", "EVENT GALLERY");
                                startActivity(intent);
                                break;
                            }
                            default:
                                break;
                        }
                        break;

                    case 3:
                        switch (childPosition) {

                            case 0: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "");
                                intent.putExtra("title", "UPCOMING MOVIES");
                                startActivity(intent);
                                break;
                            }
                            case 1: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "top10bollywoodsong");
                                intent.putExtra("title", "TOP 10 BOLLYWOOD SONGS");
                                startActivity(intent);
                                break;
                            }
                            case 2: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "firstdayfirstshow");
                                intent.putExtra("title", "FIRST DAY FIRST SHOW");
                                startActivity(intent);
                                break;
                            }

                            default:
                                break;
                        }
                        break;

                    // federal deduction
                    case 4:
                        switch (childPosition) {
                            case 0: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "amazing-images");
                                intent.putExtra("title", "AMAZING IMAGES");
                                startActivity(intent);
                                break;
                            }
                            case 1: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "australia-news");
                                intent.putExtra("title", "AUSTRALIA NEWS");
                                startActivity(intent);
                                break;
                            }
                            case 2: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "");
                                intent.putExtra("title", "BOLLYWOOD NEWS");
                                startActivity(intent);
                                break;
                            }
                            case 3: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "community-news");
                                intent.putExtra("title", "COMMUNITY NEWS");
                                startActivity(intent);
                                break;
                            }
                            case 4: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "");
                                intent.putExtra("title", "DESI NEWS");
                                startActivity(intent);
                                break;
                            }
                            case 5: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "");
                                intent.putExtra("title", "SPORTS NEWS");
                                startActivity(intent);
                                break;
                            }
                            case 6: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "");
                                intent.putExtra("title", "WORLD NEWS");
                                startActivity(intent);
                                break;
                            }
                            case 7: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "");
                                intent.putExtra("title", "HOLLYWOOD NEWS");
                                startActivity(intent);
                                break;
                            }
                            case 8: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "nsw-police-updates");
                                intent.putExtra("title", "NSW POLICE UPDATES");
                                startActivity(intent);
                                break;
                            }
                            case 9: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "news-desk");
                                intent.putExtra("title", "NEWS DESK");
                                startActivity(intent);
                                break;
                            }
                            default:
                                break;
                        }
                        break;

                    // provincial activity
                    case 5:
                        switch (childPosition) {
                            case 0: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "monthly-e-magazine");
                                intent.putExtra("title", "MONTHLY E-MAGAZINE");
                                startActivity(intent);
                                break;
                            }
                            case 1: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "weekly-e-newsletter");
                                intent.putExtra("title", "WEEKLY E-MAGAZINE");
                                startActivity(intent);
                                break;
                            }

                            default:
                                break;
                        }
                        break;

                    // expenses
                    case 6:
                        switch (childPosition) {
                            case 0: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "beauty");
                                intent.putExtra("title", "BEAUTY");
                                startActivity(intent);
                                break;
                            }
                            case 1: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "fashion");
                                intent.putExtra("title", "FASHION");
                                startActivity(intent);
                                break;
                            }
                            case 2: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "mag-corner");
                                intent.putExtra("title", "MAG CORNER");
                                startActivity(intent);
                                break;
                            }
                            case 3: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "view-point");
                                intent.putExtra("title", "VIEW POINT");
                                startActivity(intent);
                                break;
                            }

                            default:
                                break;
                        }
                        break;
                    case 7:
                        switch (childPosition) {
                            case 0: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "art-of-living");
                                intent.putExtra("title", "ART OF LIVING");
                                startActivity(intent);
                                break;
                            }
                            case 1: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "indian-astrology");
                                intent.putExtra("title", "ASTROLOGY");
                                startActivity(intent);
                                break;
                            }
                            case 2: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "horoscope");
                                intent.putExtra("title", "HOROSCOPE");
                                startActivity(intent);
                                break;
                            }
                            case 3: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "lifestyle");
                                intent.putExtra("title", "LIFE STYLE");
                                startActivity(intent);
                                break;
                            }
                            case 4: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "travel");
                                intent.putExtra("title", "TRAVEL");
                                startActivity(intent);
                                break;
                            }
                            case 5: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "yoga");
                                intent.putExtra("title", "YOGA");
                                startActivity(intent);
                                break;
                            }

                            default:
                                break;
                        }
                        break;

                    case 8:
                        switch (childPosition) {
                            case 0: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "legal");
                                intent.putExtra("title", "LEGAL");
                                startActivity(intent);
                                break;
                            }
                            case 1: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "migration");
                                intent.putExtra("title", "MIGRATION");
                                startActivity(intent);
                                break;
                            }
                            case 2: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "mortgage");
                                intent.putExtra("title", "MORTAGE");
                                startActivity(intent);
                                break;
                            }
                            case 3: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "properties");
                                intent.putExtra("title", "PROPERTIES");
                                startActivity(intent);
                                break;
                            }
                            default:
                                break;
                        }
                        break;

                    case 9:
                        switch (childPosition) {
                            case 0: {
                                Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                intent.putExtra("category", "food");
                                intent.putExtra("title", "RECEIPE OF THE WEEK");
                                startActivity(intent);
                                break;
                            }
                            default:
                                break;
                        }
                        break;
                            case 10:
                                switch (childPosition) {
                                    case 0: {
                                        Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                        intent.putExtra("category", "technology");
                                        intent.putExtra("title", "TECHNOLOGY");
                                        startActivity(intent);
                                        break;
                                    }
                                    case 2: {
                                        Intent intent = new Intent(HomepageActivity.this, MenuActivity.class);
                                        intent.putExtra("category", "arts");
                                        intent.putExtra("title", "ARTS");
                                        startActivity(intent);
                                        break;
                                    }
                                    default:
                                        break;
                                }
                                break;
                            default:
                                break;
                        }


//                expListView.setItemChecked(childPosition, true);
//                mDrawerLayout.closeDrawer(expListView);
//                loadFragment(fragment);

                return false;
            }
        });
    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context _context;
        private List<String> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<String, List<String>> _listDataChild;

        public ExpandableListAdapter(Context context,
                                     List<String> listDataHeader,
                                     HashMap<String, List<String>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(
                    this._listDataHeader.get(groupPosition))
                    .get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final String childText = (String) getChild(groupPosition,
                    childPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_item, null);
            }

            TextView txtListChild = (TextView) convertView
                    .findViewById(R.id.lblListItem);

            txtListChild.setText(childText);
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(
                    this._listDataHeader.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_group, null);
            }

            // changing the +/- on expanded list view
//            TextView txt_plusminus = (TextView) convertView.findViewById(R.id.plus_txt);
            ImageView image = (ImageView) convertView.findViewById(R.id.image);
            if (isExpanded) {
                image.setImageResource(R.drawable.arrow_up);
            } else {
                image.setImageResource(R.drawable.arrow_down);
            }

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.lblListHeader);
            // lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);


            // adding icon to expandable list view
            ImageView imgListGroup = (ImageView) convertView
                    .findViewById(R.id.ic_txt);


// nav drawer icons from resources
            //navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
            //imgListGroup.setImageDrawable(navMenuIcons.getDrawable(groupPosition));

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homepage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(HomepageActivity.this, ContactUsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}