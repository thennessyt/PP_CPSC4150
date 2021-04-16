package cpsc4150.epitaph.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cpsc4150.epitaph.R;
import cpsc4150.epitaph.activities.CodeMemorialActivity;

public class MemorialViewFragment extends Fragment
{
    int memorialID;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle extra = getActivity().getIntent().getExtras();
        memorialID = extra.getInt(CodeMemorialActivity.EXTRA_MEMORIAL_CODE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memorial_view, container, false);
    }
}