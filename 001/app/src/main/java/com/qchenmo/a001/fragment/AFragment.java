package com.qchenmo.a001.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.qchenmo.a001.R;
import com.qchenmo.a001.jump.AActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AFragment extends Fragment {

    private TextView mTvAFragment;

    private Activity mActivity;

    private Button btn_sendMsg;

    private IOonMessageClick listener;

    //传递参数
    public static AFragment newInstance(String title) {
        AFragment fragment = new AFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    public interface IOonMessageClick {
        void onClick(String string);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_a, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTvAFragment = view.findViewById(R.id.tv_AFragment);
        btn_sendMsg = view.findViewById(R.id.sendMsg);
        btn_sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((ContainerActivity)getActivity()).SetData("来自AFragment");
                listener.onClick("来自AFragment的你好");
            }
        });
        if (getArguments() != null) {
            String string = getArguments().getString("title");
            mTvAFragment.setText(string);
        }
//        if(getActivity()!=null){
//            //todo
//        }else {
//
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (IOonMessageClick) context;
        }catch (ClassCastException e){
            throw new ClassCastException("Activity 必须实现 IOMessageClick接口");

        }


    }

    @Override
    public void onDetach() {
        super.onDetach();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消异步
    }
}
