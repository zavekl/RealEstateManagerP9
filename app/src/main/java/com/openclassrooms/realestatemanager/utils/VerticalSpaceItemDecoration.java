package com.openclassrooms.realestatemanager.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by NIATEL Brice on 05/02/2021.
 */
public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;

    public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        outRect.bottom = verticalSpaceHeight;

        if (parent.getAdapter() != null) {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = verticalSpaceHeight;
            }
        }
    }
}