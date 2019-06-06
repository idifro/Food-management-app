package io.brink.thoughtfood;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayList<String> mItemName;


    public MainAdapter(ArrayList<String> mItemName) {
        this.mItemName = mItemName;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder viewHolder, int i) {
        viewHolder.itemName.setText(mItemName.get(i));


    }

    @Override
    public int getItemCount() {
        return mItemName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView itemName;
        public EditText scoreNumber;
        public EditText feedBack;
        public Button btnsubmit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.exampleText);
            scoreNumber = itemView.findViewById(R.id.scoreNumber);
            feedBack= itemView.findViewById(R.id.comment);
            btnsubmit = itemView.findViewById(R.id.btnsubmit);



        }
    }
}
