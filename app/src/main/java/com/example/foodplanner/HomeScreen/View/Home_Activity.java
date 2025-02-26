package com.example.foodplanner.HomeScreen.View;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.HomeScreen.Model.Area;
import com.example.foodplanner.HomeScreen.Model.Category;
import com.example.foodplanner.HomeScreen.Model.Recipe;
import com.example.foodplanner.HomeScreen.Presenter.HomeBridge;
import com.example.foodplanner.HomeScreen.Presenter.HomePresenter;
import com.example.foodplanner.HomeScreen.View.Adapter.AreaAdapter;
import com.example.foodplanner.HomeScreen.View.Adapter.CategoryAdapter;
import com.example.foodplanner.HomeScreen.View.Adapter.RandomAdapter;
//import com.example.foodplanner.Presnter.HomePresenter;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class Home_Activity extends AppCompatActivity implements HomeBridge.View {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private RecyclerView recyclerViewRecipes, recyclerViewCategories, recyclerViewArreaes;
    private RandomAdapter recipeAdapter;
    private CategoryAdapter categoryAdapter;
    private AreaAdapter areaAdapter;

    private List<Recipe> recipeList = new ArrayList<>();
    private List<Category> categoryList = new ArrayList<>();
    private List<Area> areaList = new ArrayList<>();

    HomePresenter homePresenter;
//    MealsPresenter mealsPresenter;
//    CategoriesPresenter categoriesPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.random_recycler_view);
        homePresenter=new HomePresenter(this);
        recyclerViewRecipes = findViewById(R.id.rrv);
        recyclerViewCategories = findViewById(R.id.crv);
        recyclerViewArreaes = findViewById(R.id.arv);

        homePresenter.GetMeals();
        homePresenter.GetCategories();
        homePresenter.GetMealsByCountry();

    }

    @Override
    public void onMealsRequestSuccess(List<Recipe> recipeList) {
        showMeals(recipeList);
    }

    private void showMeals(List<Recipe> recipeList) {
        recyclerViewRecipes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));
        recipeAdapter = new RandomAdapter(recipeList, this);
        recyclerViewRecipes.setAdapter(recipeAdapter);
    }

    @Override
    public void onMealsRequestFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCategoriesRequestSuccess(List<Category> categoryList) {
        showCategories(categoryList);
    }

    private void showCategories(List<Category> categoryList) {
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));
        categoryAdapter = new CategoryAdapter(categoryList, this);
        recyclerViewCategories.setAdapter(categoryAdapter);
    }

    @Override
    public void onCategoriesRequestFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealsByCountryRequestSuccess(List<Area> AreaList) {
        showCountries(AreaList);
    }
    private void showCountries(List<Area> AreaList) {
        recyclerViewArreaes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));
        areaAdapter = new AreaAdapter(AreaList,this);
        recyclerViewArreaes.setAdapter(areaAdapter);
    }
    @Override
    public void onMealsByCountryRequestFailure(String message) {

    }
}