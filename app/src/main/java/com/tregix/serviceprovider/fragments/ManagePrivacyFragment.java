package com.tregix.serviceprovider.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.tregix.serviceprovider.Model.ManagePrivacyRequest;
import com.tregix.serviceprovider.Model.Provider.PrivacySettings;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Retrofit.RetrofitUtil;
import com.tregix.serviceprovider.Utils.DatabaseUtil;

import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.getPrivacySettings;
import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.sendRequest;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ManagePrivacyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManagePrivacyFragment extends BaseFragment {

    private Switch profilePic;
    private Switch bannerPic;
    private Switch services;
    private Switch gallery;
    private Switch team;
    private Switch videos;
    private Switch businessHours;
    private Switch contactForm;
    private Switch appointmentOption;
    private Button updatePrivacy;

    public ManagePrivacyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ManagePrivacyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManagePrivacyFragment newInstance() {
        ManagePrivacyFragment fragment = new ManagePrivacyFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_manage_privacy, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.title_privacy_setting));

        profilePic = view.findViewById(R.id.privacy_profile_pic);
        bannerPic = view.findViewById(R.id.privacy_banner_pic);
        services = view.findViewById(R.id.privacy_show_services);
        gallery = view.findViewById(R.id.privacy_show_gallery);
        team = view.findViewById(R.id.privacy_show_team);
        videos = view.findViewById(R.id.privacy_video);
        businessHours = view.findViewById(R.id.privacy_business_hour);
        contactForm = view.findViewById(R.id.privacy_contact);
        appointmentOption = view.findViewById(R.id.privacy_apt_opt);
        updatePrivacy = view.findViewById(R.id.privacy_update);

        showProgressDialog(getString(R.string.loading_settings));

        RetrofitUtil.createProviderAPI().getPrivacySettings
                (DatabaseUtil.getInstance().getUserID()).enqueue(getPrivacySettings(this));

        updatePrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrivacySettings settings = new PrivacySettings();
                if(appointmentOption.isChecked()){
                    settings.setProfileAppointment("on");
                }
                if(bannerPic.isChecked()){
                    settings.setProfileBanner("on");
                }
                if(profilePic.isChecked()){
                    settings.setProfilePhoto("on");
                }
                if(contactForm.isChecked()){
                    settings.setProfileContact("on");
                }
                if(gallery.isChecked()){
                    settings.setProfileGallery("on");
                }
                if(businessHours.isChecked()){
                    settings.setProfileHours("on");
                }
                if(services.isChecked()){
                    settings.setProfileService("on");
                }
                if(team.isChecked()){
                    settings.setProfileTeam("on");
                }
                if(videos.isChecked()){
                    settings.setProfileVideos("on");
                }

                ManagePrivacyRequest req = new ManagePrivacyRequest
                        (DatabaseUtil.getInstance().getUserID(),settings);

                showProgressDialog(getString(R.string.update_privacy));
                RetrofitUtil.createProviderAPI().updatePrivacySettings(req).
                        enqueue(sendRequest(ManagePrivacyFragment.this));
            }
        });

        return view;
    }

    @Override
    public void onPrivacyLoaded(PrivacySettings item) {

        if(item.getProfileAppointment() != null && item.getProfileAppointment().equals("on")){
            appointmentOption.setChecked(true);
        }
        if(item.getProfileBanner() != null && item.getProfileBanner().equals("on")){
            bannerPic.setChecked(true);
        }
        if(item.getProfilePhoto() != null && item.getProfilePhoto().equals("on")){
            profilePic.setChecked(true);
        }
        if(item.getProfileContact() != null && item.getProfileContact().equals("on")){
            contactForm.setChecked(true);
        }
        if(item.getProfileGallery() != null && item.getProfileGallery().equals("on")){
            gallery.setChecked(true);
        }
        if(item.getProfileHours() != null && item.getProfileHours().equals("on")){
            businessHours.setChecked(true);
        }
        if(item.getProfileService() != null && item.getProfileService().equals("on")){
            services.setChecked(true);
        }
        if(item.getProfileTeam() != null && item.getProfileTeam().equals("on")){
            team.setChecked(true);
        }
        if(item.getProfileVideos() != null && item.getProfileVideos().equals("on")){
            videos.setChecked(true);
        }

        hideProgressDialog();
    }

}
