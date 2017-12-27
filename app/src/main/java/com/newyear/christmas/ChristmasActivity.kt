package com.newyear.christmas

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.widget.TextView

import com.newyear.R
import com.newyear.christmas.fragment.Fragment_3
import com.newyear.christmas.fragment.Fragment_1
import com.newyear.christmas.fragment.Fragment_2
import kotlinx.android.synthetic.main.activity_chrisymas.*

import java.util.ArrayList


class ChristmasActivity : AppCompatActivity() {

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var option = 0

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener { v -> onBackPressed() }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chrisymas)

        option = intent.getIntExtra("option", 0)



        setToolbar()
        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        when(option){
            1->{
                set_pic_toolbar.setImageResource(R.drawable.ic_christmas_tree)
                set_title_toolbar.text=resources.getString(R.string.option_txt_1)
            }
            2->{
                set_pic_toolbar.setImageResource(R.drawable.ic_calendar)
                set_title_toolbar.text=resources.getString(R.string.option_txt_2)
            }
            3->{
                set_pic_toolbar.setImageResource(R.drawable.ic_kite)
                set_title_toolbar.text=resources.getString(R.string.option_txt_3)
            }
            4->{
                set_pic_toolbar.setImageResource(R.mipmap.ic_saraswati)
                set_title_toolbar.text=resources.getString(R.string.option_txt_4)
            }
            5->{
                set_pic_toolbar.setImageResource(R.drawable.ic_india)
                set_title_toolbar.text=resources.getString(R.string.option_txt_5)
            }
            6->{
                set_pic_toolbar.setImageResource(R.drawable.ic_camera)
                set_title_toolbar.text=resources.getString(R.string.option_txt_6)
            }
            7->{
                set_pic_toolbar.setImageResource(R.drawable.ic_lotus)
                set_title_toolbar.text=resources.getString(R.string.option_txt_7)
            }
            8->{
                set_pic_toolbar.setImageResource(R.drawable.ic_holi)
                set_title_toolbar.text=resources.getString(R.string.option_txt_8)
            }
            9->{
                set_pic_toolbar.setImageResource(R.drawable.ic_shiva)
                set_title_toolbar.text=resources.getString(R.string.option_txt_9)
            }
            10->{
                set_pic_toolbar.setImageResource(R.drawable.ic_video_camera)
                set_title_toolbar.text=resources.getString(R.string.txt_option_10)
            }
        }



        viewPager = findViewById(R.id.viewpager)
        setupViewPager(viewPager)

        tabLayout = findViewById(R.id.tabs)
        tabLayout!!.setupWithViewPager(viewPager)
        setupTabIcons()
    }

    private fun setupTabIcons() {

        val tabOne = LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as TextView
        //tabOne.setText("ONE");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_santa_claus, 0, 0)
        tabLayout!!.getTabAt(0)!!.customView = tabOne

        val tabThree = LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as TextView
        // tabThree.setText("THREE");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_whatsapp, 0, 0)
        tabLayout!!.getTabAt(1)!!.customView = tabThree

        val tabFour = LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as TextView
        // tabThree.setText("THREE");
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_sms, 0, 0)
        tabLayout!!.getTabAt(2)!!.customView = tabFour


    }


    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter = ChristmasActivity.ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(Fragment_1(option), "ONE")
        adapter.addFrag(Fragment_2(option), "Two")
        adapter.addFrag(Fragment_3(option), "Three")
        viewPager!!.adapter = adapter
    }

       class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }
}
