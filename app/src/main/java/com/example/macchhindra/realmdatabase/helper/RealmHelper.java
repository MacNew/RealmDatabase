package com.example.macchhindra.realmdatabase.helper;

import com.example.macchhindra.realmdatabase.module.MyModule;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
  Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void save(final MyModule myModule) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                MyModule mo = realm.copyToRealm(myModule);

            }
        });
    }

    public ArrayList<String> retriveTitle()
    {
        ArrayList<String> titleList=new ArrayList<>();
        RealmResults<MyModule> spacecrafts=realm.where(MyModule.class).findAll();

        for(MyModule data:spacecrafts)
        {
            titleList.add(data.getTitle());
        }

        return titleList;
    }


    public ArrayList<String> retriveBody()
    {
        ArrayList<String> titleBody=new ArrayList<>();
        RealmResults<MyModule> spacecrafts=realm.where(MyModule.class).findAll();

        for(MyModule data:spacecrafts)
        {
            titleBody.add(data.getBody());
        }

        return titleBody;
    }
}
