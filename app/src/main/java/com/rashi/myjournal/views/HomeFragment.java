package com.rashi.myjournal.views;

import android.icu.util.Calendar;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rashi.myjournal.R;
import com.rashi.myjournal.models.Note;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {

    private Realm realm;

    @BindView(R.id.note_title)
    EditText note_title;

    @BindView(R.id.note_content)
    EditText note_content;

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

    @OnClick(R.id.btn_add)
    public void add_journal() {
        if(note_content.getText().toString().equals("") || note_title.getText().toString().equals("")) {
            Toast.makeText(getContext(), "hey enter something", Toast.LENGTH_SHORT).show();
        }
        else {
            String title = note_title.getText().toString();
            String content = note_content.getText().toString();

            realm.beginTransaction();
            Note note = realm.createObject(Note.class);
            note.setTitle(title);
            note.setContent(content);
            realm.commitTransaction();

            RealmResults<Note> result = realm.where(Note.class).findAll();

            Toast.makeText(getContext(), title + ": " + content + " size: " + result.size(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
