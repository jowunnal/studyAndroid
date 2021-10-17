package org.techtown.recycler2;

import android.view.View;

public interface OnPersonItemClickedListener {
    public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position);
}
