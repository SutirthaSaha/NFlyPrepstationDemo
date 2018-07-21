package in.nfly.dell.nflydemo.activities.singleActivities;

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

import java.util.ArrayList;
import java.util.HashMap;

import in.nfly.dell.nflydemo.R;

public class PersonalityTestActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ArrayList<String> optionsDataSet=new ArrayList<String>();
    private ArrayList<String> questionDataSet=new ArrayList<String>(){};
    private ArrayList<String> answerDataSet=new ArrayList<String>(){};
    private HashMap<String,ArrayList<String>> questionOptionsSet=new HashMap<>();
    private int[] userOptions;
    private int count=0;
    private TextView personalityTestQuestion;
    private RadioButton personalityTestOption1,personalityTestOption2,personalityTestOption3,personalityTestOption4;
    private RadioGroup personalityTestOptions;
    private RadioButton selectRadioBtn;
    private Button personalityTestPreviousBtn,personalityTestNextBtn,personalityTestSubmitBtn;

    private int optionIdSelected;
    private int user_score,max_score;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_test);
        personalityTestQuestion=findViewById(R.id.personalityTestQuestion);
        personalityTestOption1=findViewById(R.id.personalityTestOption1);
        personalityTestOption2=findViewById(R.id.personalityTestOption2);
        personalityTestOption3=findViewById(R.id.personalityTestOption3);
        personalityTestOption4=findViewById(R.id.personalityTestOption4);
        personalityTestOptions=findViewById(R.id.personalityTestOptions);
        personalityTestPreviousBtn=findViewById(R.id.personalityTestPreviousBtn);
        personalityTestNextBtn=findViewById(R.id.personalityTestNextBtn);
        personalityTestSubmitBtn=findViewById(R.id.personalityTestSubmitBtn);
        personalityTestSubmitBtn.setEnabled(false);

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

        userOptions = new int[questionDataSet.size()];
        for (int i = 0; i < questionDataSet.size(); i++) {
            userOptions[i] = 0;
        }

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
                userPrevOption=R.id.personalityTestOption3;;
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
    }

    public void onPersonalityTestSubmitBtnClick(View view) {
        saveUserOption();
        Toast.makeText(this, "Submit", Toast.LENGTH_SHORT).show();
        finish();
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
}