package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import in.nfly.dell.nflydemo.R;

public class HomeSwipeAdapter extends PagerAdapter{
    private int[] image_resources;
    private String[] title_resources,subtitle_resources;
    private Context context;
    private LayoutInflater layoutInflater;

    public HomeSwipeAdapter(Context context, int[] image_resources,String[] title_resources,String[] subtitle_resources )
    {
        this.context=context;
        this.image_resources=image_resources;
        this.title_resources=title_resources;
        this.subtitle_resources=subtitle_resources;

    }
    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view==o);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        layoutInflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view= layoutInflater.inflate(R.layout.layout_home_swipe,container,false);
        ImageView imageView=item_view.findViewById(R.id.homeSwipeImage);
        TextView textView=item_view.findViewById(R.id.homeSwipeTitle);
        TextView subtitleTextView=item_view.findViewById(R.id.homeSwipeSubtitle);
        //imageView.setImageResource(image_resources[position]);
        Picasso.with(context).load(image_resources[position]).into(imageView);

        textView.setText(title_resources[position]);
        subtitleTextView.setText(subtitle_resources[position]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((RelativeLayout)object);
    }

}
