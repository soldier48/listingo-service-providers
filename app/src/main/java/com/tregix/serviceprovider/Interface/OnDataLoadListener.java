package com.tregix.serviceprovider.Interface;

import com.tregix.serviceprovider.Model.Appointment;
import com.tregix.serviceprovider.Model.Countries;
import com.tregix.serviceprovider.Model.JobItem;
import com.tregix.serviceprovider.Model.Provider.BusinessHours;
import com.tregix.serviceprovider.Model.Provider.PrivacySettings;
import com.tregix.serviceprovider.Model.Provider.ProfileServices;
import com.tregix.serviceprovider.Model.Provider.ProviderModel;
import com.tregix.serviceprovider.Model.Provider.ProviderReviewListData;
import com.tregix.serviceprovider.Model.categories.Category;
import com.tregix.serviceprovider.Model.packages.PackageItem;
import com.tregix.serviceprovider.Utils.Constants;

import java.util.List;

/**
 * Created by Gohar Ali on 12/14/2017.
 */

public interface OnDataLoadListener {

    void onCategoriesLoad(List<Category> items);

    void onProviderLoad(List<ProviderModel> items);

    void onCountriesLoad(List<Countries> items);

    void onAppointmentsLoad(List<Appointment> items);

    void onServiceLoad(List<ProfileServices> items);

    void onReviewsLoad(List<ProviderReviewListData> items);

    void onUpdateFavorites(ProviderModel item);

    void onPrivacyLoaded(PrivacySettings item);

    void onBusinessHoursLoaded(BusinessHours item);

    void onJobsLoaded(List<JobItem> items);

    void onProfileLoaded(ProviderModel items);

    void onPackagesLoad(List<PackageItem> items);

    void onSuccess(String msg);

    void onError(Constants.Errors errorCode, String error);
}
