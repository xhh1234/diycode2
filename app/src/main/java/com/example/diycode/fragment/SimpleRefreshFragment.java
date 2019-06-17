package com.example.diycode.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.diycode.R;
import com.example.diycode.adapter.CommonAdapter;
import com.example.diycode.adapter.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public abstract class SimpleRefreshFragment<T> extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.rlv_recyclerview)
    RecyclerView rlvRecyclerview;
    @BindView(R.id.srl_swiperefresh)
    SwipeRefreshLayout srlSwiperefresh;

    List<T> mDatas=new ArrayList<>();

    CommonAdapter<T> commonAdapter;
    MultiItemTypeAdapter<T> multiItemTypeAdapter;

    LinearLayoutManager linearLayoutManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private View view;

    public SimpleRefreshFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SimpleRefreshFragment.
     */
    // TODO: Rename and change types and number of parameters
    public SimpleRefreshFragment newInstance(String param1, String param2) {
        SimpleRefreshFragment fragment = this;
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_simplerefresh, container, false);
        ButterKnife.bind(this, view);
        initView();
        initdata();
        initLinstener();
        return view;
    }

    private void initLinstener() {
        initRefresh();
        initLoadMore();
    }

    public abstract void initLoadMore();

    public abstract void initRefresh();

    private void initView() {
        srlSwiperefresh.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
    }

    public abstract void initdata();

    @SuppressLint("WrongConstant")
    public void initRecyclerView() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
