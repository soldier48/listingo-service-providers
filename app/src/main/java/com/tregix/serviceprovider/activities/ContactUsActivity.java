package com.tregix.serviceprovider.activities;


import android.os.Bundle;


import com.tregix.serviceprovider.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactUsActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactUsActivity extends BaseActivity {


    public ContactUsActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment ContactUsActivity.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactUsActivity newInstance() {
        ContactUsActivity fragment = new ContactUsActivity();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_contact_us);

    }


}
