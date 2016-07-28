package com.example.anil.saveme;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


/**
   Created by anil on 6/13/16.
 */
public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder> {

    List<Book> books = new ArrayList<>();
    Context context;
    BottomSheetBehavior bottomSheet;

    public BookRecyclerAdapter(List<Book> books, Context context,BottomSheetBehavior bottomSheet) {
        this.books = books;
        this.context = context;
        this.bottomSheet = bottomSheet;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.book_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.author.setText(books.get(position).author);
        holder.title.setText(books.get(position).title);
        holder.index.setText(books.get(position).getId()+"");
        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(view.getContext(), view);
                popup.inflate(R.menu.mfp_overflow_menu_file);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        Toast.makeText(context, item.getTitle()+ " : "+books.get(position).getId(), Toast.LENGTH_SHORT).show();
                        bottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
                        return true;
                    }
                });
                popup.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static  class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,author,index;
        ImageView menu;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            author = (TextView) itemView.findViewById(R.id.author);
            index = (TextView) itemView.findViewById(R.id.index);
            menu = (ImageView) itemView.findViewById(R.id.item_menu);

        }
    }
}
