package cpsc4150.epitaph.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cpsc4150.epitaph.EpitaphDatabase;
import cpsc4150.epitaph.R;
import cpsc4150.epitaph.activities.MemorialViewActivity;
import cpsc4150.epitaph.models.Memorial;

public class MemorialViewFragment extends Fragment
{
    private int memorialID;
    private ImageView memorialImage;
    private TextView memorialEpitaph;
    private TextView memorialName;
    private TextView memorialDateRange;
    private TextView memorialDescription;
    private EpitaphDatabase db;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle extra = getActivity().getIntent().getExtras();
        memorialID = extra.getInt(MemorialViewActivity.EXTRA_MEMORIAL_ID);

        memorialImage = getActivity().findViewById(R.id.memorialImage);
        memorialEpitaph = getActivity().findViewById(R.id.memorialEpitaph);
        memorialName = getActivity().findViewById(R.id.memorialName);
        memorialDateRange = getActivity().findViewById(R.id.memorialDateRange);
        memorialDescription = getActivity().findViewById(R.id.memorialDescription);

        db = EpitaphDatabase.getInstance(getActivity().getApplicationContext());
        Memorial memorial = db.memorialDao().getMemorial(memorialID);

        //TODO: SET IMAGE DRAWABLE
//        memorialImage.setImageDrawable();
        //Set epitaph
        memorialEpitaph.setText(memorial.getEpitaph());
        //Set name of deceased
        memorialName.setText(memorial.getName());
        //Set birth to death range
        memorialDateRange.setText(memorial.getByear() + " - " + memorial.getDyear());
        //Set description
        memorialDescription.setText(memorial.getDescription());
        //TODO: COMMENTS RECYCLER PROBABLY
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memorial_view, container, false);
    }
}