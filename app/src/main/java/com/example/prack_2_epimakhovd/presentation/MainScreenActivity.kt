/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.example.prack_2_epimakhovd.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.prack_2_epimakhovd.R
import android.widget.Button
import android.content.Intent
import android.media.Image
import android.widget.ImageView

class MainScreenActivity : ComponentActivity() {
    private lateinit var imgBtnDis: ImageView
    private lateinit var imgBtnFav: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        imgBtnDis = findViewById(R.id.img_enter)
        imgBtnFav = findViewById(R.id.img_enter_fav)

        imgBtnDis.setOnClickListener{
            startActivity(Intent(this, CharListScreenActivity::class.java))
            finish()
        }

        imgBtnFav.setOnClickListener{
            startActivity(Intent(this, MoviesScreenActivity::class.java))
            finish()
        }
    }
}
