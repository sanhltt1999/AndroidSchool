package com.example.dogapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailFragment extends Fragment {

    private ImageView mImageView;
    private TextView mNameDog;
    private TextView mBreedForTextView;
    private TextView mBreedGroupTextView;
    private TextView mLifeSpanTextView;
    private TextView mOriginTextView;
    private TextView mTemTextView;
    private TextView mHeightTextView;
    private TextView mWeightTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mImageView = view.findViewById(R.id.dogImageView);
        mNameDog = view.findViewById(R.id.nameDog);
        mBreedForTextView = view.findViewById(R.id.bredForTextView);
        mBreedGroupTextView = view.findViewById(R.id.bredGroupTextView);
        mLifeSpanTextView = view.findViewById(R.id.lifeSpanTextView);
        mOriginTextView = view.findViewById(R.id.originTextView);
        mTemTextView = view.findViewById(R.id.temTextView);
        mHeightTextView = view.findViewById(R.id.heightTextView);
        mWeightTextView = view.findViewById(R.id.weightTextView);

        DogBreed dogBreed = (DogBreed) getArguments().getSerializable("dog");

        Picasso.get()
                .load(dogBreed.getUrlDog())
                .placeholder(R.drawable.loading)
                .into(mImageView);

        mNameDog.setText(dogBreed.getName());
        mBreedForTextView.setText(dogBreed.getBredFor());
        mBreedGroupTextView.setText(dogBreed.getBreedGroup());
        mLifeSpanTextView.setText(dogBreed.getLifeSpan());
        mOriginTextView.setText(dogBreed.getOrigin());
        mTemTextView.setText(dogBreed.getTemperament());
        mHeightTextView.setText(dogBreed.getHeight().getImperial());
        mWeightTextView.setText(dogBreed.getWeight().getImperial());
    }
}