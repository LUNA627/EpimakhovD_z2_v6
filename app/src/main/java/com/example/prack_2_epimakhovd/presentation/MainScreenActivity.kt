/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.example.prack_2_epimakhovd.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.prack_2_epimakhovd.R
import android.content.Intent
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MainScreenActivity : ComponentActivity() {
    private lateinit var imgBtnRecord: ImageView
    private lateinit var imgBtnGoal: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        imgBtnRecord = findViewById(R.id.icon_add)
        imgBtnGoal = findViewById(R.id.icon_goals)


        val urlAdd = "https://cdn-icons-png.flaticon.com/512/3980/3980807.png"
        val urlGoal = "https://cdn-icons-png.flaticon.com/512/7318/7318482.png"

        Picasso.get()
            .load(urlAdd)
            .fit()
            .centerCrop()
            .into(imgBtnRecord)

        Picasso.get()
            .load(urlGoal)
            .fit()
            .centerCrop()
            .into(imgBtnGoal)

        imgBtnRecord.setOnClickListener{
            startActivity(Intent(this, AddScreenActivity::class.java))
            finish()
        }

        imgBtnGoal.setOnClickListener{
            startActivity(Intent(this, CharListScreenActivity::class.java))
            finish()
        }
    }
}
