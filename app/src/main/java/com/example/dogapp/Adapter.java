package com.example.dogapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;


import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<DogBreed> mAPIList;
    private OnClickListener mOnClickListener;

    public Adapter(List<DogBreed> APIList) {
        mAPIList = APIList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_recycletview, parent, false);
        return new DogViewHolder(view);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((DogViewHolder) holder).onBind(mAPIList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mAPIList.size();
    }

    class DogViewHolder extends RecyclerView.ViewHolder {

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        private ImageView mDogImage;
        private TextView mTextView;
        private TextView mBredForTextView;

        public void onBind(DogBreed dogBreed, int position) {
            mDogImage = itemView.findViewById(R.id.imageDog);
            mTextView = itemView.findViewById(R.id.nameDog);
            mBredForTextView = itemView.findViewById(R.id.bredFor);

            mTextView.setText(dogBreed.getName() + "");
            Picasso.get()
                    .load(dogBreed.getUrlDog())
                    .placeholder(R.drawable.loading)
                    .into(mDogImage);
            mBredForTextView.setText(dogBreed.getBredFor());

            itemView.setOnClickListener(v -> mOnClickListener.onClick(dogBreed, position));
        }
    }
    public interface OnClickListener {
        void onClick(DogBreed dogBreed, int position);
    }
}
