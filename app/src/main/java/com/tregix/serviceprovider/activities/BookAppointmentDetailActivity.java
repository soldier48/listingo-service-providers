package com.tregix.serviceprovider.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;
import com.tregix.serviceprovider.Model.ConfirmAppointment;
import com.tregix.serviceprovider.Model.Login.User;
import com.tregix.serviceprovider.Model.Provider.ProfileServices;
import com.tregix.serviceprovider.Model.Provider.ProviderModel;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Retrofit.RetrofitUtil;
import com.tregix.serviceprovider.Utils.AppUtils;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.Utils.DatabaseUtil;
import com.tregix.serviceprovider.chat.UserObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.confirmAppointement;
import static com.tregix.serviceprovider.Utils.Constants.EMPTY_STRING;
import static com.tregix.serviceprovider.chat.ChatActivity.META_DATA;
import static com.tregix.serviceprovider.chat.ChatActivity.USERS_CHILD;

public class BookAppointmentDetailActivity extends BaseActivity {

    public static final int REQUEST_CODE_SERVICE = 100;
    public static final int REQUEST_CODE_TYPE = 101;
    public static final int REQUEST_CODE_REASON = 102;
    public static final int REQUEST_CODE_VERIFICATION = 103;

    private TextView service;
    private TextView type;
    private TextView reason;

    private EditText name;
    private EditText email;
    private EditText phone;
    private EditText description;

    private Button confirmAppointment;
    private ConfirmAppointment appointment;
    private ProviderModel provider;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment_detail);

        initViews();

        getSupportActionBar().setTitle(getString(R.string.make_appointement));

        user = DatabaseUtil.getInstance().getUser();

        provider = (ProviderModel) getIntent().getBundleExtra(Constants.DATA).getSerializable(Constants.DATA);

        name.setText(user.getData().getData().getDisplayName());
        email.setText(user.getData().getData().getUserEmail());
        if (user.getData().getData().getMeta().getPhone() != null && user.getData().getData().getMeta().getPhone().size() > 0) {
            String num = user.getData().getData().getMeta().getPhone().first();
            String code = AppUtils.getCountryCode(this);
            if(num != null && !num.isEmpty()) {
                if(num.startsWith(code)) {
                    phone.setText(num);
                }else{
                    phone.setText(code+num);
                }
            }else{
                phone.setText(code);
            }
        }
        findViewById(R.id.appointment_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(BookAppointmentDetailActivity.this, SelectableItemActivity.class);
                ArrayList<String> categories = new ArrayList<>();
                for (ProfileServices services : provider.getProfileServices()) {
                    categories.add(services.getTitle());
                }
                newIntent.putStringArrayListExtra(Constants.DATA, categories);
                newIntent.putExtra(Constants.TITLE, getString(R.string.title_services));
                newIntent.putExtra(Constants.SELECTED_ITEM,service.getText().toString());
                startActivityForResult(newIntent, REQUEST_CODE_SERVICE);
            }
        });
        findViewById(R.id.appointment_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(BookAppointmentDetailActivity.this, SelectableItemActivity.class);
                ArrayList<String> categories = (ArrayList<String>) provider.getAppointmentTypes();
                newIntent.putStringArrayListExtra(Constants.DATA, categories);
                newIntent.putExtra(Constants.TITLE, getString(R.string.title_appointment_type));
                newIntent.putExtra(Constants.SELECTED_ITEM,type.getText().toString());
                startActivityForResult(newIntent, REQUEST_CODE_TYPE);
            }
        });
        findViewById(R.id.appointment_reason).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(BookAppointmentDetailActivity.this, SelectableItemActivity.class);
                ArrayList<String> categories = (ArrayList<String>) provider.getAppointmentReasons();
                newIntent.putStringArrayListExtra(Constants.DATA, categories);
                newIntent.putExtra(Constants.TITLE, getString(R.string.title_appointment_reason));
                newIntent.putExtra(Constants.SELECTED_ITEM,reason.getText().toString());
                startActivityForResult(newIntent, REQUEST_CODE_REASON);
            }
        });

        confirmAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                appointment = new ConfirmAppointment();
                if (isValid(service.getText().toString())) {
                    appointment.setAptServices(service.getText().toString());
                } else {
                    setError(service);
                    return;
                }
                if (isValid(type.getText().toString())) {
                    appointment.setAptTypes(type.getText().toString());
                } else {
                    setError(type);
                    return;
                }
                if (isValid(reason.getText().toString())) {
                    appointment.setAptReasons(reason.getText().toString());
                } else {
                    setError(reason);
                    return;
                }

                if (isValid(description.getText().toString())) {
                    appointment.setAptDescription(description.getText().toString());
                } else {
                    setError(description);
                    return;
                }

                if (isValid(name.getText().toString())) {
                    appointment.setAptName(name.getText().toString());
                } else {
                    setError(name);
                    return;
                }
                if (isValid(email.getText().toString())) {
                    appointment.setAptEmail(email.getText().toString());
                } else {
                    setError(email);
                    return;
                }
                if (isValid(phone.getText().toString())) {
                    appointment.setAptMobile(phone.getText().toString());
                } else {
                    setError(phone);
                    return;
                }


                appointment.setUserId(user.getData().getID());
                appointment.setAptCurrencySymbol(provider.getAppointmentCurrency());

                Intent intent = new Intent(BookAppointmentDetailActivity.this, PhoneNumberVerificationActivity.class);
                intent.putExtra("number", phone.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_VERIFICATION);

            }
        });
    }

    private void setError(View view){
        if(view instanceof TextView){
            ((TextView)view).setError("This field is required!");
        }
    }

    @Override
    public void onPositiveClick(String msg) {
        finish();
    }

    private boolean isValid(String text) {
       return text != null && !text.isEmpty();
    }

    private void initViews() {
        service = findViewById(R.id.appointment_service);
        type = findViewById(R.id.appointment_type);
        reason = findViewById(R.id.appointment_reason);

        name = findViewById(R.id.appointment_user_name);
        email = findViewById(R.id.appointment_user_email);
        phone = findViewById(R.id.appointment_user_phone);
        description = findViewById(R.id.appointment_description);

        confirmAppointment = findViewById(R.id.appointment_confirm);

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_SERVICE){
            if(resultCode == RESULT_OK){
                List categoryList = data.getStringArrayListExtra(Constants.DATA);
                if(categoryList != null && !categoryList.isEmpty()) {
                    service.setText(categoryList.get(0).toString());
                    service.setError(null);
                }else{
                    service.setText(EMPTY_STRING);
                }
            }
        }
        if(requestCode == REQUEST_CODE_TYPE){
            if(resultCode == RESULT_OK){
                List categoryList = data.getStringArrayListExtra(Constants.DATA);
                if(categoryList != null && !categoryList.isEmpty()) {
                    type.setText(categoryList.get(0).toString());
                    type.setError(null);
                }else{
                    type.setText(EMPTY_STRING);
                }
            }
        }
        if(requestCode == REQUEST_CODE_REASON){
            if(resultCode == RESULT_OK){
                List categoryList = data.getStringArrayListExtra(Constants.DATA);
                if(categoryList != null && !categoryList.isEmpty()) {
                    reason.setText(categoryList.get(0).toString());
                    reason.setError(null);
                }else{
                    reason.setText(EMPTY_STRING);
                }
            }
        }

        if(requestCode == REQUEST_CODE_VERIFICATION){
            if(resultCode == RESULT_OK){
                showProgressDialog(getString(R.string.msg_finalizing_appointment));
                RetrofitUtil.createProviderAPI().confirmAppointment(appointment).enqueue(confirmAppointement
                        (BookAppointmentDetailActivity.this));
            }
        }
    }

    @Override
    public void onSuccess(final String msg) {
        super.onSuccess(msg);
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference()
                .child(USERS_CHILD).child(provider.getID() + "").child(META_DATA);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserObject recevier = dataSnapshot.getValue(UserObject.class);
                if (recevier != null ){
                    try {
                        JSONObject notificationContent = new JSONObject("{\"contents\": {\"en\": \""+ Html.fromHtml(msg) +"\" }," +
                                "\"include_player_ids\": [\"" + recevier.getDeviceID() + "\"], " +
                                "\"headings\": {\"en\":  \""+ "New Appointment" +"\" }," +
                                "\"data\": { \"sender_id\":\"" + user.getData().getID() + "\"," +
                                "\"isAppointment\":\"" + true + "\"}}");
                        OneSignal.postNotification(notificationContent, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
