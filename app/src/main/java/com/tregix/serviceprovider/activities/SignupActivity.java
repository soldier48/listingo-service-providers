package com.tregix.serviceprovider.activities;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.tregix.serviceprovider.DataManager.CategoryDataManager;
import com.tregix.serviceprovider.Interface.OnSignupLoginListener;
import com.tregix.serviceprovider.Model.Login.User;
import com.tregix.serviceprovider.Model.RegisterBusiness;
import com.tregix.serviceprovider.Model.RegisterUser;
import com.tregix.serviceprovider.Model.categories.Category;
import com.tregix.serviceprovider.Model.categories.SubCategory;
import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Retrofit.RetrofitUtil;
import com.tregix.serviceprovider.Utils.AppUtils;
import com.tregix.serviceprovider.Utils.Constants;
import com.tregix.serviceprovider.Utils.DatabaseUtil;
import com.tregix.serviceprovider.Utils.UtilFirebaseAnalytics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.tregix.serviceprovider.Retrofit.RetrofitUtil.SignupUser;

public class SignupActivity extends BaseActivity implements OnSignupLoginListener {

    private EditText name;
    private EditText firstName;
    private EditText lastName;
    private Spinner gender;
    private View viewProfessionalFields;
    private View viewCategoryFields;
    private View viewBusinessTypeFields;
    private View viewSingleLinePhone;
    private EditText compamyTitle;
    private EditText phone;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Spinner categories;
    private Spinner subCategories;
    private RadioGroup registerType;
    private Button submit;
    private Map<String, List<String>> categoriesMap;

    private Button registerSingleUser;
    private Button registerBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setTitle(R.string.register_now);

        categoriesMap = new HashMap<>();
        initViews();
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAcitivty(getIntent(),LoginActivity.class);
            }
        });

        List<Category> categoriesList = DatabaseUtil.getInstance().getCategoriesList();
        if (categoriesList != null && !categoriesList.isEmpty()) {
            onCategoriesLoad(categoriesList);
        }else{
            new CategoryDataManager().loadDataFromServer(this,true);
        }
    }

    private void initViews() {

        name = findViewById(R.id.register_name);
        firstName = findViewById(R.id.register_first_name);
        lastName = findViewById(R.id.register_lastname);
        gender = findViewById(R.id.register_gender);
        viewProfessionalFields = findViewById(R.id.register_names);
        viewCategoryFields = findViewById(R.id.register_category_view);
        viewBusinessTypeFields = findViewById(R.id.register_type_view);
        viewSingleLinePhone = findViewById(R.id.single_line_phone);
        compamyTitle = findViewById(R.id.register_company);
        phone = findViewById(R.id.register_location);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        confirmPassword = findViewById(R.id.register_retype_password);
        categories = findViewById(R.id.register_category);
        subCategories = findViewById(R.id.register_subcategory);
        submit = findViewById(R.id.register_submit);
        registerSingleUser = findViewById(R.id.register_single_user);
        registerBusiness = findViewById(R.id.register_business);
        registerType = (RadioGroup) findViewById(R.id.register_type);

        registerBusiness.setSelected(true);
        registerSingleUser.setSelected(false);
        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (categoriesMap != null && !categoriesMap.isEmpty()) {
                    Set<String> keys = categoriesMap.keySet();
                    String[] keysArray = keys.toArray(new String[keys.size()]);
                    String[] subCat = categoriesMap.get(keysArray[i]).toArray(new String[categoriesMap.get(keysArray[i]).size()]);

                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                            (SignupActivity.this, android.R.layout.simple_spinner_item,
                                    subCat);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                            .simple_spinner_dropdown_item);
                    subCategories.setAdapter(spinnerArrayAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        registerType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radio_business) {
                    hideShowTypeBasedViews(View.GONE);
                } else if (checkedId == R.id.radio_professional) {
                    hideShowTypeBasedViews(View.VISIBLE);
                }

            }

        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
        registerSingleUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerSingleUser.setSelected(true);
                registerBusiness.setSelected(false);
                viewSingleLinePhone.setVisibility(View.GONE);
                hideShowTypeBasedViews(View.VISIBLE);
                hideCategoryViews(View.GONE);
                viewBusinessTypeFields.setVisibility(View.GONE);
                compamyTitle.setVisibility(View.GONE);
                registerSingleUser.setBackgroundColor(getResources().getColor(R.color.background));
                registerBusiness.setBackgroundColor(getResources().getColor(R.color.cardview_light_background));
            }
        });
        registerBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerSingleUser.setSelected(false);
                registerBusiness.setSelected(true);
                viewSingleLinePhone.setVisibility(View.VISIBLE);

                registerBusiness.setBackgroundColor(getResources().getColor(R.color.background));
                registerSingleUser.setBackgroundColor(getResources().getColor(R.color.cardview_light_background));

                hideShowTypeBasedViews(View.GONE);
                hideCategoryViews(View.VISIBLE);
                compamyTitle.setVisibility(View.VISIBLE);
                viewBusinessTypeFields.setVisibility(View.VISIBLE);
                registerType.check(R.id.radio_business);
            }
        });
    }

    private void hideShowTypeBasedViews(int visibility) {
        viewProfessionalFields.setVisibility(visibility);
        firstName.setVisibility(visibility);
        lastName.setVisibility(visibility);
        gender.setVisibility(visibility);
    }

    private void hideCategoryViews(int visibility) {
        viewCategoryFields.setVisibility(visibility);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isTaskRoot()) {
            openAcitivty(NavigationDrawerActivity.class);
        }

    }

    private void signUp() {
        RegisterUser businessUser = new RegisterUser();
        if (isValid(name)) {
            businessUser.setUsername(name.getText().toString());
        } else {
            return;
        }
        if (compamyTitle.getVisibility() == View.VISIBLE) {
            if (isValid(compamyTitle)) {
                businessUser.setCompanyName(compamyTitle.getText().toString());
            } else {
                return;
            }
        }
        if (firstName.getVisibility() == View.VISIBLE){
            if (isValid(firstName)) {
                businessUser.setFirstName(firstName.getText().toString());
            } else {
                return;
            }
        }
        if (lastName.getVisibility() == View.VISIBLE){
            if (isValid(lastName)) {
                businessUser.setLastName(lastName.getText().toString());
            } else {
                return;
            }
        }
        if (isValid(email)) {
            businessUser.setEmail(email.getText().toString());
        } else {
            return;
        }
        if (isValid(phone)) {
            businessUser.setPhone(phone.getText().toString());
        } else {
            return;
        }
        if (isValid(password)) {
            businessUser.setPassword(password.getText().toString());
        } else {
            return;
        }
        if (isValid(confirmPassword)) {
            businessUser.setConfirmPassword(confirmPassword.getText().toString());
        } else {
            return;
        }
        if (gender.getVisibility() == View.VISIBLE) {
            if (gender.getSelectedItem().toString().toLowerCase().equals("gender")) {
                AppUtils.showDialog(this,"Gender is required!", null);
                return;
            } else {
                businessUser.setGender(gender.getSelectedItem().toString());
            }
        }

        if(subCategories != null && categories != null &&
                viewCategoryFields.getVisibility() == View.VISIBLE &&
                categories.getSelectedItem() != null &&
                subCategories.getSelectedItem() != null) {
            int catId = DatabaseUtil.getInstance().getCategoryID(categories.getSelectedItem().toString());
            businessUser.setCategory(catId + "");
            businessUser.setSubCategory(DatabaseUtil.getInstance().getSubCategoriesListId(catId
                    , subCategories.getSelectedItem().toString()));
        }

        if (registerBusiness.isSelected()) {

            int selectedId = registerType.getCheckedRadioButtonId();

            if(selectedId == R.id.radio_business) {
                businessUser.setType("business");
            }else{
                businessUser.setType("professional");
            }
        } else {
            businessUser.setType("seeker");
        }

        showProgressDialog(getString(R.string.sign_up));


        RegisterBusiness business = new RegisterBusiness(businessUser);
        RetrofitUtil.createProviderAPI().registerUser(business).enqueue(SignupUser(this));
    }


    private boolean isValid(EditText view) {
        if (view.getText() != null && !view.getText().toString().isEmpty()) {
            return true;
        } else {
            view.setError(getString(R.string.required_field));
            return false;
        }
    }

    @Override
    public void onCategoriesLoad(List<Category> items) {
        for (Category item : items) {
            List<String> subCat = new ArrayList<>();
            for (SubCategory subCategory : item.getSubCategories()) {
                subCat.add(subCategory.getTitle());
            }
            categoriesMap.put(Html.fromHtml(item.getTitle()).toString(), subCat);
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        categoriesMap.keySet().toArray(new String[categoriesMap.keySet().size()]));
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        categories.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public void onSignup(User user) {
        hideProgressDialog();
        if(user.getType().toLowerCase().equals(Constants.SUCCESS)) {
            UtilFirebaseAnalytics.logEvent(Constants.EVENT_SIGNUP,Constants.KEY_EMAIL,email.getText().toString());
            showDialogSignedUp(user.getMessage(), null);
        }else{
            AppUtils.showDialog(this,user.getMessage(), null);
        }
    }

    @Override
    public void onLoginUser(User data) {

    }


    @Override
    public void OnError(String error) {
        hideProgressDialog();
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
