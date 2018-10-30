package com.example.macchhindra.realmdatabase.presenter;

import com.example.macchhindra.realmdatabase.module.MyModule;

import java.util.List;

import io.realm.Realm;

public interface Contract {
    interface  View {
        void setRecyclerView(List<MyModule> data);


        void writeToDatabase(List<MyModule> data,Realm realm);
    }
    interface Presenter{
        void getDataFromApi();
    }
}
