package net.rmoreno.ideas;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class CustomAdapter extends ParseQueryAdapter<ParseObject>{

	Context mContext;
	
	public CustomAdapter(Context context) {
		
		super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
			public ParseQuery create() {
				ParseQuery query = new ParseQuery("Ideas");
				query.addAscendingOrder("Title");

				return query;
			}
		});
		mContext = context;
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

		  ImageView icon = (ImageView) v.findViewById(R.id.image);
		  descriptionView.setText(object.getString("Title"));
		  
		  Typeface robotoBold = Typeface.createFromAsset(mContext.getAssets(),
			      "Roboto-Bold.ttf");
			      descriptionView.setTypeface(robotoBold);
		  

		  
		  return v;
		}
}
