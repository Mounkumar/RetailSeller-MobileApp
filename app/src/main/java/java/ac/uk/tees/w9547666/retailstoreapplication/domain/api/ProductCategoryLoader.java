package java.ac.uk.tees.w9547666.retailstoreapplication.domain.api;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.ac.uk.tees.w9547666.retailstoreapplication.R;
import java.ac.uk.tees.w9547666.retailstoreapplication.domain.mock.DummyWebServer;
import java.ac.uk.tees.w9547666.retailstoreapplication.util.Utils;
import java.ac.uk.tees.w9547666.retailstoreapplication.util.Utils.AnimationType;
import java.ac.uk.tees.w9547666.retailstoreapplication.view.activities.HomeActivity;
import java.ac.uk.tees.w9547666.retailstoreapplication.view.adapter.CategoryListAdapter;
import java.ac.uk.tees.w9547666.retailstoreapplication.view.adapter.CategoryListAdapter.OnItemClickListener;
import java.ac.uk.tees.w9547666.retailstoreapplication.view.fragment.ProductOverviewFragment;

/**
 * @author w9547666
 */

public class ProductCategoryLoader extends AsyncTask<String, Void, Void> {

    private static final int NUMBER_OF_COLUMNS = 2;
    private Context context;
    private RecyclerView recyclerView;

    public ProductCategoryLoader(RecyclerView listView, Context context) {

        this.recyclerView = listView;
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
            ((HomeActivity) context).getProgressBar();

        if (recyclerView != null) {
            CategoryListAdapter simpleRecyclerAdapter = new CategoryListAdapter(
                    context);

            recyclerView.setAdapter(simpleRecyclerAdapter);

            simpleRecyclerAdapter
                    .SetOnItemClickListener((view, position) -> {

                       Utils.CURRENT_CATEGORY = position;

                        Utils.switchFragmentWithAnimation(
                                R.id.frag_container,
                                new ProductOverviewFragment(),
                                ((HomeActivity) context), null,
                                AnimationType.SLIDE_LEFT);

                    });
        }

    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DummyWebServer.getDummyWebServer().addCategory();

        return null;
    }

}