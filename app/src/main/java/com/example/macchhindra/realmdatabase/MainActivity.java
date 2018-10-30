package com.example.macchhindra.realmdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.macchhindra.realmdatabase.adapter.RealmAdapter;
import com.example.macchhindra.realmdatabase.helper.RealmHelper;
import com.example.macchhindra.realmdatabase.module.MyModule;
import com.example.macchhindra.realmdatabase.presenter.Contract;
import com.example.macchhindra.realmdatabase.presenter.Presenter;

import java.util.List;
import io.realm.Realm;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements Contract.View {
     Retrofit retrofit;
     Contract.Presenter presenter;
     RecyclerView recyclerView;
     Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.recyclerView = findViewById(R.id.myRecyclerView);
        Controller controller = new Controller();
        Retrofit retrofit = controller.start();
        realm = Realm.getDefaultInstance();
        presenter = new Presenter(retrofit,this, this, realm);
        presenter.getDataFromApi();

    }

    @Override
    public void setRecyclerView(List<MyModule> data) {
        RealmAdapter adapter = new RealmAdapter(data,realm,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void writeToDatabase(List<MyModule> data,Realm realm) {
        RealmHelper helper = new RealmHelper(realm);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
