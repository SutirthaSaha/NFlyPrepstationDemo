package in.nfly.dell.nflydemo.activities.singleActivities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.activities.ProfileActivity;

public class PersonalityTestActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ArrayList<String> optionsDataSet=new ArrayList<String>();
    private ArrayList<String> questionDataSet=new ArrayList<String>(){};
    private ArrayList<String> answerDataSet=new ArrayList<String>(){};
    private HashMap<String,ArrayList<String>> questionOptionsSet=new HashMap<>();
    private int[] userOptions;
    private int count=0;
    private TextView personalityTestQuestion;
    private RadioButton personalityTestOption1,personalityTestOption2,personalityTestOption3,personalityTestOption4,personalityTestOption5;
    private RadioGroup personalityTestOptions;
    private RadioButton selectRadioBtn;
    private Button personalityTestPreviousBtn,personalityTestNextBtn,personalityTestSubmitBtn;

    private String urlInsertAttempt="http://nfly.in/gapi/insert";

    private int optionIdSelected,status;
    private String user_id;
    private int user_score,max_score;
    private String date;
    private int user_extra_score,user_agree_score,user_con_score,user_neuro_score,user_open_score;

    private ArrayList<Integer> ExtraAnsDataSet=new ArrayList<Integer>(){};
    private ArrayList<Integer> AgreeAnsDataSet=new ArrayList<Integer>(){};
    private ArrayList<Integer> ConAnsDataSet=new ArrayList<Integer>(){};
    private ArrayList<Integer> NeuroAnsDataSet=new ArrayList<Integer>(){};
    private ArrayList<Integer> OpenAnsDataSet=new ArrayList<Integer>(){};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_test);
        personalityTestQuestion=findViewById(R.id.personalityTestQuestion);
        personalityTestOption1=findViewById(R.id.personalityTestOption1);
        personalityTestOption2=findViewById(R.id.personalityTestOption2);
        personalityTestOption3=findViewById(R.id.personalityTestOption3);
        personalityTestOption4=findViewById(R.id.personalityTestOption4);
        personalityTestOption5=findViewById(R.id.personalityTestOption5);

        personalityTestOptions=findViewById(R.id.personalityTestOptions);
        personalityTestPreviousBtn=findViewById(R.id.personalityTestPreviousBtn);
        personalityTestNextBtn=findViewById(R.id.personalityTestNextBtn);
        personalityTestSubmitBtn=findViewById(R.id.personalityTestSubmitBtn);
        personalityTestSubmitBtn.setEnabled(false);

        User user=new User(PersonalityTestActivity.this);
        user_id=user.getUser_id();
        questionDataSet.add("1. I see myself as someone who is talkative.");
        questionDataSet.add("2. I tend to find fault in others.");
        questionDataSet.add("3. I do a thorough job.");
        questionDataSet.add("4. I often feel depressed.");

        questionDataSet.add("5. I find my self orignal who comes up with new ideas.");
        questionDataSet.add("6. I am generally reserved.");
        questionDataSet.add("7. I am helpful and unselfish with others.");
        questionDataSet.add("8. I am somewhat careless.");
        questionDataSet.add("9. I am generally relaxed and handle stress well.");
        questionDataSet.add("10. I am curious about many different things.");
        questionDataSet.add("11. I am full of energy.");
        questionDataSet.add("12. I start quarrels with others.");
        questionDataSet.add("13. I am a reliable worker.");
        questionDataSet.add("14. I can be tense.");

        questionDataSet.add("15. I am ingenious and a deep thinker.");
        questionDataSet.add("16. I generate a lot of enthusiasm.");
        questionDataSet.add("17. I have a forgiving nature.");
        questionDataSet.add("18. I tend to be unorganized.");
        questionDataSet.add("19. I worry a lot.");
        questionDataSet.add("20. I have an active imagination.");
        questionDataSet.add("21. I tend to be quiet.");
        questionDataSet.add("22. I am generally trusting.");
        questionDataSet.add("23. I tend to be lazy.");
        questionDataSet.add("24. I am emotionally stable.");

        questionDataSet.add("25. I am quiet inventive.");
        questionDataSet.add("26. I have an assertive personality.");
        questionDataSet.add("27. I can be cold and aloof.");
        questionDataSet.add("28. I persevere until the task is finished.");
        questionDataSet.add("29. At times I can be moody.");
        questionDataSet.add("30. I value art.");
        questionDataSet.add("31. I am shy.");
        questionDataSet.add("32. I am considerate and kind to everyone.");
        questionDataSet.add("33. I do things efficiently.");
        questionDataSet.add("34. I remain calm under tense situations.");

        questionDataSet.add("35. I am highly disciplined.");
        questionDataSet.add("36. I am generally socialable.");
        questionDataSet.add("37. I am sometimes rude to others.");
        questionDataSet.add("38. I make routines and stick to them.");
        questionDataSet.add("39. I get nervous easily.");
        questionDataSet.add("40. I like to play with ideas.");
        questionDataSet.add("41. I have few artistic ideas.");
        questionDataSet.add("42. I like to cooperate with others.");
        questionDataSet.add("43. I get easily distracted.");
        questionDataSet.add("44. I am sophisticated in art and music.");

        optionsDataSet.add("Strong No");
        optionsDataSet.add("Mild No");
        optionsDataSet.add("Neutral");
        optionsDataSet.add("Mild Yes");
        optionsDataSet.add("Strong Yes");

        for(int i=0;i<questionDataSet.size();i++){
            questionOptionsSet.put(questionDataSet.get(i),optionsDataSet);
        }

        personalityTestQuestion.setText(questionDataSet.get(0));
        personalityTestOption1.setText(questionOptionsSet.get(questionDataSet.get(0)).get(0));
        personalityTestOption2.setText(questionOptionsSet.get(questionDataSet.get(0)).get(1));
        personalityTestOption3.setText(questionOptionsSet.get(questionDataSet.get(0)).get(2));
        personalityTestOption4.setText(questionOptionsSet.get(questionDataSet.get(0)).get(3));
        personalityTestOption5.setText(questionOptionsSet.get(questionDataSet.get(0)).get(4));

        userOptions = new int[questionDataSet.size()];
        for (int i = 0; i < questionDataSet.size(); i++) {
            userOptions[i] = 0;
        }

        user_agree_score=0;
        user_con_score=0;
        user_extra_score=0;
        user_neuro_score=0;
        user_open_score=0;
        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.personalityTestToolbar);
        toolbar.setTitle("Personality");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }

    private void saveUserOption() {
        optionIdSelected=personalityTestOptions.getCheckedRadioButtonId();
        switch(optionIdSelected){
            case R.id.personalityTestOption1:
                userOptions[count]=1;
                break;
            case R.id.personalityTestOption2:
                userOptions[count]=2;
                break;
            case R.id.personalityTestOption3:
                userOptions[count]=3;
                break;
            case R.id.personalityTestOption4:
                userOptions[count]=4;
                break;
            case R.id.personalityTestOption5:
                userOptions[count]=4;
                break;
            case -1:
                userOptions[count]=0;
        }
        personalityTestOptions.clearCheck();
    }

    public void onPersonalityTestPreviousBtnClick(View view) {
        saveUserOption();
        count--;
        if(count==0){
            personalityTestPreviousBtn.setVisibility(View.INVISIBLE);
            personalityTestPreviousBtn.setEnabled(false);
        }
        if(count>0){
            personalityTestPreviousBtn.setVisibility(View.VISIBLE);
            personalityTestPreviousBtn.setEnabled(true);

            personalityTestSubmitBtn.setVisibility(View.INVISIBLE);
            personalityTestSubmitBtn.setEnabled(false);
        }
        showUserOption(count);
        personalityTestNextBtn.setVisibility(View.VISIBLE);
        personalityTestNextBtn.setEnabled(true);
        personalityTestQuestion.setText(questionDataSet.get(count));
        personalityTestOption1.setText(questionOptionsSet.get(questionDataSet.get(count)).get(0));
        personalityTestOption2.setText(questionOptionsSet.get(questionDataSet.get(count)).get(1));
        personalityTestOption3.setText(questionOptionsSet.get(questionDataSet.get(count)).get(2));
        personalityTestOption4.setText(questionOptionsSet.get(questionDataSet.get(count)).get(3));
        personalityTestOption5.setText(questionOptionsSet.get(questionDataSet.get(count)).get(4));
    }

    private void showUserOption(int count) {
        int userPrevOption=R.id.personalityTestOption1;
        switch(userOptions[count]){
            case 0:
                return;
            case 1:
                userPrevOption=R.id.personalityTestOption1;
                break;
            case 2:
                userPrevOption=R.id.personalityTestOption2;
                break;
            case 3:
                userPrevOption=R.id.personalityTestOption3;
                break;
            case 4:
                userPrevOption=R.id.personalityTestOption4;
                break;
            case 5:
                userPrevOption=R.id.personalityTestOption5;
                break;
        }

        selectRadioBtn=findViewById(userPrevOption);
        selectRadioBtn.setChecked(true);
    }

    public void onPersonalityTestNextBtnClick(View view) {

        saveUserOption();
        count++;
        if(count==(questionDataSet.size()-1)){
            personalityTestNextBtn.setVisibility(View.INVISIBLE);
            personalityTestNextBtn.setEnabled(false);

            personalityTestSubmitBtn.setVisibility(View.VISIBLE);
            personalityTestSubmitBtn.setEnabled(true);
        }
        if(count<(questionDataSet.size()-1)){
            personalityTestNextBtn.setVisibility(View.VISIBLE);
            personalityTestNextBtn.setEnabled(true);
        }
        showUserOption(count);
        personalityTestPreviousBtn.setVisibility(View.VISIBLE);
        personalityTestPreviousBtn.setEnabled(true);
        personalityTestQuestion.setText(questionDataSet.get(count));
        personalityTestOption1.setText(questionOptionsSet.get(questionDataSet.get(count)).get(0));
        personalityTestOption2.setText(questionOptionsSet.get(questionDataSet.get(count)).get(1));
        personalityTestOption3.setText(questionOptionsSet.get(questionDataSet.get(count)).get(2));
        personalityTestOption4.setText(questionOptionsSet.get(questionDataSet.get(count)).get(3));
        personalityTestOption5.setText(questionOptionsSet.get(questionDataSet.get(count)).get(4));

    }

    public void onPersonalityTestSubmitBtnClick(View view) {
        saveUserOption();
        Toast.makeText(this, "Submit", Toast.LENGTH_SHORT).show();

        ExtraAnsDataSet.add(userOptions[0]);
        ExtraAnsDataSet.add(userOptions[5]);
        ExtraAnsDataSet.add(userOptions[10]);
        ExtraAnsDataSet.add(userOptions[15]);
        ExtraAnsDataSet.add(userOptions[20]);
        ExtraAnsDataSet.add(userOptions[25]);
        ExtraAnsDataSet.add(userOptions[30]);
        ExtraAnsDataSet.add(userOptions[35]);

        AgreeAnsDataSet.add(userOptions[1]);
        AgreeAnsDataSet.add(userOptions[6]);
        AgreeAnsDataSet.add(userOptions[11]);
        AgreeAnsDataSet.add(userOptions[16]);
        AgreeAnsDataSet.add(userOptions[21]);
        AgreeAnsDataSet.add(userOptions[26]);
        AgreeAnsDataSet.add(userOptions[31]);
        AgreeAnsDataSet.add(userOptions[36]);
        AgreeAnsDataSet.add(userOptions[41]);

        ConAnsDataSet.add(userOptions[2]);
        ConAnsDataSet.add(userOptions[7]);
        ConAnsDataSet.add(userOptions[12]);
        ConAnsDataSet.add(userOptions[17]);
        ConAnsDataSet.add(userOptions[22]);
        ConAnsDataSet.add(userOptions[27]);
        ConAnsDataSet.add(userOptions[32]);
        ConAnsDataSet.add(userOptions[37]);
        ConAnsDataSet.add(userOptions[42]);

        NeuroAnsDataSet.add(userOptions[3]);
        NeuroAnsDataSet.add(userOptions[8]);
        NeuroAnsDataSet.add(userOptions[13]);
        NeuroAnsDataSet.add(userOptions[18]);
        NeuroAnsDataSet.add(userOptions[23]);
        NeuroAnsDataSet.add(userOptions[28]);
        NeuroAnsDataSet.add(userOptions[33]);
        NeuroAnsDataSet.add(userOptions[38]);

        OpenAnsDataSet.add(userOptions[4]);
        OpenAnsDataSet.add(userOptions[9]);
        OpenAnsDataSet.add(userOptions[14]);
        OpenAnsDataSet.add(userOptions[19]);
        OpenAnsDataSet.add(userOptions[24]);
        OpenAnsDataSet.add(userOptions[29]);
        OpenAnsDataSet.add(userOptions[34]);
        OpenAnsDataSet.add(userOptions[39]);
        OpenAnsDataSet.add(userOptions[40]);
        OpenAnsDataSet.add(userOptions[43]);
        /*
        user_extra_score=sum(ExtraAnsDataSet);
        user_con_score=sum(ConAnsDataSet);
        user_neuro_score=sum(NeuroAnsDataSet);
        user_open_score=sum(OpenAnsDataSet);
        user_agree_score=sum(AgreeAnsDataSet);
        */
        user_extra_score=((sum(ExtraAnsDataSet)-8)*100)/32;
        user_con_score=((sum(ConAnsDataSet)-9)*100)/36;
        user_neuro_score=((sum(NeuroAnsDataSet)-8)*100)/32;
        user_open_score=((sum(OpenAnsDataSet)-10)*100)/40;
        user_agree_score=((sum(AgreeAnsDataSet)-9)*100)/36;

        //Toast.makeText(this, "Extra Score:"+user_extra_score+"\nCon Score:"+user_con_score+"\nNeuro Score:"+user_neuro_score+"\nAgree Score:"+user_agree_score+"\nOpen Score:"+user_open_score, Toast.LENGTH_LONG).show();
        insertAttempt();

        /*
        Toast.makeText(this, Integer.toString(userOptions[count]), Toast.LENGTH_SHORT).show();

        for(int i=0;i<answerDataSet.size();i++){
            if(userOptions[i]==Integer.parseInt(answerDataSet.get(i))){
                user_score=user_score+3;
            }
        }
        Toast.makeText(this, user_score+"/"+max_score, Toast.LENGTH_SHORT).show();
        //updateTestTable();
        for(int i=0;i<questionDataSet.size();i++){
            int marks=0;
            if(userOptions[i]==Integer.parseInt(answerDataSet.get(i))){
                marks=3;
            }
            //insertResponse(quesIdDataSet.get(i),i,marks);
        }
        //insertAttempt();*/
    }
    private int sum(ArrayList<Integer> al)
    {
        int value = 0;
        for(int i : al)
        {
            value += i;
        }
        return value;
    }
    private void insertAttempt() {

        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlInsertAttempt, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    status=arrayObject.getInt("status");
                    if(status==200){
                        Intent intent=new Intent(PersonalityTestActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(PersonalityTestActivity.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PersonalityTestActivity.this, "Error", Toast.LENGTH_SHORT).show();
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
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                params.put("insert_array[user_id]", user_id);
                params.put("insert_array[extraversion]",Integer.toString(user_extra_score));
                params.put("insert_array[openness]",Integer.toString(user_open_score));
                params.put("insert_array[agreeableness]",Integer.toString(user_agree_score));
                params.put("insert_array[conscientiousness]",Integer.toString(user_con_score));
                params.put("insert_array[neuroticism]",Integer.toString(user_neuro_score));

                params.put("table","user_big5");
                return params;
            }
        };
        MySingleton.getmInstance(PersonalityTestActivity.this).addToRequestQueue(stringRequest);
    }
}