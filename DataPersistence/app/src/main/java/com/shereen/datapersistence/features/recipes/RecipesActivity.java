package com.shereen.datapersistence.features.recipes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.shereen.datapersistence.Constants;
import com.shereen.datapersistence.R;
import com.shereen.datapersistence.db.raw.RecipeDataSource;
import com.shereen.datapersistence.db.room.RoomRecipeDataSource;
import com.shereen.datapersistence.models.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RecipesActivity extends AppCompatActivity implements RecipesAdapter.RecyclerListener{

    private static final String TAG = RecipesActivity.class.getSimpleName();

    private RecyclerView recipesRecyclerView;

    private RecipeDataSource recipeDataSource;
    private RoomRecipeDataSource roomRecipeDataSource;
    private RecipeViewModel viewmodel;

    private RecipesAdapter adapter;
    private boolean usingRoom = true;

    private List<Recipe> recipeList;


    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);

        initLog();

        recipeList = new ArrayList<>();

        switch (Constants.rawRxLive) {
            case 0 : recipeDataSource = new RecipeDataSource(this); break;
            case 1 : roomRecipeDataSource = new RoomRecipeDataSource(this); break;
            case 2 : viewmodel = ViewModelProviders.of(this).get(RecipeViewModel.class); break;
            default: recipeDataSource = new RecipeDataSource(this);
        }

        setupRecyclerView();
    }

    private void initLog(){
        String message;

        switch (Constants.rawRxLive) {
            case 0 : message = "Using raw SQLite"; break;
            case 1 : message = "Using RxJava"; break;
            case 2 : message = "Using LiveData"; break;
            default: message = "Something wrong with logging rawRxLive value";
        }

        Log.d(TAG, "-------------------------------------------------------"+message);
        print(message);
    }

    @Override
    protected void onResume ()
    {
        super.onResume();

        switch (Constants.rawRxLive) {
            case 0 :    handleRawStorage(); break;
            case 1 :    handleRxStorage(); break;
            case 2:     handleLiveStorage(); break;
            default :   handleRawStorage();

        }

    }

    private void handleRawStorage(){

        List<Recipe> recipes;

        recipeDataSource.open();
        recipes = recipeDataSource.getAllRecipes();
        Recipe actionRecipe = recipes.get(0);
        actionRecipe.setName("Yellow Cake");
        recipeDataSource.updateRecipe(actionRecipe);
        //recipeDataSource.deleteRecipe(actionRecipe);

        recipes = recipeDataSource.getAllRecipes();

        adapter.setRecipes(recipes);
        adapter.notifyDataSetChanged();

    }

    private void handleRxStorage(){

        List<Recipe> recipes;

        disposable = roomRecipeDataSource.getAllRecipes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Recipe>>() {
                               @Override
                               public void accept(List<Recipe> recipes) throws Exception {
                                   adapter.setRecipes(recipes);
                                   adapter.notifyDataSetChanged();
                               }
                           }

                );

        Completable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() {
                //roomRecipeDataSource.truncateTables();

                //                    for (Recipe recipe : RecipesDataProvider.recipesList)
                //                    {
                //                        roomRecipeDataSource.createRecipe(recipe);
                //                    }
                return null;
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe();

    }

    private void handleLiveStorage(){

//        viewmodel.deleteAllRecipes();
//
//        for (Recipe recipe : RecipesDataProvider.recipesList)
//        {
//            viewmodel.addItem(recipe);
//        }


        viewmodel.getRecipeList()
                    .observe(RecipesActivity.this,
                            new Observer<List<Recipe>>() {
                                @Override
                                public void onChanged(@Nullable List<Recipe> recipes) {
                                    recipeList = recipes;
                                    adapter.setRecipes(recipes);
                                    adapter.notifyDataSetChanged();
                                }
                            });

    }


    @Override
    protected void onPause (){
        super.onPause();

        switch (Constants.rawRxLive) {
            case 0 : recipeDataSource.close();
            case 1 : break;
            case 2 : break;
        }
    }

    private void setupRecyclerView ()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipesRecyclerView.setLayoutManager(layoutManager);
        recipesRecyclerView.setHasFixedSize(true);
        adapter = new RecipesAdapter(this);
        recipesRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();

    }

    @Override
    public void getRecipeId(long recipeId) {
        Log.d(TAG, "Communicated: "+recipeId);
    }

    private void printList(){
        print("flutter");
        for (Recipe recipe : recipeList) {
            print(recipe.toString());
            print("NEXT\n");
        }
    }

    void print(String mes){
        System.out.println(TAG + " : " +mes);
    }

    private Recipe getRecipeById(long recipeId){

        Recipe recipe = new Recipe();
        recipe.setName("Not found");

        for (Recipe rec : recipeList) {
            if(rec.getRecipeId() == recipeId){
                return rec;
            }
        }

        return recipe;
    }
}

