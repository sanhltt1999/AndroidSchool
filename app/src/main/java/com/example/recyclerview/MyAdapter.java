package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> mUserList = new ArrayList<>();
    private OnClickListener mOnClickListener;

    public MyAdapter(List<User> userList) {
        mUserList = userList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).onBind(mUserList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public void filterList(List<User> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        mUserList = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private LinearLayout mLinearLayout;
        private TextView mPhoneTextView;
        private TextView mEmailTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(User user, int position) {
            nameTextView = itemView.findViewById(R.id.name);
            mLinearLayout = itemView.findViewById(R.id.lnLayout);
            mPhoneTextView =itemView.findViewById(R.id.phoneTextView);
            mEmailTextView = itemView.findViewById(R.id.emailTextView);

            nameTextView.setText(user.getName());
            mPhoneTextView.setText(user.getPhone());
            mEmailTextView.setText(user.getEmail());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mLinearLayout.getVisibility() == View.VISIBLE) {
                        mLinearLayout.setVisibility(View.GONE);
                    } else {
                        mLinearLayout.setVisibility(View.VISIBLE);
                    }
                    mOnClickListener.onClick(user, position);
                }
            });

        }

    }



    public interface OnClickListener {

        void onClick(User user, int position);
    }

}
