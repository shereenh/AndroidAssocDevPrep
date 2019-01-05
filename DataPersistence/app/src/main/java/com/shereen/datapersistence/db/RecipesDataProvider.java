package com.shereen.datapersistence.db;

import com.shereen.datapersistence.R;
import com.shereen.datapersistence.models.Recipe;
import com.shereen.datapersistence.models.RecipeStep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shereen on 12/29/18
 */

public class RecipesDataProvider {

    public static List<Recipe> recipesList;

    static
    {
        recipesList = new ArrayList<>();

        addRecipe(new Recipe(1, "Cake", R.drawable.cake_1, "Wonderful cake"),
                new RecipeStep(1,1, "mix all the ingredients of wonderful cake"),
                new RecipeStep(1,2, "preheat the oven"),
                new RecipeStep(1,3, "stir"),
                new RecipeStep(1,4, "bake"));

        addRecipe(new Recipe(1, "Cake", R.drawable.cake_1, "Wonderful cake"),
                new RecipeStep(1,1, "mix all the ingredients of wonderful cake"),
                new RecipeStep(1,2, "preheat the oven"),
                new RecipeStep(1,3, "stir"),
                new RecipeStep(1,4, "bake"));

        addRecipe(new Recipe(2,"Pie", R.drawable.pie_1,"Delicious Pie"));

        addRecipe(new Recipe(3,"Pound Cake", R.drawable.cake_2,"Fluffy cake"),
                new RecipeStep(3,1, "mix all the ingredients"),
                new RecipeStep(3,2, "preheat the oven"),
                new RecipeStep(3,3, "stir"),
                new RecipeStep(3,4, "bake"));

//        if(Constants.rawRxLive == 1 || Constants.rawRxLive == 2){
//
//            addRecipe(new Recipe("Cake", "Wonderful cake", R.drawable.cake_1),
//                    new RecipeStep(1, "mix all the ingredients of wonderful cake"),
//                    new RecipeStep(2, "preheat the oven"),
//                    new RecipeStep(3, "stir"),
//                    new RecipeStep(4, "bake"));
//
//            addRecipe(new Recipe("Pie", "Delicious Pie", R.drawable.pie_1));
//
//            addRecipe(new Recipe("Pound Cake", "Fluffy cake", R.drawable.cake_2),
//                    new RecipeStep(1, "mix all the ingredients"),
//                    new RecipeStep(2, "preheat the oven"),
//                    new RecipeStep(3, "stir"),
//                    new RecipeStep(4, "bake"));
//        }else if(Constants.rawRxLive == 3){
//
//            addRecipe(new Recipe(1, "Cake", R.drawable.cake_1, "Wonderful cake"),
//                    new RecipeStep(1,1, "mix all the ingredients of wonderful cake"),
//                    new RecipeStep(1,2, "preheat the oven"),
//                    new RecipeStep(1,3, "stir"),
//                    new RecipeStep(1,4, "bake"));
//
//            addRecipe(new Recipe(2,"Pie", R.drawable.pie_1,"Delicious Pie"));
//
//            addRecipe(new Recipe(3,"Pound Cake", R.drawable.cake_2,"Fluffy cake"),
//                    new RecipeStep(3,1, "mix all the ingredients"),
//                    new RecipeStep(3,2, "preheat the oven"),
//                    new RecipeStep(3,3, "stir"),
//                    new RecipeStep(3,4, "bake"));
//        }

    }

    private static void addRecipe(Recipe recipe, RecipeStep... steps)
    {
        if (steps.length > 0)
        {
            recipe.setSteps(Arrays.asList(steps));
        }
        recipesList.add(recipe);
    }
}
