package edu.abcd.doancnpm.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import edu.abcd.doancnpm.R;
import edu.abcd.doancnpm.TabLayout.SectionPagerAdapter;
import edu.abcd.doancnpm.TabLayout.TinKhoahocFragment;
import edu.abcd.doancnpm.TabLayout.TinThethaoFragment;
import edu.abcd.doancnpm.TabLayout.TintucFragment;
import edu.abcd.doancnpm.glide.photo;
import edu.abcd.doancnpm.glide.photoapdapter;
import me.relex.circleindicator.CircleIndicator;

import android.widget.SearchView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrangchinhFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrangchinhFragment extends Fragment {
    private View myFragment;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPager viewPager2;
    private CircleIndicator circleIndicator;
    private photoapdapter Photoapdapter;
    private SearchView searchView;
    private ListView searchResultsList;
    private ArrayAdapter<String> searchAdapter;
    private FirebaseFirestore db;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public TrangchinhFragment() {
        // Required empty public constructor
    }

    public static TrangchinhFragment newInstance(String param1, String param2) {
        TrangchinhFragment fragment = new TrangchinhFragment();
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

        Photoapdapter = new photoapdapter(this, getLisPhoto());
        db = FirebaseFirestore.getInstance();
    }

    private List<photo> getLisPhoto() {
        List<photo> list = new ArrayList<>();
        list.add(new photo(R.drawable.paper1));
        list.add(new photo(R.drawable.paper2));
        list.add(new photo(R.drawable.paper3));
        list.add(new photo(R.drawable.paper4));
        list.add(new photo(R.drawable.paper5));
        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_trangchinh, container, false);

        // Khởi tạo các thành phần UI
        viewPager2 = myFragment.findViewById(R.id.viewpager1);
        circleIndicator = myFragment.findViewById(R.id.circle_center);
        viewPager = myFragment.findViewById(R.id.viewPager);
        tabLayout = myFragment.findViewById(R.id.tabLayout);

        // Thiết lập viewPager2 và circleIndicator
        viewPager2.setAdapter(Photoapdapter);
        circleIndicator.setViewPager(viewPager2);
        Photoapdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        // Khởi tạo SearchView và ListView
        searchView = myFragment.findViewById(R.id.searchId);
        searchResultsList = myFragment.findViewById(R.id.searchResultsList);

        searchAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, new ArrayList<>());
        searchResultsList.setAdapter(searchAdapter);

        // Thiết lập listener cho SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {
                    performSearch(newText);
                } else {
                    searchResultsList.setVisibility(View.GONE);
                }
                return true;
            }

        });

        FloatingActionButton fab = myFragment.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddNewsFragment()).commit();
            }
        });

        return myFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setUpViewPager(ViewPager viewPager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new TintucFragment(), "Tin tức");
        adapter.addFragment(new TinKhoahocFragment(), "Khoa học");
        adapter.addFragment(new TinThethaoFragment(), "Thể thao");
        viewPager.setAdapter(adapter);
    }

    private void performSearch(String query) {
        query = query.toLowerCase();
        String finalQuery = query;
        db.collection("News")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<String> results = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        String title = document.getString("title");
                        String content = document.getString("content");
                        String category = document.getString("category");

                        if (title != null && title.toLowerCase().contains(finalQuery)) {
                            results.add("Tiêu đề: " + title);
                        } else if (content != null && content.toLowerCase().contains(finalQuery)) {
                            String snippet = content.substring(0, Math.min(content.length(), 100)) + "...";
                            results.add("Nội dung: " + snippet);
                        } else if (category != null && category.toLowerCase().contains(finalQuery)) {
                            results.add("Thể loại: " + category + " - " + title);
                        }
                    }
                    updateSearchResults(results);
                })
                .addOnFailureListener(e -> {
                    Log.e("TrangchinhFragment", "Tìm kiếm thất bại: ", e);
                });
    }


    private void updateSearchResults(List<String> results) {
        searchAdapter.clear();
        searchAdapter.addAll(results);
        searchResultsList.setVisibility(results.isEmpty() ? View.GONE : View.VISIBLE);
    }
}
