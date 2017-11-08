package com.example.anton.coursework;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DepartmentsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DepartmentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DepartmentsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DepartmentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DepartmentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DepartmentsFragment newInstance(String param1, String param2) {
        DepartmentsFragment fragment = new DepartmentsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Button btnAdd;
    Intent intent;
    DatabaseHelper mDatabaseHelper;
    ListView mListView;
    DepartmentAdapter departmentAdapter;

    @Override
    public void onStart() {
        super.onStart();

        mDatabaseHelper = mDatabaseHelper.getInstance(getActivity());

        btnAdd = (Button) getActivity().findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Manager> managers = new ArrayList<>();
                managers.addAll(mDatabaseHelper.getAllManagers());
                if (managers.size() != 0) {
                    intent = new Intent(getActivity(), AddDepartmentActivity.class);
                    startActivity(intent);
                }
                else {
                    toastMessage("Спочатку додайте менеджера!");
                }
            }
        });


        mListView = (ListView) getActivity().findViewById(R.id.listView);

        ArrayList<Department> departments = new ArrayList<>();
        departments.addAll(mDatabaseHelper.getAllDepartments());

        departmentAdapter = new DepartmentAdapter(getActivity(), departments);
        mListView.setAdapter(departmentAdapter);

    }

    private void toastMessage(String message) {
        Toast.makeText(MainActivity.getAppContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_departments, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
