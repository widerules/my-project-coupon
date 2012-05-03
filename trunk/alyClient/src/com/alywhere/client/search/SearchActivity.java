package com.alywhere.client.search;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import com.alywhere.client.R;
import com.alywhere.client.data.DBAdapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class SearchActivity extends Activity {
	GridView mHotGridView, mHistoryGridView;
	EditText mySearchEditText;
	Button mySearchButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		prepareView();
	}

	private void prepareView() {
		mHotGridView = (GridView) findViewById(R.id.hot_search_grid);
		mHotGridView.setAdapter(new GridAdapter(this));
		mHistoryGridView = (GridView) findViewById(R.id.history_search_grid);
		mHistoryGridView.setAdapter(new GridAdapter(this));
		mySearchEditText = (EditText) findViewById(R.id.searchEditText);
		mySearchButton = (Button) findViewById(R.id.searchButton);
		mySearchButton.setOnClickListener(new OnSearchButtonClickListener());
	}

	private class OnSearchButtonClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			String searchContent = mySearchEditText.getText().toString();
			String hostname = "172.17.66.134";
			int port = 4478;
			Socket socket = null;
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				byte[] b = new byte[100];
				String reply = null;
				socket = new Socket(hostname, port);
				bis = new BufferedInputStream(socket.getInputStream());
				bos = new BufferedOutputStream(socket.getOutputStream());
				bos.write("COMMAND".getBytes());
				bis.read(b);
				reply = new String(b);
				if (reply.equals("READYFORCOMMAND")) {
					bos.write("QUERYBYNAME".getBytes());
					bis.read(b);
					reply = new String(b);
					if (reply.equals("GETCOMMAND")) {
						bos.write("CONTENT".getBytes());
						bis.read(b);
						reply = new String(b);
						if (reply.equals("READYFORCONTENT")) {
							bos.write(searchContent.getBytes());
							bis.read(b);
							reply = new String(b);
							if (reply.equals("GETCONTENT")) {

							} else {
								Toast.makeText(SearchActivity.this, "下载信息失败",
										Toast.LENGTH_LONG);
							}
						} else {
							Toast.makeText(SearchActivity.this, "下载信息失败",
									Toast.LENGTH_LONG);
						}
					} else {
						Toast.makeText(SearchActivity.this, "下载信息失败",
								Toast.LENGTH_LONG);
					}
				} else {
					Toast.makeText(SearchActivity.this, "下载信息失败",
							Toast.LENGTH_LONG);
				}

			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
