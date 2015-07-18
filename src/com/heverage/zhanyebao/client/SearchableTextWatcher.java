package com.heverage.zhanyebao.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.heverage.zhanyebao.client.model.Client;

public class SearchableTextWatcher implements TextWatcher {
	
	private Context mContext = null;
	private ArrayAdapter<Client> mAdapter = null;

	private EditText mSearchText = null;

    private List<Client> mDataList;  
    
	private List<Client> checkArrayList;
	
	private List<Client> copyedList;

	private String searchStr = null;

	public SearchableTextWatcher(Context mContext, ArrayAdapter<Client> mAdapter,
			 EditText mSearchText, List<Client> mDataList) {
		super();
		this.mContext = mContext;
		this.mAdapter = mAdapter;
		this.mSearchText = mSearchText;
		this.mDataList = mDataList;

		checkArrayList = new ArrayList<Client>();	
		
		copyedList = copyFromSourceList();
	}
	
	public List<Client> copyFromSourceList(){
		List<Client> copyedList = new ArrayList<Client>(mDataList.size());
		copyedList.addAll(mDataList)	;
		return copyedList;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		// 搜索功能只针对ListView中的内容数据，不针对标题数据。
		// 使用没添加的分组字母的数据创建新的ArrayList
		checkArrayList.clear();
		checkArrayList.addAll (copyedList);
		searchStr = mSearchText.getText().toString();

		if (searchStr.length() != 0) {
			checkSearchStr(searchStr);
			
			mAdapter.clear();
			mAdapter.addAll(checkArrayList);	
			
			
//			mAdapter = new ClientAdapter(mContext,
//						android.R.layout.simple_list_item_1, checkArrayList);
//			
//			mListView.setAdapter(mAdapter);
		}

	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		if (searchStr.length() == 0) {
			mAdapter.clear();
			mDataList.clear();
			mDataList.addAll (copyedList);
			mAdapter.addAll(mDataList);	
			
//			mAdapter = new ClientAdapter(mContext,
//					android.R.layout.simple_list_item_1, newDataArrayList);
//			mListView.setAdapter(mAdapter);
		}
		mSearchText.requestFocus();
	}

	/** 
 *  
 */
	public void checkSearchStr(String search) {

		String tempSearch;
		String tempList;
		String newDataChar = null;
		String checkArrayListItem = null;
		// 当输入的搜索字符为字母时
		if (search.matches("[a-zA-Z]+")) {
			for (int i = 0; i < search.length(); i++) {
				for (int j = 0; j < checkArrayList.size(); j++) {
					// checkArrayListItem = checkArrayList.get(j);
					// //如果联系人名称不为字母，则得到联系人名称的所有首字母
					// if (!checkArrayListItem.matches("[a-zA-Z]+"))
					// {
					// newDataChar = mSort
					// .getAllPinYinHeadChar(checkArrayListItem);
					// }
					// else
					// {
					// newDataChar = checkArrayListItem;
					// }
					newDataChar = checkArrayList.get(j).getAllCharHeader();
					// 取出输入的字符串的第i个字母，并转换为大写
					tempSearch = String.valueOf(search.charAt(i)).toUpperCase();
					// 取出得到的联系人名称所有首字母的第i个字母，并转换为大写
					tempList = String.valueOf(newDataChar.charAt(i))
							.toUpperCase();

					if (!checkArrayList.get(j).isRealPerson() || !(tempSearch.equals(tempList)) ) {
						checkArrayList.remove(j);
						newDataChar = null;
						j--;
					}
				}
			}
		}
		// 当输入的搜索字符为汉字时
		else if (search.matches("[\u4e00-\u9fa5]+")) {

			for (int j = 0; j < checkArrayList.size(); j++) {
				if (!checkArrayList.get(j).isRealPerson() || !checkArrayList.get(j).getClient_name().contains(search)) {
					checkArrayList.remove(j);
					j--;
				}
			}
		} else {
			search = String.valueOf(search.charAt(0));
			for (int j = 0; j < checkArrayList.size(); j++) {
				if (!checkArrayList.get(j).isRealPerson() || (!checkArrayList.get(j).getClient_name().contains(search)
						&& !checkArrayList.get(j).getPinyinName()
								.contains(search.toUpperCase()))) {
					checkArrayList.remove(j);
					j--;
				}
			}
		}
	}
}
