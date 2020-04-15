package com.example.gmaildisplay;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.bloco.faker.Faker;

public class ContactAdapter extends BaseAdapter {

    List<ContactModel> contacts; //lay danh sach doi tuong
    Context context; //lay doi tuong Activity

    public ContactAdapter(List<ContactModel> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    public ContactAdapter(List<ContactModel> contacts) {
        this.contacts = contacts;
    }

    @Override
    public int getCount() { //tra ve so luong doi tuong trong danh sach
        return contacts.size();
    }

    @Override
    public Object getItem(int position) { //tra ve doi tuong tai vi tri position
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) { //tra ve id cua doi tuong tai vi tri position
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) { //tao view cho doi tuong ben trong khung nhin

        ViewHolder viewHolder;

        if(convertView == null) { //neu la tao moi view cho doi tuong
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_contact, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imageRound = convertView.findViewById(R.id.image_round);
            viewHolder.textRound = convertView.findViewById(R.id.text_round);
            viewHolder.textName = convertView.findViewById(R.id.text_name);
            viewHolder.textSubject = convertView.findViewById(R.id.text_subject);
            viewHolder.textContent = convertView.findViewById(R.id.text_content);
            viewHolder.textTime = convertView.findViewById(R.id.text_time);
            viewHolder.imageFavorite = convertView.findViewById(R.id.image_favorite);
            convertView.setTag(viewHolder);
        }
        else  //neu la su dung recycle
            viewHolder = (ViewHolder) convertView.getTag();

        //gan du lieu cua doi tuong va cho view
        final ContactModel contact = contacts.get(position);
        viewHolder.textRound.setText(contact.getName().substring(0,1)); //lay ki tu dau trong ten
        viewHolder.textName.setText(contact.getName());
        viewHolder.textSubject.setText(contact.getSubject());
        viewHolder.textContent.setText(contact.getContent());
        viewHolder.textTime.setText(contact.getTime());
        viewHolder.imageRound.setColorFilter(Color.parseColor(contact.getColor()));

        if(contact.isFavorite())
            viewHolder.imageFavorite.setImageResource(R.drawable.ic_star_favorite);
        else
            viewHolder.imageFavorite.setImageResource(R.drawable.ic_star_normal);

        viewHolder.imageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isFavorite = contacts.get(position).isFavorite();
                contacts.get(position).setFavorite(!isFavorite);
                notifyDataSetChanged();
            }
        });


        return convertView;
    }

    class ViewHolder {
        ImageView imageRound;
        TextView textRound;
        TextView textName;
        TextView textSubject;
        TextView textContent;
        TextView textTime;
        ImageView imageFavorite;
    }

}
