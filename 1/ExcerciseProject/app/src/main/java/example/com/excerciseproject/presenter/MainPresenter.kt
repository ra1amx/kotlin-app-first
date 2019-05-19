package example.com.excerciseproject.presenter

import example.com.excerciseproject.Work
import example.com.excerciseproject.model.Coordinate
import example.com.excerciseproject.model.MainInteractor
import example.com.excerciseproject.view.MainView
import example.com.excerciseproject.view.WorkType

/**
 * @since 2019
 * @author Anton Vlasov - whalemare
 */
class MainPresenter(private val interactor: MainInteractor) {

    private var view: MainView? = null

    fun attachView(view: MainView) {
        this.view = view
    }

    fun onCheckedType(workType: WorkType) {
        view?.showWork(workType.getWorks())
    }

    fun onClickSend(name: String,
                    phone: String,
                    checkedExercises: MutableList<Work>,
                    location: Coordinate? = null) {
//        interactor.sendWebHook(name, phone, sendCoordinates)
        view?.openEmailApp(
            "Запрос на работу",
            """
                Я хочу чтобы вы сделали то-то
                Имя: $name
                Телефон: $phone
                Работа: $checkedExercises
                Координаты: ${location?.latitude};${location?.longitude}
            """.trimIndent(),
            "eremin.i@bitrix24.ru"
        )
    }
}