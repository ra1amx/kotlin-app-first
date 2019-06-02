package example.com.excerciseproject.presenter

import com.google.android.gms.maps.model.LatLng
import example.com.excerciseproject.view.order.ProcessOrderView

/**
 * @since 2019
 * @author Anton Vlasov - whalemare
 */
class ProcessOrderPresenter: BasePresenter<ProcessOrderView>() {

    private var coordinate: LatLng? = null

    fun onClickMap(coordinate: LatLng) {
        this.coordinate = coordinate;
        view?.showCoordinate(coordinate)
    }
}