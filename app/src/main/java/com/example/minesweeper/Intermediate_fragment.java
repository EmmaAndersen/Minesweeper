package com.example.minesweeper;

//package com.example.minesweeper;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Cassandra Chedjou & Emma-sophie Andersen
 * */
public class Intermediate_fragment extends Fragment {

    private View view;
    private RecyclerView recycler;
    private FirebaseDatabase fireBaseRootNode;
    private DatabaseReference databaseReference;
    private List<String> name;
    private List<String> score;

    int[] myIntegerMinute;
    int[] myIntegerSeconde;
    int max = 0;

    public Intermediate_fragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.intermediate_fragment, container, false);

        name=new ArrayList<String>();
        score=new ArrayList<String>();

        dataeasy();

        return view;
    }
    public void dataeasy()
    {

        try{
            fireBaseRootNode= FirebaseDatabase.getInstance();
            databaseReference = fireBaseRootNode.getReference("intermediate");//database.getReference("Numberofuser");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    for(DataSnapshot result: dataSnapshot.getChildren())
                    {
                        name.add(result.getKey()+": ");
                        score.add(""+result.getValue(String.class));
                    }


                    recycler=view.findViewById(R.id.Rintermediate);
                    AdapterScore adapter = new AdapterScore(name, score, findBestScore());
                    recycler.setAdapter(adapter);
                    recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    recycler.setItemAnimator(new DefaultItemAnimator());

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("TAG", "Failed to read value.", error.toException());
                }
            });}
        catch (Exception e)
        {
            Log.d("File not find","File no find");
        }
    }


    private int findBestScore() {
        int maxValue = 0;
        myIntegerMinute = new int[score.size()];
        myIntegerSeconde = new int[score.size()];
        for (int j = 0; j < score.size(); j++) {

            int dot = score.get(j).indexOf(':');
            myIntegerMinute[j] = Integer.parseInt(score.get(j).substring(0, dot - 1));
            myIntegerSeconde[j] = Integer.parseInt(score.get(j).substring(dot + 1, score.get(j).length()));
        }

        for (int i = 0; i < score.size() - 1; i++) {

            if ((myIntegerMinute[maxValue]) > (myIntegerMinute[i])) {
                maxValue = i;
            }
            if ((myIntegerMinute[maxValue]) == (myIntegerMinute[i])) {

                if ((myIntegerSeconde[maxValue]) >= (myIntegerSeconde[i])) {
                    Log.d("newLog", String.valueOf(myIntegerSeconde[maxValue]) + ": " + String.valueOf(myIntegerSeconde[i]));
                    maxValue = i;
                    Log.d("compareLog", score.get(i) + "name: " + String.valueOf(maxValue));
                }
            }
        }
        return maxValue;
    }
}


