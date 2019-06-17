package com.example.diycode.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

/**
 * 通用的ViewHolder
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private SparseArray<View> mViews=new SparseArray<>();
    private View mItemView;
    public static int mLayoutId=0;

    public ViewHolder(@NonNull View itemView, Context mContext,int layoutId) {
        super(itemView);
        this.mContext = mContext;
        mItemView=itemView;
        mViews.put(layoutId,mItemView);
//        Log.e("Layout 的id", "ViewHolder: "+itemView.getId());
    }

    /**
     * 根据不同的layoutId创建有着不同的View的ViewHolder
     * @param mContext
     * @param parent
     * @param layoutId
     * @return
     */
    public static ViewHolder get(Context mContext,ViewGroup parent,int layoutId){
        mLayoutId=layoutId;
        View itemview= LayoutInflater.from(mContext).inflate(layoutId,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemview,mContext,layoutId);
        return viewHolder;
    }

    /**
     * 实例View通过ViewId来
     * @param viewId
     * @param <T>
     * @return
     */
    public  <T extends View>T getView(int viewId){
        View view=mViews.get(viewId);
        if (view==null){
            view=mItemView.findViewById(viewId);
//            if (view==null) {}
//                Log.e("View is null", "getView: "+mItemView.getId());

            mViews.put(viewId,view);
        }
        return (T) view;
    }

//辅助方法，根据自己的需要自己定义
    /**
     * 实现TextView的setText()方法
     * @param viewId
     * @param msg
     * @return
     */
    public ViewHolder setText(int viewId,String msg){
        TextView tv_content=getView(viewId);
        tv_content.setText(msg);
        return this;
    }

    /**
     * 实现View是显示还是不显示
     * @param viewId
     * @param visibility  View.GONE,View.VISIBLE,View.INVISIBLE
     * @return
     */
    public ViewHolder setVisibility(int viewId,int visibility){
        View view=getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * @param viewId imageView的id
     * @param imgUrl 图片的Url
     * @return
     */
    public ViewHolder setImageView(int viewId,String imgUrl){
        ImageView imageView=getView(viewId);
        Glide.with(imageView.getContext()).load(imgUrl).into(imageView);
        return this;
    }

    /**
     * @param l 点击事件
     * @param viewId  控件的id
     */
    public void setOnClickLinstance(View.OnClickListener l,int... viewId){
        if (viewId==null){
            return;
        }
        for (int viewid:viewId
             ) {
            getView(viewid).setOnClickListener(l);
        }
    }

}
