package example.com.excerciseproject.presenter

import com.google.android.gms.maps.model.LatLng
import example.com.excerciseproject.model.storage.Storage
import example.com.excerciseproject.view.order.ProcessOrderView

/**
 * @since 2019
 * @author Anton Vlasov - whalemare
 */
class ProcessOrderPresenter : BasePresenter<ProcessOrderView>() {

    private var coordinate: LatLng? = null

    fun onClickMap(coordinate: LatLng) {
        this.coordinate = coordinate;
        view?.showCoordinate(coordinate)
    }

    fun onClickSend(name: String, phone: String, sendLocation: Boolean) {
        val works = Storage.workStorage.map.mapValues { entry -> entry.value.filter { it.checked } }
        val coordinatesPayload = if (sendLocation && coordinate != null) "Координаты: ${coordinate?.latitude};${coordinate?.longitude}" else ""
        view?.openEmailApp(
            "Запрос на работу",
            """
                Я хочу чтобы Вы сделали: ${works.values.joinToString(", ")}
                Имя: $name
                Телефон: $phone
                $coordinatesPayload
            """.trimIndent(),
            "eremin.i@bitrix24.ru"
        )
    }
}