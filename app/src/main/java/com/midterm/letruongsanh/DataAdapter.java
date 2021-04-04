package com.midterm.letruongsanh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Data> mDataList = new ArrayList<>();
    private OnClickListener mOnClickListener;

    public DataAdapter(List<Data> userList) {
        mDataList = userList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).onBind(mDataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mInputTextView;
        private TextView mActionTextView;
        private TextView mResultTextView;
        private ImageView mDeleteImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(Data data, int position) {

            mInputTextView = itemView.findViewById(R.id.intputTextView);
            mActionTextView = itemView.findViewById(R.id.actionTextView);
            mResultTextView =itemView.findViewById(R.id.resultTextView);
            mDeleteImage = itemView.findViewById(R.id.deleteImageView);

            mInputTextView.setText(mDataList.get(position).getInput());
            mActionTextView.setText(mDataList.get(position).getAction());
            mResultTextView.setText(mDataList.get(position).getResult());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDataList.remove(position);
                    notifyDataSetChanged();
                    mOnClickListener.onDelete(mDataList);
                }
            });
        }

    }

    public interface OnClickListener {

        void onClick(Data data, int position);
        void onDelete(List<Data> datas);
    }

}
