package cn.endureblaze.ka.utils;

import android.view.animation.Animation;
import android.view.animation.GridLayoutAnimationController;
import android.view.animation.LayoutAnimationController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class PlayAnimUtil
{
	/**
     * 播放RecyclerView动画
     *
     * @param animation
     * @param isReverse
     */
    public static void playLayoutAnimationWithRecyclerView(RecyclerView mRecyclerView, Animation animation, boolean isReverse)
	{
		if (CheckSimpleModeUtil.isSimpleMode())
		{
			LayoutAnimationController controller = new LayoutAnimationController(animation);
			controller.setDelay(0.1f);
			controller.setOrder(isReverse ? LayoutAnimationController.ORDER_REVERSE : LayoutAnimationController.ORDER_NORMAL);
			mRecyclerView.setLayoutAnimation(controller);
			Objects.requireNonNull(mRecyclerView.getAdapter()).notifyDataSetChanged();
			mRecyclerView.scheduleLayoutAnimation();
		}
    }

	public static void playLayoutAnimation(Animation animation, boolean isReverse)
	{
		if (CheckSimpleModeUtil.isSimpleMode())
		{
			LayoutAnimationController controller = new LayoutAnimationController(animation);
			controller.setDelay(0.1f);
			controller.setOrder(isReverse ? LayoutAnimationController.ORDER_REVERSE : LayoutAnimationController.ORDER_NORMAL);
		}
	}
	/**
     * 播放View动画
     *
     * @param animation
     * @param isReverse
     */
    public static void playLayoutAnimationWithGridLayout(RecyclerView mRecyclerView, Animation animation, boolean isReverse)
	{
		if (CheckSimpleModeUtil.isSimpleMode())
		{
			GridLayoutAnimationController controller = new GridLayoutAnimationController(animation);
			controller.setColumnDelay(0.2f);
			controller.setRowDelay(0.3f);
			controller.setOrder(isReverse ? LayoutAnimationController.ORDER_REVERSE : LayoutAnimationController.ORDER_NORMAL);
			mRecyclerView.setLayoutAnimation(controller);
			Objects.requireNonNull(mRecyclerView.getAdapter()).notifyDataSetChanged();
			mRecyclerView.scheduleLayoutAnimation();
		}
	}
}
