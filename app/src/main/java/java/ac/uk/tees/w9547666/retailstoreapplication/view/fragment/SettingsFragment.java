package java.ac.uk.tees.w9547666.retailstoreapplication.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import java.ac.uk.tees.w9547666.retailstoreapplication.R;
import java.ac.uk.tees.w9547666.retailstoreapplication.util.Utils;
import java.ac.uk.tees.w9547666.retailstoreapplication.util.Utils.AnimationType;
import java.ac.uk.tees.w9547666.retailstoreapplication.view.activities.HomeActivity;

/**
 * @author w9547666
 */
/**
 * Fragment that appears in the "content_frame", shows a animal.
 */
public class SettingsFragment extends Fragment {

    private TextView submitLog;
    private Toolbar mToolbar;

    /**
     * Instantiates a new settings fragment.
     */
    public SettingsFragment() {
        // Empty constructor required for fragment subclasses
    }

    public static Fragment newInstance() {

        return new SettingsFragment();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
     * android.view.ViewGroup, android.os.Bundle)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_settings, container,
                false);

        getActivity().setTitle("About App");

        mToolbar = (Toolbar) rootView.findViewById(R.id.htab_toolbar);
        if (mToolbar != null) {
            ((HomeActivity) getActivity()).setSupportActionBar(mToolbar);
        }

        if (mToolbar != null) {
            ((HomeActivity) getActivity()).getSupportActionBar()
                    .setDisplayHomeAsUpEnabled(true);

            mToolbar.setNavigationIcon(R.drawable.ic_drawer);

        }

        mToolbar.setNavigationOnClickListener(v -> ((HomeActivity) getActivity()).getmDrawerLayout()
                .openDrawer(GravityCompat.START));

        mToolbar.setTitleTextColor(Color.WHITE);



        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener((v, keyCode, event) -> {

            if (event.getAction() == KeyEvent.ACTION_UP
                    && keyCode == KeyEvent.KEYCODE_BACK) {

                Utils.switchContent(R.id.frag_container,
                        Utils.HOME_FRAGMENT,
                        ((HomeActivity) (getContext())),
                        AnimationType.SLIDE_UP);

            }
            return true;
        });



        return rootView;
    }
}
