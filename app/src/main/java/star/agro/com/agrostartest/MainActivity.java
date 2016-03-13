package star.agro.com.agrostartest;

/**
 * Created by vikasmalhotra on 3/13/16.
 */

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import star.agro.com.agrostartest.Adapter.CatalogueAdapter;
import star.agro.com.agrostartest.Adapter.ImageSliderAdapter;
import star.agro.com.agrostartest.Model.Catalogue;

public class MainActivity extends ActionBarActivity {

    ProgressDialog progressDialog;

    ViewPager imageSlider;
    ImageSliderAdapter imageSliderAdapter;

    RecyclerView recyclerView;
    CatalogueAdapter adapter;
    List<Catalogue> data;
    AsyncTask dataGenerator;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String languageToLoad;
        Locale locale;
        Configuration config;
        switch (item.getItemId()) {
            case R.id.langEng:
                languageToLoad = "en"; // your language
                locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                generateDummyData("");
                return true;
            case R.id.langHi:
                languageToLoad = "hi"; // your language
                locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                data.clear();
                generateDummyData("");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            generateDummyData(query);
        }
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        setupViews();
        generateDummyData("");
    }

    protected int getLayout() {
        return R.layout.main_layout;
    }

    protected void setupViews() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait...");

        imageSlider = (ViewPager) this.findViewById(R.id.imageSlider);
        imageSliderAdapter = new ImageSliderAdapter();
        imageSlider.setOffscreenPageLimit(2);
        imageSlider.setAdapter(imageSliderAdapter);

        recyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 3);
        recyclerView.setLayoutManager(manager);
        adapter = new CatalogueAdapter();
        recyclerView.setAdapter(adapter);

        data = new ArrayList<>();
    }

    protected void generateDummyData(final String query) {
        dataGenerator = null;
        dataGenerator = new AsyncTask() {
            String mobile_model = "";
            String corp = "";

            @Override
            protected void onPreExecute() {
                progressDialog.show();
                data.clear();
                mobile_model = MainActivity.this.getResources().getString(R.string.mobile_no);
                corp = MainActivity.this.getResources().getString(R.string.corp);
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] params) {
                for (int i = 0; i < 1000; i++) {
                    Catalogue catalogue = new Catalogue();
                    catalogue.title = mobile_model + i;
                    catalogue.subtitle = corp + i;
                    catalogue.uri = "";
                    if (catalogue.title.contains(query) || catalogue.subtitle.contains(query)) {
                        data.add(catalogue);
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                adapter.setData(data);
                progressDialog.dismiss();
            }
        };
        dataGenerator.execute();
    }
}
