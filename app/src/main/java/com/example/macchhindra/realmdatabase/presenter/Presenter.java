package com.example.macchhindra.realmdatabase.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.macchhindra.realmdatabase.GerritAPI;
import com.example.macchhindra.realmdatabase.helper.RealmHelper;
import com.example.macchhindra.realmdatabase.module.MyModule;
import java.util.ArrayList;
import java.util.List;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Presenter implements Contract.Presenter {
    Retrofit retrofit;
    Context context;
    Contract.View view;
    Realm realm;

    public Presenter(Retrofit retrofit, Context context, Contract.View view,Realm realm) {
        this.retrofit = retrofit;
        this.context = context;
        this.view = view;
        this.realm = realm;
    }

    @Override
    public void getDataFromApi() {
        RealmHelper helper=new RealmHelper(realm);
        if (helper.retriveBody()!=null || helper.retriveBody().size()!= 0) {
            getOflineData();
            Toast.makeText(context, "I am from local database", Toast.LENGTH_SHORT).show();

        } else {
            GerritAPI gerritAPI = retrofit.create(GerritAPI.class);
            Call<List<MyModule>> call = gerritAPI.getData();
            call.enqueue(new Callback<List<MyModule>>() {
                @Override
                public void onResponse(Call<List<MyModule>> call, Response<List<MyModule>> response) {
                    if (response.isSuccessful()) {
                        List<MyModule> data = response.body();
                        RealmHelper helper = new RealmHelper(realm);
                        for (MyModule oflinedata: data) {
                            helper.save(oflinedata);
                        }
                        view.setRecyclerView(data);
                        Toast.makeText(context, "I am from Internet", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "It is not sucess", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<List<MyModule>> call, Throwable t) {

                }
            });
        }
    }

    public void getOflineData() {
        List<MyModule> mylist = new ArrayList<>();
        RealmHelper helper=new RealmHelper(realm);
        ArrayList<String>body = helper.retriveBody();
        ArrayList<String>title = helper.retriveTitle();
        for (int i=0;i<body.size();i++) {
            MyModule module = new MyModule();
            module.setBody(body.get(i));
            module.setTitle(title.get(i));
            mylist.add(module);
        }
        view.setRecyclerView(mylist);
    }
}
