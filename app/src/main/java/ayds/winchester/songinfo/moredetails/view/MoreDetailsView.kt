package ayds.winchester.songinfo.moredetails.view

import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ayds.observer.Observable
import ayds.observer.Subject
import ayds.winchester.songinfo.R
import ayds.winchester.songinfo.moredetails.model.MoreDetailsModel
import ayds.winchester.songinfo.moredetails.model.MoreDetailsModelInjector
import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.winchester.songinfo.utils.UtilsInjector
import ayds.winchester.songinfo.utils.navigation.NavigationUtils
import ayds.winchester.songinfo.utils.view.ImageLoader

interface MoreDetailsView {
    val uiEventObservable: Observable<MoreDetailsUiEvent>
    val uiState: MoreDetailsUiState

    fun openFullArticle()
}

class MoreDetailsViewActivity : AppCompatActivity(), MoreDetailsView {
    private val onActionSubject = Subject<MoreDetailsUiEvent>()
    override val uiEventObservable: Observable<MoreDetailsUiEvent> = onActionSubject
    override var uiState: MoreDetailsUiState = MoreDetailsUiState()
    private val artistInfoHelper: ArtistInfoHelper = MoreDetailsViewInjector.artistInfoHelper
    private val imageLoader: ImageLoader = UtilsInjector.imageLoader
    private val navigationUtils: NavigationUtils = UtilsInjector.navigationUtils
    private lateinit var moreDetailsModel: MoreDetailsModel
    private lateinit var artistInfoTextView: TextView
    private lateinit var viewFullArticleButton: Button
    private lateinit var logoImageView: ImageView
    private lateinit var sourceInfoTextView: TextView
    private lateinit var nextCardButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_info)
        initModule()
        initProperties()
        initListeners()
        initObservers()
        initArtistName()
        notifySearchArtistInfoAction()
    }

    override fun openFullArticle() {
        navigationUtils.openExternalUrl(this, uiState.pageUrl)
    }

    private fun initModule() {
        MoreDetailsViewInjector.init(this)
        moreDetailsModel = MoreDetailsModelInjector.getMoreDetailsModel()
    }

    private fun initProperties() {
        artistInfoTextView = findViewById(R.id.textPane2)
        viewFullArticleButton = findViewById(R.id.openUrlButton)
        logoImageView = findViewById(R.id.imageView)
        sourceInfoTextView = findViewById(R.id.sourceInfoTextView)
        nextCardButton = findViewById(R.id.nextCardButton)
    }

    private fun initListeners() {
        viewFullArticleButton.setOnClickListener { notifyOpenFullArticleAction() }
        nextCardButton.setOnClickListener{ notifyNavigateToNextCardAction() }
    }

    private fun notifyOpenFullArticleAction() {
        onActionSubject.notify(MoreDetailsUiEvent.ViewFullArticle)
    }

    private fun notifyNavigateToNextCardAction() {
        onActionSubject.notify(MoreDetailsUiEvent.NavigateToNextCard)
    }

    private fun initObservers() {
        moreDetailsModel.cardObservable
            .subscribe { value -> updateArtistInfo(value) }

    }

    private fun updateArtistInfo(artist: Card) {
        updateUiState(artist)
        updateArtistInfo()
        updateMoreDetailsState()
        updateSourceInfo()
        updateSourceLogo()
    }

    private fun updateUiState(artist: Card) {
        when (artist) {
            EmptyCard -> updateNoResultsUiState(artist)
            else -> updateArtistUiState(artist)
        }
    }

    private fun updateArtistUiState(artist: Card) {
        uiState = uiState.copy(
            pageUrl = artist.infoURL,
            info = artistInfoHelper.artistInfoTextToHtml(artist, uiState.artistName),
            sourceInfo = artist.source,
            IMAGE_URL = artist.sourceLogoURL,
            actionsEnabled = true
        )
    }

    private fun updateNoResultsUiState(artist: Card) {
        uiState = uiState.copy(
            pageUrl = artist.infoURL,
            info = artistInfoHelper.artistInfoTextToHtml(artist, uiState.artistName),
            sourceInfo = artist.source,
            IMAGE_URL = artist.sourceLogoURL,
            actionsEnabled = false
        )
    }

    private fun updateArtistInfo() {
        runOnUiThread {
            artistInfoTextView.text = Html.fromHtml(uiState.info)
        }
    }

    private fun updateMoreDetailsState() {
        enableActions(uiState.actionsEnabled)
    }

    private fun enableActions(enable: Boolean) {
        runOnUiThread {
            viewFullArticleButton.isEnabled = enable
        }
    }

    private fun initLogoImage() {
        runOnUiThread {
                imageLoader.loadImageIntoView(uiState.IMAGE_URL, logoImageView)
        }
    }

    private fun initArtistName() {
        val artistName = intent.getStringExtra(ARTIST_NAME_EXTRA) ?: ""
        uiState = uiState.copy(artistName = artistName)
    }

    private fun notifySearchArtistInfoAction() {
        onActionSubject.notify(MoreDetailsUiEvent.Search)
    }

    private fun updateSourceInfo() {
        runOnUiThread {
            sourceInfoTextView.text = Html.fromHtml(uiState.sourceInfo)
        }
    }

    private fun updateSourceLogo() {
        initLogoImage()
    }

    companion object {
        const val ARTIST_NAME_EXTRA = "artistName"
    }
}