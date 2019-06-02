package example.com.excerciseproject.presenter

import example.com.excerciseproject.view.WorkType
import example.com.excerciseproject.view.select.SelectView
import example.com.excerciseproject.view.select.Selectable

/**
 * @since 2019
 * @author Anton Vlasov - whalemare
 */
class SelectPresenter : BasePresenter<SelectView>() {

    lateinit var workType: WorkType

    fun onAttachWorkType(workType: WorkType) {
        this.workType = workType

        view?.showWorks(workType.getWorks().map { work ->
            Selectable(work, false)
        })
    }


}