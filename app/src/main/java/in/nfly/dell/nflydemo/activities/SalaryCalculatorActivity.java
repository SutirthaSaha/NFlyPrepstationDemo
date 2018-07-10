package in.nfly.dell.nflydemo.activities;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;

public class SalaryCalculatorActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AutoCompleteTextView salaryCalcCompanyAutoText;
    private EditText salaryCalcCTCEditText;
    private String urlCompany="http://nfly.in/gapi/load_all_rows";
    private String urlSalaryCheck="http://nfly.in/api/get_salary";

    private ArrayAdapter<String> adapter;

    private ArrayList<String> companyList=new ArrayList<String>(){};

    private String company_name,ctc;
    private String min,max;

    private int status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_calculator);
        setToolbar();
        salaryCalcCompanyAutoText=findViewById(R.id.salaryCalcCompanyAutoText);
        salaryCalcCTCEditText=findViewById(R.id.salaryCalcCTCEditText);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setValues();
    }

    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlCompany, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(SalaryCalculatorActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        companyList.add(arrayObject.getString("company_name"));
                    }
                    //Toast.makeText(SalaryCalculatorActivity.this, companyList.toString(), Toast.LENGTH_SHORT).show();
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(SalaryCalculatorActivity.this,android.R.layout.simple_dropdown_item_1line,companyList.toArray(new String[companyList.size()]));
                    salaryCalcCompanyAutoText.setThreshold(1);
                    salaryCalcCompanyAutoText.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SalaryCalculatorActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

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
                params.put("key", "company_name");
                params.put("order", "ASC");
                params.put("table", "salary_companies");
                return params;
            }
        };
        MySingleton.getmInstance(SalaryCalculatorActivity.this).addToRequestQueue(stringRequest);
    }

    public void onSalaryCalcClick(View view) {
        ctc=salaryCalcCTCEditText.getText().toString().trim();
        company_name=salaryCalcCompanyAutoText.getText().toString().trim();
        checkSalary();
    }

    private void checkSalary() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlSalaryCheck, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(SalaryCalculatorActivity.this, response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    status=Integer.parseInt(jsonObject.getString("status"));
                    if(status==200) {
                        min = jsonObject.getString("salary_min");
                        max = jsonObject.getString("salary_max");
                    }
                    else if(status==201){
                        min = jsonObject.getString("salary_min_excep");
                        max = jsonObject.getString("salary_max_excep");
                    }

                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(SalaryCalculatorActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(SalaryCalculatorActivity.this);
                    }

                    builder.setCancelable(true);
                    builder.setMessage("The tentative range of in-hand salary is:\n" +
                            min+" - "+max+" Rs./month");

                    AlertDialog alert=builder.create();
                    alert.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SalaryCalculatorActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

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
                params.put("company_name", company_name);
                params.put("ctc", ctc);
                return params;
            }
        };
        MySingleton.getmInstance(SalaryCalculatorActivity.this).addToRequestQueue(stringRequest);
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.salaryCalcToolbar);
        toolbar.setTitle("Salary Calculator");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
}