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
    List<Integer> myIntegerMinute;
    List<Integer> myIntegerSeconde;
    int max = 0;
    List<String> copyname;

    List<String> sortscore;
    List<String> sortname;
    int dot;

    public Easy_fragment() {
    }

    private void Sorting() {
        while (sortscore.size() < score.size() - 1) {
            for (int i = 1; i < score.size(); i++) {
                dot = score.get(i).indexOf(':');
                /*if (Integer.parseInt(score.get(i).substring(0, dot - 1)) < Integer.parseInt(score.get(i - 1).substring(0, dot - 1))) {
                    max = i;
                }*/
                //if (Integer.parseInt(score.get(i).substring(0, dot - 1)) == Integer.parseInt(score.get(i - 1).substring(0, dot - 1))) {

                    int deb = Integer.parseInt(score.get(i).substring(dot + 1, score.get(i).length()));
                    int end = Integer.parseInt(score.get(i - 1).substring(dot + 1, score.get(i - 1).length()));

                    if (deb < end) {
                        max = i;
                        if(i == score.size() -1){
                            dot = score.get(max).indexOf(':');
                            sortscore.add(Integer.parseInt(score.get(max).substring(0, dot - 1)) + ":" + Integer.parseInt(score.get(max).substring(dot + 1, score.get(max).length())));
                            score.remove(max); // or remove
                            //myIntegerMinute.remove(max); // or remove
                            sortname.add(copyname.get(max));
                            copyname.remove(max);
                        }
                    }
                //}
            }

          /*  dot = score.get(max).indexOf(':');
            sortscore.add(Integer.parseInt(score.get(max).substring(0, dot - 1)) + ":" + Integer.parseInt(score.get(max).substring(dot + 1, score.get(max).length())));
            score.remove(max); // or remove
            //myIntegerMinute.remove(max); // or remove
            sortname.add(copyname.get(max));
            copyname.remove(max);*/

        }

        dot = score.get(0).indexOf(':');
        sortscore.add(Integer.parseInt(score.get(0).substring(0, dot - 1)) + ":" + Integer.parseInt(score.get(0).substring(dot + 1, score.get(0).length())));
        score.remove(0); // or remove
        //myIntegerMinute.remove(max); // or remove
        sortname.add(copyname.get(0));
        copyname.remove(0);

        Log.d("myLog", sortname + ": " + sortscore);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.easy_fragment, container, false);

        name = new ArrayList<String>();
        score = new ArrayList<String>();
        copyname = new ArrayList<>();
        copyname = name;
        sortscore = new ArrayList<>();
        sortname = new ArrayList<>();

        myIntegerMinute = new ArrayList<>();
        myIntegerSeconde = new ArrayList<>();


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
                        Sorting();


                        //sortHighScoreStrings();
                    }


                    recycler = view.findViewById(R.id.Reasy);

                    AdapterScore adapter = new AdapterScore(sortname, name, sortscore, score);
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
  /*      myIntegerMinute = new int[score.size()];
        myIntegerSeconde = new int[score.size()];
        for (int i = 0; i < score.size(); i++) {
            int dot = score.get(i).indexOf(':');
            myIntegerMinute[i] = Integer.parseInt(score.get(i).substring(0, dot - 1));
            myIntegerSeconde[i] = Integer.parseInt(score.get(i).substring(dot + 1, score.get(i).length()));
        }
        *//*for (int i = 0; i < myStringArray.length; i++) {
            myintegerArray[i] = Integer.parseInt(myStringArray[i]);
        }*//*
        Arrays.sort(myIntegerSeconde);
        Arrays.sort(myIntegerMinute);
        Log.d("secondsA", Arrays.toString(myIntegerSeconde));
        Log.d("secondsM", Arrays.toString(myIntegerMinute));*/
    }

}
