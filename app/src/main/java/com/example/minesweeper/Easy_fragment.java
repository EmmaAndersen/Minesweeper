package com.example.minesweeper;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minesweeper.startScreen.MenuManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Easy_fragment extends Fragment {

    private View view;
    private RecyclerView recycler;
    private FirebaseDatabase fireBaseRootNode;
    private DatabaseReference databaseReference;
    private List<String> name;
    private List<String> score;
    int[] myIntegerMinute;
    int[] myIntegerSeconde;

    public Easy_fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.easy_fragment, container, false);

        name = new ArrayList<String>();
        score = new ArrayList<String>();

        dataeasy();

        return view;
    }

    public void dataeasy() {

        try {
            fireBaseRootNode = FirebaseDatabase.getInstance();
            databaseReference = fireBaseRootNode.getReference("easy");//database.getReference("Numberofuser");

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    for (DataSnapshot result : dataSnapshot.getChildren()) {
                        name.add(result.getKey() + ": ");
                    /*Collections.sort(result, new Comparator<T>() {
                        @Override
                        public int compare(long o1, long o2) {
                            return
                        }
                    });*/


                        score.add("" + result.getValue(String.class));

                        sortHighScoreStrings();
                    }

                    recycler = view.findViewById(R.id.Reasy);

                    AdapterScore adapter = new AdapterScore(name, myIntegerMinute, myIntegerSeconde, score);
                    recycler.setAdapter(adapter);
                    recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    recycler.setItemAnimator(new DefaultItemAnimator());

                    //databaseReference.orderByValue().limitToLast(5);

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("TAG", "Failed to read value.", error.toException());
                }
            });
        } catch (Exception e) {
            Log.d("File not find", "File no find");
        }
    }

    private void sortHighScoreStrings() {
       /* String[] myStringArray = new String[score.size()];
        for (int i = 0; i < score.size(); i++) {
            myStringArray[i] = score.get(i);
        }*/
        //int[] myintegerArray = new int[myStringArray.length];
        myIntegerMinute = new int[score.size()];
        myIntegerSeconde = new int[score.size()];
        for (int i = 0; i < score.size(); i++)
        {
            int dot = score.get(i).indexOf(':');
            myIntegerMinute[i] = Integer.parseInt(score.get(i).substring(0, dot - 1));
            myIntegerSeconde[i] = Integer.parseInt(score.get(i).substring(dot + 1, score.get(i).length()));
        }
        /*for (int i = 0; i < myStringArray.length; i++) {
            myintegerArray[i] = Integer.parseInt(myStringArray[i]);
        }*/
        Arrays.sort(myIntegerSeconde);
        Arrays.sort(myIntegerMinute);
        Log.d("secondsA", Arrays.toString(myIntegerSeconde));
        Log.d("secondsM", Arrays.toString(myIntegerMinute));
    }

}
