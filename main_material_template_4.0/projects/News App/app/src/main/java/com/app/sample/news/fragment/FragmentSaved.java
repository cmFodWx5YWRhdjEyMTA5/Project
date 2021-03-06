package com.app.sample.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.sample.news.ActivityMain;
import com.app.sample.news.ActivityNewsDetails;
import com.app.sample.news.R;
import com.app.sample.news.adapter.AdapterNewsList;
import com.app.sample.news.adapter.AdapterNewsListWithHeader;
import com.app.sample.news.data.GlobalVariable;
import com.app.sample.news.model.News;

public class FragmentSaved extends Fragment {

    private View root_view;
    private RecyclerView recyclerView;
    private AdapterNewsList mAdapter;
    private GlobalVariable global;
    private LinearLayout lyt_notfound;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root_view = inflater.inflate(R.layout.fragment_saved, null);
        global = (GlobalVariable) getActivity().getApplication();

        lyt_notfound = (LinearLayout) root_view.findViewById(R.id.lyt_notfound);
        recyclerView = (RecyclerView) root_view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        mAdapter =  new AdapterNewsList(getActivity(), global.getSaved());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AdapterNewsList.OnItemClickListener() {
            @Override
            public void onItemClick(View v, News obj, int position) {
                ActivityNewsDetails.navigate((ActivityMain) getActivity(), v.findViewById(R.id.image), obj);
            }
        });
        checkItems();
        return root_view;
    }

    private void checkItems(){
        if(mAdapter.getItemCount()<=0){
            lyt_notfound.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            lyt_notfound.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        mAdapter.setItems(global.getSaved());
        checkItems();
        super.onResume();
    }
}
