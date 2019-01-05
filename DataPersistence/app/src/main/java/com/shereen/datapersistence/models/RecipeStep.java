package com.shereen.datapersistence.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by shereen on 12/29/18
 */

@Entity(tableName = "recipe_steps",
        primaryKeys = {"step_number", "recipe_id"},
        indices = {@Index("recipe_id")},    //makes queries faster
        foreignKeys = @ForeignKey(entity = Recipe.class, parentColumns = "main_recipe_id", childColumns = "recipe_id", onDelete = CASCADE)
)
public class RecipeStep {

    //private long id;

    @ColumnInfo(name = "recipe_id")
    private long recipeId;

    @ColumnInfo(name = "step_number")
    private int stepNumber;

    private String instruction;

    public RecipeStep(){

    }

    @Ignore
    public RecipeStep (int stepNumber, String instruction)
    {
        this.stepNumber = stepNumber;
        this.instruction = instruction;
    }

    @Ignore
    public RecipeStep (long recipeId, int stepNumber, String instruction)
    {
        this.recipeId = recipeId;
        this.stepNumber = stepNumber;
        this.instruction = instruction;
    }

//    public long getId ()
//    {
//        return id;
//    }
//
//    public void setId(long id)
//    {
//        this.id = id;
//    }


    public long getRecipeId()
    {
        return recipeId;
    }

    public void setRecipeId(long recipeId)
    {
        this.recipeId = recipeId;
    }

    public int getStepNumber()
    {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber)
    {
        this.stepNumber = stepNumber;
    }

    public String getInstruction()
    {
        return instruction;
    }

    public void setInstruction(String instruction)
    {
        this.instruction = instruction;
    }
}
