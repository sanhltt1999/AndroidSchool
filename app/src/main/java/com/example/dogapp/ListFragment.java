package com.example.dogapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dogapp.model.DogAPIService;
import com.example.dogapp.model.DogBreed;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListFragment extends Fragment {

    private DogAPIService mDogAPIService;
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private List<DogBreed> mDogBreeds;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController mNavController = Navigation.findNavController(view);

        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mDogBreeds = new ArrayList<>();
        mAdapter = new Adapter(mDogBreeds);
        mRecyclerView.setAdapter(mAdapter);

        mDogAPIService = new DogAPIService();

        mDogAPIService.getDog()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<DogBreed> dogBreeds) {
                        for (DogBreed dog : dogBreeds) {
                            mDogBreeds.add(dog);
                            Log.d("Debug", dog.getName() + "  " + dog.getWeight().getImperial() + " " + dog.getBredFor());
                        }
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });
        mAdapter.setOnClickListener(new Adapter.OnClickListener() {
            @Override
            public void onClick(DogBreed dogBreed, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("dog", dogBreed);
                mNavController.navigate(R.id.action_listFragment_to_detailFragment, bundle);
            }
        });
    }
}