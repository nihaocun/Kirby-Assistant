package cn.endureblaze.ka.chat;

import android.os.*;
import androidx.core.app.*;
import androidx.appcompat.widget.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.bmob.v3.*;
import cn.bmob.v3.exception.*;
import cn.bmob.v3.listener.*;
import cn.endureblaze.ka.bean.*;
import cn.endureblaze.ka.bmob.*;
import cn.endureblaze.ka.helper.*;
import java.util.*;

import cn.endureblaze.ka.R;
import cn.endureblaze.ka.base.*;
import cn.endureblaze.ka.main.*;
import cn.endureblaze.ka.utils.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scwang.smartrefresh.layout.api.*;
import com.scwang.smartrefresh.layout.listener.*;
import com.scwang.smartrefresh.header.MaterialHeader;


public class MainChatFragment extends BaseFragment
{
	private List<Chat> chatlist = new ArrayList<>();
	private ChatAdapter adapter;
	private RecyclerView re;
	private RefreshLayout refresh;
	private String name;
	private FloatingActionButton edit_mess_button;
	private View view;
	private MainActivity m;
	private int messItem;
	private EditText edit_编辑;
	private TextView mess_load_fail;  
	//private BottomDialog mess_dia;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
        view = inflater.inflate(R.layout.main_chat, container, false);
		m = (MainActivity)getActivity();
		initMess(view);
		refresh.autoRefresh();
		return view;
	}
	
	private void initMess(View view)
	{
		mess_load_fail = (TextView)view.findViewById(R.id.mess_loadfail_text);
		//设置显示留言的列表
		re = (RecyclerView)view.findViewById(R.id.chat_list);
		GridLayoutManager layoutManager=new GridLayoutManager(getActivity(), 1);
		re.setLayoutManager(layoutManager);
		adapter = new ChatAdapter(chatlist,getActivity(),getActivity().getSupportFragmentManager());	
		//refresh数据
		refresh = (RefreshLayout)view.findViewById(R.id.refresh);
		MaterialHeader mMaterialHeader=(MaterialHeader) refresh.getRefreshHeader();
		mMaterialHeader.setColorSchemeColors(ThemeUtil.getColorPrimary(getActivity()));
		refresh.setOnRefreshListener(new OnRefreshListener(){
				@Override
				public void onRefresh(RefreshLayout re)
				{
					refresh.setEnableLoadMore(false);
					edit_mess_button.setVisibility(View.GONE);
					getChat();
				}
			});
		refresh.setOnLoadMoreListener(new OnLoadMoreListener(){
				@Override
				public void onLoadMore(RefreshLayout re)
				{
					getMoreChat();
				}
			});
		//使用BmobUser类获取部分用户数据
		name = UserUtil.getCurrentUser().getUsername();
		edit_mess_button = (FloatingActionButton)view.findViewById(R.id.fab_chat_edit);
		edit_mess_button.setOnClickListener(new View.OnClickListener(){			
				@Override
				public void onClick(View v)			
				{
					EditChatDialog.newInstance("0",null,ChatMode.CHAT_SEND_MODE)
					.setTheme(R.style.BottomDialogStyle)
					.setMargin(0)
					.setShowBottom(true)
					.show(getActivity().getSupportFragmentManager());
				}
			});
	}
	public void  getChat()
	{
		chatlist.clear();//清空列表
		//使用BmobQuery获取留言数据
		BmobQuery<BmobChat> query=new BmobQuery<BmobChat>();
		query.order("-createdAt");//时间降序排列
		query.setLimit(20);
		query.findObjects(new FindListener<BmobChat>() {	
				@Override
				public void done(List<BmobChat> list, BmobException e)
				{
					if (e == null)
					{
						refresh.setEnableLoadMore(true);
						mess_load_fail.setVisibility(View.GONE);
						Message message = messHandler.obtainMessage();
						message.what = 0;
						//以消息为载体
						message.obj = list;//这里的list就是查询出list
						//向handler发送消息
						messHandler.sendMessage(message);
						messItem=20;
						edit_mess_button.setVisibility(View.VISIBLE);
						ScaleAnimation mess_fab_anim = (ScaleAnimation) AnimationUtils.loadAnimation(getActivity(), R.transition.mess_fab);
						edit_mess_button.startAnimation(mess_fab_anim);
				}
					else
					{
						mess_load_fail.setVisibility(View.VISIBLE);
						mess_load_fail.setText(getActivity().getResources().getString(R.string.load_fail)+e.getMessage());
						refresh.finishRefresh();
					}
				}
			});
	}
	public void getMoreChat(){
		//使用BmobQuery获取留言数据
		BmobQuery<BmobChat> query=new BmobQuery<BmobChat>();
		query.order("-createdAt");//时间降序排列
		query.setSkip(messItem);
		query.setLimit(20);
		query.findObjects(new FindListener<BmobChat>() {
				@Override
				public void done(List<BmobChat> list, BmobException e)
				{
					if (e == null)
					{
						Message message = moreMessHandler.obtainMessage();
						message.what = 0;
						//以消息为载体
						message.obj = list;//这里的list就是查询出list
						//向handler发送消息
						moreMessHandler.sendMessage(message);
						messItem=messItem+20;
					}
					else
					{
						Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
						refresh.finishLoadMore();
					}
				}
			});
	}
	private Handler messHandler=new Handler(){
		
		private String chat;
		private boolean show_all;
		private String userHead;
		@Override
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
				case 0:
					List<BmobChat> list= (List<BmobChat>)msg.obj;
					for (BmobChat m : list)
					{
						//从获取的数据中提取需要的数据
						String id=m.getObjectId();
						String user=m.getNickname();
						String chat_full=m.getChat();
						if(chat_full.length()>40){
							 chat=chat_full.substring(0,40)+"...";
							 show_all=true;
						}
						else
						{
							 chat=chat_full;
							 show_all=false;
						}
						String time_=m.getCreatedAt();
						String time = time_.substring(0, 16);
						Chat mess=new Chat(id,user,userHead,chat,time,chat_full,show_all);
						//将查询到的数据依次添加到列表
						chatlist.add(mess);
						//设置适配器
						re.setAdapter(adapter);
						LayoutAnimationController controller = LayoutAnimationHelper.makeLayoutAnimationController();
						ViewGroup viewGroup = (ViewGroup)view.findViewById(R.id.chat_list);
						viewGroup.setLayoutAnimation(controller);
						viewGroup.scheduleLayoutAnimation();
						PlayAnimUtil.playLayoutAnimationWithRecyclerView(re,LayoutAnimationHelper.getAnimationSetFromBottom(),false);
					}			
					//refresh回调
					refresh.finishRefresh();
					break;
			}
		}
	};
	private Handler moreMessHandler=new Handler(){

		private String chat;

		private boolean show_all;

		@Override
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
				case 0:
					List<BmobChat> list= (List<BmobChat>)msg.obj;
					for (BmobChat m : list)
					{
						//从获取的数据中提取需要的数据
						String id=m.getObjectId();
						String user=m.getNickname();
						String userHead=null;
						String chat_full=m.getChat();
						if(chat_full.length()>40){
							chat=chat_full.substring(0,40)+"...";
							show_all=true;
						}
						else
						{
							chat=chat_full;
							show_all=false;
						}
						String time_=m.getCreatedAt();
						String time = time_.substring(0, 16);
						Chat mess=new Chat(id,user,userHead,chat,time,chat_full,show_all);
						//将查询到的数据依次添加到列表
						chatlist.add(mess);
						re.getAdapter().notifyItemChanged(messItem);
					}			
					//refresh回调
					refresh.finishLoadMore();
					break;
			}
		}
	};
}
