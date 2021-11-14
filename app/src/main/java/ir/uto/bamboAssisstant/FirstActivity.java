package ir.uto.bamboAssisstant;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import ir.uto.bamboAssisstant.Adapter.PagerAdapter;
import ir.uto.bamboAssisstant.Util.MyFragments;

public class FirstActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager2 pager2;
    BottomNavigationView bottomNavigationView;
    MaterialToolbar toolbar;
    public DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
        pager2.setAdapter(new PagerAdapter(this, MyFragments.getAllFragments()));
        setNavigationViewListener();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itm_routine:
                        pager2.setCurrentItem(0);
                        bottomNavigationView.getMenu().getItem(0).setChecked(true);
                        break;

                    case R.id.itm_report:
                        pager2.setCurrentItem(1);
                        bottomNavigationView.getMenu().getItem(1).setChecked(true);
                        break;
                }
                return true;
            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itm_rep_history:
                startActivity(new Intent(getApplicationContext(), ReportHistoryActivity.class));

                break;
            case R.id.itm_water_activity:

                startActivity(new Intent(getApplicationContext(), WaterReminderActivity.class));
                break;
            case R.id.itm_notif:
                pager2.setCurrentItem(2);

        }
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void initial() {
        pager2 = findViewById(R.id.pager);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        toolbar = findViewById(R.id.matToolbar);
        drawerLayout = findViewById(R.id.my_drawer_layout);
    }
}