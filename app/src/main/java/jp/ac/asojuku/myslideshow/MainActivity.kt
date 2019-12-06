package jp.ac.asojuku.myslideshow

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //MadiaPlayer用の変数
    private lateinit var player: MediaPlayer;


    //pagerを操作するための必要なアダプタークラスを内部クラスとして用意
    class MyAdapter(fm:FragmentManager):FragmentPagerAdapter(fm){

        //アダプターに画像の情報を保持しておく
        //画像のリソースIDのリストを作る
        private val resources = listOf(
            R.drawable.slide00,R.drawable.slide01,
            R.drawable.slide02,R.drawable.slide03,
            R.drawable.slide04,R.drawable.slide05,
            R.drawable.slide06,R.drawable.slide07,
            R.drawable.slide08,R.drawable.slide09
            );

        override fun getItem(position: Int): Fragment {
            //ページ番号をリソースIDとして引き渡しImageFragmentのインスタンスを生成して返す
            return ImageFragment.newInstance(resources[position]);
        }

        override fun getCount(): Int {
            //resourcesリストの要素数を返す
            return this.resources.size
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //画面クラスに配置されたPagerViewのadapterプロパティに
        //内部クラスで定義したmyAdapterのインスタンスを設定する
        this.pager.adapter = MyAdapter(this.supportFragmentManager)

        //画面のインスタンスが生成されるとタイマーのスレッドも起動させる
        //handlerのインスタンスを取得
        val handler = Handler();

        //timer処理のスレッドを起動
        //5000ms間隔でタイマークラスを実行
        kotlin.concurrent.timer(period = 5000){
            //5000ms間隔で実行したい処理
            //handlerでpagerを進める処理
            handler.post(
                object :Runnable{
                    //実行するrunメソッド
                    override fun run() {
                        //メインメソッドで実行する処理
                        //pagerのcurrentItem(ページ番号)を1進める。10を超えたら0に戻す
                        pager.currentItem = (pager.currentItem + 1) % 10;
                    }
                }
            )
        }
        //MediaPlayerの変数にインスタンスを代入
        this.player = MediaPlayer.create(this,R.raw.getdown);
        //BGMとしてループ再生をONにする
        this.player.isLooping = true;
    }
    //画面が表示・再表示された時のイベントコールバックメソッド
    override fun onResume() {
        super.onResume()
        //MediaPlayerのサウンドファイル再生をスタート
        this.player.start();
    }

    override fun onPause() {
        super.onPause()
        //playerのサウンドファイル再生を一時停止
        this.player.pause();
    }
}
