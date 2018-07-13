package in.nfly.dell.nflydemo.activities.singleActivities;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.adapters.SplashScreenSwipeAdapter;
import me.relex.circleindicator.CircleIndicator;

public class SplashScreenSliderActivity extends AppCompatActivity {
    private int[] swipeImageDataSet= {R.drawable.presentation,R.drawable.splash_test,R.drawable.splash_resume};
    private String[] swipeTitleDataSet={"Video Courses","Placement Papers","Resume Builder"};
    private String[] swipeSubtitleDataSet={"Watch videos to learn etc etc","Solve mock tests blahblah this and that","Build your own resume with no effort"};

    private static int currentPage = 0;
    private static int NUM_PAGES;
    private ViewPager viewPager;
    private SplashScreenSwipeAdapter swipeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_slider);

        View someView = findViewById(R.id.splashScreenViewPager);
        View root = someView.getRootView();
        root.setBackgroundColor(Color.parseColor("#ff6961"));
        setViewPager();
    }
    private void setViewPager()
    {
        viewPager=findViewById(R.id.splashScreenViewPager);
        swipeAdapter= new SplashScreenSwipeAdapter(this,swipeImageDataSet,swipeTitleDataSet,swipeSubtitleDataSet);
        viewPager.setAdapter(swipeAdapter);
        CircleIndicator indicator = findViewById(R.id.splashScreenSliderIndicator);
        indicator.setViewPager(viewPager);
        NUM_PAGES=swipeImageDataSet.length;

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if(currentPage==NUM_PAGES){

                    View someView = findViewById(R.id.splashScreenViewPager);
                    View root = someView.getRootView();
                    root.setBackgroundColor(Color.parseColor("#ff6961"));
                    currentPage=0;
                }
                else if (currentPage==1)
                {

                    View someView = findViewById(R.id.splashScreenViewPager);
                    View root = someView.getRootView();
                    root.setBackgroundColor(Color.parseColor("#ffb347"));
                }
                else if(currentPage==2)
                {

                    View someView = findViewById(R.id.splashScreenViewPager);
                    View root = someView.getRootView();
                    root.setBackgroundColor(Color.parseColor("#aec6cf"));
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
