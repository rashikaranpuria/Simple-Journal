package com.rashi.myjournal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rashi.myjournal.R;
import com.rashi.myjournal.models.JournalEntry;

import org.w3c.dom.Text;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by rashi on 8/5/17.
 */

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.ViewHolder> {

    private ArrayList<JournalEntry> mJournalEntries;
    private Context mContext;

    public JournalAdapter(Context context, ArrayList<JournalEntry> journalEntries) {
        this.mJournalEntries = journalEntries;
        this.mContext = context;
    }

    @Override
    public JournalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.journal_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.journalTitle.setText(mJournalEntries.get(position).title);
        holder.journalDescription.setText(mJournalEntries.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mJournalEntries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView journalTitle;
        private TextView journalDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            journalTitle = (TextView) itemView.findViewById(R.id.text_journal_title);
            journalDescription = (TextView) itemView.findViewById(R.id.text_journal_description);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mContext, "note clicked", Toast.LENGTH_SHORT).show();
        }
    }

    public void reloadList(JournalEntry newobj) {
        this.mJournalEntries.add(newobj);
        notifyDataSetChanged();
    }

    public void resetList() {
        this.mJournalEntries = new ArrayList<>();
        notifyDataSetChanged();
    }
}
