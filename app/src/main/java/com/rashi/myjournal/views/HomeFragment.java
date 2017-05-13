package com.rashi.myjournal.views;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.rashi.myjournal.R;
import com.rashi.myjournal.adapter.JournalAdapter;
import com.rashi.myjournal.models.JournalEntry;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {

    private Realm realm;

    @BindView(R.id.note_title)
    EditText note_title;

    @BindView(R.id.note_content)
    EditText note_content;

    @BindView(R.id.journals_list)
    RecyclerView listJournals;

    JournalAdapter j;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);
        realm = Realm.getDefaultInstance();

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RealmResults<JournalEntry> result = realm.where(JournalEntry.class).findAll();

        ArrayList<JournalEntry> n = new ArrayList<JournalEntry>(result);

        j = new JournalAdapter(getContext(), n);

        listJournals.setHasFixedSize(true);
        listJournals.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        listJournals.setAdapter(j);
    }

    @OnClick(R.id.btn_add)
    public void add_journal() {
        if(note_content.getText().toString().equals("") || note_title.getText().toString().equals("")) {
            Toast.makeText(getContext(), "hey enter something", Toast.LENGTH_SHORT).show();
        }
        else {
            String title = note_title.getText().toString();
            String content = note_content.getText().toString();

            realm.beginTransaction();
            JournalEntry journalEntry = realm.createObject(JournalEntry.class);
            journalEntry.setTitle(title);
            journalEntry.setContent(content);
            realm.commitTransaction();

            RealmResults<JournalEntry> result = realm.where(JournalEntry.class).findAll();

            ArrayList<JournalEntry> n = new ArrayList<JournalEntry>(result);

            j.reloadList(journalEntry);
            Toast.makeText(getContext(), title + ": " + content + " size: " + result.size(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
