package com.william.kotlinsimpletest.activity.fragment_dialog

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.william.kotlinsimpletest.R
import kotlinx.android.synthetic.main.activity_fragment_dialog.*

class FragmentDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_dialog)
        FragmentManager.enableDebugLogging(true)

        //组装ViewPager
        val fragmentPagerAdapter = MyFragmentPagerAdapter(supportFragmentManager)
        fragment_dialog_vp.adapter = fragmentPagerAdapter

        //监听Button点击
        var colorFragment: ColorFragment? = null

        fragment_dialog_btn_add_fragment.setOnClickListener {
            val newFragment = ColorFragment.newInstance(getRandomColor())
            fragmentPagerAdapter.addFragmentAndNotify(newFragment)
            colorFragment = newFragment
        }
        fragment_dialog_btn_add_duplicate.setOnClickListener {
            colorFragment?.let {
                fragmentPagerAdapter.addFragmentAndNotify(it)
                supportFragmentManager.beginTransaction().add(it,"tag").commit()
            }
        }

    }

    @ColorInt
    private fun getRandomColor(): Int {
        val colorList = arrayListOf<Int>(
            Color.BLACK,
            Color.BLUE,
            Color.CYAN,
            Color.DKGRAY,
            Color.LTGRAY,
            Color.YELLOW,
            Color.RED,
            Color.MAGENTA,
            Color.GREEN
        )
        val randomNum = java.util.Random().nextInt(100)
        val index = randomNum.rem(colorList.size)
        return colorList[index]
    }

    class MyFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val fragmentList: MutableList<Fragment> = ArrayList()

        fun addFragmentAndNotify(fragment: Fragment) {
            fragmentList.add(fragment)
            notifyDataSetChanged()
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }
    }

    class ColorFragment : Fragment() {
        companion object {
            const val BG_COLOR = "bg_color"
            fun newInstance(bgColor: Int): ColorFragment {
                val colorFragment = ColorFragment()
                val argument = Bundle()
                argument.putInt(BG_COLOR, bgColor)
                colorFragment.arguments = argument
                return colorFragment
            }
        }

        private var bgColor: Int = 0

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            bgColor = arguments?.getInt(BG_COLOR, Color.GRAY) ?: Color.GRAY
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_color_fragment, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            val ll = view.findViewById<LinearLayout>(R.id.color_fragment_ll)
            ll.setBackgroundColor(bgColor)
        }
    }
}
