package cpsc4150.epitaph;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class CodeMemorialFragment extends Fragment
{
    public static final String EXTRA_MEMORIAL_CODE = "cpsc4150.epitaph.memorial_code";

    private EditText memorialCodeView;
    private Button goToMemorialButtonView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        memorialCodeView = getActivity().findViewById(R.id.enterMemorialCodeEdit);
        goToMemorialButtonView = getActivity().findViewById(R.id.enterMemorialCodeButton);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_code_memorial, container, false);
    }

    public void onGotoClick(View view)
    {
        //Create intent to start MemorialViewActivity
        Intent intent = new Intent(getActivity(), MemorialViewActivity.class);

        //Put memorial code into intent
        intent.putExtra(EXTRA_MEMORIAL_CODE, memorialCodeView.getText());

        startActivity(intent);
    }
}