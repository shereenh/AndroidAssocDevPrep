package com.shereen.datapersistence.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.v4.text.util.LinkifyCompat;

import java.util.List;

/**
 * Created by shereen on 12/29/18
 */
@Entity(tableName = "recipe",
        indices = {@Index(value = "main_recipe_id", unique = true)})
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "main_recipe_id")
    private long recipeId;

    private String name;

    private String description;

    @ColumnInfo(name = "image_resource_id")
    private int imageResourceId;

    @Ignore
    private List<RecipeStep> steps;

    @ColumnInfo(name = "number_of_stars")
    private Integer numberOfStars;

    public Recipe ()
    {
    }

    @Ignore
    public Recipe (String name, String description, int imageResourceId)
    {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    @Ignore
    public Recipe (long recipeId, String name, int imageResourceId, String description)
    {
        this.recipeId = recipeId;
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    @Ignore
    public Recipe (long id, String name, String description, int imageResourceId)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }


    public long getId ()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getImageResourceId()
    {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId)
    {
        this.imageResourceId = imageResourceId;
    }

    public List<RecipeStep> getSteps()
    {
        return steps;
    }

    public void setSteps(List<RecipeStep> steps)
    {
        this.steps = steps;
    }

    public Integer getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Integer numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String toString ()
    {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageResourceId=" + imageResourceId +
                ", steps=" + steps +
                '}';
    }
}
