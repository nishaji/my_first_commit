package sprytechies.skillregister.ui.experience;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import java.util.ArrayList;
import java.util.List;
import sprytechies.skillregister.data.remote.JsonParse;
import sprytechies.skillregister.data.remote.remote_model.CompanySugession;


public class CompanySuggessionAdapter extends ArrayAdapter<String> {

    private List<String> suggestions;

    public CompanySuggessionAdapter(Activity context, String nameFilter) {
        super(context, android.R.layout.simple_dropdown_item_1line);
        suggestions = new ArrayList<String>();
    }

    @Override
    public int getCount() {
        return suggestions.size();
    }

    @Override
    public String getItem(int index) {
        return suggestions.get(index);
    }

    @Override
    public Filter getFilter() {
        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                JsonParse jp = new JsonParse();
                if (constraint != null) {
                    List<CompanySugession> new_suggestions = jp.getParseJsonBuss(constraint.toString());
                    suggestions.clear();
                    for (int i = 0; i < new_suggestions.size(); i++) {
                        suggestions.add(new_suggestions.get(i).getItem());
                    }
                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence contraint,
                                          FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return myFilter;
    }

}

