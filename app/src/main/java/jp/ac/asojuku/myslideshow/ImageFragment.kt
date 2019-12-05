package jp.ac.asojuku.myslideshow


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 */

//グローバルな変数として追加
val IMG_RES_ID = "IMG_RES_ID";

class ImageFragment : Fragment() {

    //画像のリソースIDを保持するプロパティフィールド
    private var imgResId:Int? = null;

    //フラグメント継承クラスが生成されたタイミングのイベントで実行されるコールバックメソッド
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //argumentsプロパティの画像リソースIdを変数に代入
        this.arguments?.let {
            this.imgResId = it.getInt(IMG_RES_ID);

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    //companion objectを使ってクラスにStaticメソッドを実装
    companion object{
        //Static メソッドを定義
        fun newInstance(imageResourseId: Int): ImageFragment {
            //Bundleクラスの変数を作る（情報をまとめて保管できるクラス）
            val bundle = Bundle()

            //バンドル変数に画像のリソースIDを保存する
            //キーワードと値（画像リソースID）
            bundle.putInt("IMG_RES_ID",imageResourseId);

            //自分の方のインスタンスを作る
            val imageFragment = ImageFragment();

            //生成したインスタンスのargumentプロパティにbundle情報を設定しておく
            imageFragment.arguments = bundle;

            //生成したインスタンスをリターンする
            return imageFragment;

        }
    }

}
