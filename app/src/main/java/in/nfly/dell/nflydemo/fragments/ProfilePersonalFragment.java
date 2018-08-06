package in.nfly.dell.nflydemo.fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.activities.LoginActivity;
import in.nfly.dell.nflydemo.activities.MainActivity;
import in.nfly.dell.nflydemo.activities.OnBoardRegisterActivity;
import in.nfly.dell.nflydemo.activities.ProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilePersonalFragment extends Fragment {

    private TextView profileFullName,profileCurrentDesignation,profileGender,profileDob,
            profileCurrentCity,profileHomeTown,profileCoverLetter,
            profileHobbiesTextDialog,profileLanguagesTextDialog,profileFacebook,profileLinkedIn,profileTwitter,
    profileQuora,profileEmail, profilePhone,profileAddress;

    private Spinner editBasicDetailsGender;
    private String[] genderOptions={"Male","Female","Others"};
    private EditText editBasicDetailsCurrentDesignation,editBasicDetailsDob,
            editBasicDetailsCurrentCity,editBasicDetailsHomeTown,editCoverLetter,editHobbies,editLanguages,
            editContactDetailsPhone, editContactDetailsAddress,
            editSocialProfilesFacebook,editSocialProfilesLinkedIn,editSocialProfilesTwitter,editSocialProfilesQuora;

    private int status;
    private String urlPersonal="http://nfly.in/profileapi/personal_details";
    private String urlUpdate="http://nfly.in/gapi/update";
    private String oneRow="http://nfly.in/gapi/load_rows_one";
    private String allRows="http://nfly.in/gapi/load_all_rows";
    private String urlInsert="http://nfly.in/gapi/insert";
    private String urlDataExists="http://nfly.in/gapi/data_exists_one";
    private String urlGetDetails="http://nfly.in/gapi/get_details_one";
    private String urlDelete="http://nfly.in/gapi/delete_with_one";
    private String urlGetCPoints="http://nfly.in/gapi/get_cpoints";

    private String cpoints;
    private String hobby_id,hobby_name;
    private String language_id,language_name;

    private HashMap<String,String> languageMap=new HashMap<String,String>(){};
    private HashMap<String,String> hobbyMap=new HashMap<String,String>(){};
    
    private ImageView profileImage;

    private ImageView basicDetailsEditBtn, coverLetterEditBtn, hobbiesAddBtn,
    languagesAddBtn, contactDetailsEditBtn,socialProfilesEditBtn, editBasicDetailsDobCalenderBtn;

    private String user_id;
    private int basicCount=0;

    private RecyclerView profileHobbiesRecyclerView;
    private RecyclerView profileLanguagesRecyclerView;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<String> dataSet=new ArrayList<String>(){
        {
            add("painting");
            add("reading");
           }};

    private ArrayList<String> languageTitleDataSet=new ArrayList<String>(){};
    private ArrayList<String> languageIdDataSet=new ArrayList<String>(){};
    private ArrayList<String> hobbyTitleDataSet=new ArrayList<String>(){};
    private ArrayList<String> hobbyIdDataSet=new ArrayList<String>(){};


    public ProfilePersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile_personal, container, false);

        User user=new User(getActivity());
        user_id=user.getUser_id();

        profileImage=view.findViewById(R.id.profileImage);

        profileFullName=view.findViewById(R.id.profileFullName);
        profileCurrentDesignation=view.findViewById(R.id.profileCurrentDesignation);
        profileGender=view.findViewById(R.id.profileGender);
        profileDob=view.findViewById(R.id.profileDob);
        profileCurrentCity=view.findViewById(R.id.profileCurrentCity);
        profileHomeTown=view.findViewById(R.id.profileHomeTown);

        profileCoverLetter=view.findViewById(R.id.profileCoverLetter);
        profileHobbiesTextDialog= view.findViewById(R.id.profileHobbiesTextDialog);
        profileLanguagesTextDialog=view.findViewById(R.id.profileLanguagesTextDialog);

        profileFacebook=view.findViewById(R.id.profileFacebook);
        profileTwitter=view.findViewById(R.id.profileTwitter);
        profileLinkedIn=view.findViewById(R.id.profileLinkedIn);
        profileQuora=view.findViewById(R.id.profileQuora);

        profileCoverLetter=view.findViewById(R.id.profileCoverLetter);

        profileEmail=view.findViewById(R.id.profileEmail);
        profilePhone=view.findViewById(R.id.profilePhone);
        profileAddress=view.findViewById(R.id.profileAddress);

        basicDetailsEditBtn=view.findViewById(R.id.basicDetailsEditBtn);
        coverLetterEditBtn=view.findViewById(R.id.coverLetterEditBtn);
        hobbiesAddBtn=view.findViewById(R.id.hobbiesAddBtn);
        languagesAddBtn=view.findViewById(R.id.languagesAddBtn);
        contactDetailsEditBtn=view.findViewById(R.id.contactDetailsEditBtn);
        socialProfilesEditBtn=view.findViewById(R.id.socialProfilesEditBtn);

        checkUserAdditionalDetails();
        socialProfilesEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View contactDetailsEditLayout=layoutInflater.inflate(R.layout.dialog_edit_social_profiles,null);

                editSocialProfilesFacebook=contactDetailsEditLayout.findViewById(R.id.editSocialProfilesFacebook);
                editSocialProfilesLinkedIn=contactDetailsEditLayout.findViewById(R.id.editSocialProfilesLinkedIn);
                editSocialProfilesQuora=contactDetailsEditLayout.findViewById(R.id.editSocialProfilesQuora);
                editSocialProfilesTwitter=contactDetailsEditLayout.findViewById(R.id.editSocialProfilesTwitter);

                if(!profileFacebook.getText().toString().equals("Not Provided")) {
                    editSocialProfilesFacebook.setText(profileFacebook.getText().toString());
                }
                if(!profileLinkedIn.getText().toString().equals("Not Provided"))
                    editSocialProfilesLinkedIn.setText(profileLinkedIn.getText().toString());
                if(!profileTwitter.getText().toString().equals("Not Provided"))
                    editSocialProfilesTwitter.setText(profileTwitter.getText().toString());
                if(!profileQuora.getText().toString().equals("Not Provided"))
                    editSocialProfilesQuora.setText(profileQuora.getText().toString());

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(contactDetailsEditLayout);
                alertDialog.setCancelable(false);

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editSocialDetails();
                        getCPoints();
                    }
                });
                AlertDialog alert=alertDialog.create();
                alert.show();

            }
        });

        contactDetailsEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View contactDetailsEditLayout=layoutInflater.inflate(R.layout.dialog_edit_contact_details,null);

                editContactDetailsAddress=contactDetailsEditLayout.findViewById(R.id.editContactDetailsAddress);
                editContactDetailsPhone=contactDetailsEditLayout.findViewById(R.id.editContactDetailsPhone);

                if(!profileAddress.getText().toString().equals("Not Provided")) {
                    editContactDetailsAddress.setText(profileAddress.getText().toString());
                }
                if(!profilePhone.getText().toString().equals("Not Provided")) {
                    editContactDetailsPhone.setText(profilePhone.getText().toString());
                }

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(contactDetailsEditLayout);
                alertDialog.setCancelable(false);

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editContactDetails();
                        getCPoints();
                    }
                });
                AlertDialog alert=alertDialog.create();
                alert.show();

            }
        });

        languagesAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View languagesAddLayout=layoutInflater.inflate(R.layout.dialog_edit_languages,null);

                editLanguages=languagesAddLayout.findViewById(R.id.editLanguages);

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(languagesAddLayout);
                alertDialog.setCancelable(false);

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        languageTitleDataSet.clear();
                        languageIdDataSet.clear();
                        languageMap.clear();
                        checkLanguageExists();
                        getCPoints();
                    }
                });
                AlertDialog alert=alertDialog.create();
                alert.show();

            }
        });

        hobbiesAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View hobbiesAddLayout=layoutInflater.inflate(R.layout.dialog_edit_hobbies,null);

                editHobbies=hobbiesAddLayout.findViewById(R.id.editHobbies);

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(hobbiesAddLayout);
                alertDialog.setCancelable(false);

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hobbyTitleDataSet.clear();
                        hobbyIdDataSet.clear();
                        hobbyMap.clear();
                        checkHobbyExists();
                        getCPoints();
                    }
                });
                AlertDialog alert=alertDialog.create();
                alert.show();

            }
        });

        coverLetterEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View coverLetterEditLayout=layoutInflater.inflate(R.layout.dialog_edit_cover_letter,null);

                editCoverLetter=coverLetterEditLayout.findViewById(R.id.editCoverLetter);

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(coverLetterEditLayout);
                alertDialog.setCancelable(false);

                if(profileCoverLetter.getText().toString().trim().equals("You have not added any cover letter")){
                    editCoverLetter.setText("");
                }
                else {
                    editCoverLetter.setText(profileCoverLetter.getText().toString().trim());
                }
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();

                    }
                });

                alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editCoverLetter();
                        getCPoints();
                    }
                });
                AlertDialog alert=alertDialog.create();
                alert.show();

            }
        });

        basicDetailsEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View basicDetailsEditLayout=layoutInflater.inflate(R.layout.dialog_edit_basic_details,null);

                editBasicDetailsCurrentDesignation=basicDetailsEditLayout.findViewById(R.id.editBasicDetailsCurrentDesignation);
                editBasicDetailsGender=basicDetailsEditLayout.findViewById(R.id.editBasicDetailsGender);
                editBasicDetailsDob=basicDetailsEditLayout.findViewById(R.id.editBasicDetailsDob);
                editBasicDetailsCurrentCity=basicDetailsEditLayout.findViewById(R.id.editBasicDetailsCurrentCity);
                editBasicDetailsHomeTown=basicDetailsEditLayout.findViewById(R.id.editBasicDetailsHomeTown);
                editBasicDetailsDobCalenderBtn=basicDetailsEditLayout.findViewById(R.id.editBasicDetailsDobIcon);

                editBasicDetailsCurrentDesignation.setText(profileCurrentDesignation.getText().toString());
                if(!profileDob.getText().toString().equals("You have not added any basic details")) {
                    editBasicDetailsDob.setText(profileDob.getText().toString());
                }
                editBasicDetailsCurrentCity.setText(profileCurrentCity.getText().toString());
                editBasicDetailsHomeTown.setText(profileHomeTown.getText().toString());
                editBasicDetailsGender.setPrompt(profileGender.getText().toString());

                final Calendar calendar = Calendar.getInstance();
                final  DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel(editBasicDetailsDob,calendar);
                    }

                };

                editBasicDetailsDobCalenderBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(getContext(), date, calendar
                                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });


                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),R.layout.custom_spinner_onboard_textview,genderOptions);
                editBasicDetailsGender.setAdapter(adapter);

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(basicDetailsEditLayout);
                alertDialog.setCancelable(false);

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editBasicDetails();
                        getCPoints();
                    }
                });

                AlertDialog alert=alertDialog.create();
                alert.show();
            }
        });
        setHobbies(view);
        setLanguages(view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        profileHobbiesRecyclerView.setAdapter(null);
        profileLanguagesRecyclerView.setAdapter(null);
        MySingleton.release();
    }

    private void getCPoints() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlGetCPoints, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    cpoints=arrayObject.getString("cpoints");
                    updateCPoints();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error Exists", Toast.LENGTH_SHORT).show();
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
                params.put("key","user_id");
                params.put("value",user_id);
                return params;
            }
        };
        MySingleton.getmInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void updateCPoints() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlUpdate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), "C Points updated", Toast.LENGTH_SHORT).show();
                ((ProfileActivity)getActivity()).setValues();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

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
                params.put("update_array[cpoints]",cpoints);
                params.put("key", "user_id");
                params.put("value", user_id);
                params.put("table","user");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void checkUserAdditionalDetails() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlDataExists, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    status=arrayObject.getInt("status");
                    if(status==200){
                        checkSocialDetails();
                    }
                    else{
                        Toast.makeText(getActivity(), "Check Again", Toast.LENGTH_SHORT).show();
                        insertAdditionalDetails();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error Exists", Toast.LENGTH_SHORT).show();
                insertAdditionalDetails();
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
                params.put("key","user_id");
                params.put("value",user_id);
                params.put("table","user_additional_details");
                return params;
            }
        };
        MySingleton.getmInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void checkSocialDetails() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlDataExists, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    status=arrayObject.getInt("status");
                    if(status==200){
                        setValues();
                    }
                    else{
                        Toast.makeText(getActivity(), "Check Again", Toast.LENGTH_SHORT).show();
                        insertSocialDetails();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error Exists", Toast.LENGTH_SHORT).show();
                insertSocialDetails();
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
                params.put("key","user_id");
                params.put("value",user_id);
                params.put("table","user_social_profile");
                return params;
            }
        };
        MySingleton.getmInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void insertSocialDetails() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlInsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    status=arrayObject.getInt("status");
                    if(status==200){

                        checkSocialDetails();
                    }
                    else{
                        Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
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
                params.put("table","user_social_profile");
                return params;
            }
        };
        MySingleton.getmInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void insertAdditionalDetails() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlInsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    status=arrayObject.getInt("status");
                    if(status==200){

                        checkSocialDetails();
                    }
                    else{
                        Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
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
                params.put("table","user_additional_details");
                return params;
            }
        };
        MySingleton.getmInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void checkLanguageExists() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlDataExists, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    status=arrayObject.getInt("status");
                    if(status==200){
                        getLanguageDetails();
                    }
                    else{
                        Toast.makeText(getActivity(), "Check Again", Toast.LENGTH_SHORT).show();
                        addToLanguageTable();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getActivity(), "Error Exists", Toast.LENGTH_SHORT).show();
                addToLanguageTable();
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
                params.put("key","language_name");
                params.put("value",editLanguages.getText().toString());
                params.put("table","language");
                return params;
            }
        };
        MySingleton.getmInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void addToLanguageTable() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlInsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    status=arrayObject.getInt("status");
                    if(status==200){
                        checkLanguageExists();
                    }
                    else{
                        Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error Add to Table", Toast.LENGTH_SHORT).show();
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
                params.put("insert_array[language_name]",editLanguages.getText().toString());
                params.put("table","language");
                return params;
            }
        };
        MySingleton.getmInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void getLanguageDetails() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlGetDetails, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    language_id=arrayObject.getString("language_id");
                    addLanguages();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error Get Language Details", Toast.LENGTH_SHORT).show();
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
                params.put("key","language_name");
                params.put("value",editLanguages.getText().toString());
                params.put("table","language");
                return params;
            }
        };
        MySingleton.getmInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void checkHobbyExists() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlDataExists, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    status=arrayObject.getInt("status");
                    if(status==200){
                        getHobbyDetails();
                    }
                    else{
                        Toast.makeText(getActivity(), "Check Again", Toast.LENGTH_SHORT).show();
                        addToHobbyTable();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                addToHobbyTable();
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
                params.put("key","hobby_name");
                params.put("value",editHobbies.getText().toString());
                params.put("table","hobby");
                return params;
            }
        };
        MySingleton.getmInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void addToHobbyTable() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlInsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    status=arrayObject.getInt("status");
                    if(status==200){
                        checkHobbyExists();
                    }
                    else{
                        Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
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
                params.put("insert_array[hobby_name]",editHobbies.getText().toString());
                params.put("table","hobby");
                return params;
            }
        };
        MySingleton.getmInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void getHobbyDetails() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlGetDetails, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    hobby_id=arrayObject.getString("hobby_id");
                    addHobbies();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
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
                params.put("key","hobby_name");
                params.put("value",editHobbies.getText().toString());
                params.put("table","hobby");
                return params;
            }
        };
        MySingleton.getmInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void editCoverLetter() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlUpdate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                setValues();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

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
                params.put("update_array[user_cover_letter]",editCoverLetter.getText().toString());
                params.put("key", "user_id");
                params.put("value", user_id);
                params.put("table","user_additional_details");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void addHobbies() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlInsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    status=arrayObject.getInt("status");
                    if(status==200){
                        getHobbies();
                    }
                    else{
                        Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
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
                params.put("insert_array[hobby_id]",hobby_id);
                params.put("table","user_hobby");
                return params;
            }
        };
        MySingleton.getmInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void addLanguages() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlInsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    status=arrayObject.getInt("status");
                    if(status==200){
                        getLanguages();
                    }
                    else{
                        Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error Add Language", Toast.LENGTH_SHORT).show();
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
                params.put("insert_array[language_id]",language_id);
                params.put("table","user_language");
                return params;
            }
        };
        MySingleton.getmInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void editContactDetails() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlUpdate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                setValues();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

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
                params.put("update_array[user_phone]",editContactDetailsPhone.getText().toString());
                params.put("update_array[user_address]",editContactDetailsAddress.getText().toString());
                params.put("key", "user_id");
                params.put("value", user_id);
                params.put("table","user_additional_details");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void editSocialDetails() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlUpdate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                setValues();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

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
                params.put("update_array[user_fb]",editSocialProfilesFacebook.getText().toString());
                params.put("update_array[user_tw]",editSocialProfilesTwitter.getText().toString());
                params.put("update_array[user_ln]",editSocialProfilesLinkedIn.getText().toString());
                params.put("update_array[user_qr]",editSocialProfilesQuora.getText().toString());
                params.put("key", "user_id");
                params.put("value", user_id);
                params.put("table","user_social_profile");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void updateLabel(EditText edittext, Calendar calendar) {
        String Format = "yy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(Format, Locale.UK);
        edittext.setText(sdf.format(calendar.getTime()));
    }

    private void editBasicDetails() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlUpdate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                setValues();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

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
                params.put("update_array[user_dob]",editBasicDetailsDob.getText().toString());
                params.put("update_array[user_designation]",editBasicDetailsCurrentDesignation.getText().toString());
                params.put("update_array[user_current_city]",editBasicDetailsCurrentCity.getText().toString());
                params.put("update_array[user_city]",editBasicDetailsHomeTown.getText().toString());
                params.put("key", "user_id");
                params.put("value", user_id);
                params.put("table","user_additional_details");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void setValues() {
        basicCount=0;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlPersonal, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONObject user=jsonObject.getJSONObject("user");
                    profileFullName.setText(user.getString("fname")+" "+user.getString("lname"));
                    profileEmail.setText(user.getString("email"));


                    JSONObject user_social_profile=jsonObject.getJSONObject("user_social_profile");
                    if(user_social_profile.getString("user_fb").isEmpty()){
                        profileFacebook.setText("Not Provided");
                    }
                    else{
                        profileFacebook.setText(user_social_profile.getString("user_fb"));
                    }

                    if(user_social_profile.getString("user_ln").isEmpty()){
                        profileLinkedIn.setText("Not Provided");
                    }
                    else{
                        profileLinkedIn.setText(user_social_profile.getString("user_ln"));
                    }

                    if(user_social_profile.getString("user_tw").isEmpty()){
                        profileTwitter.setText("Not Provided");
                    }
                    else{
                        profileTwitter.setText(user_social_profile.getString("user_tw"));
                    }

                    if(user_social_profile.getString("user_qr").isEmpty()){
                        profileQuora.setText("Not Provided");
                    }
                    else{
                        profileQuora.setText(user_social_profile.getString("user_qr"));
                    }

                    JSONObject user_additional_details=jsonObject.getJSONObject("user_additional_details");

                    if(user_additional_details.getString("user_designation").isEmpty()){
                        profileCurrentDesignation.setText("");
                        basicCount++;
                    }
                    else{
                        profileCurrentDesignation.setText(user_additional_details.getString("user_designation"));
                    }

                    if(user_additional_details.getString("user_phone").isEmpty()){
                        profilePhone.setText("Not Provided");
                    }
                    else{
                        profilePhone.setText(user_additional_details.getString("user_phone"));
                    }

                    if(user_additional_details.getString("user_cover_letter").isEmpty()) {
                        profileCoverLetter.setText("You have not added any cover letter");
                    }
                    else{
                        profileCoverLetter.setText(user_additional_details.getString("user_cover_letter"));
                    }

                    if(user_additional_details.getString("user_current_city").isEmpty()){
                        profileCurrentCity.setText("");
                        basicCount++;
                    }
                    else{
                        profileCurrentCity.setText(user_additional_details.getString("user_current_city")+"/");
                    }

                    if(user_additional_details.getString("user_city").isEmpty()){
                        profileHomeTown.setText("");
                        basicCount++;
                    }
                    else{
                        profileHomeTown.setText(user_additional_details.getString("user_city"));
                    }

                    if(user_additional_details.getString("user_gender").isEmpty()){
                        profileGender.setText("");
                        basicCount++;
                    }
                    else{
                        profileGender.setText("("+user_additional_details.getString("user_gender")+")");
                    }
                    if(user_additional_details.getString("user_dob").isEmpty() || user_additional_details.getString("user_dob").equals("0000-00-00")){
                        profileDob.setText("");
                        basicCount++;
                    }
                    else{
                        profileDob.setText(user_additional_details.getString("user_dob"));
                    }

                    if(user_additional_details.getString("user_address").isEmpty()){
                        profileAddress.setText("Not Provided");
                    }
                    else{
                        profileAddress.setText(user_additional_details.getString("user_address"));
                    }

                    if(basicCount==5){
                        profileDob.setText("You have not added any basic details");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

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
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }
    private void setHobbies(View view)
    {
        profileHobbiesRecyclerView=view.findViewById(R.id.profileHobbiesRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        profileHobbiesRecyclerView.setLayoutManager(layoutManager);

        getHobbies();
    }

    private void getHobbies(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, allRows, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++) {
                        arrayObject = parentArray.getJSONObject(i);
                        hobbyMap.put(arrayObject.getString("hobby_id"),arrayObject.getString("hobby_name"));
                    }
                    setHobbies();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

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
                params.put("key", "hobby_id");
                params.put("order", "ASC");
                params.put("table", "hobby");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void setHobbies(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, oneRow, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                profileHobbiesTextDialog.setVisibility(View.INVISIBLE);
                //Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    if(parentArray.length()==0){
                        profileHobbiesTextDialog.setVisibility(View.VISIBLE);
                    }
                    for(int i=0;i<parentArray.length();i++) {
                        arrayObject = parentArray.getJSONObject(i);
                        hobbyIdDataSet.add(arrayObject.getString("uh_id"));
                        hobbyTitleDataSet.add(hobbyMap.get(arrayObject.getString("hobby_id")));
                    }
                    adapter= new ProfilePersonalHobbiesAdapter(getActivity(),hobbyIdDataSet,hobbyTitleDataSet);
                    profileHobbiesRecyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    profileHobbiesTextDialog.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

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
                params.put("table", "user_hobby");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void setLanguages(View view) {

        profileLanguagesRecyclerView=view.findViewById(R.id.profileLanguagesRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        profileLanguagesRecyclerView.setLayoutManager(layoutManager);

        getLanguages();
    }

    private void getLanguages(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, allRows, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++) {
                        arrayObject = parentArray.getJSONObject(i);
                        languageMap.put(arrayObject.getString("language_id"),arrayObject.getString("language_name"));
                    }
                    setLanguages();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
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
                params.put("key", "language_id");
                params.put("order", "ASC");
                params.put("table", "language");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void setLanguages(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, oneRow, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                profileLanguagesTextDialog.setVisibility(View.INVISIBLE);
                //Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    if(parentArray.length()==0){
                        profileLanguagesTextDialog.setVisibility(View.VISIBLE);
                    }
                    for(int i=0;i<parentArray.length();i++) {
                        arrayObject = parentArray.getJSONObject(i);
                        languageIdDataSet.add(arrayObject.getString("ul_id"));
                        languageTitleDataSet.add(languageMap.get(arrayObject.getString("language_id")));
                    }
                    adapter= new ProfilePersonalLanguagesAdapter(getActivity(),languageIdDataSet,languageTitleDataSet);
                    profileLanguagesRecyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                profileLanguagesTextDialog.setVisibility(View.VISIBLE);
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
                params.put("table", "user_language");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

    public class ProfilePersonalLanguagesAdapter extends RecyclerView.Adapter<ProfilePersonalLanguagesAdapter.ProfilePersonalLanguagesHolder> {

        private Context context;
        private ArrayList<String> idDataSet,titleDataSet;

        public ProfilePersonalLanguagesAdapter(Context context, ArrayList<String> idDataSet, ArrayList<String> titleDataSet) {
            this.context = context;
            this.idDataSet = idDataSet;
            this.titleDataSet = titleDataSet;
        }

        @NonNull
        @Override
        public ProfilePersonalLanguagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profile_personal_language,parent,false);
            ProfilePersonalLanguagesHolder holder=new ProfilePersonalLanguagesHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ProfilePersonalLanguagesHolder holder, final int position) {
            holder.ProfilePersonalLanguagesTitle.setText(titleDataSet.get(position));
            holder.profileLanguageDeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, idDataSet.get(position), Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alert = new AlertDialog.Builder(
                            getActivity());
                    alert.setTitle("Alert!!");
                    alert.setMessage("Are you sure to delete record");
                    alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //do your work here
                            deleteLanguage(idDataSet.get(position));
                            dialog.dismiss();

                        }
                    });
                    alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });

                    alert.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return titleDataSet.size();
        }

        public class ProfilePersonalLanguagesHolder extends RecyclerView.ViewHolder{

            public TextView ProfilePersonalLanguagesTitle;
            public ImageView profileLanguageDeleteBtn;

            public ProfilePersonalLanguagesHolder(View itemView) {
                super(itemView);
                ProfilePersonalLanguagesTitle=itemView.findViewById(R.id.profilePersonalLanguage);
                profileLanguageDeleteBtn=itemView.findViewById(R.id.profileLanguageDeleteBtn);
            }
        }
    }

    private void deleteLanguage(final String ul_id) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlDelete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    //status=arrayObject.getInt("status");
                    if(arrayObject.getString("status").equals("deleted")){
                        languageIdDataSet.clear();
                        languageTitleDataSet.clear();
                        setLanguages();
                        Toast.makeText(getActivity(), "Deletion done", Toast.LENGTH_SHORT).show();
                        getCPoints();
                    }
                    else{
                        Toast.makeText(getActivity(), "Check Again", Toast.LENGTH_SHORT).show();
                        addToHobbyTable();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

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
                params.put("key", "ul_id");
                params.put("value", ul_id);
                params.put("table", "user_language");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

    public class ProfilePersonalHobbiesAdapter extends RecyclerView.Adapter<ProfilePersonalHobbiesAdapter.ProfilePersonalHobbiesHolder> {

        private Context context;
        private ArrayList<String> idDataSet,titleDataSet;

        public ProfilePersonalHobbiesAdapter(Context context, ArrayList<String> idDataSet, ArrayList<String> titleDataSet) {
            this.context = context;
            this.idDataSet = idDataSet;
            this.titleDataSet = titleDataSet;
        }

        @NonNull
        @Override
        public ProfilePersonalHobbiesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profile_personal_hobby,parent,false);
            ProfilePersonalHobbiesHolder holder=new ProfilePersonalHobbiesHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ProfilePersonalHobbiesHolder holder, final int position) {
            holder.ProfilePersonalHobbiesTitle.setText(titleDataSet.get(position));
            holder.profileHobbyDeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, idDataSet.get(position), Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alert = new AlertDialog.Builder(
                            getActivity());
                    alert.setTitle("Alert!!");
                    alert.setMessage("Are you sure to delete record");
                    alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //do your work here
                            deleteHobby(idDataSet.get(position));
                            dialog.dismiss();

                        }
                    });
                    alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });

                    alert.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return titleDataSet.size();
        }

        public class ProfilePersonalHobbiesHolder extends RecyclerView.ViewHolder{

            public TextView ProfilePersonalHobbiesTitle;
            public ImageView profileHobbyDeleteBtn;

            public ProfilePersonalHobbiesHolder(View itemView) {
                super(itemView);

                ProfilePersonalHobbiesTitle=itemView.findViewById(R.id.profilePersonalHobby);
                profileHobbyDeleteBtn=itemView.findViewById(R.id.profileHobbyDeleteBtn);
            }
        }
    }
    private void deleteHobby(final String uh_id) {

            StringRequest stringRequest=new StringRequest(Request.Method.POST,urlDelete, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject arrayObject=new JSONObject(response);
                        //status=arrayObject.getInt("status");
                        if(arrayObject.getString("status").equals("deleted")){
                            hobbyIdDataSet.clear();
                            hobbyTitleDataSet.clear();
                            setHobbies();
                            Toast.makeText(getActivity(), "Deletion done", Toast.LENGTH_SHORT).show();
                            getCPoints();
                        }
                        else{
                            Toast.makeText(getActivity(), "Check Again", Toast.LENGTH_SHORT).show();
                            addToHobbyTable();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

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
                    params.put("key", "uh_id");
                    params.put("value", uh_id);
                    params.put("table", "user_hobby");
                    return params;
                }
            };
            MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
        }
}