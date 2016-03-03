package layout;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mars.httpapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_page2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_page2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_page2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PAGE = "ARG_PAGE";

    // json object response url
    private String urlJsonObj = "https://studygroupformer.herokuapp.com/users/1";

    // json array response url
    private String urlJsonArry = "https://studygroupformer.herokuapp.com/studygroups";



    // Progress dialog
    private ProgressDialog pDialog;

    private TextView txtResponse;

    // temporary string to show the parsed response
    private String jsonResponse;
    private Button btnMakeArrayRequest;
    private Button btnMakeObjectRequest;
    // TODO: Rename and change types of parameters
    private int mPage;
    //private String mParam2;



    public fragment_page2() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static fragment_page2 newInstance(int page) {
        fragment_page2 fragment = new fragment_page2();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPage = getArguments().getInt(ARG_PAGE);
            //mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_page2, container, false);
        FrameLayout frameLayout = (FrameLayout) view;
       // textView.setText("Fragment2 #" + mPage);
        //http related
        btnMakeObjectRequest = (Button) view.findViewById(R.id.JsonObjButton);

        txtResponse = (TextView) view.findViewById(R.id.JsonDataView);
        txtResponse.setMovementMethod(new ScrollingMovementMethod());

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        //end http related
        return view;
    }



}
