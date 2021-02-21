package com.uog.smartlibraryfyp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class SearchBookAdapter extends ArrayAdapter
{
    List list=new ArrayList();
    public SearchBookAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public void add(Book object)
    {
        super.add(object);
        list.add(object);
    }

    public void clearData()
    {
        list.clear();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row=convertView;
        ContactHolder contactHolder;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.search_book_layout,parent,false);
            contactHolder=new ContactHolder();
            contactHolder.txtBookName=(TextView)row.findViewById(R.id.list_item_book_name);
            contactHolder.txtBookAuthor=(TextView)row.findViewById(R.id.list_item_author_name);
            contactHolder.imgBookType=(ImageView) row.findViewById(R.id.list_item_image);
            row.setTag(contactHolder);
        }
        else
        {
            contactHolder=(ContactHolder)row.getTag();
        }
        Book obj=(Book) this.getItem(position);
        contactHolder.txtBookName.setText(""+obj.getBook_name());
        contactHolder.txtBookAuthor.setText("Author : "+obj.getBook_author());
        contactHolder.imgBookType.setImageBitmap(obj.getImg_book_type());
        return row;

    }

    static class ContactHolder
    {
        TextView txtBookName,txtBookAuthor;
        ImageView imgBookType;
    }
}
