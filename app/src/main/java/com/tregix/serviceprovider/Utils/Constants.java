package com.tregix.serviceprovider.Utils;

import com.tregix.serviceprovider.Retrofit.ProviderApi;

/**
 * Created by Gohar Ali on 12/25/2017.
 */

public class Constants {

    public static final String IS_RESULT_ACTIVITY = "isResultActivity";
    public  static final int GOOGLE_API_CLIENT_ID = 1;
    public static final String IS_FEATURED_CODE = "1";
    public static final String LOCALE = "locale";
    public static final String POSITION = "position";

    public enum Errors { CATEGORY_FAILED(1), FEATURED(2), PROVIDER(3), SEARCH(4), COUNTRY_LOAD_FAILED(5), APPOINTMENT_FAILED(6), FAILED(7);

        private int value;

        private Errors(int value){
            this.value=value;
        }

        public static int value(String s)
        {
            return Errors.valueOf(s).value;
        }

    }

    public static final String KEYWORD = "keyword";
    public static final String LOCATION = "geo";
    public static final String COUNTRY = "country";
    public static final String CITY = "city";
    public static final String ZIP_CODE = "zipCode";
    public static final String DISTANCE = "distance";
    public static final String IS_FEATURED = "isFeatured";
    public static final String SUB_CATEGORY = "subCategory";
    public static final String CATEGORY = "category";
    public static final String DATA = "data";
    public static final String EMPTY_STRING = "";
    public static final String ISFIRSTTIME = "isFirstTime";
    public static final String ISUSERLOGGEDIN = "isUserLoggedIn";
    public static final String TITLE = "title";
    public static final String ID = "id";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String SELECTED_ITEM="selected";
    public static final String CATEGORY_ID = "categoryId";

    public static final String USERNAME="userName";
    public static final String PASSWORD = "password";


    //Navigation Bar URLs
    public static final String ABOUTUS = ProviderApi.BASE_SITE + "about";
    public static final String CONTACTUS = ProviderApi.BASE_SITE + "contact-us";
    public static final String TERMSOFUSE = ProviderApi.BASE_SITE + "terms-of-use";
    public static final String HELPSUPPORT = ProviderApi.BASE_SITE + "help-support";
    public static final String URL = "url";
    public static final String SUCCESS = "success";

    //Analytics Events
    public static final String EVENT_LOGIN = "login";
    public static final String EVENT_SIGNUP = "Signup";
    public static final String EVENT_SEARCH = "Search";
    public static final String EVENT_ADVANCE_SEARCH = "Advance Search";
    public static final String EVENT_APPOINTMENT = "Book Appointment";
    public static final String EVENT_CALL= "Call Provider";
    public static final String EVENT_EMAIL= "Email Provider";
    public static final String EVENT_WEBSITE= "Provider Website";
    public static final String EVENT_CLICK= "Click";

    public static final String KEY_EMAIL = "Email";
    public static final String KEY_KEYWORD = "Keyword";
    public static final String KEY_LOCATION = "Location";
    public static final String KEY_CATEGORY = "Category";

    public static final int REQUEST_KEY_LOGIN= 100;

}
