package com.sirelon.library.badgenavigationicon.badge;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

import com.sirelon.library.badgenavigationicon.R;

/**
 * @author sirelon
 * @since 30.01.17 on 15:39.
 */
public class BadgeNavigationIcon {

    private BadgeDrawable textDrawable;

    public void initBadgeForNavigation(Toolbar toolbar) {

        Resources resources = toolbar.getResources();

        Drawable navigationIcon = resources.getDrawable(R.drawable.ic_navigation_menu);
        // Without this the navigationIcon would resize and fit all space.
        if (navigationIcon instanceof BitmapDrawable)
            ((BitmapDrawable) navigationIcon).setGravity(Gravity.CENTER);

        int fontSize = resources.getDimensionPixelSize(R.dimen.text_size_badge);

        int roundSize = (int) (fontSize * 1.7);

        textDrawable = new BadgeDrawable(roundSize, Color.WHITE, fontSize, Color.RED);

        LayerDrawable badgeLayerDrawable = new LayerDrawable(new Drawable[]{navigationIcon, textDrawable});

        badgeLayerDrawable.setLayerInset(1, roundSize, 0, 0, (int) (roundSize / 1.5));

        toolbar.setNavigationIcon(badgeLayerDrawable);
    }

    public void updateBadge(int count) {
        if (textDrawable == null)
            throw new IllegalStateException("Should call initBadgeForNavigation before call this method!");

        if (count <= 0)
            textDrawable.updateText(null);
        else
            textDrawable.updateText("" + count);
    }
}
