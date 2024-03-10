package com.ise.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ise.myapplication.adapter.SearchUserRecyclerAdapter;
import com.ise.myapplication.model.UserModel;
import com.ise.myapplication.utils.firebase;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.ise.myapplication.R;
import com.ise.myapplication.adapter.SearchUserRecyclerAdapter;

public class SearchActivity extends AppCompatActivity {

    EditText searchInput;
    ImageButton searchButton;
    ImageButton backButton;
    RecyclerView recyclerView;

    SearchUserRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchInput = findViewById(R.id.seach_username_input);
        searchButton = findViewById(R.id.search_user_btn);
        backButton = findViewById(R.id.back_btn);
        recyclerView = findViewById(R.id.search_user_recycler_view);

        searchInput.requestFocus();


//        backButton.setOnClickListener(v -> {
//            onBackPressed();
//        });
//
//        searchButton.setOnClickListener(v -> {
//            String searchTerm = searchInput.getText().toString();
//            if(searchTerm.isEmpty() || searchTerm.length()<3){
//                searchInput.setError("Invalid Username");
//                return;
//            }
//            else {
//                setupSearchRecyclerView(searchTerm);
//            }
//        });
//    }
        backButton.setOnClickListener(v -> {
            onBackPressed();
        });

//        searchButton.setOnClickListener(v -> {
//            String searchTerm = searchInput.getText().toString();
//            if(searchTerm.isEmpty() || searchTerm.length()<3)
//            {
//                searchInput.setError("Invalid Username");
//                return;
//            }
//            else
//            {
//            setupSearchRecyclerView(searchTerm);
//            }
//        });
        searchButton.setOnClickListener(v -> {
            String searchTerm = searchInput.getText().toString();
            if (searchTerm.isEmpty() || searchTerm.length() < 3) {
                searchInput.setError("Invalid Username (minimum 3 characters)");
            } else {
                // Call the method to set up the search RecyclerView
                setupSearchRecyclerView(searchTerm);
            }
        });

    }

//    void setupSearchRecyclerView(String searchTerm) {
//        Query query = firebase.allUserCollectionReference()
//                .orderBy("username")
//                .startAt(searchTerm)
//                .endAt(searchTerm + "\uf8ff");
//
//        FirestoreRecyclerOptions<UserModel> options = new FirestoreRecyclerOptions.Builder<UserModel>()
//                .setQuery(query, UserModel.class).build();
//
//        adapter = new SearchUserRecyclerAdapter(options, getApplicationContext());
//
//        adapter.setOnDataChangedListener(new FirestoreRecyclerAdapter.OnDataChangedListener<UserModel>() {
//            @Override
//            public void onDataChanged() {
//                // Handle data change
//                Log.d(TAG, "Data changed. Item count: " + adapter.getItemCount());
//            }
//
//            @Override
//            public void onError(Exception e) {
//                // Handle error
//                Log.e(TAG, "Error loading data: " + e.getMessage());
//            }
//        });
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//        adapter.startListening();
//    }


    void setupSearchRecyclerView(String searchTerm){

        Query query = firebase.allUserCollectionReference()
                .whereGreaterThanOrEqualTo("username",searchTerm)
                .whereLessThanOrEqualTo("username",searchTerm+'\uf8ff');
//        Query query = firebase.allUserCollectionReference()
//                .orderBy("username")
//                .startAt(searchTerm)
//                .endAt(searchTerm + "\uf8ff");
        FirestoreRecyclerOptions<UserModel> options = new FirestoreRecyclerOptions.Builder<UserModel>()
                .setQuery(query,UserModel.class).build();

        adapter = new SearchUserRecyclerAdapter(options,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.startListening();


//        Query query = firebase.allUserCollectionReference()
//                .whereGreaterThanOrEqualTo("username",searchTerm)
//                .whereLessThanOrEqualTo("username",searchTerm+'\uf8ff');
//
//        FirestoreRecyclerOptions<UserModel> options = new FirestoreRecyclerOptions.Builder<UserModel>()
//                .setQuery(query,UserModel.class).build();
//
//        adapter = new SearchUserRecyclerAdapter(options,getApplicationContext());
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//        adapter.startListening();

            // Convert the search term to lowercase for case-insensitive search
//            String searchTermLowerCase = searchTerm.toLowerCase();
//
//            Query query = firebase.allUserCollectionReference()
//                    .orderBy("username")
//                    .startAt(searchTermLowerCase)
//                    .endAt(searchTermLowerCase + "\uf8ff");
//
//            FirestoreRecyclerOptions<UserModel> options = new FirestoreRecyclerOptions.Builder<UserModel>()
//                    .setQuery(query, UserModel.class).build();
//
//            adapter = new SearchUserRecyclerAdapter(options, getApplicationContext());
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setAdapter(adapter);
//            adapter.startListening();
        }




    @Override
    protected void onStart() {
        super.onStart();
        if(adapter!=null)
            adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(adapter!=null)
            adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null)
            adapter.startListening();
    }
}