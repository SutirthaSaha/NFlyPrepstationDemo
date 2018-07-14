package in.nfly.dell.nflydemo.fragments;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
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
import android.widget.Toolbar;

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
import in.nfly.dell.nflydemo.activities.MainActivity;
import in.nfly.dell.nflydemo.activities.SalaryCalculatorActivity;
import in.nfly.dell.nflydemo.activities.singleActivities.PracticeTestActivity;
import in.nfly.dell.nflydemo.adapters.LearnTipsAdapter;
import in.nfly.dell.nflydemo.adapters.ProfilePersonalHobbiesAdapter;
import in.nfly.dell.nflydemo.adapters.ProfilePersonalLanguagesAdapter;

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

    private HashMap<String,String> languageMap=new HashMap<String,String>(){};
    private HashMap<String,String> hobbyMap=new HashMap<String,String>(){};
    
    private ImageView profileImage;

    private ImageView basicDetailsEditBtn, coverLetterEditBtn, hobbiesAddBtn,
    languagesAddBtn, contactDetailsEditBtn,socialProfilesEditBtn, editBasicDetailsDobCalenderBtn;

    private String user_id;

    private RecyclerView profileHobbiesRecyclerView;
    private RecyclerView profileLanguagesRecyclerView;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<String> dataSet=new ArrayList<String>(){
        {
            add("painting");
            add("reading");
           }};

    private ArrayList<String> languageIdDataSet=new ArrayList<String>(){};
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

        setValues();
        socialProfilesEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View contactDetailsEditLayout=layoutInflater.inflate(R.layout.dialog_edit_social_profiles,null);

                editSocialProfilesFacebook=contactDetailsEditLayout.findViewById(R.id.editSocialProfilesFacebook);
                editSocialProfilesLinkedIn=contactDetailsEditLayout.findViewById(R.id.editSocialProfilesLinkedIn);
                editSocialProfilesQuora=contactDetailsEditLayout.findViewById(R.id.editSocialProfilesQuora);
                editSocialProfilesTwitter=contactDetailsEditLayout.findViewById(R.id.editSocialProfilesTwitter);

                editSocialProfilesFacebook.setText(profileFacebook.getText().toString());
                editSocialProfilesLinkedIn.setText(profileLinkedIn.getText().toString());
                editSocialProfilesTwitter.setText(profileTwitter.getText().toString());
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

                editContactDetailsAddress.setText(profileAddress.getText().toString());
                editContactDetailsPhone.setText(profilePhone.getText().toString());

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
                        languageIdDataSet.clear();
                        addLanguages();
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
                        hobbyIdDataSet.clear();
                        addHobbies();
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

                editCoverLetter.setText(profileCoverLetter.getText().toString().trim());
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
                editBasicDetailsDob.setText(profileDob.getText().toString());
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
                        setHobbies();
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
                params.put("insert_array[hobby_id]",editHobbies.getText().toString().trim());
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
                        setLanguages();
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
                params.put("insert_array[language_id]",editLanguages.getText().toString().trim());
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
                params.put("update_array[user_qo]",editSocialProfilesQuora.getText().toString());
                params.put("key", "user_id");
                params.put("value", user_id);
                params.put("table","user_additional_details");
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
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlPersonal, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONObject user=jsonObject.getJSONObject("user");
                    profileFullName.setText(user.getString("fname")+" "+user.getString("lname"));
                    profileEmail.setText(user.getString("email"));

                    JSONObject user_additional_details=jsonObject.getJSONObject("user_additional_details");
                    profileCurrentDesignation.setText(user_additional_details.getString("user_designation"));
                    profilePhone.setText(user_additional_details.getString("user_phone"));

                    profileCoverLetter.setText(user_additional_details.getString("user_cover_letter"));

                    profileCurrentCity.setText(user_additional_details.getString("user_current_city"));
                    profileHomeTown.setText(user_additional_details.getString("user_city"));
                    profileGender.setText(user_additional_details.getString("user_gender"));
                    profileDob.setText(user_additional_details.getString("user_dob"));
                    profileAddress.setText(user_additional_details.getString("user_address"));

                    JSONObject user_social_profile=jsonObject.getJSONObject("user_social_profile");
                    profileFacebook.setText(user_social_profile.getString("user_fb"));
                    profileLinkedIn.setText(user_social_profile.getString("user_ln"));
                    profileTwitter.setText(user_social_profile.getString("user_tw"));
                    profileQuora.setText(user_social_profile.getString("user_qr"));

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
                //Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++) {
                        arrayObject = parentArray.getJSONObject(i);
                        hobbyIdDataSet.add(hobbyMap.get(arrayObject.getString("hobby_id")));
                    }
                    adapter= new ProfilePersonalHobbiesAdapter(hobbyIdDataSet);
                    profileHobbiesRecyclerView.setAdapter(adapter);
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
                //Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++) {
                        arrayObject = parentArray.getJSONObject(i);
                        languageIdDataSet.add(languageMap.get(arrayObject.getString("language_id")));
                    }
                    adapter= new ProfilePersonalLanguagesAdapter(languageIdDataSet);
                    profileLanguagesRecyclerView.setAdapter(adapter);
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
                params.put("table", "user_language");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }
}