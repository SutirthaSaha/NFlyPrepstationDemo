package in.nfly.dell.nflydemo.fragments;


import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.activities.SalaryCalculatorActivity;
import in.nfly.dell.nflydemo.activities.singleActivities.PracticeTestActivity;
import in.nfly.dell.nflydemo.adapters.LearnTipsAdapter;

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

    private String urlPersonal="http://nfly.in/profileapi/personal_details";
    private String urlUpdate="http://nfly.in/gapi/update";

    private ImageView profileImage;

    private ImageView basicDetailsEditBtn, coverLetterAddBtn, coverLetterEditBtn, hobbiesAddBtn, hobbiesEditBtn, languagesEditBtn,
    languagesAddBtn, contactDetailsEditBtn,socialProfilesEditBtn;

    private String user_id;

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

        profileEmail=view.findViewById(R.id.profileEmail);
        profilePhone=view.findViewById(R.id.profilePhone);
        profileAddress=view.findViewById(R.id.profileAddress);

        basicDetailsEditBtn=view.findViewById(R.id.basicDetailsEditBtn);
        coverLetterAddBtn=view.findViewById(R.id.coverLetterAddBtn);
        coverLetterEditBtn=view.findViewById(R.id.coverLetterEditBtn);
        hobbiesAddBtn=view.findViewById(R.id.hobbiesAddBtn);
        hobbiesEditBtn=view.findViewById(R.id.hobbiesEditBtn);
        languagesAddBtn=view.findViewById(R.id.languagesAddBtn);
        languagesEditBtn=view.findViewById(R.id.languagesEditBtn);
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

        languagesEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View languagesEditLayout=layoutInflater.inflate(R.layout.dialog_edit_languages,null);

                editLanguages=languagesEditLayout.findViewById(R.id.editLanguages);

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(languagesEditLayout);
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

                        Toast.makeText(getActivity(), "Submit clicked", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(), "Submit clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alert=alertDialog.create();
                alert.show();

            }
        });

        hobbiesEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View hobbiesEditLayout=layoutInflater.inflate(R.layout.dialog_edit_hobbies,null);

                editHobbies=hobbiesEditLayout.findViewById(R.id.editHobbies);

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(hobbiesEditLayout);
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
                        Toast.makeText(getActivity(), "Submit clicked", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(), "Submit clicked", Toast.LENGTH_SHORT).show();
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

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();

                    }
                });

                alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Submit clicked", Toast.LENGTH_SHORT).show();
                        if(!editCoverLetter.getText().toString().isEmpty()){
                            profileCoverLetter.setText(editCoverLetter.getText().toString());
                        }
                    }
                });
                AlertDialog alert=alertDialog.create();
                alert.show();

            }
        });

        coverLetterAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View coverLetterAddLayout=layoutInflater.inflate(R.layout.dialog_edit_cover_letter,null);

                editCoverLetter=coverLetterAddLayout.findViewById(R.id.editCoverLetter);

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(coverLetterAddLayout);
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
                        Toast.makeText(getActivity(), "Submit clicked", Toast.LENGTH_SHORT).show();
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

                editBasicDetailsCurrentDesignation.setText(profileCurrentDesignation.getText().toString());
                editBasicDetailsDob.setText(profileDob.getText().toString());
                editBasicDetailsCurrentCity.setText(profileCurrentCity.getText().toString());
                editBasicDetailsHomeTown.setText(profileHomeTown.getText().toString());
                editBasicDetailsGender.setPrompt(profileGender.getText().toString());

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
        return view;
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
}