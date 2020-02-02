package com.tregix.serviceprovider.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Utils.AppUtils;

import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

/**
 * Created by Gohar Ali on 2/12/2018.
 */

public class PhoneNumberVerificationActivity extends BaseActivity {
    public static final int TIMEOUT_DURATION = 60;
    private Button resend;
    private Button verify;
    private EditText code;
    private TextView title;
    private TextView msg;
    private String phoneNumber;
    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_verify_number);

        initViews();

        getSupportActionBar().setTitle(R.string.phone_num_verification);

        phoneNumber = getIntent().getStringExtra("number");

        title.setText(getString(R.string.verify) + " " + phoneNumber);
        msg.setText(getString(R.string.waiting_for_automatically_detect_an_sms_sent_to) + " " + phoneNumber);

        resend.setEnabled(false);
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendVerificationCode(phoneNumber, mResendToken);
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (code.getText() != null && !code.getText().toString().isEmpty() && mVerificationId != null && !mVerificationId.isEmpty()) {
                    String m_Text = code.getText().toString();
                    showProgressDialog(getString(R.string.msg_phone_verification));
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId
                            , m_Text);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                hideProgressDialog();
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(String s) {
                super.onCodeAutoRetrievalTimeOut(s);

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                // Log.w(TAG, "onVerificationFailed", e);
                hideProgressDialog();
                AppUtils.showDialog(PhoneNumberVerificationActivity.this,"Phone Number Verification Failed!", null);

            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;
                mResendToken = token;

                new CountDownTimer(60000, 1000) { // adjust the milli seconds here

                    public void onTick(long millisUntilFinished) {
                        resend.setText(""+String.format("%d sec",
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    }

                    public void onFinish() {
                        resend.setEnabled(true);
                        resend.setText(getString(R.string.title_resend));
                    }
                }.start();

                hideProgressDialog();
            }
        };

        showProgressDialog(getString(R.string.msg_phone_verification));
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                TIMEOUT_DURATION,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks         // OnVerificationStateChangedCallbacks
                );
    }

    private void initViews() {
        resend = findViewById(R.id.resend);
        verify = findViewById(R.id.verify);
        code = findViewById(R.id.code);
        title = findViewById(R.id.ui_verify_number);
        msg = findViewById(R.id.ui_title_sms);
    }

    public void resendVerificationCode(String phoneNumber,
                                       PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                TIMEOUT_DURATION,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(PhoneNumberVerificationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hideProgressDialog();
                        if (task.isSuccessful()) {
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(PhoneNumberVerificationActivity.this, R.string.code_not_valid, Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}
