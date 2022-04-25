package ayds.winchester.songinfo.moredetails.fulllogic

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import ayds.winchester.songinfo.R
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import com.google.gson.Gson
import com.google.gson.JsonObject
import android.text.Html
import android.widget.Button
import android.widget.ImageView
import ayds.winchester.songinfo.utils.UtilsInjector
import ayds.winchester.songinfo.utils.view.ImageLoader
import retrofit2.Response
import java.io.IOException
import java.lang.StringBuilder

const val imageUrl =
    "https://upload.wikimedia.org/wikipedia/commons/8/8c/Wikipedia-logo-v2-es.png"
const val wikipediaAPIbaseURL = "https://en.wikipedia.org/w/"

class OtherInfoWindow : AppCompatActivity() {
    private val imageLoader: ImageLoader = UtilsInjector.imageLoader
    private lateinit var artistInfoTextView: TextView
    private lateinit var viewFullArticleButton: Button
    private lateinit var logoImageView: ImageView
    private lateinit var dataBase: DataBase
    private lateinit var artistName: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_info)
        loadArtistName()
        initDatabase()
        initProperties()
        initLogoImage()
        getArtistInfo()
    }

    private fun initDatabase() {
        dataBase = DataBase(this)
    }

    private fun loadArtistName() {
        artistName = intent.getStringExtra(ARTIST_NAME_EXTRA)!!
    }

    private fun initProperties() {
        artistInfoTextView = findViewById(R.id.textPane2)
        viewFullArticleButton = findViewById(R.id.openUrlButton)
        logoImageView = findViewById(R.id.imageView)
    }

    private fun initLogoImage() {
        runOnUiThread {
            imageLoader.loadImageIntoView(imageUrl, logoImageView)
        }
    }

    private fun textToHtml(text: String, term: String?): String {
        val builder = StringBuilder()
        builder.append("<html><div width=400>")
        builder.append("<font face=\"arial\">")
        val textWithBold = text
            .replace("'", " ")
            .replace("\n", "<br>")
            .replace("(?i)" + term!!.toRegex(), "<b>" + term.uppercase() + "</b>")
        builder.append(textWithBold)
        builder.append("</font></div></html>")
        return builder.toString()
    }

    private fun getWikipediaAPI(): WikipediaAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(wikipediaAPIbaseURL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        return retrofit.create(WikipediaAPI::class.java)
    }

    private fun getArtistInfoFromLocal(): String?{
        return DataBase.getInfo(dataBase, artistName)
    }

    private fun jsonToArtistInfo(serviceData:String?): String {
        val gson = Gson()
        val jobj = gson.fromJson(serviceData, JsonObject::class.java)
        val query = jobj["query"].asJsonObject
        val snippet = query["search"].asJsonArray[0].asJsonObject["snippet"]
        val pageid = query["search"].asJsonArray[0].asJsonObject["pageid"]
        return snippet.asString.replace("\\n", "\n")
    }

    private fun getArtistInfoFromExternal(): String{
        val wikipediaAPI = getWikipediaAPI()
        var text = "No Results"
        try {
            val callResponse: Response<String> = wikipediaAPI.getArtistInfo(artistName).execute()
            val snippet = jsonToArtistInfo(callResponse.body())
            if (snippet != "") {
                text = textToHtml(text, artistName)
            }
            return text
        } catch (e1: IOException) {
            e1.printStackTrace()
            return text
        }
    }

    private fun getArtistInfo() {

        Thread {
            val text:String
            val artistInfoLocal = getArtistInfoFromLocal()
            if (artistInfoLocal != null) {
                text = "[*]$artistInfoLocal"
            }
            else {
                val artistInfoExternal = getArtistInfoFromExternal()
//                val urlString = "https://en.wikipedia.org/?curid=$pageid"
//                viewFullArticleButton.setOnClickListener {
//                    val intent = Intent(Intent.ACTION_VIEW)
//                    intent.data = Uri.parse(urlString)
//                    startActivity(intent)
//                }
                text = artistInfoExternal
                DataBase.saveArtist(dataBase, artistName, artistInfoExternal)
            }
            runOnUiThread {
                artistInfoTextView.text = Html.fromHtml(text)
            }
        }.start()
    }

    companion object {
        const val ARTIST_NAME_EXTRA = "artistName"
    }
}