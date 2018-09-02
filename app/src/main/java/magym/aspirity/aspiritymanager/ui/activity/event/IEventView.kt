package magym.aspirity.aspiritymanager.ui.activity.event

import magym.aspirity.aspiritymanager.model.request.RequestEvent

interface IEventView {

    fun initEvent(ev: RequestEvent)

}