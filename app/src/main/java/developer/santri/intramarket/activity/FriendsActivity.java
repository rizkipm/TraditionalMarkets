package developer.santri.intramarket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.flipviewpager.adapter.BaseFlipAdapter;
import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import developer.santri.intramarket.R;
import developer.santri.intramarket.Utils;
import developer.santri.intramarket.model.Friend;

/**
 * @author Yalantis
 */
public class FriendsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        final ListView friends = (ListView) findViewById(R.id.friends);

        FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();
        friends.setAdapter(new FriendsAdapter(this, Utils.friends, settings));
        friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Friend f = (Friend) friends.getAdapter().getItem(position);

                if(f.getNickname()== "Pasar Melayan"){
                    Intent a =new Intent(getApplicationContext(),MainContext.class);
                    startActivity(a);
                }else if (f.getNickname()== "Pasar Arjosari"){
                    Intent b= new Intent(getApplicationContext(),MainContext2.class);
                    startActivity(b);
                }else if (f.getNickname()== "Pasar Kosambi"){
                    Intent c= new Intent(getApplicationContext(),MainContext3.class);
                    startActivity(c);
                }else if (f.getNickname()== "Pasar Beringharjo"){
                    Intent d= new Intent(getApplicationContext(),MainContext4.class);
                    startActivity(d);
                }else if (f.getNickname()=="Pasar Segar Depok"){
                    Intent e= new Intent(getApplicationContext(),MainContext5.class);
                    startActivity(e);
                }else if (f.getNickname()=="Pasar Klewer Solo"){
                    Intent g= new Intent(getApplicationContext(),MainContext6.class);
                    startActivity(g);
                }else if (f.getNickname()=="Pasar Tanah Abang"){
                    Intent h= new Intent(getApplicationContext(),MainContext7.class);
                    startActivity(h);
                }else if (f.getNickname()=="Pasar Jatinegara"){
                    Intent i =new Intent(getApplicationContext(),MainContext8.class);
                    startActivity(i);
                }


                Toast.makeText(FriendsActivity.this, f.getNickname(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    class FriendsAdapter extends BaseFlipAdapter<Friend> {

        private final int PAGES = 3;
        private int[] IDS_INTEREST = {R.id.interest_1, R.id.interest_2};

        public FriendsAdapter(Context context, List<Friend> items, FlipSettings settings) {
            super(context, items, settings);
        }

        @Override
        public View getPage(int position, View convertView, ViewGroup parent, Friend friend1, Friend friend2) {
            final FriendsHolder holder;

            if (convertView == null) {
                holder = new FriendsHolder();
                convertView = getLayoutInflater().inflate(R.layout.friends_merge_page, parent, false);
                holder.leftAvatar = (ImageView) convertView.findViewById(R.id.first);
                holder.rightAvatar = (ImageView) convertView.findViewById(R.id.second);
                holder.infoPage = getLayoutInflater().inflate(R.layout.friends_info, parent, false);
                holder.nickName = (TextView) holder.infoPage.findViewById(R.id.nickname);

                for (int id : IDS_INTEREST)
                    holder.interests.add((TextView) holder.infoPage.findViewById(id));

                convertView.setTag(holder);
            } else {
                holder = (FriendsHolder) convertView.getTag();
            }

            switch (position) {
                // Merged page with 2 friends
                case 1:
                    holder.leftAvatar.setImageResource(friend1.getAvatar());
                    if (friend2 != null)
                        holder.rightAvatar.setImageResource(friend2.getAvatar());
                    break;
                default:
                    fillHolder(holder, position == 0 ? friend1 : friend2);
                    holder.infoPage.setTag(holder);
                    return holder.infoPage;
            }
            return convertView;
        }

        @Override
        public int getPagesCount() {
            return PAGES;
        }

        private void fillHolder(FriendsHolder holder, Friend friend) {
            if (friend == null)
                return;
            Iterator<TextView> iViews = holder.interests.iterator();
            Iterator<String> iInterests = friend.getInterests().iterator();
            while (iViews.hasNext() && iInterests.hasNext())
                iViews.next().setText(iInterests.next());
            holder.infoPage.setBackgroundColor(getResources().getColor(friend.getBackground()));
            holder.nickName.setText(friend.getNickname());
        }

        class FriendsHolder {
            ImageView leftAvatar;
            ImageView rightAvatar;
            View infoPage;

            List<TextView> interests = new ArrayList<>();
            TextView nickName;
        }
    }
}
