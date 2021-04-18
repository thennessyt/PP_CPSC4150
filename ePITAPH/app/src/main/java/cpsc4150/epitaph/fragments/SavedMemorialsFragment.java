/*
    Teresa Chen - C80914941 - tlchen@g.clemson.edu
    Taylor Hennessey - C64479320 - tthenne@g.clemson.edu

    Code adapted from Zybooks
 */

package cpsc4150.epitaph.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cpsc4150.epitaph.EpitaphDatabase;
import cpsc4150.epitaph.R;
import cpsc4150.epitaph.activities.MemorialViewActivity;
import cpsc4150.epitaph.models.Account;
import cpsc4150.epitaph.models.Memorial;
import cpsc4150.epitaph.models.SavedMemorials;

public class SavedMemorialsFragment extends Fragment
{
    public interface OnMemorialSelectedListener
    {
        void onMemorialSelected(int memorialID);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof SavedMemorialsFragment.OnMemorialSelectedListener)
        {
            mListener = (SavedMemorialsFragment.OnMemorialSelectedListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement OnMemorialSelectedListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            // Notify activity of memorial selection
            String memorialID = (String) view.getTag();
            mListener.onMemorialSelected(Integer.parseInt(memorialID));
        }
    };

    // Reference to the activity
    private SavedMemorialsFragment.OnMemorialSelectedListener mListener;
    private EpitaphDatabase db;
    private int accountID;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        db = EpitaphDatabase.getInstance(getActivity().getApplicationContext());
        Bundle extra = getActivity().getIntent().getExtras();
        accountID = extra.getInt(Account.EXTRA_ACCOUNT_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_memorials, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_saved_memorials);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Send memorials to recycler view
        List<SavedMemorials> list = db.savedMemorialsDao().getSavedMemorials(accountID);
        List<Memorial> memorialList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++)
        {
            Memorial memorial = db.memorialDao().getMemorial(list.get(i).memorialID);
            memorialList.add(memorial);
        }
        MemorialAdapter adapter = new MemorialAdapter(memorialList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private class MemorialHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        private Memorial memorial;

        private TextView memorialItemName;
        private TextView memorialItemRange;
        private TextView memorialItemLocation;

        public MemorialHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater.inflate(R.layout.list_item_memorial, parent, false));
            itemView.setOnClickListener(this);
            memorialItemName = itemView.findViewById(R.id.memorialItemName);
            memorialItemRange = itemView.findViewById(R.id.memorialItemRange);
            memorialItemLocation = itemView.findViewById(R.id.memorialItemLocation);
        }

        public void bind(Memorial memorial)
        {
            this.memorial = memorial;
            memorialItemName.setText(memorial.getName());
            memorialItemRange.setText(memorial.getByear() + " - " + memorial.getDyear());
            String locations = "";
            for(int i = 0; i < memorial.getLocations().size(); i++)
            {
                locations += memorial.getLocations().get(i) + "\n";
            }
            memorialItemLocation.setText(locations);
        }

        @Override
        public void onClick(View view)
        {
            // Tell ListActivity what memorial was clicked
            mListener.onMemorialSelected(memorial.getId());
        }
    }

    private class MemorialAdapter extends RecyclerView.Adapter<SavedMemorialsFragment.MemorialHolder>
    {
        private List<Memorial> memorials;

        public MemorialAdapter(List<Memorial> memorials)
        {
            this.memorials = memorials;
        }

        @Override
        public SavedMemorialsFragment.MemorialHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new SavedMemorialsFragment.MemorialHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(SavedMemorialsFragment.MemorialHolder holder, int position)
        {
            Memorial memorial = memorials.get(position);
            holder.bind(memorial);
        }

        @Override
        public int getItemCount()
        {
            return memorials.size();
        }
    }
}