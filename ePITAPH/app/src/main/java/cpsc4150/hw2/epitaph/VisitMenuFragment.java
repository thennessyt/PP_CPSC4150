package cpsc4150.hw2.epitaph;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VisitMenuFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visit_menu, container, false);
    }

    public void onSavedClick(View view)
    {
        //Start SavedMemorialsActivity
        Intent intent = new Intent(getActivity(), SavedMemorialsActivity.class);
        startActivity(intent);
    }

    public void onEnterClick(View view)
    {
        //Start CodeMemorialActivity
        Intent intent = new Intent(getActivity(), CodeMemorialActivity.class);
        startActivity(intent);
    }

    public void onLocalClick(View view)
    {
        //Start LocalMemorialsActivity
        Intent intent = new Intent(getActivity(), LocalMemorialsActivity.class);
        startActivity(intent);
    }
}