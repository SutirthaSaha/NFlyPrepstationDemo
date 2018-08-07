package in.nfly.dell.nflydemo.activities;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.adapters.ViewPagerAdapter;
import in.nfly.dell.nflydemo.fragments.ProfileAcademicFragment;
import in.nfly.dell.nflydemo.fragments.ProfilePersonalFragment;
import in.nfly.dell.nflydemo.fragments.ProfilePersonalityFragment;
import in.nfly.dell.nflydemo.fragments.ProfileResumeFragment;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayoutProfile;
    private ViewPager viewPagerProfile;
    private ViewPagerAdapter viewPagerAdapter;
    private ProgressBar profileProgressBar;
    private TextView profileCompletionPercent;
    private String user_id,cpoints;
    private String urlPersonal="http://nfly.in/profileapi/personal_details";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        profileProgressBar=findViewById(R.id.profileProgressBar);
        tabLayoutProfile=findViewById(R.id.tabLayoutProfile);
        viewPagerProfile=findViewById(R.id.viewPagerProfile);

        profileCompletionPercent=findViewById(R.id.profileCompletionPercent);

        User user=new User(ProfileActivity.this);
        user_id=user.getUser_id();

        setValues();
        viewPagerProfile.setOffscreenPageLimit(5);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(new ProfilePersonalFragment(),"Personal");
        viewPagerAdapter.addFragments(new ProfileAcademicFragment(),"Academic");
        viewPagerAdapter.addFragments(new ProfilePersonalityFragment(),"Personality");
        viewPagerAdapter.addFragments(new ProfileResumeFragment(),"Resume");

        viewPagerProfile.setAdapter(viewPagerAdapter);
        tabLayoutProfile.setupWithViewPager(viewPagerProfile);
    }

    public void setValues() {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, urlPersonal, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject=new JSONObject(response);

                        JSONObject user=jsonObject.getJSONObject("user");
                        cpoints=user.getString("cpoints");
                        if(Integer.parseInt(cpoints)<60){
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                                profileProgressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
                            }
                            else{
                                profileProgressBar.getProgressDrawable().setColorFilter(
                                        Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                            }
                        }
                        profileProgressBar.setProgress(Integer.parseInt(cpoints));
                        profileCompletionPercent.setText(cpoints+"%");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                }
            })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("X-Api-Key", "59671596837f42d974c7e9dcf38d17e8");
                    return headers;
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("key", "user_id");
                    params.put("value", user_id);
                    return params;
                }
            };
            MySingleton.getmInstance(ProfileActivity.this).addToRequestQueue(stringRequest);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySingleton.release();
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.profileToolbar);
        toolbar.setTitle("Profile");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
}
