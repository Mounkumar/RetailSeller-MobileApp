package java.ac.uk.tees.w9547666.retailstoreapplication.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

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

public class ContactUsFragment extends Fragment {

    private Toolbar mToolbar;

    public ContactUsFragment() {

    }

    public static Fragment newInstance() {

        return new ContactUsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_about, container,
                false);

        getActivity().setTitle("Contact Us");


        mToolbar = (Toolbar) rootView.findViewById(R.id.htab_toolbar);
        if (mToolbar != null) {
            ((HomeActivity) getActivity()).setSupportActionBar(mToolbar);
        }

        if (mToolbar != null) {
            ((HomeActivity) getActivity()).getSupportActionBar()
                    .setDisplayHomeAsUpEnabled(true);

            mToolbar.setNavigationIcon(R.drawable.ic_drawer);

        }

        mToolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).getmDrawerLayout()
                        .openDrawer(GravityCompat.START);
            }
        });

        mToolbar.setTitleTextColor(Color.WHITE);

        rootView.findViewById(R.id.locations).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                    }
                });

        rootView.findViewById(R.id.contact_num).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + "07824828482"));
                        startActivity(callIntent);

                    }
                });

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP
                        && keyCode == KeyEvent.KEYCODE_BACK) {

                    Utils.switchContent(R.id.frag_container,
                            Utils.HOME_FRAGMENT,
                            ((HomeActivity) (getContext())),
                            AnimationType.SLIDE_UP);

                }
                return true;
            }
        });


        return rootView;
    }
}
