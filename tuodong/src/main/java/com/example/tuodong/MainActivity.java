package com.example.tuodong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private List<Bean.DataBean>new_list;
    private Adapter adapter;
    private List<Bean.DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getInitData();
        Util simple = new Util(adapter);
        //拖拽是否滑动删除
        //simple.setSwipEnable(false);
        ItemTouchHelper touchHelper = new ItemTouchHelper(simple);
        touchHelper.attachToRecyclerView(mRecycler);
    }
    private void getInitData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://www.qubaobei.com/")
                .build();
        Task task = retrofit.create(Task.class);
        Observable<Bean> init = task.getInit();
        init.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean value) {
                        list = value.getData();
                        new_list.addAll(list);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        new_list=new ArrayList<>();
        adapter = new Adapter(new_list, MainActivity.this);
        mRecycler.setAdapter(adapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
