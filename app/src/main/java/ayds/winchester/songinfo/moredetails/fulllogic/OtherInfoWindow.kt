package ayds.winchester.songinfo.moredetails.fulllogic

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import ayds.winchester.songinfo.R
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import com.google.gson.Gson
import com.google.gson.JsonObject
import android.content.Intent
import android.net.Uri
import com.squareup.picasso.Picasso
import android.text.Html
import android.widget.Button
import android.widget.ImageView
import ayds.winchester.songinfo.utils.UtilsInjector
import ayds.winchester.songinfo.utils.UtilsInjector.imageLoader
import ayds.winchester.songinfo.utils.view.ImageLoader
import retrofit2.Response
import java.io.IOException
import java.lang.StringBuilder

class OtherInfoWindow : AppCompatActivity() {

    private val imageLoader: ImageLoader = UtilsInjector.imageLoader
    private val imageUrl =
        "https://upload.wikimedia.org/wikipedia/commons/8/8c/Wikipedia-logo-v2-es.png"

    private lateinit var textPane2: TextView
    private lateinit var viewFullArticleButton: Button
    private lateinit var logoImageView: ImageView
    private lateinit var dataBase: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_info)

        open(intent.getStringExtra("artistName"))
        initProperties()
        initLogoImage()
    }

    private fun initProperties() {
        textPane2 = findViewById(R.id.textPane2)
        viewFullArticleButton = findViewById(R.id.openUrlButton)
        logoImageView = findViewById(R.id.imageView)
    }

    private fun initLogoImage() {
        runOnUiThread {
            imageLoader.loadImageIntoView(imageUrl, logoImageView)
        }
    }

    fun getArtistInfo(artistName: String?) {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://en.wikipedia.org/w/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val wikipediaAPI = retrofit.create(WikipediaAPI::class.java)

        Thread {
            var text = DataBase.getInfo(dataBase, artistName)
            if (text != null) {
                text = "[*]$text"
            }
            else {
                val callResponse: Response<String>
                try {
                    callResponse = wikipediaAPI.getArtistInfo(artistName).execute()
                    val gson = Gson()
                    val jobj = gson.fromJson(callResponse.body(), JsonObject::class.java)
                    val query = jobj["query"].asJsonObject
                    val snippet = query["search"].asJsonArray[0].asJsonObject["snippet"]
                    val pageid = query["search"].asJsonArray[0].asJsonObject["pageid"]
                    if (snippet == null) {
                        text = "No Results"
                    }
                    else {
                        text = snippet.asString.replace("\\n", "\n")
                        text = textToHtml(text, artistName)
                        DataBase.saveArtist(dataBase, artistName, text)
                    }
                    val urlString = "https://en.wikipedia.org/?curid=$pageid"
                    viewFullArticleButton.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(urlString)
                        startActivity(intent)
                    }
                } catch (e1: IOException) {
                    e1.printStackTrace()
                }
            }
            val finalText = text
            runOnUiThread {
                textPane2!!.text = Html.fromHtml(finalText)
            }
        }.start()
    }

    private fun open(artist: String?) {
        dataBase = DataBase(this)
        DataBase.saveArtist(dataBase, "test", "sarasa")
        getArtistInfo(artist)
    }

    companion object {
        const val ARTIST_NAME_EXTRA = "artistName"
        fun textToHtml(text: String, term: String?): String {
            val builder = StringBuilder()
            builder.append("<html><div width=400>")
            builder.append("<font face=\"arial\">")
            val textWithBold = text
                .replace("'", " ")
                .replace("\n", "<br>")
                .replace("(?i)" + term!!.toRegex(), "<b>" + term.toUpperCase() + "</b>")
            builder.append(textWithBold)
            builder.append("</font></div></html>")
            return builder.toString()
        }
    }
}