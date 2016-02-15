package layout;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.mars.httpapp.AppController;
import com.example.mars.httpapp.MainActivity;
import com.example.mars.httpapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_page3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_page3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_page3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PAGE = "ARG_PAGE";

    // json object response url
    private String urlJsonObj = "http://10.0.2.2:3000/users/1";

    // json array response url
    private String urlJsonArry = "http://10.0.2.2:3000/studygroups";

    // Progress dialog
    private ProgressDialog pDialog;

    private TextView txtResponse;

    // temporary string to show the parsed response
    private String jsonResponse;
    private Button btnMakeArrayRequest;

    // TODO: Rename and change types of parameters
    private int mPage;
    //private String mParam2;



    public fragment_page3() {
        // Required empty public constructor
    }




    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_page.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_page3 newInstance(int page) {
        fragment_page3 fragment = new fragment_page3();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //check for precached data in singleton
        String recoverydata= AppController.getInstance().grouplist;
        if (recoverydata != null) {
            // Restore last state for checked position.
            txtResponse.setText(recoverydata);
        }
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
        View view = inflater.inflate(R.layout.fragment_fragment_page3, container, false);
        RelativeLayout frameLayout = (RelativeLayout) view;
        //textView.setText("Fragment3 #" + mPage);
        btnMakeArrayRequest = (Button) view.findViewById(R.id.JsonArrayButton);
        //ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollview);
        txtResponse = (TextView) view.findViewById(R.id.JsonDataView);
        txtResponse.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                txtResponse.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        txtResponse.setMovementMethod(new ScrollingMovementMethod());

        btnMakeArrayRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json array request
                makeJsonArrayRequest();
            }
        });

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        return view;
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    /**
     * Method to make json array request where response starts with [
     * */
    private void makeJsonArrayRequest() {

        showpDialog();

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("response:", response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);

                                String name = person.getString("department") + person.getString("class_number");
                                String date = person.getString("date");
                                String time = person.getString("time");
                                String description = person.getString("description");
                                /*JSONObject phone = person
                                        .getJSONObject("phone");
                                String home = phone.getString("home");
                                String mobile = phone.getString("mobile");*/

                                jsonResponse += "Name: " + name + "\n\n";
                                jsonResponse += "Date: " + date + "\n\n";
                                jsonResponse += "Time:" + time + "\n\n";
                                jsonResponse += "Description:" + description + "\n\n";
                                //jsonResponse += "Mobile: " + mobile + "\n\n\n";

                            }

                            txtResponse.setText(jsonResponse);
                            AppController.getInstance().grouplist = jsonResponse;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: ", error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }


}
