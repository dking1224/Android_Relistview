package com.example.listviewdemo;

import java.util.ArrayList;
import java.util.List;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity implements OnRefreshListener,OnScrollListener{
	
	private SwipeRefreshLayout swipeRefreshLayout;
	private ListView listView;
	private List<String> list = new ArrayList<String>();
	private ArrayAdapter<String> adapter ;
	private View footer;
	private boolean isfinsh = true;//是否加载完成
	private ImageView imageView;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				list.add("bbbbbbbbb1");
				adapter.notifyDataSetChanged();
				if(listView.getFooterViewsCount()>0){
					listView.removeFooterView(footer);
				}
				isfinsh = true;
				break;

			default:
				break;
			}
		};
	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView)findViewById(R.id.img);
		imageView.setOnTouchListener(new ImageViewTouch());
		swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.rf);
		swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,  
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
		swipeRefreshLayout.setOnRefreshListener(this);
		listView = (ListView)findViewById(R.id.ls);
		footer = LayoutInflater.from(this).inflate(R.layout.footer, null, false);
		for(int i = 0 ;i<20;i++){
			list.add("aaaaaaaaa"+i);
		}
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		listView.addFooterView(footer);
		listView.setAdapter(adapter);
		listView.removeFooterView(footer);
		listView.setOnScrollListener(this);
	}

	@Override
	public void onRefresh() {
		if(isfinsh){
			isfinsh = false;
			new ReTask().execute();
		}else{
			swipeRefreshLayout.setRefreshing(false);
		}
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		int countIndex = view.getLastVisiblePosition();
		Log.d("countIndex", ""+countIndex);
		Log.d("totalItemCount", ""+totalItemCount);
		if((countIndex+1) == totalItemCount){
			if(totalItemCount > 0){
				if(isfinsh){
					isfinsh = false;
					listView.addFooterView(footer);
					new GetTask().execute();
				}else{
					
				}
			}
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
	}
	
	private class ReTask extends AsyncTask<Void , Integer, Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if(result){
				list.clear();
				for(int i = 0 ;i<20;i++){
					list.add("aaaaaaaaa"+i);
				}
				adapter.notifyDataSetChanged();
				swipeRefreshLayout.setRefreshing(false);
				isfinsh = true;
				}
		}
	}
	
	private class GetTask extends AsyncTask<Void , Integer, Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if(result)
				handler.sendEmptyMessage(1);
		}
	}

}
