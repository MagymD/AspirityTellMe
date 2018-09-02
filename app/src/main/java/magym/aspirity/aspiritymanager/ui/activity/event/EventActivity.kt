package magym.aspirity.aspiritymanager.ui.activity.event

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.github.marlonlom.utilities.timeago.TimeAgo
import kotlinx.android.synthetic.main.activity_event.*
import magym.aspirity.aspiritymanager.R
import magym.aspirity.aspiritymanager.consts.ModelEn
import magym.aspirity.aspiritymanager.model.request.RequestEvent
import magym.aspirity.aspiritymanager.utils.fromHtml
import magym.aspirity.aspiritymanager.utils.init

class EventActivity : AppCompatActivity(), IEventView {

    private val presenter = EventPresenter(this)

    private lateinit var event: RequestEvent
    private var dateEvent: String = "0"
    private val idEvent by lazy { intent.getLongExtra(ModelEn.ID_EVENT.const, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        presenter.initEvent(idEvent)
        presenter.updateRead(idEvent)
    }

    override fun onResume() {
        super.onResume()
        event_date.text = TimeAgo.using(dateEvent.toLong())
    }

    override fun initEvent(ev: RequestEvent) {
        event = ev
        supportActionBar?.init(event.titleFilter)
        supportActionBar?.subtitle = event.titleTemplate
        event_description.text = event.descriptionEvent.fromHtml()
        dateEvent = event.dateEvent
        event_date.text = TimeAgo.using(dateEvent.toLong())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}