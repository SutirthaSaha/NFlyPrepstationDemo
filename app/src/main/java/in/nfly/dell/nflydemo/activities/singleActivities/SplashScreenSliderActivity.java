package in.nfly.dell.nflydemo.activities.singleActivities;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.adapters.HomeSwipeAdapter;
import me.relex.circleindicator.CircleIndicator;

public class SplashScreenSliderActivity extends AppCompatActivity {
    private int[] swipeImageDataSet= {R.drawable.imagevideo,R.drawable.imagepaper,R.drawable.imageresume};
    private String[] swipeTitleDataSet={"Video Courses","Placement Papers","Resume Builder"};
    private static int currentPage = 0;
    private static int NUM_PAGES;
    private ViewPager viewPager;
    private HomeSwipeAdapter swipeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_slider);
        setViewPager();
    }
    private void setViewPager()
    {
        viewPager=findViewById(R.id.homeViewPager);
        swipeAdapter= new HomeSwipeAdapter(this,swipeImageDataSet,swipeTitleDataSet);
        viewPager.setAdapter(swipeAdapter);
        CircleIndicator indicator = findViewById(R.id.homeSliderIndicator);
        indicator.setViewPager(viewPager);
        NUM_PAGES=swipeImageDataSet.length;

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if(currentPage==NUM_PAGES){
                    currentPage=0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);}
        }, 2000, 2000);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
