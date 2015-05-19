package com.asia.commonutil.util;

import android.util.SparseArray;
import android.view.View;

public class ViewHolder {
    
    //私有化构造器,防止实例化
    private ViewHolder(){}
    
	@SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
	    //此处会报警告:未检查的转换
		SparseArray<View> viewArrays = (SparseArray<View>) view.getTag();
		if (viewArrays == null) {
		    viewArrays = new SparseArray<View>();
			view.setTag(viewArrays);
		}
		View childView = viewArrays.get(id);
		if (childView == null) {
			childView = view.findViewById(id);
			viewArrays.put(id, childView);
		}
		//此处会报警告:未检查的转换
		return (T) childView;
	}
}
