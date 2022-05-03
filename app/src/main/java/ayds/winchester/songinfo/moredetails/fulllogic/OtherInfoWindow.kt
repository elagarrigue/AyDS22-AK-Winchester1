package ayds.winchester.songinfo.moredetails.fulllogic

import android.content.Intent
import android.net.Uri
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
import ayds.winchester.songinfo.moredetails.fulllogic.model.repository.local.wikipedia.OtherInfoDataBase
import ayds.winchester.songinfo.moredetails.fulllogic.model.repository.local.wikipedia.database.WikipediaAPI
import ayds.winchester.songinfo.utils.UtilsInjector
import ayds.winchester.songinfo.utils.view.ImageLoader
import retrofit2.Response
import java.lang.StringBuilder

private const val IMAGE_URL =
    "https://upload.wikimedia.org/wikipedia/commons/8/8c/Wikipedia-logo-v2-es.png"
private const val WIKIPEDIA_API_BASE_URL = "https://en.wikipedia.org/w/"
private const val NO_RESULTS_TEXT = "No Results"
private const val LOCALLY_SAVED_MARK = "[*]"
private const val FULL_ARTICLE_URL = "https://en.wikipedia.org/?curid="
private const val SNIPPET = "snippet"
private const val SEARCH = "search"
private const val PAGEID = "pageid"
private const val QUERY = "query"

class OtherInfoWindow : AppCompatActivity() {
    private val imageLoader: ImageLoader = UtilsInjector.imageLoader
    private lateinit var artistInfoTextView: TextView
    private lateinit var viewFullArticleButton: Button
    private lateinit var logoImageView: ImageView
    private lateinit var dataBase: OtherInfoDataBase
    private lateinit var artistName: String
    private lateinit var wikipediaAPI: WikipediaAPI
    private var pageid: String? = null
    private var artistInfoText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_info)
        loadArtistName()
        initDatabase()
        initProperties()
        initLogoImage()
        initWikipediaApi()
        searchAction()
    }

    private fun initDatabase() {
        dataBase = OtherInfoDataBase(this)
    }

    private fun initWikipediaApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl(WIKIPEDIA_API_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        wikipediaAPI = retrofit.create(WikipediaAPI::class.java)
    }

    private fun loadArtistName() {
        artistName = intent.getStringExtra(ARTIST_NAME_EXTRA)?: ""
    }

    private fun initProperties() {
        artistInfoTextView = findViewById(R.id.textPane2)
        viewFullArticleButton = findViewById(R.id.openUrlButton)
        logoImageView = findViewById(R.id.imageView)
    }

    private fun initLogoImage() {
        runOnUiThread {
            imageLoader.loadImageIntoView(IMAGE_URL, logoImageView)
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

    private fun getArtistInfoFromLocal(): String? {
        return dataBase.getInfo(artistName)
    }

    private fun jsonToArtistInfo(serviceData: String?): String {
        val gson = Gson()
        val jobj = gson.fromJson(serviceData, JsonObject::class.java)
        val query = jobj[QUERY].asJsonObject
        val search = query[SEARCH].asJsonArray[0]
        val snippet = search.asJsonObject[SNIPPET]
        val pageid = search.asJsonObject[PAGEID]
        this.pageid = pageid.asString
        return snippet.asString.replace("\\n", "\n")
    }

    private fun getArtistInfoFromExternal(): String {
        val resultText = NO_RESULTS_TEXT
        return try {
            val callResponse: Response<String> = wikipediaAPI.getArtistInfo(artistName).execute()
            val snippet = jsonToArtistInfo(callResponse.body())
            getExternalInfoSnippet(snippet)
        } catch (e1: Exception) {
            e1.printStackTrace()
            resultText
        }
    }

    private fun getExternalInfoSnippet(snippet : String) = if (snippet != "") textToHtml(snippet, artistName) else NO_RESULTS_TEXT

    private fun getArtistInfo():String {
        val artistInfoLocal = getArtistInfoFromLocal()
        return if (artistInfoLocal != null) {
            "$LOCALLY_SAVED_MARK$artistInfoLocal"
        } else {
            val artistInfoExternal = getArtistInfoFromExternal()
            dataBase.saveArtist(artistName, artistInfoExternal)
            artistInfoExternal
        }
    }

    private fun searchAction() {
        Thread {
            artistInfoText = getArtistInfo()
            updateUI()
        }.start()
    }

    private fun updateUI() {
        updateFullArticleButton()
        updateArtistInfo()
    }

    private fun updateFullArticleButton() {
        if (pageid != null) {
            viewFullArticleButton.setOnClickListener { openFullArticle() }
        }
    }

    private fun openFullArticle() {
        val urlString = "$FULL_ARTICLE_URL$pageid"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(urlString)
        startActivity(intent)
    }

    private fun updateArtistInfo() {
        runOnUiThread {
            artistInfoTextView.text = Html.fromHtml(artistInfoText)
        }
    }

    companion object {
        const val ARTIST_NAME_EXTRA = "artistName"
    }
}