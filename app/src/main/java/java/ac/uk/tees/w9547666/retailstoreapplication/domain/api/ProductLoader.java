package java.ac.uk.tees.w9547666.retailstoreapplication.domain.api;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.ac.uk.tees.w9547666.retailstoreapplication.domain.mock.DummyWebServer;
import java.ac.uk.tees.w9547666.retailstoreapplication.model.Repository;
import java.ac.uk.tees.w9547666.retailstoreapplication.view.activities.HomeActivity;
import java.ac.uk.tees.w9547666.retailstoreapplication.view.adapter.ProductsInCategoryPagerAdapter;
import java.ac.uk.tees.w9547666.retailstoreapplication.view.fragment.ProductListFragment;
import java.util.Set;

/**
 * @author w9547666
 */

public class ProductLoader extends AsyncTask<String, Void, Void> {

    private Context context;
    private ViewPager viewPager;
    private TabLayout tabs;
    private RecyclerView recyclerView;

    public ProductLoader(RecyclerView listView, Context context,
                         ViewPager viewpager, TabLayout tabs) {

        this.viewPager = viewpager;
        this.tabs = tabs;
        this.context = context;

    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

        if (null != ((HomeActivity) context).getProgressBar())
            ((HomeActivity) context).getProgressBar().setVisibility(
                    View.VISIBLE);

    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        if (null != ((HomeActivity) context).getProgressBar())
            ((HomeActivity) context).getProgressBar().setVisibility(
                    View.GONE);

        setUpUi();

    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

       // FakeWebServer.getFakeWebServer().getAllElectronics();
        DummyWebServer.getDummyWebServer().getAllAlcholicDrinks();

        return null;
    }

    private void setUpUi() {

        setupViewPager();



    }

    private void setupViewPager() {

        ProductsInCategoryPagerAdapter adapter = new ProductsInCategoryPagerAdapter(
                ((HomeActivity) context).getSupportFragmentManager());

        Set<String> keys = Repository.getCenterRepository().getMapOfProductsInCategory()
                .keySet();

        for (String string : keys) {

            adapter.addFrag(new ProductListFragment(string), string);

        }

        viewPager.setAdapter(adapter);

//		viewPager.setPageTransformer(true,
//				new );

        tabs.setupWithViewPager(viewPager);

    }

}