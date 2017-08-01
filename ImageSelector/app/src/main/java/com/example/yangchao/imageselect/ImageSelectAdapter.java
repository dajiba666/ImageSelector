package com.example.yangchao.imageselect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.yangchao.imageselect.view.SquareImageView;

import java.util.ArrayList;


/**
 * Created by 超 on 2017/7/18.
 */

public class ImageSelectAdapter extends RecyclerView.Adapter {
    private ArrayList<String> mList;
    private Context mContext;
    private ArrayList<String> mImageList;
    private int mMaxNum;
    public ImageSelectAdapter(ArrayList<String> list, Context context, ArrayList<String> imageList, int mMaxCount) {
        this.mList = list;
        this.mContext = context;
        this.mImageList = imageList;
        this.mMaxNum = mMaxCount;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //显示判断
        ViewHolder viewHolder = (ViewHolder) holder;
        if (mList.get(0).equals("")){
            viewHolder.rlPhoto.setVisibility(View.GONE);
            viewHolder.ivCamera.setVisibility(View.VISIBLE);
        }else {

            viewHolder.rlPhoto.setVisibility(View.VISIBLE);
            viewHolder.ivCamera.setVisibility(View.GONE);
            Glide.with(mContext).load(mList.get(position)).into(viewHolder.ivPhoto);

           if (mImageList.contains(mList.get(position))){
               viewHolder.ivRight.setSelected(true);


           }else {
               viewHolder.ivRight.setSelected(false);
           }
            ((ViewHolder) holder).rlPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMaxNum<=mImageList.size()){
                        return;
                    }

                    if (mImageList.contains(mList.get(position))){
                        mImageList.remove(mList.get(position));
                    }else {
                        mImageList.add(mList.get(position));
                    }
                    notifyItemChanged(position);

                    imageSelectListener.imageSelect(mImageList);
                }
            });
        }
    }

    private ImageSelectListener imageSelectListener;

    public void setImageSelectListener(ImageSelectListener imageSelectListener) {
        this.imageSelectListener = imageSelectListener;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



   public class ViewHolder extends RecyclerView.ViewHolder{
        public SquareImageView ivCamera;
        public RelativeLayout rlPhoto ;
        public SquareImageView ivPhoto;
        public ImageView ivRight;
        public ViewHolder(View itemView) {
            super(itemView);
            ivCamera = (SquareImageView) itemView.findViewById(R.id.siv_camera);
            ivRight = (ImageView) itemView.findViewById(R.id.iv_right);
            ivPhoto = (SquareImageView) itemView.findViewById(R.id.siv_photo);
            rlPhoto = (RelativeLayout) itemView.findViewById(R.id.rl_photo);
        }
    }
}
