package jp.ac.asojuku.myslideshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
            return ImageFragment.newInstance(position);
        }

        override fun getCount(): Int {
            //resourcesリストの要素数を返す
            return resources.size;
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //画面クラスに配置されたPagerViewのadapterプロパティに
        //内部クラスで定義したmyAdapterのインスタンスを設定する
        this.pager.adapter = MyAdapter(this.supportFragmentManager)
    }
}
