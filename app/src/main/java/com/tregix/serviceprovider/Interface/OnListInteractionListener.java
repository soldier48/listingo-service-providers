package com.tregix.serviceprovider.Interface;

import com.tregix.serviceprovider.Model.Appointment;
import com.tregix.serviceprovider.Model.JobItem;
import com.tregix.serviceprovider.Model.Provider.ProviderModel;
import com.tregix.serviceprovider.Model.categories.Category;
import com.tregix.serviceprovider.Model.packages.PackageItem;

/**
 * Created by Gohar Ali on 12/13/2017.
 */

public interface OnListInteractionListener  {

     void onProviderListInteraction(ProviderModel item) ;

     void onCategoryListInteraction(Category item) ;

     void onAppointmentInteraction(Appointment item, int pos) ;

     void onProviderFavorite(ProviderModel item);

     void onJobItemSelection(JobItem item);

     void onUserMessageSelection(String path, int post);

     void onPackageSelection(PackageItem item);

     void removeItem(int position);
}
