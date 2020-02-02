package com.tregix.serviceprovider.Utils;

import android.text.Html;

import androidx.annotation.NonNull;

import com.tregix.serviceprovider.Model.Countries;
import com.tregix.serviceprovider.Model.Login.User;
import com.tregix.serviceprovider.Model.categories.Category;
import com.tregix.serviceprovider.Model.categories.SubCategory;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Gohar Ali on 12/24/2017.
 */

public class DatabaseUtil {

    private static Realm realm;
    private  static  DatabaseUtil databaseUtil;

    private DatabaseUtil(){
        realm = Realm.getDefaultInstance();
    }

    public static DatabaseUtil getInstance(){
        if(databaseUtil == null) {
            databaseUtil = new DatabaseUtil();
        }

        return databaseUtil;
    }
    public void storeCategoriesList(final List<Category> categories) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(categories);
            }
        });
    }

    public void storeUser(final User user) {
        deleteUser();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(user);
            }
        });
    }

    private void deleteUser() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<User> cat = realm.where(User.class).findAll();
                if(cat != null) {
                    cat.deleteAllFromRealm();
                }
            }
        });
    }

    public void updateUser(String banner, String avatar) {
        RealmResults<User> list = realm.where(User.class).findAll();

        if(list != null && list.size() > 0) {
            User toEdit = list.get(0);
            if (toEdit != null) {
                realm.beginTransaction();
                toEdit.getData().getData().setBanner(banner);
                toEdit.getData().getData().setAvatar(avatar);
                realm.commitTransaction();
            }
        }
    }

    public void storeCountries(final List<Countries> country) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Countries> cat = realm.where(Countries.class).findAll();
                if(cat != null) {
                    cat.deleteAllFromRealm();
                }
            }
        });

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(country);
            }
        });
    }
    public User getUser() {
            return realm.where(User.class).findFirst();
    }

    public int getUserID(){
        return getUser().getData().getID();
    }

    public List<Category>  getCategoriesList() {
        return realm.where(Category.class).findAll();
    }

    public String  getSubCategoriesListId(int cat,String subCat) {
        Category category = realm.where(Category.class).equalTo("id",cat).findFirst();
        if(category != null) {
            List<SubCategory> subCatList = category.getSubCategories();

            for (SubCategory subCategory : subCatList) {
                if (Html.fromHtml(subCategory.getTitle()).toString().toLowerCase().equals(subCat.toLowerCase())) {
                    return subCategory.getSlug();
                }
            }
        }

        return subCat;
    }

    public void delteCategoriesList() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Category> cat = realm.where(Category.class).findAll();
                if(cat != null) {
                    cat.deleteAllFromRealm();
                }
            }
        });

    }


    @NonNull
    public ArrayList<String> getCategories() {
        ArrayList<String> categories = new ArrayList<>();
        List<Category> list = getCategoriesList();
        for(Category category:list){
            categories.add(Html.fromHtml(category.getTitle()).toString());
        }
        return categories;
    }

    public int getCategoryID(String name){
        if(name != null && !name.isEmpty()) {
            List<Category> list = getCategoriesList();
            for (Category category : list) {
                if (Html.fromHtml(category.getTitle()).toString().toLowerCase().equals(name.toLowerCase())) {
                    return category.getId();
                }
            }
        }
        return -1;
    }

    @NonNull
    public ArrayList<String> getCountries() {
        ArrayList<String> categories = new ArrayList<>();

        List<Countries> list =   realm.where(Countries.class).findAll();
        for(Countries country:list){
            categories.add(Html.fromHtml(country.getName()).toString());
        }
        return categories;
    }

    @NonNull
    public ArrayList<String> getCities(String name) {
        ArrayList<String> categories = new ArrayList<>();

        List<Countries> list =   realm.where(Countries.class).equalTo("name",name).findAll();

        if(!list.isEmpty()) {
            categories.addAll(list.get(0).getCities());
        }

        return categories;
    }

    @NonNull
    public ArrayList<String> getSubCategories(String cat) {
        ArrayList<String> categories = new ArrayList<>();
        if(cat!= null) {

            List<Category> list = getCategoriesList();
            Category requiredCat = new Category();
            for (Category category : list) {
                if (Html.fromHtml(category.getTitle()).toString().equals(Html.fromHtml(cat).toString())) {
                    requiredCat = category;
                }
            }
            if (requiredCat.getSubCategories() != null && !requiredCat.getSubCategories().isEmpty()) {
                for (SubCategory category : requiredCat.getSubCategories()) {
                    categories.add(Html.fromHtml(category.getTitle()).toString());
                }
            }
        }
        return categories;
    }

    @NonNull
    public ArrayList<SubCategory> getAllSubCategories(String cat) {
        ArrayList<SubCategory> categories = new ArrayList<>();
        if(cat!= null){
        List<Category> list = getCategoriesList();
        Category requiredCat = new Category();
        for (Category category : list){
            if(Html.fromHtml(category.getTitle()).toString().equals(Html.fromHtml(cat).toString())){
                requiredCat = category;
            }
        }
        if(requiredCat.getSubCategories()!= null && !requiredCat.getSubCategories().isEmpty()) {
            for (SubCategory category : requiredCat.getSubCategories()) {
                categories.add(category);
            }
        }
        }
        return categories;
    }

}
