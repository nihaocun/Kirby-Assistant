package cn.endureblaze.ka.resources.game;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.endureblaze.ka.R;
import cn.endureblaze.ka.base.BaseActivity;
import cn.endureblaze.ka.bean.ConsoleOld;
import cn.endureblaze.ka.helper.LayoutAnimationHelper;
import cn.endureblaze.ka.utils.PlayAnimUtil;
import cn.endureblaze.ka.utils.ThemeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class GameListActivity extends BaseActivity
{
	private List<ConsoleOld> gamelist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        ThemeUtil.setClassTheme(this);
		setContentView(R.layout.activity_gamelist);
		//配置toolbar
		Toolbar toolbar= findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.game_title);
		//配置列表
		RecyclerView r= findViewById(R.id.game_list);
		GridLayoutManager layoutManager=new GridLayoutManager(this, 1);
		r.setLayoutManager(layoutManager);
        GameAdapter adapter = new GameAdapter(gamelist, this);
		r.setAdapter(adapter);
		PlayAnimUtil.playLayoutAnimationWithRecyclerView(r,LayoutAnimationHelper.getAnimationSetFromBottom(),false);
		//获取数据
		Intent intent=getIntent();
		String game=intent.getStringExtra("consose_name");
		toolbar.setSubtitle(game);
		//判断数据然后处理列表
		if (Objects.requireNonNull(game).endsWith("gba"))
		{	
			ConsoleOld[] game_data = {
				new ConsoleOld("星之卡比 梦之泉DX", "https://gitee.com/nihaocun/ka_image/raw/master/game/mengzhiquandx.jpg","gba_mzqdx"),
				new ConsoleOld("星之卡比 镜之大迷宫", "https://gitee.com/nihaocun/ka_image/raw/master/game/jingmi.jpg","gba_jm"),
			}; 
			int index = 0;
			while (index < game_data.length)
			{       	
				gamelist.add(game_data[index++]);
			}
		}
		if (game.equals("gb"))
		{
			ConsoleOld[]game_data={
				new ConsoleOld("星之卡比 1", "https://gitee.com/nihaocun/ka_image/raw/master/game/xing1.jpg","gb_x1"),
				new ConsoleOld("星之卡比 2", "https://gitee.com/nihaocun/ka_image/raw/master/game/xing2.jpg","gb_x2"),
				new ConsoleOld("星之卡比 卡比宝石星", "https://gitee.com/nihaocun/ka_image/raw/master/game/baoshixing.jpg","gb_bsx"),
				new ConsoleOld("星之卡比 卡比打砖块", "https://gitee.com/nihaocun/ka_image/raw/master/game/dazhuankuai.jpg","gb_dzk"),
				new ConsoleOld("星之卡比 卡比弹珠台", "https://gitee.com/nihaocun/ka_image/raw/master/game/danzhutai.jpg","gb_dzt"),
		};
			int index = 0;
			while (index < game_data.length)
			{       	
				gamelist.add(game_data[index++]);
			}
		}
		if(game.endsWith("gbc")){
			ConsoleOld[]game_data={
				new ConsoleOld("星之卡比 滚滚卡比", "https://gitee.com/nihaocun/ka_image/raw/master/game/gungun.jpg","gbc_gg"),
			};
			int index = 0;
			while (index < game_data.length)
			{       	
				gamelist.add(game_data[index++]);
			}
		}
		if (game.equals("sfc"))
		{
			ConsoleOld[] game_data = {
				new ConsoleOld("星之卡比 3", "https://gitee.com/nihaocun/ka_image/raw/master/game/xing3.jpg","sfc_x3"),
				new ConsoleOld("星之卡比 超豪华版", "https://gitee.com/nihaocun/ka_image/raw/master/game/kss.jpg","sfc_kss"),
				new ConsoleOld("星之卡比 卡比梦幻都", "https://gitee.com/nihaocun/ka_image/raw/master/game/menghuandu.jpg","sfc_mhd"),
				new ConsoleOld("星之卡比 玩具箱合集", "https://gitee.com/nihaocun/ka_image/raw/master/game/toybox.jpg","sfc_toybox"),
				new ConsoleOld("[仅美国]星之卡比 卡比魔方气泡", "https://gitee.com/nihaocun/ka_image/raw/master/game/mofangqipao.jpg","sfc_mfqp"),
				new ConsoleOld("[仅日本]星之卡比 卡比宝石星DX", "https://gitee.com/nihaocun/ka_image/raw/master/game/baoshixingdx.jpg","sfc_bsxdx"),
			}; 
			int index = 0;
			while (index < game_data.length)
			{       	
				gamelist.add(game_data[index++]);
			}
		}
		if (game.equals("n64"))
		{

			ConsoleOld[] game_data = {
				new ConsoleOld("星之卡比 64", "https://gitee.com/nihaocun/ka_image/raw/master/game/k64.jpg","n64_k64"),
			}; 
			int index = 0;
			while (index < game_data.length)
			{       	
				gamelist.add(game_data[index++]);
			}
		}
		if (game.equals("ngc"))
		{

			ConsoleOld[] game_data = {
				new ConsoleOld("星之卡比 飞天赛车", "https://gitee.com/nihaocun/ka_image/raw/master/game/feitian.jpg","ngc_ft"),
			}; 
			int index = 0;
			while (index < game_data.length)
			{       	
				gamelist.add(game_data[index++]);
			}
		}
		if (game.equals("wii"))
		{

			ConsoleOld[] game_data = {
				new ConsoleOld("星之卡比 重返梦幻岛", "https://gitee.com/nihaocun/ka_image/raw/master/game/chongfan.jpg","wii_cf"),
				new ConsoleOld("星之卡比 毛线卡比", "https://gitee.com/nihaocun/ka_image/raw/master/game/maoxian.jpg","wii_mx"),
			}; 
			int index = 0;
			while (index < game_data.length)
			{       	
				gamelist.add(game_data[index++]);
			}
		}
		if (game.equals("nds"))
		{

			ConsoleOld[] game_data = {
				new ConsoleOld("星之卡比 触摸卡比", "https://gitee.com/nihaocun/ka_image/raw/master/game/chumo.jpg","nds_cm"),
				new ConsoleOld("星之卡比 超究豪华版", "https://gitee.com/nihaocun/ka_image/raw/master/game/kssu.jpg","nds_kssu"),
				new ConsoleOld("星之卡比 呐喊团", "https://gitee.com/nihaocun/ka_image/raw/master/game/nahantuan.jpg","nds_nht"),
				new ConsoleOld("星之卡比 集合！卡比", "https://gitee.com/nihaocun/ka_image/raw/master/game/jihe.jpg","nds_jh"),
			}; 
			int index = 0;
			while (index < game_data.length)
			{       	
				gamelist.add(game_data[index++]);
			}
		}
		if (game.equals("fc"))
		{

			ConsoleOld[] game_data = {
				new ConsoleOld("星之卡比 梦之泉物语", "https://gitee.com/nihaocun/ka_image/raw/master/game/mengzhiquan.jpg","fc_mzq"),
			}; 
			int index = 0;
			while (index < game_data.length)
			{       	
				gamelist.add(game_data[index++]);
			}
		}
	}
}
