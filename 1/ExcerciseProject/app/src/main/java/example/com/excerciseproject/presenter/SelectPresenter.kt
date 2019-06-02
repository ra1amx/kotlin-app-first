package example.com.excerciseproject.presenter

import example.com.excerciseproject.Work
import example.com.excerciseproject.view.WorkType
import example.com.excerciseproject.view.select.SelectView
import example.com.excerciseproject.view.select.Selectable

/**
 * @since 2019
 * @author Anton Vlasov - whalemare
 */
class SelectPresenter : BasePresenter<SelectView>() {

    lateinit var workType: WorkType
    var works = listOf<Selectable<Work>>()

    fun onAttachWorkType(workType: WorkType) {
        this.workType = workType
        this.works = workType.getWorks().map { work ->
            Selectable(work, false)
        }
        view?.showWorks(works)
    }

    fun onCheckedElement(element: Selectable<Work>) {
        works = works.map { work ->
            if (work.item.name == element.item.name) {
                element
            } else {
                work
            }
        }
        view?.showWorks(works)
    }

}