package net.rmoreno.ideas;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class CustomAdapter extends ParseQueryAdapter<ParseObject>{

	

	public CustomAdapter(Context context) {
		
		super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
			public ParseQuery create() {
				ParseQuery query = new ParseQuery("Ideas");
				query.whereEqualTo("Title", "hello");
				return query;
			}
		});
	}


	public View getItemView(ParseObject object, View v, ViewGroup parent) {
		  if (v == null) {
		    v = View.inflate(getContext(), R.layout.item_list, null);
		  }
		 
		  // Take advantage of ParseQueryAdapter's getItemView logic for
		  // populating the main TextView/ImageView.
		  // The IDs in your custom layout must match what ParseQueryAdapter expects
		  // if it will be populating a TextView or ImageView for you.
		  super.getItemView(object, v, parent);
		 
		  // Do additional configuration before returning the View.
		  TextView descriptionView = (TextView) v.findViewById(R.id.title);
		  ImageView icon = (ImageView) v.findViewById(R.drawable.ic_launcher);
		  descriptionView.setText(object.getString("title"));
		  return v;
		}
}
