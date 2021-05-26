package com.example.minesweeper.startScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.minesweeper.GameActivity;
import com.example.minesweeper.MainActivity;
import com.example.minesweeper.R;

/**
 * <h1>The highscore fragment.</h1>
 *
 * @author Jenny Johannesson
 * @since 2021-05-13
 */
public class HighscoreFragment extends Fragment
{
    private View view;
    private Button backButton;
  /*  private RecyclerView recyclereasy;
    private FirebaseDatabase fireBaseRootNode;
    private DatabaseReference databaseReference;
    private List<String> name;
    private List<String> score;*/

    public HighscoreFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        //Inflate the layout for this fragment
        view = inflater.inflate(R.layout.highscore, container, false);
       // buttonListener();

       // List<String> name=new ArrayList<String>();
        //List<String> score=new ArrayList<String>();

        //Not ready
       // dataeasy();
        /*recyclereasy=view.findViewById(R.id.recyclereasyrank);
        AdapterRanking adapter = new AdapterRanking(name,score);
        recyclereasy.setAdapter(adapter);
        recyclereasy.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclereasy.setItemAnimator(new DefaultItemAnimator());*/




        return view;
    }



     //Not ready
  /*  public void dataeasy()
    {

        fireBaseRootNode= FirebaseDatabase.getInstance();
        databaseReference = fireBaseRootNode.getReference("easy");//database.getReference("Numberofuser");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                try {
                    JSONArray jsonArray=new JSONArray(value);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject user= jsonArray.getJSONObject(i);
                        name.add("user"+i);
                        score.add(user.getString("score"));

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.w("TAG", "Failed to read value.");
                }
                Log.d("TAG", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });


    }

*/


    private  void buttonListener(){
        backButton = view.findViewById(R.id.back_button);
        backButton.setOnClickListener(buttonListener);

    }
    private View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            MainActivity.fragmentManager.beginTransaction().replace(R.id.container, MenuManager.getInstance().startFragment, null ).commit();
        }
    };

}
