package in.nfly.dell.nflydemo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.PlayerConfig;
import in.nfly.dell.nflydemo.R;

public class CourseStudyActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private Toolbar toolbar;
    private String course_id,course_name;
    private String urlCourseVideo="http://nfly.in/gapi/load_rows_one";
    private String video_url;

    private YouTubePlayerView courseStudyVideoView;
    private YouTubePlayer courseYoutubePlayer;

    private ListView courseStudyVideoList;
    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> urlDataSet=new ArrayList<String>(){};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_study);
        Intent intent=getIntent();
        course_id=intent.getStringExtra("course_id");
        course_name=intent.getStringExtra("course_name");
        Toast.makeText(this, course_id+"\n"+course_name, Toast.LENGTH_SHORT).show();

        courseStudyVideoView=findViewById(R.id.courseStudyVideoView);
        courseStudyVideoList=findViewById(R.id.courseStudyVideoList);

        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setValues();
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.courseStudyToolbar);
        toolbar.setTitle(course_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }

    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlCourseVideo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        titleDataSet.add((i+1)+" . "+arrayObject.getString("nfly_video_name"));
                        urlDataSet.add(arrayObject.getString("nfly_app_url"));
                    }
                    if (courseYoutubePlayer != null) {
                        courseYoutubePlayer.release();
                    }
                    video_url=urlDataSet.get(0);
                    courseStudyVideoView.initialize(PlayerConfig.API_KEY,CourseStudyActivity.this);

                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(CourseStudyActivity.this,android.R.layout.simple_list_item_1,titleDataSet);
                    courseStudyVideoList.setAdapter(adapter);
                    courseStudyVideoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if (courseYoutubePlayer != null) {
                                courseYoutubePlayer.release();
                            }
                            video_url=urlDataSet.get(position);
                            courseStudyVideoView.initialize(PlayerConfig.API_KEY,CourseStudyActivity.this);

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CourseStudyActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
                params.put("key", "nfly_course_id");
                params.put("value", course_id);
                params.put("table", "nfly_course_video");
                return params;
            }
        };
        MySingleton.getmInstance(CourseStudyActivity.this).addToRequestQueue(stringRequest);
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        courseYoutubePlayer=youTubePlayer;
        if(!b){
            courseYoutubePlayer.loadVideo(video_url);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
