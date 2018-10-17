package youjaeyoung.org.footsteps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.checked;

/**
 * Created by dongseoul on 2017. 12. 5..
 */

public class CustomListViewCheckAdapter extends BaseAdapter {
    public ViewHolder viewholder;
    public ArrayList<ListViewItemCheck> listViewItemList = new ArrayList<ListViewItemCheck>();
    public CustomListViewCheckAdapter(){

    }

    @Override
    public int getCount(){
        return listViewItemList.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listviewcheck,parent,false);

            viewholder = new ViewHolder();
            viewholder.txtV = (TextView)convertView.findViewById(R.id.textView1);
            viewholder.cb_checkbox = (CheckBox)convertView.findViewById(R.id.checkBox1);
            convertView.setTag(viewholder);
        }else
            viewholder = (ViewHolder)convertView.getTag();

        ListViewItemCheck listViewItem = listViewItemList.get(position);
        viewholder.txtV.setText(listViewItem.getText());
        /*viewholder.cb_checkbox.setChecked(false);*/
        /*viewholder.cb_checkbox.setChecked(((ListView)parent).isItemChecked(position));*/

        return convertView;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return listViewItemList.get(position);
    }

    public void addItem(String text){
        ListViewItemCheck item = new ListViewItemCheck();

        item.setText(text);

        listViewItemList.add(item);
    }
}

class ViewHolder{
    TextView txtV;
    CheckBox cb_checkbox;
}
