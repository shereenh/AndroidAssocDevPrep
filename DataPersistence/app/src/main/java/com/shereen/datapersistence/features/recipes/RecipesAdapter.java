package com.shereen.datapersistence.features.recipes;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shereen.datapersistence.R;
import com.shereen.datapersistence.models.Recipe;
import com.shereen.datapersistence.models.RecipeStep;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shereen on 12/29/18
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private List<Recipe> recipes = Collections.emptyList();
    private Context context;
    private RecyclerListener listener;
    HashMap<Long, Boolean> clickedMap;

    public RecipesAdapter(Context context)
    {
        this.context = context;
        listener = (RecyclerListener) context;
    }

    void setRecipes (List<Recipe> recipes)
    {
        this.recipes = recipes;
        clickedMap = new HashMap<Long, Boolean>();
        for (Recipe recipe : recipes) {
            clickedMap.put(recipe.getId(), false);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView recipeImage;
        TextView recipeName;
        TextView recipeDescription;
        Button viewMore;
        TextView readMoreText;

        public ViewHolder (View v)
        {
            super(v);

            recipeImage = (ImageView) v.findViewById(R.id.recipe_image);
            recipeName = (TextView) v.findViewById(R.id.recipe_name);
            recipeDescription = (TextView) v.findViewById(R.id.recipe_description);
            viewMore = v.findViewById(R.id.recipe_read_more);
            readMoreText = v.findViewById(R.id.readMoreTextView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder (final ViewHolder holder, int position)
    {
        final Recipe recipe = recipes.get(position);

        holder.recipeName.setText(recipe.getName());
        holder.recipeDescription.setText(recipe.getDescription());

        Picasso.with(context)
                .load(recipe.getImageResourceId())
                .resize(340, 200)
                .centerCrop()
                .into(holder.recipeImage);

        holder.viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(clickedMap.get(recipe.getId()) != null){

                    if( clickedMap.containsKey(recipe.getId()) && clickedMap.get(recipe.getId())){
                        readLessClicked(holder, recipe.getId());
                    }else{
                        readMoreClicked(holder, recipe);
                    }

                    if (listener != null) {
                        listener.getRecipeId(recipe.getId());
                    }
                }

            }
        });

    }

    @Override
    public int getItemCount ()
    {
        return this.recipes.size();
    }

    public interface RecyclerListener {
        void getRecipeId(long recipeId);
    }

    private void readMoreClicked(final ViewHolder holder, Recipe recipe){
        clickedMap.put(recipe.getId(), true);
        holder.readMoreText.setVisibility(View.VISIBLE);
        holder.viewMore.setText("View Less<<");
        holder.readMoreText.setText(addSteps(recipe));
    }

    private void readLessClicked(final ViewHolder holder, long recipeId){
        clickedMap.put(recipeId, false);
        holder.readMoreText.setVisibility(View.GONE);
        holder.viewMore.setText("View more>>");
    }

    private String addSteps(Recipe recipe){

        StringBuilder message = new StringBuilder();
        List<RecipeStep> steps = recipe.getSteps();

        if (steps != null) {
            for (RecipeStep step : steps) {
                message.append(step.getStepNumber());
                message.append(" ");
                message.append(step.getInstruction());
                message.append("\n");
            }
        }else{
            message.append("No Steps!");
        }

        return message.toString();
    }
}
