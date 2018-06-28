package com.example.vuphu.project_hoa_hoc;

import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatHoaHocActivity extends AppCompatActivity {

    private RecyclerView list;

    private Query mQuery;
    private TopicAdapter mAdapter;
    private DatabaseReference mDatabase;
    private List<String> mCHH_Ids = new ArrayList<>();
    private List<ChatHoaHoc> CHHList = new ArrayList<>();
    private ChildEventListener mChildEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_hoa_hoc);

        mDatabase = FirebaseDatabase.getInstance().getReference();// Root Database
        mQuery = mDatabase.child("ChatHoaHoc");
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                ChatHoaHoc chatHoaHoc = dataSnapshot.getValue(ChatHoaHoc.class);
                mCHH_Ids.add(dataSnapshot.getKey());
                CHHList.add(chatHoaHoc);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                ChatHoaHoc newTopic = dataSnapshot.getValue(ChatHoaHoc.class);
                String topicKey = dataSnapshot.getKey();
                int topicIndex = mCHH_Ids.indexOf(topicKey);
                if (topicIndex > -1) {
                    CHHList.set(topicIndex, newTopic);
                    mAdapter.notifyItemChanged(topicIndex);
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String topicKey = dataSnapshot.getKey();
                int topicIndex = mCHH_Ids.indexOf(topicKey);
                if (topicIndex > -1) {
                    mCHH_Ids.remove(topicIndex);
                    CHHList.remove(topicIndex);
                    mAdapter.notifyItemRemoved(topicIndex);
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Không kết nối được nguồn dữ liệu", Toast.LENGTH_SHORT).show();
            }
        };
        mQuery.addChildEventListener(mChildEventListener);
        mAdapter = new TopicAdapter(this, CHHList);
        mAdapter.notifyDataSetChanged();
        list = (RecyclerView) findViewById(R.id.listCHH);
        list.setHasFixedSize(true);
        LinearLayoutManager mManager = new LinearLayoutManager(this);
        list.setLayoutManager(mManager);
        list.setAdapter(mAdapter);
//        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                //Do some magic
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //Do some magic
//                return false;
//            }
//        });
//        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//                //Do some magic
//            }
//
//            @Override
//            public void onSearchViewClosed() {
//                //Do some magic
//            }
//        });
    }

//    @Override
//    public void onBackPressed() {
//        if (searchView.isSearchOpen()) {
//            searchView.closeSearch();
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
//            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            if (matches != null && matches.size() > 0) {
//                String searchWrd = matches.get(0);
//                if (!TextUtils.isEmpty(searchWrd)) {
//                    searchView.setQuery(searchWrd, false);
//                }
//            }
//
//            return;
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//
//        MenuItem item = menu.findItem(R.id.action_search);
//        searchView.setMenuItem(item);
//        return true;
//    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (mAdapter != null) {
//            cleanupListener();
//        }
//    }

    private static class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, txtKiHieu;
        CircleImageView img;

        TopicViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.txt_tenCHH);
            txtKiHieu = (TextView) itemView.findViewById(R.id.txtkiHieu);
            img = (CircleImageView) itemView.findViewById(R.id.img_CHH);
        }
    }

    private class TopicAdapter extends RecyclerView.Adapter<TopicViewHolder> {
        private Context mContext;
        private List<ChatHoaHoc> chatHoaHocs;

        TopicAdapter(Context context, List<ChatHoaHoc> query) {
            mContext = context;
            chatHoaHocs = query;
        }

        @Override
        public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.item, parent, false);
            return new TopicViewHolder(view);
        }
        @Override
        public void onBindViewHolder(TopicViewHolder holder, final int position) {
            final ChatHoaHoc chatHoaHoc = chatHoaHocs.get(position);
            if (chatHoaHoc.getPhanLoai().equals("Phi Kim")){
                holder.img.setBackgroundResource(R.color.phiKim);
            }
            if (chatHoaHoc.getPhanLoai().equals("Kim Loại")){
                holder.img.setBackgroundResource(R.color.kimLoai);
            }
            if (chatHoaHoc.getPhanLoai().equals("Khí Hiếm")){
                holder.img.setBackgroundResource(R.color.khiHiem);
            }
            holder.txtKiHieu.setText(chatHoaHoc.getKiHieu());
            holder.tv_name.setText(chatHoaHoc.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ChatHoaHocActivity.this,DetailCHHActivity.class);
                    intent.putExtra("key",mCHH_Ids.get(position));
                    intent.putExtra("myObject", new Gson().toJson(chatHoaHoc));
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return CHHList.size();
        }


    }

//    public void cleanupListener() {
//        if (mChildEventListener != null) {
//            mQuery.removeEventListener(mChildEventListener);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tools, menu);
        menu.findItem(R.id.CHH).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.BTH)
        {
            startActivity( new Intent(ChatHoaHocActivity.this, BangTuanHoanActivity.class));
        }
        if (item.getItemId() == R.id.BaiCaHoaTri){
            startActivity( new Intent(ChatHoaHocActivity.this, BaiCaHoaTriActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }


}
