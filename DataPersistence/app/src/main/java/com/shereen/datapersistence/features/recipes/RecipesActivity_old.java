package com.shereen.datapersistence.features.recipes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.shereen.datapersistence.R;
import com.shereen.datapersistence.db.raw.RecipeDataSource;
import com.shereen.datapersistence.db.room.RoomRecipeDataSource;
import com.shereen.datapersistence.models.Recipe;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shereen on 12/30/18
 */

public class RecipesActivity_old extends AppCompatActivity {

    private static final String TAG = RecipesActivity.class.getSimpleName();

    private RecyclerView recipesRecyclerView;
    private RecipeDataSource recipeDataSource;
    private RoomRecipeDataSource roomRecipeDataSource;
    private RecipesAdapter adapter;
    private boolean usingRoom = true;


    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);

        Log.d(TAG, "usingRoom: " + usingRoom);

        if(usingRoom){
            roomRecipeDataSource = new RoomRecipeDataSource(this);
        }else{
            recipeDataSource = new RecipeDataSource(this);
        }

        setupRecyclerView();
    }

    @Override
    protected void onResume ()
    {
        super.onResume();

        List<Recipe> recipes;

        if(usingRoom){

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

    // Async Task Implementation (Remove Flowable)
    //            new AsyncTask(){
    //
    //                @Override
    //                protected List<Recipe> doInBackground(Void... voids) {
    //
    ////                    roomRecipeDataSource.truncateTables();
    ////
    ////                    for (Recipe recipe : RecipesDataProvider.recipesList)
    ////                    {
    ////                        roomRecipeDataSource.createRecipe(recipe);
    ////                    }
    //
    //                    return roomRecipeDataSource.getAllRecipes();
    //                }
    //
    //                @Override
    //                protected void onPostExecute(List<Recipe> recipes) {
    //                    super.onPostExecute(recipes);
    //                    adapter.setRecipes(recipes);
    //                    adapter.notifyDataSetChanged();
    //                }
    //            }.execute();


    }else{

        recipeDataSource.open();
        //recipeDataSource.truncateTables();

//        for (Recipe recipe : RecipesDataProvider.recipesList)
//        {
//            recipeDataSource.createRecipe(recipe);
//        }

        recipes = recipeDataSource.getAllRecipes();
        Recipe actionRecipe = recipes.get(0);
        actionRecipe.setName("Yellow Cake");
        recipeDataSource.updateRecipe(actionRecipe);
        //recipeDataSource.deleteRecipe(actionRecipe);

        recipes = recipeDataSource.getAllRecipes();

        adapter.setRecipes(recipes);
        adapter.notifyDataSetChanged();

    }

    }


    @Override
    protected void onPause (){
        super.onPause();
        if(usingRoom){

        }else{
            recipeDataSource.close();
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

}
