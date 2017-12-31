package inter;

import android.view.View;

/**
 * Created by mohamed on 12/10/17.
 */

public interface ItemClickListener {
    // this interface used to action ClickListener
    void onClick(View view,int position,boolean isLongClick);
}
