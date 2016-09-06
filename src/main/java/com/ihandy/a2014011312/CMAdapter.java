package com.ihandy.a2014011312;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by weixy on 2016/9/5.
 */
public class CMAdapter extends BaseAdapter
{
    private ArrayList<String> datas = new ArrayList<>();
    private Context mContext;
    private int halving;

    private SQLiteDatabase db;

    public CMAdapter(Context mContext)
    {
        db = DBHelper.getInstance(mContext);

        datas.add("Watched");
        Cursor c1 = db.query ("watched",null,null,null,null,null,null);
        while(c1.moveToNext())
        {
            datas.add(c1.getString(c1.getColumnIndex("categorytext")));
        }
        datas.add("Unwatched");
        Cursor c2 = db.query ("unwatched",null,null,null,null,null,null);
        while(c2.moveToNext())
        {
            datas.add(c2.getString(c2.getColumnIndex("categorytext")));
        }

        halving = c1.getCount() + 1;
//        datas.add("1");
//        datas.add("2");
//        datas.add("3");
//        datas.add("4");
//        datas.add("555");
//        datas.add("66666");
        this.mContext = mContext;

    }

    @Override
    public int getCount()
    {
        return datas.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.catogary_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.category_title = (TextView) convertView.findViewById(R.id.category_text);
            viewHolder.arrow_pic = (ImageView) convertView.findViewById(R.id.category_pic);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(position == 0)
        {
            viewHolder.category_title.setText(datas.get(position));
            viewHolder.category_title.setTextColor(Color.rgb(255, 0, 0));
 //           viewHolder.arrow_pic.setVisibility(View.INVISIBLE);
        }
        else if(position == halving)
        {
            viewHolder.category_title.setText(datas.get(position));
            viewHolder.category_title.setTextColor(Color.rgb(255, 0, 0));
 //           viewHolder.arrow_pic.setVisibility(View.INVISIBLE);
        }
        else
        {
            if(position < halving)
            {
                viewHolder.category_title.setText(datas.get(position));
                viewHolder.arrow_pic.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.down));
            }
            else
            {
                viewHolder.category_title.setText(datas.get(position));
                viewHolder.arrow_pic.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.up));
            }

        }
        return convertView;
    }

    private class ViewHolder
    {
        TextView category_title;
        ImageView arrow_pic;
    }
}
