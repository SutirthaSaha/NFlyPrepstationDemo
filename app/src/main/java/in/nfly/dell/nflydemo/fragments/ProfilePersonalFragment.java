package in.nfly.dell.nflydemo.fragments;


import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.activities.singleActivities.PracticeTestActivity;
import in.nfly.dell.nflydemo.adapters.LearnTipsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilePersonalFragment extends Fragment {

    private TextView profileFullName,profileCurrentDesignation,profileGender,profileDob,
            profileCurrentCity,profileHomeTown,profileCoverLeterTextDialog,
            profileHobbiesTextDialog,profileLanguagesTextDialog,profileFacebook,profileLinkedin,profileTwitter,
    profileQuora,profileEmail, profilePhone,profileAddress;

    private EditText editBasicDetailsName,editBasicDetailsCurrentDesignation,editBasicDetailsGender,editBasicDetailsDob,editBasicDetailsCurrentCity,editBasicDetailsHomeTown;
    private String urlPersonal="http://nfly.in/profileapi/personal_details";

    private ImageView profileImage;

    private ImageView basicDetailsEditBtn;

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

        profileCoverLeterTextDialog=view.findViewById(R.id.profileCoverLetterTextDialog);
        profileHobbiesTextDialog= view.findViewById(R.id.profileHobbiesTextDialog);
        profileLanguagesTextDialog=view.findViewById(R.id.profileLanguagesTextDialog);

        profileFacebook=view.findViewById(R.id.profileFacebook);
        profileTwitter=view.findViewById(R.id.profileTwitter);
        profileLinkedin=view.findViewById(R.id.profileLinkedin);
        profileQuora=view.findViewById(R.id.profileQuora);

        profileEmail=view.findViewById(R.id.profileEmail);
        profilePhone=view.findViewById(R.id.profilePhone);
        profileAddress=view.findViewById(R.id.profileAddress);

        basicDetailsEditBtn=view.findViewById(R.id.basicDetailsEditBtn);
        basicDetailsEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View basicDetailsEditLayout=layoutInflater.inflate(R.layout.dialog_edit_basic_details,null);

                editBasicDetailsName=basicDetailsEditLayout.findViewById(R.id.editBasicDetailsName);
                editBasicDetailsCurrentDesignation=basicDetailsEditLayout.findViewById(R.id.editBasicDetailsCurrentDesignation);
                editBasicDetailsGender=basicDetailsEditLayout.findViewById(R.id.editBasicDetailsGender);
                editBasicDetailsDob=basicDetailsEditLayout.findViewById(R.id.editBasicDetailsDob);
                editBasicDetailsCurrentCity=basicDetailsEditLayout.findViewById(R.id.editBasicDetailsCurrentCity);
                editBasicDetailsHomeTown=basicDetailsEditLayout.findViewById(R.id.editBasicDetailsHomeTown);

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
                        Toast.makeText(getActivity(), "Submit clicked", Toast.LENGTH_SHORT).show();
                        if(!editBasicDetailsName.getText().toString().isEmpty()){
                            profileFullName.setText(editBasicDetailsName.getText().toString());
                        }
                    }
                });

                AlertDialog alert=alertDialog.create();
                alert.show();
            }
        });
        setValues();
        return view;
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
                    profileLinkedin.setText(user_social_profile.getString("user_ln"));
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