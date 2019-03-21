package com.example.habibdurodola.takehome.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.habibdurodola.takehome.R;
import com.example.habibdurodola.takehome.activity.MainApplication;
import com.example.habibdurodola.takehome.fragment.mainFragment.MainFragment;
import com.example.habibdurodola.takehome.model.github.Github;

import java.util.List;


/**
 * Created by habib.durodola on 2017-08-17.
 */

public class GitHubAdapterAdapter extends RecyclerView.Adapter<GitHubAdapterAdapter.ItemViewHolder> {
    private List<Github> nodeList;
    Context context;
    private MainFragment fragment;
    private FragmentActivity fragmentActivity;
    private Github github;

    public GitHubAdapterAdapter(List<Github> nodeList, FragmentActivity fragmentActivity, MainFragment fragment) {
        this.nodeList = nodeList;
        this.context = MainApplication.getInstance();
       this.fragment = fragment;
       this.fragmentActivity = fragmentActivity;

    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell, viewGroup, false);
        return new ItemViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ItemViewHolder groupViewHolder, final int i) {

      github =  nodeList.get(i);
        groupViewHolder.name.setText(github.getName());

        groupViewHolder.details.setText(github.getDescription().toString());


    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name, details;
        private LinearLayout row;


        ItemViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            details = itemView.findViewById(R.id.location);
            row = itemView.findViewById(R.id.row);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragment.switchFragment(fragmentActivity,""+nodeList.get(getAdapterPosition()).getForks(), ""+nodeList.get(getAdapterPosition()).getStargazersCount());

                }
            });


        }
    }

    @Override
    public int getItemCount() {
        if (nodeList != null) {
            return nodeList.size();
        } else {
            return -1;

        }
    }

}